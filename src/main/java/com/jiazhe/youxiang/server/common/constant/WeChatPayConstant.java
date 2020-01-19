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
    微信统一下单接口地址
     */
    public static String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 请求微信退款接口
     */
    public static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
    商户接收付款成功/失败通知地址
     */
    public static String NOTIFY_URL = "/api/wxpay/notify";

    /**
     商户接收退款成功/失败通知地址
     */
    public static String REFUND_NOTIFY_URL = "/api/wxpay/refundnotify";

    /**
     * 根据code获取openid的url
     */
    public static String AUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";

    /**
     * 订单付款查询url
     */
    public static String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 订单退款查询url
     */
    public static String ORDER_REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

    /**
     key为商户平台设置的密钥key
     */
    public static String API_KEY ;

    /**
     * 域名
     */
    public static String DOMAIN;

    /**
     * 公众号id
     */
    public static String APP_ID;

    /**
     * 公众号密钥
     */
    public static String APP_SECRET;

    /**
     * 商户id
     */
    public static String MCH_ID;

    @Value("${wechat_pay_api_key}")
    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    @Value("${spring.boot.admin.client.instance.service-url}")
    public void setDOMAIN(String domain) {
        DOMAIN = domain;
    }

    @Value("${wechat_public.appid}")
    public void setAppId(String appId) {
        APP_ID = appId;
    }

    @Value("${wechat_public.secret}")
    public void setAppSecret(String appSecret) {
        APP_SECRET = appSecret;
    }

    @Value("${wechat_public.mchid}")
    public void setMchId(String mchId) {
        MCH_ID = mchId;
    }

}
