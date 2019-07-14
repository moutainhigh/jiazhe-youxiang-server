/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server;

import com.jiazhe.youxiang.base.util.RSAUtil;
import org.assertj.core.util.Lists;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019/1/3
 */
public class TestRSA {


//    private final static String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8GqWTP4ZfvbCRAgJoX+IvzL49" +
//            "obmoPv3Hk5weRarH0v8ya9W4egmroCEmZCHv5dPLvoVniGwsO/N+o7Nq7IKV3UJP" +
//            "DdZJxZTx023DYcdg1d3h39f7m9Vfp+VMWklz5dRCnRZ0dTs/TeVjXAYETSuH35EJ" +
//            "KpZU9n0PWQY8XJeoIQIDAQAB" +
//            "SK1pVeSMJDu3sDYAHwIDAQAB";
//    private final static String PRIVATE_KEY="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALwapZM/hl+9sJEC" +
//            "Amhf4i/Mvj2huag+/ceTnB5FqsfS/zJr1bh6CaugISZkIe/l08u+hWeIbCw7836j" +
//            "s2rsgpXdQk8N1knFlPHTbcNhx2DV3eHf1/ub1V+n5UxaSXPl1EKdFnR1Oz9N5WNc" +
//            "BgRNK4ffkQkqllT2fQ9ZBjxcl6ghAgMBAAECgYBB8VmAYHG8GKTQgRLVAmAp/63l" +
//            "r0+FsueG0rGVTzkKqmZ6h6E59dZ0C0Pz0ICxODHiqFzeKc46R54aFEtuvZEdXJ1z" +
//            "nM8DE85QAaVCS0Rpc3HmxF8HFz7cyxDJIsItWtL/IkEQgh5rgh6ivGp2o9OCrvWB" +
//            "UzGEwKAokiwHlMP/bQJBAOUyFDAKuMBsj0teSaGH1NKfeesBemUJJhI8EQY4/MDB" +
//            "g20HF6KLzsq1EDf5R5qZn8cq42OP+I/i1EXSIYFdgnMCQQDSGlKTJ8trljrubI+j" +
//            "5MtFP5ACxLR3exLGoyJw5dJV3M5k/vxWb4H6W1B3Tfy7A6qVTgsHDTmwrdnAmN6k" +
//            "8gIbAkEAltdKqgOkbCAGftfjJcg7rRMALkaewvgucjnOCoZe8o1pbcwJJhVVV9uq" +
//            "fOw2z+zjVzVGyjPqpufmRL1zB6z/OQJBAM/bAr5arECvy3V9KU33V7IWhB55BCEA" +
//            "PT8OvU/7ze7sF7SLHpFUl75bAGxHVzSdF/HF19sOFnjYRYjos7tzQOkCQQCAED5l" +
//            "xIGn16XjAbdbWbCIpTG0r9+nF8s7IbtfY2mI+iWmnz7eSNJUHCmE9Hntg82T1hHM" +
//            "+4MBVvvdDZGBLZ1z";

//
//    private final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCp7ueeye5Gd8ncKWGP+73WIho" +
//            "OXW3gquAFNzDWgK5/ViOQKJToPJShvfKomd6UCQvbd8lmGyzJw6D6RSTAz0HRyqI" +
//            "0X7vskKpIJObS6MwBcKaR5NrEKaFaoAngurvb8ROTXX3uNhul5wfHwkePw2XrxYV" +
//            "IR+yBXAsgPZr0d76uQIDAQAB\n";
//    private final static String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMKnu557J7kZ3ydw" +
//            "pYY/7vdYiGg5dbeCq4AU3MNaArn9WI5AolOg8lKG98qiZ3pQJC9t3yWYbLMnDoPp" +
//            "FJMDPQdHKojRfu+yQqkgk5tLozAFwppHk2sQpoVqgCeC6u9vxE5Ndfe42G6XnB8f" +
//            "CR4/DZevFhUhH7IFcCyA9mvR3vq5AgMBAAECgYAdK3+dLz1zqqHbSjEz3g7UeCrw" +
//            "23N9jZJfvkCa8bko4ANORfdNavgFT/6AGjhBhxL1HJTtEFtxuW+eauZPZZPWaW9q" +
//            "p7OAMYj1iPyqZDlFOHoS8P9/yvDtnN61vwueqcy/4KEU6uL8WXNbnQWF/LR9qVek" +
//            "8/ATFP9KjNJLftgSgQJBAOYAwNNOa9OUp4mzu2tk6fzLraYaaBruGsj2107ndF++" +
//            "QdwevIBe96iI3b2SE3fSwvXAbJCwtVe9OpDASuBwR2kCQQDYqCz/dnRb+lPOBrQw" +
//            "9Ej2CFNvfdbeqKG9tLljZKN0covR/yoEeWFW4Ui4u34knSuvu++h8xeNIdFuK1Qv" +
//            "t37RAkAcW1tqsfB5VYqSX6ZrxzVSBYqTQA41w8VpoYVKJR8j7sEP8norGYpPLRCp" +
//            "nqjnzQRcaCz6ac7x0lK2Jf8VTMKBAkBeutHJ9RIVcVOZPqckzHrmUGei1QPRISxv" +
//            "FQkGI2ewr7Dg5c+KW0QrR5+TXi9edPY4BVVWm0KN6951bC6IGpQxAkBcVbvh+vVs" +
//            "iLW7SdOox5Q4820RCXU7l+aACtB71L7avsc52KYof2YBtIFXmrT5a8NXpfFSVb+B" +
//            "69Cqd5NInmDK";

