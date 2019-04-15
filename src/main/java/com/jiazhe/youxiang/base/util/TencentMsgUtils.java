package com.jiazhe.youxiang.base.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.jiazhe.youxiang.server.common.enums.MessageCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.MessageException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    public static String smsSign = "悠享互联";
//    public static String smsSign = "wuli豆豆";

    /**
     * 验证码模板为固定的，不修改
     */
    public static int ver_code_templateId = 307385;

    private static final Logger logger = LoggerFactory.getLogger(TencentMsgUtils.class);

    /**
     * 发送验证短信
     */
    public static SmsSingleSenderResult sendVerificationCodeMsg(String mobile, String code) {
        String[] params = {code, "5"};
        return sendMsg(mobile, ver_code_templateId, params);
    }

    /**
     * 发送业务短信
     */
    public static SmsSingleSenderResult sendMsg(String mobile, int templateId, String[] params) {
        try {
            SmsSingleSender smsSingleSender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = smsSingleSender.sendWithParam("86", mobile, templateId, params, smsSign, "", "");
            return result;
        } catch (Exception e) {
            logger.error("腾讯云短信发送异常，异常信息:" + e.getMessage());
        }
        return null;
    }

    /**
     * 通过腾讯云模板id获取模板信息
     *
     * @param templateId
     * @return
     */
    public static JSONObject getTemplateById(int templateId) {
        String random = RandomUtil.generateVerifyCode(10);
        String requestUrl = "https://yun.tim.qq.com/v5/tlssmssvr/get_template?sdkappid=" + appId + "&random=" + random;
        JSONObject params = new JSONObject();
        long time = System.currentTimeMillis() / 1000;
        String sig = ShaUtils.getSha256("appkey=" + appKey + "&random=" + random + "&time=" + time);
        params.put("time", time);
        params.put("tpl_id", "[" + templateId + "]");
        params.put("sig", sig);
        String string = HttpUtil.httpPost(requestUrl, params);
        JSONObject jb = JSONObject.fromObject(string);
        return jb;
    }

    /**
     * 验证系统数据库中保存的模板信息是否和腾讯云中的一致，并且腾讯端是否审核通过
     *
     * @param templateId
     * @param templateText
     * @return
     */
    public static void validateTemplate(int templateId, String templateText) {
        JSONObject jb = getTemplateById(templateId);
        if (jb.getInt("result") != 0) {
            throw new MessageException(MessageCodeEnum.GET_TENCENT_TEMPLATE_ERROR);
        }
        JSONArray data = jb.getJSONArray("data");
        if (data.size() != 1) {
            throw new MessageException(MessageCodeEnum.GET_TENCENT_TEMPLATE_ERROR);
        }
        JSONObject templateJb = data.getJSONObject(0);
        if (templateJb.getInt("status") != 0) {
            throw new MessageException(MessageCodeEnum.TENCENT_TEMPLATE_AUDIT_FAILED);
        }
        String remoteTemplateText = templateJb.getString("text");
        if (!templateText.equals(remoteTemplateText)) {
            throw new MessageException(MessageCodeEnum.TENCENT_TEMPLATE_DIFFERENT);
        }
    }
}
