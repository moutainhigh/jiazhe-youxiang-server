/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;


import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密解密帮助类
 *
 * @author niexiao
 * @created 2019/1/3
 */
@Component
public class RSAUtil {

    public static Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);

    @Value("${ras.public_key}")
    public void setPublicKey(String publicKey) {
        QR_PUBLIC_KEY = publicKey;
    }

    @Value("${ras.private_key}")
    public void setPrivateKey(String privateKey) {
        QR_PRIVATE_KEY = privateKey;
    }

    /**
     * 扫描二维码充值密钥
     */
    private static String QR_PUBLIC_KEY;
    private static String QR_PRIVATE_KEY;

    @Value("${bocdc.rsa.public_key}")
    public void setBOCDCPublicKey(String publicKey) {
        BOCDC_PUBLIC_KEY = publicKey;
    }

    @Value("${bocdc.rsa.private_key}")
    public void setBOCDCPrivateKey(String privateKey) {
        BOCDC_PRIVATE_KEY = privateKey;
    }

    /**
     * 中行储蓄卡(BOCDC）密钥
     */
    private static String BOCDC_PUBLIC_KEY;
    private static String BOCDC_PRIVATE_KEY;

    @Value("${boccc.rsa.zh_public_key}")
    public void setZHPublicKey(String publicKey) {
        ZH_PUBLIC_KEY = publicKey;
    }

    @Value("${boccc.rsa.sf_public_key}")
    public void setSFPublicKey(String publicKey) {
        SF_PUBLIC_KEY = publicKey;
    }

    @Value("${boccc.rsa.sf_private_key}")
    public void setSFPrivateKey(String privateKey) {
        SF_PRIVATE_KEY = privateKey;
    }

    /**
     * 中行公钥，三方公钥，三方私钥
     */
    private static String ZH_PUBLIC_KEY;
    private static String SF_PUBLIC_KEY;
    private static String SF_PRIVATE_KEY;

    /**
     * 扫描二维码功能，公钥解密
     *
     * @param decryptStr
     * @return
     * @throws Exception
     */
    public static String qrPublicDecrypt(String decryptStr) throws Exception {
        //将Base64编码后的公钥转换成PublicKey对象
        PublicKey publicKey = RSAUtil.string2PublicKey(QR_PUBLIC_KEY);
        //加密后的内容Base64解码
        byte[] base642Byte = RSAUtil.base642Byte(decryptStr);
        //用公钥解密
        byte[] publicDecrypt = RSAUtil.publicDecrypt(base642Byte, publicKey);
        return new String(publicDecrypt);
    }

    /**
     * 中行储蓄卡，公钥加密
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String bocdcPublicEncrypt(String str) throws Exception {
        return RSAUtil.publicEncrypt(str, BOCDC_PUBLIC_KEY);
    }

    /**
     * 中行储蓄卡，私钥解密
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String bocdcPrivateDecrypt(String str) throws Exception {
        return RSAUtil.privateDecrypt(str, BOCDC_PRIVATE_KEY);
    }

    /**
     * 中行信用卡，利用中行公钥加密字符串
     *
     * @param str 加密字符串，加密为16进制的
     *            由于中行那边不是分步解密，如果这里我们用分步加密，
     *            到中行那边就会由于内容太多而导致解密失败
     *            所以加解密存在4种情况
     *            ①分步加密  可以   分步解密
     *            ②分步加密   不可以   直接解密（分步加密内容比直接加密内容长）
     *            ③直接加密    可以    分步解密
     *            ④直接加密     可以     直接解密
     * @return
     * @throws Exception
     */
    public static String bocccPublicEncrypt(String str) throws Exception {
        PublicKey publicKey = RSAUtil.string2PublicKey(ZH_PUBLIC_KEY);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] enc = cipher.doFinal(str.getBytes("UTF-8"));
        return new String(Hex.encode(enc), "ISO-8859-1").toUpperCase();
