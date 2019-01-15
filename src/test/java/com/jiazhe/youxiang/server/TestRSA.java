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


    private final static String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8GqWTP4ZfvbCRAgJoX+IvzL49" +
            "obmoPv3Hk5weRarH0v8ya9W4egmroCEmZCHv5dPLvoVniGwsO/N+o7Nq7IKV3UJP" +
            "DdZJxZTx023DYcdg1d3h39f7m9Vfp+VMWklz5dRCnRZ0dTs/TeVjXAYETSuH35EJ" +
            "KpZU9n0PWQY8XJeoIQIDAQAB" +
            "SK1pVeSMJDu3sDYAHwIDAQAB";
    private final static String PRIVATE_KEY="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALwapZM/hl+9sJEC" +
            "Amhf4i/Mvj2huag+/ceTnB5FqsfS/zJr1bh6CaugISZkIe/l08u+hWeIbCw7836j" +
            "s2rsgpXdQk8N1knFlPHTbcNhx2DV3eHf1/ub1V+n5UxaSXPl1EKdFnR1Oz9N5WNc" +
            "BgRNK4ffkQkqllT2fQ9ZBjxcl6ghAgMBAAECgYBB8VmAYHG8GKTQgRLVAmAp/63l" +
            "r0+FsueG0rGVTzkKqmZ6h6E59dZ0C0Pz0ICxODHiqFzeKc46R54aFEtuvZEdXJ1z" +
            "nM8DE85QAaVCS0Rpc3HmxF8HFz7cyxDJIsItWtL/IkEQgh5rgh6ivGp2o9OCrvWB" +
            "UzGEwKAokiwHlMP/bQJBAOUyFDAKuMBsj0teSaGH1NKfeesBemUJJhI8EQY4/MDB" +
            "g20HF6KLzsq1EDf5R5qZn8cq42OP+I/i1EXSIYFdgnMCQQDSGlKTJ8trljrubI+j" +
            "5MtFP5ACxLR3exLGoyJw5dJV3M5k/vxWb4H6W1B3Tfy7A6qVTgsHDTmwrdnAmN6k" +
            "8gIbAkEAltdKqgOkbCAGftfjJcg7rRMALkaewvgucjnOCoZe8o1pbcwJJhVVV9uq" +
            "fOw2z+zjVzVGyjPqpufmRL1zB6z/OQJBAM/bAr5arECvy3V9KU33V7IWhB55BCEA" +
            "PT8OvU/7ze7sF7SLHpFUl75bAGxHVzSdF/HF19sOFnjYRYjos7tzQOkCQQCAED5l" +
            "xIGn16XjAbdbWbCIpTG0r9+nF8s7IbtfY2mI+iWmnz7eSNJUHCmE9Hntg82T1hHM" +
            "+4MBVvvdDZGBLZ1z";

    @Test
    public void testRSA2() {
        try {
            //=================客户端=================
            //hello, i am infi, good night!加密

            String message = "104320173921060,32217035,815587014324,621786**********4817,3,20190115,114656";

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