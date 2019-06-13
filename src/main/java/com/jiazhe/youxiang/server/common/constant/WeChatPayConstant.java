package com.jiazhe.youxiang.server.common.constant;

/**
 * @author tu
 * @description：微信支付相关常量
 * @date 2018/12/9
 */
public class WeChatPayConstant {

    /**
     微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    public static String APP_ID = "wx68281f971cf77b90";

    /**
     微信支付分配的商户号
     */
    public static String MCH_ID = "1499770702";

    /**
     * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    public static String DEVICE_INFO = "WEB";

    /**
     交易类型
     */
    public static String TRADE_TYPE = "JSAPI";

    /**
    统一下单接口地址
     */
    public static String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
    通知地址【接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。】
     */
    public static String NOTIFY_URL = "https://admin.offline.ue-link.com/api/wxpay/notify";

    /**
     key为商户平台设置的密钥key
     */
    public static String API_KEY = "youxianghulian20180509lizhelizhe";

}
