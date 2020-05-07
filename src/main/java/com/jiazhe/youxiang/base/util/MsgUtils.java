package com.jiazhe.youxiang.base.util;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.jiazhe.youxiang.server.biz.message.MessageBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.vo.resp.login.SendVerificationCodeResp;
import com.jiazhe.youxiang.server.vo.resp.message.SendSingleMsgResp;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * 通过手机号，发送【验证码】短信
     *
     * @param phone
     * @param code
     * @return
     */
    public static SendVerificationCodeResp sendVerificationCodeMsg(String phone, String code) {
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
        msgUtils.messageBiz.insertVerCodeMsg(sendMsgResp.getServiceProvider(), phone, code, CommonConstant.MSG_TYPE_VER_CODE);
        return sendMsgResp;
    }

    /**
     * 通过下面参数，首选腾讯云发送，再选择阿里云发送【业务】短信
     *
     * @param mobile
     * @param tencentTemplateId
     * @param tencentTemplateContent
     * @param aliTemplateCode
     * @param aliTemplateContent
     * @param params
     * @return
     */
    public static SendSingleMsgResp sendBusinessMsg(String mobile, Integer tencentTemplateId, String tencentTemplateContent, String aliTemplateCode, String aliTemplateContent, String[] params) {
        String errMsg = "";
        SendSingleMsgResp resp = new SendSingleMsgResp();
        resp.setSuccess(false);
        SmsSingleSenderResult smsSingleSenderResult = null;
        //无腾讯云的短信模板
        if (tencentTemplateId == 0 || ("").equals(tencentTemplateContent)) {
            errMsg = errMsg + "【腾讯云】：无对应模板。";
        } else {
            smsSingleSenderResult = TencentMsgUtils.sendMsg(mobile, tencentTemplateId, params);
            if (null != smsSingleSenderResult && smsSingleSenderResult.result == 0) {
                resp.setSuccess(true);
            } else {
                errMsg = errMsg + "【腾讯云】：" + smsSingleSenderResult.errMsg + "。";
            }
            resp.setServiceProvider(CommonConstant.MSG_SERVICE_PROVIDER_TENCENT);
        }
        if (!resp.isSuccess()) {
            if (("").equals(aliTemplateCode) || ("").equals(aliTemplateContent)) {
                errMsg = errMsg + "【阿里云】：无对应模板。";
            } else {
                SendSmsResponse sendSmsResponse = AliMsgUtils.sendMsg(mobile, aliTemplateCode, aliTemplateContent, params);
                if (sendSmsResponse.getCode() != null && VER_CODE_SEND_SUCCESS.equals(sendSmsResponse.getCode())) {
                    resp.setSuccess(true);
                } else {
                    errMsg = errMsg + "【阿里云】：" + sendSmsResponse.getMessage() + "。";
                }
                resp.setServiceProvider(CommonConstant.MSG_SERVICE_PROVIDER_ALI);
            }
        }
        resp.setErrorMsg(errMsg);
        return resp;
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

    public static void validateTemplate(Integer tencentTemplateId, String tencentTemplateContent, String aliTemplateCode, String aliTemplateContent) {
//        if (tencentTemplateId != 0 && !("").equals(tencentTemplateContent)) {
//            TencentMsgUtils.validateTemplate(tencentTemplateId, tencentTemplateContent);
//        }
//        if (!("").equals(aliTemplateCode) && !("").equals(aliTemplateContent)) {
//            AliMsgUtils.validateTemplate(aliTemplateCode, aliTemplateContent);
//        }
    }

    /**
     * 将excel中的一行和模板，组装成一条短信（带有验证信息和错误信息）
     *
     * @param row             excel中的一行
     * @param templateContent 模板内容
     * @return
     */
    public static JSONObject formatterMsg(Row row, String templateContent) {
        StringBuilder errMsg = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        String PARAM_REG = "\\$?\\{[0-9a-zA-Z]+}";
        Pattern p = Pattern.compile(PARAM_REG);
        Matcher matcher = p.matcher(templateContent);
        int i = 1;
        boolean legal = true;
        boolean hasNull = false;
        boolean hasSemicolon = false;
        while (matcher.find()) {
            String temp = matcher.group();
            String cellStr = ExcelUtils.getStringValue(row.getCell(i));
            templateContent = templateContent.replace(temp, cellStr);
            i++;
            if (Strings.isEmpty(cellStr)) {
                hasNull = true;
                legal = false;
            }
            if (cellStr.contains(";")) {
                hasSemicolon = true;
                legal = false;
            }
        }
        if (hasNull) {
            errMsg.append("excel中不能有空值单元格。");
        }
        if (hasSemicolon) {
            errMsg.append("excel中不能有英文分号。");
        }
        jsonObject.put("content", templateContent);
        jsonObject.put("legal", legal);
        jsonObject.put("errMsg", errMsg.toString());
        return jsonObject;
    }

    /**
     * 将excel中的一行和模板，组装成一条短信（带有验证信息和错误信息）
     *
     * @param row             excel中的一行
     * @param templateContent 模板内容
     * @return
     */
    public static JSONObject row2Params(Row row, String templateContent) {
        StringBuilder content = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile",ExcelUtils.getStringValue(row.getCell(0)));
        String PARAM_REG = "\\$?\\{[0-9a-zA-Z]+}";
        Pattern p = Pattern.compile(PARAM_REG);
        Matcher matcher = p.matcher(templateContent);
        int i = 1;
        while (matcher.find()) {
            content.append(ExcelUtils.getStringValue(row.getCell(i)) + ";");
            i++;
        }
        jsonObject.put("content",content.toString());
        return jsonObject;
    }

    /**
     * 由带分号的参数和短信模板，组装短信完整内容
     * @param param
     * @param templateContent
     * @return
     */
    public static String param2Content(String param, String templateContent) {
        String[] params = param.split(";");
        String PARAM_REG = "\\$?\\{[0-9a-zA-Z]+}";
        Pattern p = Pattern.compile(PARAM_REG);
        Matcher matcher = p.matcher(templateContent);
        int i = 0;
        while (matcher.find()) {
            String temp = matcher.group();
            templateContent = templateContent.replace(temp, params[i]);
            i++;
        }
        return templateContent;
    }
}
