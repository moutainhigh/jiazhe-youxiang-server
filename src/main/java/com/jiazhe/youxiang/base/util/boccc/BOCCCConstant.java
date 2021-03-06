package com.jiazhe.youxiang.base.util.boccc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author TU
 * @description 中行信用卡常量  Bank Of China Credit Card
 * @date 2019-09-02.
 */
@Component
public class BOCCCConstant {

    /**
     * 中行信用卡环境执行
     */
    public static String[] BOCCC_ENVIRONMENT = {"dev", "boccc-test", "boccc-online"};

    /**
     * BOCCC文件编码格式
     */
    public static final String CHAR_SET = "GBK";

    /**
     * 1天的毫秒数
     */
    public static final Long DAY_SEC = 24L * 60 * 60 * 1000;

    /**
     * 字段间的分隔符
     */
    public static final String BOC_Separator = " |#| ";

    /**
     * 商户名称：北京悠享首字母大写
     */
    public static final String MERCHANT_NAME = "YOUX";

    /**
     * 商户ID
     */
    public static final String MERCHANT_ID = "M" + MERCHANT_NAME + "00001";

    /**
     * 门店ID
     */
    public static final String SHOP_ID = "M" + MERCHANT_NAME + "0000000001";

    /**
     * 生成文件的根目录
     */
    public static final String rootPath = "/opt/jiazhe/webserver/files/boccc/";

    /**
     * PGP 三方加密公钥路径，这里公钥和私钥不是一对
     */
    public static String publicKeyPath = rootPath + "pgp.key/dsfpublic.asc";

    /**
     * PGP 三方解密私钥路径，这里公钥和私钥不是一对
     */
    public static String privateKeyPath = rootPath + "pgp.key/dealsecret.asc";

    /**
     * 登录中行服务器key路径
     */
    public static String loginPrivateKeyPath = rootPath + "sftp.key/YOUX.key";

    /**
     * 下载到本地文件的根路径
     */
    public static String downloadPath = rootPath + "download/";

    /**
     * 将要上传的文件的根路径
     */
    public static String uploadPath = rootPath + "upload/";

    /**
     * 商户信息路径，包括手动上传的excel，自动生成的源文件、压缩文件、加密文件
     */
    public static String merchantPath = rootPath + "merchant/";

    /**
     * 商品信息路径，包括手动上传的excel，自动生成的源文件、压缩文件、加密文件
     */
    public static String productPath = rootPath + "product/";

    /**
     * 优惠券信息路径，包括手动上传的excel，自动生成的源文件、压缩文件、加密文件
     */
    public static String couponPath = rootPath + "coupon/";

    /**
     * 优惠券信息路径，包括手动上传的excel，自动生成的源文件、压缩文件、加密文件
     */
    public static String picPath = rootPath + "pic/";

    /**
     * 优惠券已使用信息
     */
    public static String cusedPath = rootPath + "cused/";

    /**
     * 退货信息包括返回退货信息反馈接口
     */
    public static String ccancelPath = rootPath + "ccancel/";

    /**
     * 每日购买商品清单
     */
    public static String dailyPurchase = rootPath + "dailypurchase/";

    /**
     * 每日剩余清单
     */
    public static String dailyRemain = rootPath + "dailyremain/";

    /**
     * 九个接口文件名模板
     */
    public static String FILE_INDEX = "00";
    // 1、商品信息
    public static String WARES_SOURCE = "WARES." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P";
    public static String WARES_ZIP = "WARES." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P.ZIP";
    public static String WARES_PGP = "WARES." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P.ZIP.DAT";
    // 2、商户信息
    public static String MER_SOURCE = "MER." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P";
    public static String MER_ZIP = "MER." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P.ZIP";
    public static String MER_PGP = "MER." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P.ZIP.DAT";
    // 3、优惠券信息
    public static String COUPON_SOURCE = "COUPON." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P";
    public static String COUPON_ZIP = "COUPON." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P.ZIP";
    public static String COUPON_PGP = "COUPON." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P.ZIP.DAT";
    // 4、图片信息
    public static String PIC_ZIP = "PIC." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P.ZIP";
    public static String PIC_PGP = "PIC." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".P.ZIP.DAT";
    // 5、优惠券使用更新
    public static String CUSED_SOURCE = "CUSED." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".U";
    public static String CUSED_ZIP = "CUSED." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".U.ZIP";
    public static String CUSED_PGP = "CUSED." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".U.ZIP.DAT";
    // 6、反馈退货
    public static String BJYX_CCANCEL_SOURCE = "CCANCEL." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".C";
    public static String BJYX_CCANCEL_ZIP = "CCANCEL." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".C.ZIP";
    public static String BJYX_CCANCEL_PGP = "CCANCEL." + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".C.ZIP.DAT";

    // 7、总行发给第三方的退货信息
    public static String BOC_CCANCEL_SOURCE = "CCANCEL.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".C";
    public static String BOC_CCANCEL_ZIP = "CCANCEL.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".ZIP";
    public static String BOC_CCANCEL_PGP = "CCANCEL.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".ZIP.DAT";
    // 8、每日优惠券剩余
    public static String CREMA_SOURCE = "CREMA.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".B";
    public static String CREMA_ZIP = "CREMA.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".ZIP";
    public static String CREMA_PGP = "CREMA.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".ZIP.DAT";
    // 9、每日商品购买清单
    public static String CSELL_SOURCE = "CSELL.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".B";
    public static String CSELL_ZIP = "CSELL.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".ZIP";
    public static String CSELL_PGP = "CSELL.BOC" + MERCHANT_NAME + ".YYYYMMDD." + FILE_INDEX + ".ZIP.DAT";

    /**
     * 中行信用卡实时接口：已使用请求
     */
    public static String REAL_TIME_USED_URL;

    /**
     * 中行信用卡实时接口：已退货请求
     */
    public static String REAL_TIME_REFUND_URL;

    /**
     * SFTP 登录用户名
     */
    public static String username;
    /**
     * SFTP 登录密码
     */
    public static String password;
    /**
     * SFTP 服务器地址IP地址
     */
    public static String host;
    /**
     * SFTP 端口
     */
    public static int port;

    /**
     * 上传到中行的根路径
     */
    public static String outPath;

    /**
     * 中行下发文件的根路径
     */
    public static String inPath;

    /**
     * 中行信用卡解密PGP文件，字符串
     */
    public static String PASSPHRASE;

    @Value("${boccc.realtime.used_url}")
    public void setUsedUrl(String usedUrl) {
        REAL_TIME_USED_URL = usedUrl;
    }

    @Value("${boccc.realtime.refund_url}")
    public void setRefundUrl(String refundUrl) {
        REAL_TIME_REFUND_URL = refundUrl;
    }

    @Value("${boccc.sftp.username}")
    public void setUsername(String USERNAME) {
        username = USERNAME;
    }

    @Value("${boccc.sftp.password}")
    public void setPassword(String PASSWORD) {
        password = PASSWORD;
    }

    @Value("${boccc.sftp.host}")
    public void setHost(String HOST) {
        host = HOST;
    }

    @Value("${boccc.sftp.port}")
    public void setPort(int PORT) {
        port = PORT;
    }

    @Value("${boccc.sftp.out}")
    public void setOutPath(String OUTPATH) {
        outPath = OUTPATH;
    }

    @Value("${boccc.sftp.in}")
    public void setInPath(String INPATH) {
        inPath = INPATH;
    }

    @Value("${boccc.pgp.passphrase}")
    public void setPassPhrase(String passphrase) {
        PASSPHRASE = passphrase;
    }
}
