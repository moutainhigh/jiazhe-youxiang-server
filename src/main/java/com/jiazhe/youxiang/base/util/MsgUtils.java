package com.jiazhe.youxiang.base.util;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.jiazhe.youxiang.server.biz.message.MessageBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.vo.resp.login.SendVerificationCodeResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Message;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author TU
 * @description 用于协调腾讯云和阿里云短信业务
 * @date 2019-04-03.
 */
@Component
public class MsgUtils {

    private static final Logger logger = LoggerFactory.getLogger(MsgUtils.class);

    @Autowired
    private MessageBiz messageBiz;
    private static MsgUtils msgUtils;

    @PostConstruct
    public void init() {
        msgUtils = this;
        msgUtils.messageBiz = this.messageBiz;
    }

    /**
     * 阿里云发送验证码成功返回code
     */
    public static String VER_CODE_SEND_SUCCESS = "OK";

    /**
     * 通过手机号，发送验证码
     *
     * @param phone
     * @return
     */
    public static SendVerificationCodeResp sendVerificationCodeMsg(String phone,String code) {
        SendVerificationCodeResp sendMsgResp = new SendVerificationCodeResp();
        //先尝试用腾讯云发送
        SmsSingleSenderResult smsSingleSenderResult = TencentMsgUtils.sendVerificationCodeMsg(phone, code);
        if (null != smsSingleSenderResult && smsSingleSenderResult.result == 0) {
            sendMsgResp.setBizId("***********");
            sendMsgResp.setServiceProvider(CommonConstant.MSG_SERVICE_PROVIDER_TENCENT);
            RedisUtils.set(CommonConstant.REDIS_VER_CODE + phone, code, CommonConstant.FIVE_MINUTES);
        } else {
            SendSmsResponse sendSmsResponse = AliMsgUtils.sendVerificationCodeMsg(phone, code);
            if (sendSmsResponse.getCode() != null && VER_CODE_SEND_SUCCESS.equals(sendSmsResponse.getCode())) {
                sendMsgResp.setBizId(sendSmsResponse.getBizId());
                sendMsgResp.setServiceProvider(CommonConstant.MSG_SERVICE_PROVIDER_ALI);
                RedisUtils.set(CommonConstant.REDIS_VER_CODE + phone, code, CommonConstant.FIVE_MINUTES);
            }
        }
        msgUtils.messageBiz.insertVerCodeMsg(sendMsgResp.getServiceProvider(),phone, code, CommonConstant.MSG_TYPE_VER_CODE);
        return sendMsgResp;
    }

    public static boolean sendBusinessMsg(String phone, String content){
        SmsSingleSenderResult smsSingleSenderResult = TencentMsgUtils.sendBusinessMsg(phone, content);
        if (null != smsSingleSenderResult && smsSingleSenderResult.result == 0) {
            return true;
        } else {
            SendSmsResponse sendSmsResponse = AliMsgUtils.sendVerificationCodeMsg(phone, content);
            if (sendSmsResponse.getCode() != null && VER_CODE_SEND_SUCCESS.equals(sendSmsResponse.getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过phone和code进行验证
     *
     * @param phone
     * @param code
     * @return
     */
    public static void isVerified(String phone, String code) {
        Object redis_code = RedisUtils.get(CommonConstant.REDIS_VER_CODE + phone);
        if (null == redis_code || !code.equals(redis_code)) {
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
        }
    }
}
