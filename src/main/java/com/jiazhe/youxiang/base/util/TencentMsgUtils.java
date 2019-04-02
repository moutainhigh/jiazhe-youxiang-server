package com.jiazhe.youxiang.base.util;

import com.github.qcloudsms.SmsMobileStatusPuller;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.SmsStatusPullCallbackResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author TU
 * @description 用于腾讯短信业务
 * @date 2019-04-02.
 */
public class TencentMsgUtils {

    public static int appId = Integer.valueOf(PropertyUtils.getProperty("tencentAppId"));

    public static String appKey = PropertyUtils.getProperty("tencentAppKey");

    public static int templateId = 306838;

    public static String smsSign = "wuli豆豆";

    private static final Logger logger = LoggerFactory.getLogger(TencentMsgUtils.class);

    /**
     * 发送验证短信
     */
    public static SmsSingleSenderResult sendMsg(String phone) {
        try {
            String[] params = {RandomUtil.generateVerifyCode(4), "5"};
            SmsSingleSender smsSingleSender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = smsSingleSender.sendWithParam("86", phone, templateId, params, smsSign, "", "");
            return result;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拉取短信回执
     *
     * @param phone
     * @return
     */
    public static SmsStatusPullCallbackResult querySendDetails(String phone) {
        try {
            long beginTime = System.currentTimeMillis()-CommonConstant.ONE_HOUR;
            long endTime = System.currentTimeMillis();
            SmsMobileStatusPuller msPuller = new SmsMobileStatusPuller(appId, appKey);
            // 拉取短信回执
            SmsStatusPullCallbackResult callbackResult = msPuller.pullCallback("86",
                    phone, beginTime/1000, endTime/1000, 1);
            return callbackResult;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证码验证
     */
    public static boolean isVerified(String phone, String code) {
        String VERIFIED_CODE_REG = "\\d{4}";
        SmsStatusPullCallbackResult querySendDetailsResponse = querySendDetails(phone);
        if (null == querySendDetailsResponse) {
            logger.error("通过phone，查出的querySendDetailsResponse为空");
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
        }
        List<SmsStatusPullCallbackResult.Callback> smsSendDetailDTOList = querySendDetailsResponse.callbacks;
        if (CollectionUtils.isEmpty(smsSendDetailDTOList)) {
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
        }
        String content = smsSendDetailDTOList.get(smsSendDetailDTOList.size() - 1).description;
        String content_code = "";
        Pattern pattern = Pattern.compile(VERIFIED_CODE_REG);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            content_code = matcher.group();
        }
        if (!content_code.equals(code)) {
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
        }
        long receiveTime = DateUtil.strToMinutes(smsSendDetailDTOList.get(smsSendDetailDTOList.size() - 1).user_receive_time).getTime();
        //短信验证码在有5分钟有效期内
        if (System.currentTimeMillis() - receiveTime > CommonConstant.FIVE_MINUTES) {
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_EXPIRY);
        }
        return true;
    }
}
