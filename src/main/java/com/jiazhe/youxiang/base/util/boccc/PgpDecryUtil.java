package com.jiazhe.youxiang.base.util.boccc;

import cryptix.message.KeyBundleMessage;
import cryptix.message.MessageFactory;
import cryptix.message.stream.DecodedMessageInputStream;
import cryptix.message.stream.DecryptionKeyCallback;
import cryptix.message.stream.DecryptionKeyRequest;
import cryptix.message.stream.DecryptionKeyReturnValue;
import cryptix.message.stream.VerificationKeyCallback;
import cryptix.message.stream.VerificationKeyRequest;
import cryptix.message.stream.VerificationKeyReturnValue;
import cryptix.openpgp.PGPKeyBundle;
import cryptix.pki.KeyID;
import cryptix.pki.KeyIDFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Collection;
import java.util.Iterator;

public class PgpDecryUtil implements DecryptionKeyCallback,
		VerificationKeyCallback {

	private PGPKeyBundle publicAlice = null;
	private PGPKeyBundle secretBob = null;
	private String Passphrase;

	
	public String getPassphrase() 
	{
		return Passphrase;
	}

	
	public void setPassphrase(String passphrase) 
	{
		Passphrase = passphrase;
	}

	
	
	public void DecryUtil(String decryFile, String decryedFile, String secretasc) throws Exception 
	{
		java.security.Security
				.addProvider(new cryptix.jce.provider.CryptixCrypto());
		
		java.security.Security
				.addProvider(new cryptix.openpgp.provider.CryptixOpenPGP());
		
		
		FileInputStream inAsc = null;;
		
		try {
			Collection msgs;
			KeyBundleMessage kbm;
			
			MessageFactory mf = MessageFactory.getInstance("OpenPGP");
			
			inAsc = new FileInputStream(secretasc);
			msgs = mf.generateMessages(inAsc);
			
			kbm = (KeyBundleMessage) msgs.iterator().next();
			secretBob = (PGPKeyBundle) kbm.getKeyBundle();
			
			inAsc.close();
			inAsc = null;
			
		} 
		catch (Exception me)
		{
			System.err.println("Reading keybundle failed.");
			me.printStackTrace();
			throw me;
			
		}
		finally
		{
			try {
				if (inAsc != null)
					inAsc.close();
				inAsc = null;
				
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}

		
		
		FileInputStream inDecry = null;
		FileOutputStream outDecryed = null;
		
		try 
		{
			inDecry = new FileInputStream(decryFile);
			outDecryed = new FileOutputStream(decryedFile);
			
			DecodedMessageInputStream decodedInputStream = 
				          DecodedMessageInputStream.getInstance("OpenPGP");
			decodedInputStream.init(inDecry, this, this);

			System.out.println("Decoding message...");
			
			byte[] buf = new byte[4096];
			int len = decodedInputStream.read(buf);
			
			while (len > 0) 
			{
				outDecryed.write(buf, 0, len);
				len = decodedInputStream.read(buf);
			}

			
			System.out.println("Decoding done.");
			
		} 
		catch (Exception ioe)
		{
			System.err.println("IO error.");
			ioe.printStackTrace();
			throw ioe;
			
		} finally
		{
			try 
			{
				if (inDecry != null)
					inDecry.close();
				inDecry = null;
				
				if (outDecryed != null)
					outDecryed.close();
				outDecryed = null;
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				
			}
		}
	}

	public VerificationKeyReturnValue getVerificationKey(
			VerificationKeyRequest request) {
		try {
			
			if (request.getRetryCount() >= 1)
				return new VerificationKeyReturnValue(
						VerificationKeyReturnValue.IGNORE);

			KeyID[] hintIDs = request.getKeyIDHints();

			KeyIDFactory kidf = KeyIDFactory.getInstance("OpenPGP");
			
			KeyID aliceID = kidf.generateKeyID((PublicKey) publicAlice
					.getPublicKeys().next());

			for (int i = 0; i < hintIDs.length; i++)
				if (hintIDs[i].match(aliceID))
					return new VerificationKeyReturnValue(publicAlice);

			return new VerificationKeyReturnValue(
					VerificationKeyReturnValue.IGNORE);

		} 
		catch (NoSuchAlgorithmException nsae)
		{
			System.err.println("Cannot find OpenPGP implementation."
							+ " This usually means that the Cryptix " +
									"OpenPGP provider is not "
							+ "installed correctly.");
			
			nsae.printStackTrace();	
			throw new RuntimeException();
		} 
		catch (InvalidKeyException ike)
		{
			System.err.println("Invalid key.");
			ike.printStackTrace();
			throw new RuntimeException();
		}
	}

	public DecryptionKeyReturnValue getDecryptionKey(
			DecryptionKeyRequest request) {
		try 
		{
			if (request.getRetryCount() >= 1)
				return new DecryptionKeyReturnValue(
						DecryptionKeyReturnValue.FAIL);
			
			KeyID[] hintIDs = request.getKeyIDHints();
			KeyIDFactory kidf = KeyIDFactory.getInstance("OpenPGP");
			
			KeyID bobID = kidf.generateKeyID((PublicKey) 
		    secretBob.getPublicKeys().next());

			for (int i = 0; i < hintIDs.length; i++)
			{
				if (hintIDs[i].match(bobID))
					return new DecryptionKeyReturnValue(secretBob,
							                            Passphrase.toCharArray());
				
				Iterator it = secretBob.getPublicSubKeys();
				
				while (it.hasNext()) 
				{
					KeyID bobSubID = kidf.generateKeyID((PublicKey) it.next());
					
					if (hintIDs[i].match(bobSubID))
						return new DecryptionKeyReturnValue(secretBob,
								Passphrase.toCharArray());
				}
			}

			return new DecryptionKeyReturnValue(DecryptionKeyReturnValue.FAIL);

		}
		catch (Exception ike) 
		{
			System.err.println("Invalid key.");
			ike.printStackTrace();
			throw new RuntimeException();
		}
	}

}

