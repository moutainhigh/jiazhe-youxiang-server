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
     * 商户名称：北京悠享首字母大写
     */
    public static final String MERCHANT_NAME = "BJYX";

    /**
     * 商户ID
     */
    public static final String MERCHANT_ID = "M" + MERCHANT_NAME + "XXXXNNNNNNNNNN";

    /**
     * 生成文件的根目录
     */
    public static final String rootPath = "/opt/jiazhe/webserver/files/boccc/";

    /**
     * PGP加密解密公钥路径
     */
    public static String publicKeyPath = rootPath + "pgp.key/jiazheng_public.asc";

    /**
     * PGP加密解密私钥路径
     */
    public static String privateKeyPath = rootPath + "pgp.key/jiazheng_private.asc";

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
     * 九个接口文件名模板
     */
    // 1、商品信息
    public static String WARES_SOURCE = "WARES.XXXX.YYYYMMDD.00.P";
    public static String WARES_ZIP = "WARES.XXXX.YYYYMMDD.00.P.ZIP";
    public static String WARES_PGP = "WARES.XXXX.YYYYMMDD.00.P.ZIP.DAT";
    // 2、商户信息
    public static String MER_SOURCE = "MER.XXXX.YYYYMMDD.00.P";
    public static String MER_ZIP = "MER.XXXX.YYYYMMDD.00.P.ZIP";
    public static String MER_PGP = "MER.XXXX.YYYYMMDD.00.P.ZIP.DAT";
    // 3、优惠券信息
    public static String COUPON_SOURCE = "COUPON.XXXX.YYYYMMDD.00.P";
    public static String COUPON_ZIP = "COUPON.XXXX.YYYYMMDD.00.P.ZIP";
    public static String COUPON_PGP = "COUPON.XXXX.YYYYMMDD.00.P.ZIP.DAT";
    // 4、图片信息
    public static String PIC_SOURCE = "PIC.XXXX.YYYYMMDD.00.P.ZIP";
    public static String PIC_ZIP = "PIC.XXXX.YYYYMMDD.00.P.ZIP";
    public static String PIC_PGP = "PIC.XXXX.YYYYMMDD.00.P.ZIP.DAT";
    // 5、优惠券使用更新
    public static String CUSED_SOURCE = "CUSED.XXXX.YYYYMMDD.00.U";
    public static String CUSED_ZIP = "CUSED.XXXX.YYYYMMDD.00.U.ZIP";
    public static String CUSED_PGP = "CUSED.XXXX.YYYYMMDD.00.U.ZIP.DAT";
    // 6、反馈退货
    public static String BJYX_CCANCEL_SOURCE = "CCANCEL.XXXX.YYYYMMDD.00.C";
    public static String BJYX_CCANCEL_ZIP = "CCANCEL.XXXX.YYYYMMDD.00.C.ZIP";
    public static String BJYX_CCANCEL_PGP = "CCANCEL.XXXX.YYYYMMDD.00.C.ZIP.DAT";

    // 7、总行发给第三方的退货信息
    public static String BOC_CCANCEL_SOURCE = "CCANCEL.BOCXXXX.YYYYMMDD.00.C";
    public static String BOC_CCANCEL_ZIP = "CCANCEL.BOCXXXX.YYYYMMDD.00.ZIP";
    public static String BOC_CCANCEL_PGP = "CCANCEL.BOCXXXX.YYYYMMDD.00.ZIP.DAT";
    // 8、每日优惠券剩余
    public static String CREMA_SOURCE = "CREMA.BOCXXXX.YYYYMMDD.00.B";
    public static String CREMA_ZIP = "CREMA.BOCXXXX.YYYYMMDD.00.ZIP";
    public static String CREMA_PGP = "CREMA.BOCXXXX.YYYYMMDD.00.ZIP.DAT";
    // 9、每日商品购买清单
    public static String CSELL_SOURCE = "CSELL.BOCXXXX.YYYYMMDD.00.B";
    public static String CSELL_ZIP = "CSELL.BOCXXXX.YYYYMMDD.00.ZIP";
    public static String CSELL_PGP = "CSELL.BOCXXXX.YYYYMMDD.00.ZIP.DAT";
}
