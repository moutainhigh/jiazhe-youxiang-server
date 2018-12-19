package com.jiazhe.youxiang.server.common.constant;

/**
 * @author tu
 * @description：微信支付相关常量
 * @date 2018/12/9
 */
public class WeChatPayConstant {

    //统一下单接口地址
    public static String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    //应用id
    public static String APP_ID = "123";

    //商户号
    public static String MCH_ID = "456";

    //通知地址【接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。】
    public static String NOTIFY_URL = "http://www.weixin.qq.com/wxpay/pay";

    //交易类型
    public static String TRADE_TYPE = "APP";

    public static String API_KEY = "111";

    public static String AppSecret = "111";

}