    private final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCp7ueeye5Gd8ncKWGP+73WIho" +
            "OXW3gquAFNzDWgK5/ViOQKJToPJShvfKomd6UCQvbd8lmGyzJw6D6RSTAz0HRyqI" +
            "0X7vskKpIJObS6MwBcKaR5NrEKaFaoAngurvb8ROTXX3uNhul5wfHwkePw2XrxYV" +
            "IR+yBXAsgPZr0d76uQIDAQAB";

    private final static String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMKnu557J7kZ3ydw" +
            "pYY/7vdYiGg5dbeCq4AU3MNaArn9WI5AolOg8lKG98qiZ3pQJC9t3yWYbLMnDoPp" +
            "FJMDPQdHKojRfu+yQqkgk5tLozAFwppHk2sQpoVqgCeC6u9vxE5Ndfe42G6XnB8f" +
            "CR4/DZevFhUhH7IFcCyA9mvR3vq5AgMBAAECgYAdK3+dLz1zqqHbSjEz3g7UeCrw" +
            "23N9jZJfvkCa8bko4ANORfdNavgFT/6AGjhBhxL1HJTtEFtxuW+eauZPZZPWaW9q" +
            "p7OAMYj1iPyqZDlFOHoS8P9/yvDtnN61vwueqcy/4KEU6uL8WXNbnQWF/LR9qVek" +
            "8/ATFP9KjNJLftgSgQJBAOYAwNNOa9OUp4mzu2tk6fzLraYaaBruGsj2107ndF++" +
            "QdwevIBe96iI3b2SE3fSwvXAbJCwtVe9OpDASuBwR2kCQQDYqCz/dnRb+lPOBrQw" +
            "9Ej2CFNvfdbeqKG9tLljZKN0covR/yoEeWFW4Ui4u34knSuvu++h8xeNIdFuK1Qv" +
            "t37RAkAcW1tqsfB5VYqSX6ZrxzVSBYqTQA41w8VpoYVKJR8j7sEP8norGYpPLRCp" +
            "nqjnzQRcaCz6ac7x0lK2Jf8VTMKBAkBeutHJ9RIVcVOZPqckzHrmUGei1QPRISxv" +
            "FQkGI2ewr7Dg5c+KW0QrR5+TXi9edPY4BVVWm0KN6951bC6IGpQxAkBcVbvh+vVs" +
            "iLW7SdOox5Q4820RCXU7l+aACtB71L7avsc52KYof2YBtIFXmrT5a8NXpfFSVb+B" +
            "69Cqd5NInmDK";

    @Test
    public void bacthRSA2() {
        List<String> messages = Lists.newArrayList(
//                "104320157321103,32909275,914073761272,621756*********9056,160,20190520,163346",
//                "104320157321103,32903271,912577583153,621786*********5982,25,20190505,153739",
//                "104320157321103,32903271,912571581752,621787*********3543,75,20190505,150639",
//                "104320157321103,32903271,912788920059,621786*********6108,100,20190507,092320",
//                "104320157321103,32903271,912686970867,621756*********1561,150,20190506,151427",
//                "104320157321103,32903271,912886004302,601382*********3959,25,20190508,093814",
//                "104320157321103,32903271,912887043167,621786*********0373,24,20190508,113953",
//                "104320157321103,32903271,912888955844,621785*********9800,47,20190508,160712",
                "104320157321103,32909275,914995882948,621786*********2444,200,20190529,084453");


        int index = 0;
        for (String message : messages) {
            System.out.println(++index + " : 私钥加密并Base64编码的结果：");
            System.out.println(privateEncrypt(message).trim());
        }
    }


    private String privateEncrypt(String message) {
        try {
            java.security.Security.addProvider(
                    new BouncyCastleProvider()
            );
            PrivateKey privateKey = RSAUtil.string2PrivateKey(PRIVATE_KEY);
            byte[] privateEncrypt = RSAUtil.privateEncrypt(message.getBytes(), privateKey);

            //加密后的内容Base64编码
            return RSAUtil.byte2Base64(privateEncrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testRSA2() {
        try {

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
            String temp = "KdPqsBxKs0Zmq+FSTfNVNlqagrK4oblLIGtzMTeR/V3TntW7IY3qi60CwHOb5XmEsW0uQBua+3lonRIw2+135rUe/nEc0nKmqpbDsZMyMx88r9gLGOVqeufz2ts1Wf4k/gQA5xzUYzuvnFKsqFN4sNRx27f0bpiZ+E4VjlLsRDo=";
            byte[] base642Byte = RSAUtil.base642Byte(temp);
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