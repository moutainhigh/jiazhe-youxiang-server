package com.jiazhe.youxiang.base.util;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.vo.resp.login.SendVerificationCodeResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TU
 * @description 用于协调腾讯云和阿里云短信业务
 * @date 2019-04-03.
 */
public class MsgUtils {

    private static final Logger logger = LoggerFactory.getLogger(MsgUtils.class);

    /**
     * 短信验证码位数
     */
    public static int VER_CODE_LENGTH = 4;

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
    public static SendVerificationCodeResp sendVerificationCodeMsg(String phone) {
        String code = RandomUtil.generateVerifyCode(VER_CODE_LENGTH);
        SendVerificationCodeResp sendMsgResp = new SendVerificationCodeResp();
        //先尝试用腾讯云发送
        SmsSingleSenderResult smsSingleSenderResult = TencentMsgUtils.sendVerificationCodeMsg(phone, code);
        if (null != smsSingleSenderResult && smsSingleSenderResult.result == 0) {
            sendMsgResp.setBizId("***********");
            RedisUtils.set(CommonConstant.REDIS_VER_CODE + phone, code, CommonConstant.FIVE_MINUTES);
            return sendMsgResp;
        } else {
            SendSmsResponse sendSmsResponse = AliMsgUtils.sendVerificationCodeMsg(phone, code);
            if (sendSmsResponse.getCode() != null && VER_CODE_SEND_SUCCESS.equals(sendSmsResponse.getCode())) {
                sendMsgResp.setBizId(sendSmsResponse.getBizId());
                RedisUtils.set(CommonConstant.REDIS_VER_CODE + phone, code, CommonConstant.FIVE_MINUTES);
            }
            return sendMsgResp;
        }
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
