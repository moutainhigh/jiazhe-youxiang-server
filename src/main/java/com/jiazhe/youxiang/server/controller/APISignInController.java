package com.jiazhe.youxiang.server.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.realm.AuthRealm;
import com.jiazhe.youxiang.base.util.*;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
import com.jiazhe.youxiang.server.common.enums.LoginEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.SendMsgResp;
import com.jiazhe.youxiang.server.vo.req.login.LoginReq;
import com.jiazhe.youxiang.server.vo.resp.login.LoginResp;
import com.jiazhe.youxiang.server.vo.resp.sysuser.UserWithRoleResp;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("api/signin")
public class APISignInController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(APISignInController.class);

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private AuthRealm authRealm;
    @Autowired
    private SysUserBiz sysUserBiz;

    @ApiOperation(value = "登录", httpMethod = "GET",notes = "登录")
    @RequestMapping(value = "/signin")
    public Object sigin(@ModelAttribute LoginReq req,HttpServletRequest request,HttpServletResponse response) throws IOException, ClientException, ParseException {
        String loginName = req.getLoginname();
        String password =req.getPassword();
        String identifyingCode = req.getIdentifyingCode();
        String bizId = req.getBizId();
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(loginName);
        //首先判断用户是否存在且唯一
        if(sysUserDTOList.size()==1){
            SysUserDTO sysUserDTO = sysUserDTOList.get(0);
            String saltPassword = EncryptPasswordUtil.encrypt(sysUserDTO.getSalt(), password);
            //密码是否正确
            if(saltPassword.equals(sysUserDTO.getPassword())){
                //判断最后一次登陆ip是否一致，一致则直接登陆
                if(sysUserDTO.getLastLoginIp().equals(IpAdrressUtil.getIpAdrress(request))){

                }else{
                    //判断有没有短信bizId传过来
                    if(Strings.isEmpty(bizId)){
                        throw new CommonException(LoginEnum.LOGIN_DIFFERENT_CLIENT.getCode(),LoginEnum.LOGIN_DIFFERENT_CLIENT.getType(),LoginEnum.LOGIN_DIFFERENT_CLIENT.getMessage());
                    }
                    else{
                        //判断验证码是否正确
                        if(AliUtils.isVerified(sysUserDTO.getMobile(), identifyingCode, bizId)){
                            sysUserBiz.updateLastLoginInfo(sysUserDTO.getId(),IpAdrressUtil.getIpAdrress(request));
                        }else{
                            throw new CommonException(LoginEnum.LOGIN_IDENTIFYING_CODE_ERROR.getCode(),LoginEnum.LOGIN_IDENTIFYING_CODE_ERROR.getType(),LoginEnum.LOGIN_IDENTIFYING_CODE_ERROR.getMessage());
                        }
                    }
                }
            }else{
                throw new CommonException(LoginEnum.LOGIN_PASSWRLD_WRONG.getCode(),LoginEnum.LOGIN_PASSWRLD_WRONG.getType(),LoginEnum.LOGIN_PASSWRLD_WRONG.getMessage());
            }
            Subject subject = SecurityUtils.getSubject();
            Collection<Session> sessions = sessionDAO.getActiveSessions();
            for (Session session : sessions) {
                if (null != session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) {
                    logger.info("登录用户" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                    SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                    if (loginName.equals(sysUserDTO.getLoginName())) {
                        // session.setTimeout(0); //这里就把session清除
                        logger.info(("删除用户seesion" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString()));
                        // session清除，
                        sessionDAO.delete(session);
                    }
                }
            }
            subject.login(new UsernamePasswordToken(loginName, password));
            // 将seesion过期时间设置为8小时
            subject.getSession().setTimeout(ConstantFetchUtil.hour_8);
            AuthorizationInfo info = authRealm.doGetAuthorizationInfo(subject.getPrincipals());
            String permission = StringUtils.join(info.getStringPermissions(), ",");
            CookieUtil.addCookie(response, "permission", permission);
            CookieUtil.addCookie(response, "displayName", sysUserDTO.getDisplayName());
        }else{
            throw new CommonException(LoginEnum.LOGIN_USER_ILLEGAL.getCode(),LoginEnum.LOGIN_USER_ILLEGAL.getType(),LoginEnum.LOGIN_USER_ILLEGAL.getMessage());
        }
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据登陆名，发送验证码", httpMethod = "GET",notes = "根据登陆名，发送验证码")
    @RequestMapping(value = "/sendcode")
    public Object sendCode(HttpServletRequest request) throws IOException, ClientException, ParseException {
        String loginname = request.getParameter("loginname");
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(loginname);
        SendSmsResponse res = AliUtils.sendMsg(sysUserDTOList.get(0).getMobile());
        SendMsgResp sendMsgResp = new SendMsgResp();
        sendMsgResp.setBizId(res.getBizId());
        return ResponseFactory.buildResponse(sendMsgResp);
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
