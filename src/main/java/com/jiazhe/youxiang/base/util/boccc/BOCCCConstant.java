package com.jiazhe.youxiang.base.util.boccc;

/**
 * @author TU
 * @description 中行信用卡常量  Bank Of China Credit Card
 * @date 2019-09-02.
 */
public class BOCCCConstant {

    /**
     * 1天的毫秒数
     */
    public static final Long DAY_SEC = 24L * 60 * 60 * 1000;

    /**
     * 字段间的分隔符
     */
    public static final String BOC_Separator = " |#| ";

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
    public static String publicKeyPath = rootPath + "pgp.key/public_partner.asc";

    /**
     * PGP加密解密私钥路径
     */
    public static String privateKeyPath = rootPath + "pgp.key/secret_partner.asc";

    /**
     * 登录中行服务器key路径
     */
    public static String loginPrivateKeyPath = rootPath + "pgp.key/partner08.key";

    /**
     * 下载到本地文件的根路径
     */
    public static String downloadPath = rootPath + "download/";

    /**
     * 将要上传的文件的根路径
     */
    public static String uploadPath = rootPath + "upload/";

    /**
     * 包含 中行信用卡  字样的优惠券
     */
    public static String couponName = "中行信用卡";

}
