package com.jiazhe.youxiang.base.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @date 2018/10/29
 * @description 用于阿里短信服务
 */
public class AliUtils {

    private static final Logger logger = LoggerFactory.getLogger(AliUtils.class);
    /**
     * 发送验证短信
     */
    public static SendSmsResponse sendMsg(String phone) {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", PropertyUtils.getProperty("accessKeyId"), PropertyUtils.getProperty("accessKeySecret"));
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        } catch (ClientException e) {
            logger.error("阿里云短信服务异常，错误堆栈信息为：",e);
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
        request.setSignName("悠享");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_147418355");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + RandomUtil.generateVerifyCode(4) + "\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("阿里云短信服务异常，错误堆栈信息为：",e);
            throw new LoginException(LoginCodeEnum.LOGIN_SENDCODE_ERROR);
        }
        if (sendSmsResponse.getCode() == null && !sendSmsResponse.getCode().equals("OK")) {
            //请求失败
            throw new LoginException(LoginCodeEnum.LOGIN_SENDCODE_ERROR);
        }
        return sendSmsResponse;
    }

    public static QuerySendDetailsResponse querySendDetails(String phone, String bizId) {
        //可自助调整超时时间  
        System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
        System.setProperty("sun.net.client.defaultReadTimeout", "60000");
        //初始化acsClient,暂不支持region化  
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", PropertyUtils.getProperty("accessKeyId"), PropertyUtils.getProperty("accessKeySecret"));
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        } catch (ClientException e) {
            logger.error("阿里云短信服务异常，错误堆栈信息为：",e);
            throw new LoginException(LoginCodeEnum.ALI_MESSAGE_SERVICE_EXCEPTION);
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象  
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码  
        request.setPhoneNumber(phone);
        //可选-流水号  
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd  
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小  
        request.setPageSize(10L);
        //必填-当前页码从1开始计数  
        request.setCurrentPage(1L);
        //hint 此处可能会抛出异常，注意catch  
        QuerySendDetailsResponse querySendDetailsResponse = null;
        try {
            querySendDetailsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("阿里云短信服务异常，错误堆栈信息为：",e);
            throw new LoginException(LoginCodeEnum.ALI_MESSAGE_SERVICE_EXCEPTION);
        }
        return querySendDetailsResponse;
    }

    /**
     * 验证码验证
     */
    public static boolean isVerified(String phone, String code, String bizId) {
        QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(phone, bizId);
        List<QuerySendDetailsResponse.SmsSendDetailDTO> smsSendDetailDTOList = querySendDetailsResponse.getSmsSendDetailDTOs();
        if (smsSendDetailDTOList.size() == 0) {
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
        }
        String content = smsSendDetailDTOList.get(smsSendDetailDTOList.size() - 1).getContent();
        if (!content.contains(code)){
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
        }
        long receiveTime = DateUtil.strToMinutes(smsSendDetailDTOList.get(smsSendDetailDTOList.size() - 1).getReceiveDate()).getTime();
        //短信验证码在有5分钟有效期内
        if (System.currentTimeMillis() - receiveTime > CommonConstant.FIVE_MINUTES) {
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_EXPIRY);
        }
        return true;
    }
}
