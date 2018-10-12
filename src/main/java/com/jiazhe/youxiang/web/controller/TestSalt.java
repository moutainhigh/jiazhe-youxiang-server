package com.jiazhe.youxiang.web.controller;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by TU on 2018/10/12.
 */
public class TestSalt {
        public static void main(String[] args) {
            String hashAlgorithmName = "MD5";//加密方式
            Object crdentials = "123";//密码原值
            ByteSource salt = ByteSource.Util.bytes("0578");//以账号作为盐值
            int hashIterations = 1024;//加密1024次
            Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
            System.out.println(result);
        }
}
