package com.jiazhe.youxiang.base.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TU
 * @description 用于腾讯短信业务
 * @date 2019-04-02.
 */
public class TencentMsgUtils {

    public static int appId = Integer.valueOf(PropertyUtils.getProperty("tencentAppId"));

    public static String appKey = PropertyUtils.getProperty("tencentAppKey");

    public static int ver_code_templateId = 306838;
    public static int business_templateId = 309066;
    public static String smsSign = "wuli豆豆";

//    public static int ver_code_templateId = 307385;
//    public static int business_templateId = ;
//    public static String smsSign = "悠享互联";

    private static final Logger logger = LoggerFactory.getLogger(TencentMsgUtils.class);

    /**
     * 发送验证短信
     */
    public static SmsSingleSenderResult sendVerificationCodeMsg(String phone, String code) {
        try {
            String[] params = {code, "5"};
            SmsSingleSender smsSingleSender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = smsSingleSender.sendWithParam("86", phone, ver_code_templateId, params, smsSign, "", "");
            return result;
        } catch (Exception e) {
            logger.error("腾讯云短信发送异常，异常信息:" + e.getMessage());
        }
        return null;
    }

    /**
     * 发送验证短信
     */
    public static SmsSingleSenderResult sendBusinessMsg(String phone, String content) {
        try {
            String[] params = {content};
            SmsSingleSender smsSingleSender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = smsSingleSender.sendWithParam("86", phone, business_templateId, params, smsSign, "", "");
            return result;
        } catch (Exception e) {
            logger.error("腾讯云短信发送异常，异常信息:" + e.getMessage());
        }
        return null;
    }
}
