/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server;

import com.jiazhe.youxiang.base.util.RSAUtil;
//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019/1/3
 */
public class TestRSA {


    private final static String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDjEJ0AX4z3YPOsv1PPwRh8AGdJ" +
            "+uDMc2kCBVWWrHyNIPX2ABXPvq4Z8jKnNcFtH+DKt/PBELDIET8HvMAWeAL1jgbl" +
            "fz/zN1WDo4SUXiSxs6cc4ksuoCExZk5zGJShSAy/WkgvogpOMFzuzPJaPflWF9ed" +
            "SK1pVeSMJDu3sDYAHwIDAQAB";
    private final static String PRIVATE_KEY="MIICWwIBAAKBgQDjEJ0AX4z3YPOsv1PPwRh8AGdJ+uDMc2kCBVWWrHyNIPX2ABXP" +
            "vq4Z8jKnNcFtH+DKt/PBELDIET8HvMAWeAL1jgblfz/zN1WDo4SUXiSxs6cc4ksu" +
            "oCExZk5zGJShSAy/WkgvogpOMFzuzPJaPflWF9edSK1pVeSMJDu3sDYAHwIDAQAB" +
            "AoGAcT4+3wnIdzPOM5B0Tz/hYy6cVOQfKzg/3bclWXEK6SPQJ3Ehb6uMjvog0w3X" +
            "OwJUDSYzFk2prQ4U3ln/fvU5m6cy3OfNql25to4bi0WviODHZUT801lPWuKzdgHc" +
            "CJZYcrEyPGzxnOZkPYk+nFJgQQS8gS37l6/dDmSOAlNPaNkCQQD4Vl3L+7SxlmES" +
            "x51g8ghGUEqc2y6h4kzn9BXF8CIjIYvgqGrhG+nNEo6ahj9qhSGzhhPRLnYdDL9h" +
            "OYmPiWtbAkEA6hI21X9MegNeP8xIKV+xf/JXBL/dG1pb6a1DqVylqSLdfpUgnCJ0" +
            "dbQI2tEBHs/rEsugzadGB6BmU1z7PwrNjQJAfH8XwMZsHMdt3PNTk6FiwpVIAoDj" +
            "TObddT9plvS+uGv96t+jzTyoJlXSReddbXJWpeBmmOy46/FTFFO7Acl7lwJAXVx+" +
            "epGX02LciN3WOopspffnNYHBk1NrLb1qK3dErD230vFwXFZx1TDGB2Et7ThJLuoB" +
            "bYW2rPwRVFbV78NDUQJAN4odbCSRzhvAKAUwbdMTEpSEUfCHofW9zi0E4GCwJG3m" +
            "iquclOfgW5dwE6oIQXrIgvF3totKHtqXTbQcp18rnw==";

    @Test
    public void testRSA2() {
        try {
            //=================客户端=================
            //hello, i am infi, good night!加密

            String message = "104320173921060,32217035,815587014324,621786**********4817,1,20180609,114656";

            java.security.Security.addProvider(
                    new BouncyCastleProvider()
            );
            PrivateKey privateKey = RSAUtil.string2PrivateKey(PRIVATE_KEY);
            byte[] privateEncrypt = RSAUtil.privateEncrypt(message.getBytes(), privateKey);

            //加密后的内容Base64编码
            String byte2Base64 = RSAUtil.byte2Base64(privateEncrypt);
            System.out.println("私钥加密并Base64编码的结果：" + byte2Base64);
            //##############	网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################


            //===================服务端================
            //将Base64编码后的公钥转换成PublicKey对象
            PublicKey publicKey = RSAUtil.string2PublicKey(PUBLIC_KEY);
            //加密后的内容Base64解码
            byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
            //用公钥解密
            byte[] publicDecrypt = RSAUtil.publicDecrypt(base642Byte, publicKey);
            //解密后的明文
            System.out.println("解密后的明文: " + new String(publicDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRSA() {
        try {
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
            //生成RSA公钥和私钥，并Base64编码
            KeyPair keyPair = RSAUtil.getKeyPair();
            String publicKeyStr = RSAUtil.getPublicKey(keyPair);
            String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
            System.out.println("RSA公钥Base64编码:" + publicKeyStr);
            System.out.println("RSA私钥Base64编码:" + privateKeyStr);

            //=================客户端=================
            //hello, i am infi, good night!加密
            String message = "kldsajfljdfklasjdflk2ifjdsalfjkslajflaskjflksdf";
            //将Base64编码后的公钥转换成PublicKey对象
            PublicKey publicKey = RSAUtil.string2PublicKey(PUBLIC_KEY);
            //用公钥加密
            byte[] publicEncrypt = RSAUtil.publicEncrypt(message.getBytes(), publicKey);
            //加密后的内容Base64编码
            String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
            System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);


            //##############	网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################


            //===================服务端================
            //将Base64编码后的私钥转换成PrivateKey对象
            java.security.Security.addProvider(
                    new BouncyCastleProvider()
            );
            PrivateKey privateKey = RSAUtil.string2PrivateKey(PRIVATE_KEY);
            //加密后的内容Base64解码
            byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
            //用私钥解密
            byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
            //解密后的明文
            System.out.println("解密后的明文: " + new String(privateDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}