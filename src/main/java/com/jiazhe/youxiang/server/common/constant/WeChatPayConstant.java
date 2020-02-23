package com.jiazhe.youxiang.server.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tu
 * @description：微信支付相关常量
 * @date 2018/12/9
 */
@Component
public class WeChatPayConstant {

    /**
     * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    public static String DEVICE_INFO = "WEB";

    /**
     交易类型
     */
    public static String TRADE_TYPE = "JSAPI";

    /**
     * 随机字符串长度
     */
    public static Integer NONCE_STR_LENGTH = 32 ;

    /**
    统一下单接口地址
     */
    public static String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


    /**
    通知地址【接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。】
     */
    public static String NOTIFY_URL = "/api/wxpay/notify";

    /**
     * 根据code获取openid的url
     */
    public static String AUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";

    /**
     * 订单查询url
     */
    public static String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     key为商户平台设置的密钥key
     */
    public static String API_KEY ;

    @Value("${wechat_pay_api_key}")
    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

}
