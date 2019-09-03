package com.jiazhe.youxiang.base.util.boccc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author TU
 * @description 中行信用卡常量  Bank Of China Credit Card
 * @date 2019-09-02.
 */
public class BOCCCConstant {

    /**
     * 商户ID
     */
    public static final String MERCHANT_ID = "12345";

    /**
     * 商户名称：北京悠享首字母大写
     */
    public static final String MERCHANT_NAME = "BJYX";

    /**
     * 生成文件的根目录
     */
    public static final String rootPath = "/opt/jiazhe/webserver/files/boccc/";

    /**
     * PGP加密解密公钥路径
     */
    public static String publicKeyPath = "/opt/jiazhe/webserver/files/boccc/pgp.key/public_partner.asc";

    /**
     * PGP加密解密私钥路径
     */
    public static String privateKeyPath = "/opt/jiazhe/webserver/files/boccc/pgp.key/secret_partner.asc";

    /**
     * 登录中行服务器key
     */
    public static String loginPrivateKeyPath = "/opt/jiazhe/webserver/files/boccc/pgp.key/partner08.key";

}
