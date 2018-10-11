package com.jiazhe.youxiang.server.controller;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jiazhe.youxiang.base.util.*;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by TU on 2018/10/11.
 */
@Controller
@RequestMapping("/signin")
public class SignInController {

    @Autowired
    SessionDAO sessionDAO;

    @RequestMapping(value = "/sendsignincode")
    public void sendIdentifyingCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException {
        String code = "";
        String msg = "" ;
        JSONObject data = new JSONObject();
        String phone = request.getParameter("phone");
        SendSmsResponse res = AliUtils.sendSignInMsg(phone);
        if(res.getCode() != null && res.getCode().equals("OK")){
            code = "000000";
            msg = "发送验证码成功";
            data.put("bizId", res.getBizId());
        }else{
            code = "000001";
            msg = "发送短信失败，原因：" + res.getMessage();//发送失败的原因
        }
        ResponseUtil.responseUtils(response, ResultPackage.resultPackage(code, data, msg));
    }

    @RequestMapping(value = "/signin")
    public void signuporin(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException, ParseException, ParseException {
        String code = "";
        String msg = "" ;
        JSONObject data = new JSONObject();
        String phone = request.getParameter("phone");
        String identifyingCode = request.getParameter("code");
        String bizId = request.getParameter("bizId");
        QuerySendDetailsResponse querySendDetailsResponse = AliUtils.querySendDetails(phone,bizId);
        List<QuerySendDetailsResponse.SmsSendDetailDTO> smsSendDetailDTOList = querySendDetailsResponse.getSmsSendDetailDTOs();
        if(smsSendDetailDTOList.size()==0){
            code = "000001";
            msg = "验证码错误";
        }else{
            String content = smsSendDetailDTOList.get(smsSendDetailDTOList.size()-1).getContent();
            if(content.contains(identifyingCode)){//验证码正确
                long receiveTime = MyDateUtils.strToMinutes(smsSendDetailDTOList.get(smsSendDetailDTOList.size()-1).getReceiveDate()).getTime();
                if(new Date().getTime()-receiveTime < 5*60*1000) {//短信验证码没过期
                    Subject subject = SecurityUtils.getSubject();
                    try {
                        Collection<Session> sessions = sessionDAO.getActiveSessions();
                        for (Session session : sessions) {
                            if (null != session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) {
                                System.out.println("登录用户" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                                if (phone.equals(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString())) {
                                    // session.setTimeout(0); //这里就把session清除
                                    System.out.println("删除用户seesion" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString());
                                    sessionDAO.delete(session); // session清除，
                                }
                            }
                        }
                        subject.login(new UsernamePasswordToken(phone, ""));
                        subject.getSession().setTimeout(ConstantFetchUtil.hour_8);// 将seesion过期时间设置为8小时
                        CookieUtil.addCookie(response, "phone", phone);
                    }catch (AuthenticationException e) {

                    }
                }else{
                    code = "000001";
                    msg = "验证码已过期";
                }
            }
        }
        ResponseUtil.responseUtils(response, ResultPackage.resultPackage(code, data, msg));
    }
}
