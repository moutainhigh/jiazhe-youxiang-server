package com.jiazhe.youxiang.server.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.realm.AuthToken;
import com.jiazhe.youxiang.base.realm.UserRealm;
import com.jiazhe.youxiang.base.util.*;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LoginType;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.login.SendMsgReq;
import com.jiazhe.youxiang.server.vo.resp.login.SendMsgResp;
import com.jiazhe.youxiang.server.vo.req.login.LoginReq;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
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
import java.util.List;

/**
 * @author TU
 * @date 2018/10/11.
 */
@RestController
@RequestMapping("api/signin")
public class APISignInController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(APISignInController.class);

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private UserRealm userRealm;
    @Autowired
    private SysUserBiz sysUserBiz;

    @ApiOperation(value = "登录", httpMethod = "GET", notes = "登录")
    @RequestMapping(value = "/signin")
    public Object signin(@ModelAttribute LoginReq req, HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException, ParseException {
        String loginName = req.getLoginname();
        String password = req.getPassword();
        String identifyingCode = req.getIdentifyingCode();
        String bizId = req.getBizId();
        //首先判断用户是否存在且唯一
        CommonValidator.validateNull(req.getLoginname(),new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        CommonValidator.validateNull(req.getPassword(),new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(loginName);
        if (sysUserDTOList.size() != 1) {
            throw new LoginException(LoginCodeEnum.LOGIN_USER_ILLEGAL);
        }
        SysUserDTO sysUserDTO = sysUserDTOList.get(0);
        String saltPassword = EncryptPasswordUtil.encrypt(sysUserDTO.getSalt(), password);
        //密码是否正确
        if (!saltPassword.equals(sysUserDTO.getPassword())) {
            throw new LoginException(LoginCodeEnum.LOGIN_PASSWRLD_WRONG);
        }
        //判断最后一次登陆ip是否一致，一致则直接登陆
        if (!sysUserDTO.getLastLoginIp().equals(IpAdrressUtil.getIpAdrress(request))){
            //判断有没有短信bizId传过来
            CommonValidator.validateNull(bizId,new LoginException(LoginCodeEnum.LOGIN_DIFFERENT_CLIENT));
            CommonValidator.validateNull(identifyingCode,new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_EMPTY));
            //判断验证码是否正确
            if (!AliUtils.isVerified(sysUserDTO.getMobile(), identifyingCode, bizId)) {
                throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
            }
        }
        Subject subject = SecurityUtils.getSubject();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            if (null != session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) {
                logger.info("登录用户" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                if (loginName.equals((session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString()))) {
                    // session.setTimeout(0); //这里就把session清除
                    logger.info(("删除用户seesion" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString()));
                    // session清除，
                    sessionDAO.delete(session);
                }
            }
        }
       /* AuthToken authToken = new AuthToken(loginName, password, LoginType.USER.toString());*/
       /* authToken.setRememberMe(req.getRememberMe().equals("1"));*/
        subject.login(new AuthToken(loginName, password, LoginType.USER.toString()));
        // 将seesion过期时间设置为8小时
        subject.getSession().setTimeout(ConstantFetchUtil.hour_8);
        AuthorizationInfo info = userRealm.doGetAuthorizationInfo(subject.getPrincipals());
        String permission = StringUtils.join(info.getStringPermissions(), ",");
        CookieUtil.addCookie(response, "permission", permission);
        CookieUtil.addCookie(response, "displayName", sysUserDTO.getDisplayName());
        sysUserBiz.updateLastLoginInfo(sysUserDTO.getId(), IpAdrressUtil.getIpAdrress(request));
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据登陆名，发送验证码", httpMethod = "GET", response = SendMsgResp.class, notes = "根据登陆名，发送验证码")
    @RequestMapping(value = "/sendcode")
    public Object sendCode(@ModelAttribute SendMsgReq req) throws ClientException {
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(req.getLoginname());
        if (sysUserDTOList.size() != 1) {
            throw new LoginException(LoginCodeEnum.LOGIN_USER_ILLEGAL);
        }
        if (!ValidateUtils.phoneValidate(sysUserDTOList.get(0).getMobile())) {
            throw new LoginException(LoginCodeEnum.LOGIN_MOBILE_ILLEGAL);
        }
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