//        byte[] publicEncrypt = encrypt(str.getBytes(), publicKey);
//        return new String(Hex.encode(publicEncrypt), "ISO-8859-1").toUpperCase();
    }

    /**
     * 中行信用卡，利用三方私钥解密字符串
     *
     * @param str 入参为16进制的，解密为正常字符串
     * @return
     * @throws Exception
     */
    public static String bocccPrivateDecrypt(String str) {
        try {
            byte[] arr = Hex.decode(str);
            PrivateKey privateKey = RSAUtil.string2PrivateKey(SF_PRIVATE_KEY);
            byte[] privateDecrypt = decrypt(arr, privateKey);
            return new String(privateDecrypt);
        } catch (Exception e) {
            LOGGER.error("中行信用卡实时接口，解密失败，异常信息：" + e.getMessage());
        }
        return null;
    }

    /**
     * 生成秘钥对
     *
     * @return
     * @throws Exception
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    /**
     * 获取公钥(Base64编码)
     *
     * @param keyPair
     * @return
     */
    public static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * 获取私钥(Base64编码)
     */
    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * 将Base64编码后的公钥转换成PublicKey对象
     */
    public static PublicKey string2PublicKey(String pubStr) throws Exception {
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 将Base64编码后的私钥转换成PrivateKey对象
     */
    public static PrivateKey string2PrivateKey(String priStr) throws Exception {
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 公钥加密
     *
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 私钥加密
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] privateEncrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 公钥解密
     *
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] publicDecrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 私钥解密
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 字节数组转Base64编码
     *
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * Base64编码转字节数组
     *
     * @param base64Key
     * @return
     * @throws IOException
     */
    public static byte[] base642Byte(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    /**
     * 公钥解密
     *
     * @param content      待解密内容
     * @param publicKeyStr 公钥字符串
     * @return
     * @throws Exception
     */
    public static String publicDecrypt(String content, String publicKeyStr) throws Exception {
        //将Base64编码后的公钥转换成PublicKey对象
        PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
        //加密后的内容Base64解码
        byte[] base642Byte = RSAUtil.base642Byte(content);
        //用公钥解密
        byte[] publicDecrypt = decrypt(base642Byte, publicKey);
        return new String(publicDecrypt);
    }

    /**
     * 公钥加密
     *
     * @param content      待加密内容
     * @param publicKeyStr 公钥字符串
     * @return
     * @throws Exception
     */
    public static String publicEncrypt(String content, String publicKeyStr) throws Exception {
        //将Base64编码后的公钥转换成PublicKey对象
        PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
        //用公钥加密
        byte[] publicEncrypt = encrypt(content.getBytes(), publicKey);
        //加密后的内容Base64编码
        String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
        return new String(byte2Base64);
    }

    /**
     * 私钥解密
     *
     * @param content       待解密字符串
     * @param privateKeyStr 私钥字符串
     * @return
     * @throws Exception
     */
    public static String privateDecrypt(String content, String privateKeyStr) throws Exception {
        //将Base64编码后的私钥转换成PrivateKey对象
        PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
        //加密后的内容Base64解码
        byte[] base642Byte = RSAUtil.base642Byte(content);
        //用私钥解密
        byte[] privateDecrypt = decrypt(base642Byte, privateKey);
        return new String(privateDecrypt);
    }

    /**
     * 私钥加密
     *
     * @param content       待加密内容
     * @param privateKeyStr 私钥字符串
     * @return
     * @throws Exception
     */
    public static String privateEncrypt(String content, String privateKeyStr) throws Exception {
        //将Base64编码后的私钥转换成PrivateKey对象
        PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
        //用私钥加密
        byte[] publicEncrypt = encrypt(content.getBytes(), privateKey);
        //加密后的内容Base64编码
        String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
        return new String(byte2Base64);

    }

    /**
     * 分步加密
     *
     * @param content 待加密内容
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] content, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] enBytes = null;
        for (int i = 0; i < content.length; i += 64) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(content, i, i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * 分步解密
     *
     * @param content 待解密内容
     * @param key     公、私钥皆可
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] content, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] enBytes = null;
        for (int i = 0; i < content.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(content, i, i + 128));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }
}