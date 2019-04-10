package com.jiazhe.youxiang.base.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TU
 * @description 用于腾讯短信业务
 * @date 2019-04-02.
 */
public class TencentMsgUtils {

    public static int appId = Integer.valueOf(PropertyUtils.getProperty("tencentAppId"));

    public static String appKey = PropertyUtils.getProperty("tencentAppKey");

    public static int ver_code_templateId = 306838;
    public static String smsSign = "wuli豆豆";

//    public static int ver_code_templateId = 307385;
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
    public static SmsSingleSenderResult sendBusinessMsg(String phone, int templateId, String[] params) {
        try {
            SmsSingleSender smsSingleSender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = smsSingleSender.sendWithParam("86", phone, templateId, params, smsSign, "", "");
            return result;
        } catch (Exception e) {
            logger.error("腾讯云短信发送异常，异常信息:" + e.getMessage());
        }
        return null;
    }

    public static JSONObject getTemplateById(int templateId) {
        String random = String.valueOf(Math.random());
        String requestUrl = "https://yun.tim.qq.com/v5/tlssmssvr/get_template?sdkappid=" + appId + "&random=" + random;
        Map params = new HashMap();
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        Integer[] tpl_id = new Integer[]{templateId};
        String sig = ShaUtils.getSha256("appkey=" + appKey + "&random=" + random + "&time=" + time);
        params.put("time", time);
        params.put("tpl_id", tpl_id);
        params.put("sig", sig);
        //调用httpRequest方法，这个方法主要用于请求地址，并加上请求参数
//        String string = HttpUtil.httpRequest(requestUrl, params);
//        //处理返回的JSON数据并返回
//        JSONObject pageBean = JSONObject.fromObject(string);
//        return pageBean;
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getTemplateById(309066));
    }
}
