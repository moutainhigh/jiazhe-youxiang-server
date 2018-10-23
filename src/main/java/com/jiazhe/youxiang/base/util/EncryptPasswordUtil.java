package com.jiazhe.youxiang.base.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author tu
 * @description：
 * @date 2018/10/20
 */
public class EncryptPasswordUtil {

    //加密算法
    public static final String ALGORITHMNAME = "MD5";
    //加密次数
    public static final Integer HASHITERATIONS = 1024;

    public static String encrypt(String salt , String password) {
        ByteSource saltByte = ByteSource.Util.bytes(salt);
        String saltPassword = new SimpleHash(ALGORITHMNAME, password, saltByte, HASHITERATIONS).toString();
        return saltPassword;
    }
}
