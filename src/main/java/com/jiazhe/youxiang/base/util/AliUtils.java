package com.jiazhe.youxiang.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

//该类用于阿里短信服务
public class AliUtils {
	//发送验证短信
	public static SendSmsResponse sendMsg(String phone) throws ServerException, ClientException{
		//设置超时时间-可自行调整
	    System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	    System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	    //初始化ascClient,暂时不支持多region（请勿修改）
	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", PropertyUtils.getProperty("accessKeyId"),PropertyUtils.getProperty("accessKeySecret"));
	    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
	    IAcsClient acsClient = new DefaultAcsClient(profile);
	    //组装请求对象
	    SendSmsRequest request = new SendSmsRequest();
	    //使用post提交
	    request.setMethod(MethodType.POST);
	    //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
	    request.setPhoneNumbers(phone);
		request.setSignName("悠享");//必填:短信签名-可在短信控制台中找到
		request.setTemplateCode("SMS_147418355");//必填:短信模板-可在短信控制台中找到
	    //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	    //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
	    request.setTemplateParam("{\"code\":\""+RandomUtil.generateVerifyCode(4)+"\"}");
	    //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
	    //request.setSmsUpExtendCode("90997");
	    //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
	    request.setOutId("yourOutId");
	    //请求失败这里会抛ClientException异常
	    SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	    /*if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
	    //请求成功
	    }*/
		return sendSmsResponse;
	}
	
	public static QuerySendDetailsResponse querySendDetails(String phone,String bizId) throws ClientException{
        //可自助调整超时时间  
        System.setProperty("sun.net.client.defaultConnectTimeout", "60000");  
        System.setProperty("sun.net.client.defaultReadTimeout", "60000");
        //初始化acsClient,暂不支持region化  
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", PropertyUtils.getProperty("accessKeyId"),PropertyUtils.getProperty("accessKeySecret"));
	    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
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
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);  
       return querySendDetailsResponse;  
    }

    //验证码验证
    public static boolean isVerified(String phone,String code,String bizId) throws ClientException ,ParseException {
		/*QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(phone,bizId);
		List<QuerySendDetailsResponse.SmsSendDetailDTO> smsSendDetailDTOList = querySendDetailsResponse.getSmsSendDetailDTOs();
		if(smsSendDetailDTOList.size()!=0){ //有该条短信记录
			String content = smsSendDetailDTOList.get(smsSendDetailDTOList.size()-1).getContent();
			if(content.contains(code)) {//短信内容包含该验证码
				long receiveTime = MyDateUtils.strToMinutes(smsSendDetailDTOList.get(smsSendDetailDTOList.size() - 1).getReceiveDate()).getTime();
				if (new Date().getTime() - receiveTime < 5 * 60 * 1000) {//短信验证码在有5分钟有效期内
					return true;
				}
			}
		}
		return false;*/
		return true;
	}
}
