package com.jiazhe.youxiang.server.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.*;
import com.jiazhe.youxiang.server.dao.mapper.SysUserPOMapper;
import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.domain.po.SysUserPOExample;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

/**
 * Created by TU on 2018/10/11.
 */
@Controller
@RequestMapping("api/signin")
public class SignInController extends BaseController{

    @Autowired
    SessionDAO sessionDAO;
    @Autowired
    private SysUserPOMapper sysUserPOMapper;

    //后台有登录请求，则发送验证码
    @RequestMapping(value = "/sendsignincode")
    public void sendIdentifyingCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException {
        String code = "";
        String msg = "";
        JSONObject data = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        SysUserPOExample sysUserPOExample = new SysUserPOExample();
        SysUserPOExample.Criteria criteria = sysUserPOExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysUserPO> sysUserPOList = sysUserPOMapper.selectByExample(sysUserPOExample);
        if (sysUserPOList.size() == 1) {//根据用户名和密码判断，是否有该用户，有该用户，给该用户手机发验证短信
            ByteSource salt = ByteSource.Util.bytes(sysUserPOList.get(0).getSalt());
            String saltPassword = new SimpleHash("MD5",password,salt,1024).toString();
            if(saltPassword.equals(sysUserPOList.get(0).getPassword())){//密码加密后一致
                if (!ValidateUtils.phoneValidate(sysUserPOList.get(0).getMobile())) {//判断该用户的绑定的手机号是否合法
                    code = "000001";
                    msg = "您还没有绑定合法的手机号码，请联系后台管理员";
                } else {//合法发送验证码
                   /* SendSmsResponse res = AliUtils.sendMsg(sysUserPOList.get(0).getMobile());
                    if (res.getCode() != null && res.getCode().equals("OK")) {
                        code = "000000";
                        msg = "发送验证码成功";
                        data.put("bizId", res.getBizId());
                        data.put("phone", sysUserPOList.get(0).getMobile());
                    } else {
                        code = "000001";
                        msg = "发送短信失败，原因：" + res.getMessage();//发送失败的原因
                    }*/
                    code = "000000";
                    msg = "发送验证码成功";
                }
            }else{
                code = "000001";
                msg = "密码错误" ;//发送失败的原因
            }
        } else {
            code = "000001";
            msg = "用户不存在";
        }
        ResponseUtil.responseUtils(response, ResultPackage.resultPackage(code, data, msg));
    }

    //后台用户通过验证码和密码登录
    @RequestMapping(value = "/signin")
    public void sigin(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException, ParseException {
        String code = "";
        String msg = "";
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String identifyingCode = request.getParameter("code");
        String bizId = request.getParameter("bizId");
        if (AliUtils.isVerified(phone, identifyingCode, bizId)) {//短信验证码验证通过
            Subject subject = SecurityUtils.getSubject();
            try {
                Collection<Session> sessions = sessionDAO.getActiveSessions();
                System.out.println(sessions.size());
                for (Session session : sessions) {
                    if (null != session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) {
                        System.out.println("登录用户" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                        SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                        SysUserPO sysUserPO = (SysUserPO) simplePrincipalCollection.getPrimaryPrincipal();
                        if (name.equals(sysUserPO.getName())) {
                            // session.setTimeout(0); //这里就把session清除
                            System.out.println("删除用户seesion" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString());
                            sessionDAO.delete(session); // session清除，
                        }
                    }
                }
                subject.login(new UsernamePasswordToken(name, password));
                subject.getSession().setTimeout(ConstantFetchUtil.hour_8);// 将seesion过期时间设置为8小时
                CookieUtil.addCookie(response, "name", name);
                code = "000000";
                msg = "登陆成功";
            } catch (AuthenticationException e) {

            }
        } else {
            code = "000001";
            msg = "验证码错误";
        }
        ResponseUtil.responseUtils(response, ResultPackage.resultPackage(code, new JSONObject(), msg));
    }

    @RequiresPermissions("test:pagetest")
    @RequestMapping(value = "/pagetest")
    public String pageTest(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException, ParseException {
        return null;
    }

    @RequiresPermissions("test:ajaxtest")
    @RequestMapping(value = "/ajaxtest")
    public void ajaxTest(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException, ParseException {
        ResponseUtil.responseUtils(response, ResultPackage.resultPackage("000000", new JSONObject(), "ajax有权限访问，测试成功"));
    }
}
