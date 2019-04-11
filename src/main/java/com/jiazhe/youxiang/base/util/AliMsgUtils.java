package com.jiazhe.youxiang.base.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author TU
 * @date 2018/10/29
 * @description 用于阿里短信服务
 */
public class AliMsgUtils {

    private static final Logger logger = LoggerFactory.getLogger(AliMsgUtils.class);

    public static String aliAccessKeyId = "LTAIOAhuwWw5pdlZ";
    public static String aliAccessKeySecret = "hjhwNmIurazb7NMsjZo5sMsPr9bBif";
    public static String SignName = "悠享";

    /**
     * 验证码模板为固定的
     */
    public static String ver_code_TemplateCode = "SMS_147418355";


    /**
     * 发送验证短信
     */
    public static SendSmsResponse sendVerificationCodeMsg(String phone, String code) {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliAccessKeyId, aliAccessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        } catch (ClientException e) {
            logger.error("阿里云短信服务异常，错误堆栈信息为：", e);
            throw new LoginException(LoginCodeEnum.LOGIN_SENDCODE_ERROR);
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SignName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(ver_code_TemplateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("阿里云短信服务异常，错误堆栈信息为：", e);
            throw new LoginException(LoginCodeEnum.LOGIN_SENDCODE_ERROR);
        }
        return sendSmsResponse;
    }

    /**
     * 发送业务短信
     */
    public static SendSmsResponse sendBusinessMsg(String mobile, String templateCode, String templateContent, String[] params) {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliAccessKeyId, aliAccessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        } catch (ClientException e) {
            logger.error("阿里云短信服务异常，错误堆栈信息为：", e);
            throw new LoginException(LoginCodeEnum.LOGIN_SENDCODE_ERROR);
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SignName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//        request.setTemplateParam("{\"content\":\"" + content + "\"}");
        request.setTemplateParam(packageTemplateParam(templateContent, params));
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("阿里云短信服务异常，错误堆栈信息为：", e);
            throw new LoginException(LoginCodeEnum.LOGIN_SENDCODE_ERROR);
        }
        return sendSmsResponse;
    }

    /**
     * 组装templateParam参数
     *
     * @param templateContent
     * @param params
     * @return
     */
    public static String packageTemplateParam(String templateContent, String[] params) {
        StringBuilder sb = new StringBuilder("{");
        String[] key = new String[params.length];
        String PARAM_REG = "\\$\\{[a-zA-Z]+}";
        Pattern p = Pattern.compile(PARAM_REG);
        Matcher matcher = p.matcher(templateContent);
        int i = 0;
        while (matcher.find()) {
            String temp = matcher.group();
            key[i] = temp.substring(2, temp.length() - 1);
            i++;
        }
        for (int j = 0; j < params.length; j++) {
            sb.append("\"" + key[j] + "\":\"" + params[j] + "\",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        String templateContent = "您于${time}兑换的${productName}服务，卡号为${code}，密码为${keyt}。如有任何疑惑请拨打客服电话1111";
        String[] params = {"123", "4555", "345", "234"};
        System.out.println(packageTemplateParam(templateContent, params));
    }

    public static void validateTemplate(String aliTemplateCode, String aliTemplateContent) {

    }
}
