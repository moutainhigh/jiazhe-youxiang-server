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
import org.apache.shiro.authc.IncorrectCredentialsException;
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
import java.net.URLEncoder;
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
    @RequestMapping(value = "/usersignin")
    public Object signin(@ModelAttribute LoginReq req, HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException, ParseException {
        String loginName = req.getLoginname();
        String password = req.getPassword();
        String identifyingCode = req.getIdentifyingCode();
        String bizId = req.getBizId();
        //首先判断用户是否存在且唯一
        CommonValidator.validateNull(req.getLoginname(), new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        CommonValidator.validateNull(req.getPassword(), new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(loginName);
        if (sysUserDTOList.size() != 1) {
            throw new LoginException(LoginCodeEnum.LOGIN_USER_ILLEGAL);
        }
        SysUserDTO sysUserDTO = sysUserDTOList.get(0);
        //判断最后一次登陆ip是否一致，一致则直接登陆
        if (!sysUserDTO.getLastLoginIp().equals(IpAdrressUtil.getIpAdrress(request))) {
            //判断有没有短信bizId传过来
            CommonValidator.validateNull(bizId, new LoginException(LoginCodeEnum.LOGIN_DIFFERENT_CLIENT));
            CommonValidator.validateNull(identifyingCode, new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_EMPTY));
            //判断验证码是否正确
            if (!AliUtils.isVerified(sysUserDTO.getMobile(), identifyingCode, bizId)) {
                throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
            }
        }
        Subject subject = SecurityUtils.getSubject();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            if (null != session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) {
                Subject s = new Subject.Builder().session(session).buildSubject();
                SysUserDTO temp = (SysUserDTO) s.getPrincipal();
                if (loginName.equals(temp.getLoginName())) {
                    logger.info(("删除" + temp.getLoginName() + "session"));
                    sessionDAO.delete(session);
                }
            }
        }
        try {
            AuthToken authToken = new AuthToken(loginName, password, LoginType.USER.toString());
            /*authToken.setRememberMe(req.getRememberMe().equals("1"));*/
            subject.login(authToken);
        } catch (Exception e) {
            if (e instanceof IncorrectCredentialsException) {
                throw new LoginException(LoginCodeEnum.LOGIN_PASSWRLD_WRONG);
            }
        }
        // 将seesion过期时间设置为8小时
        subject.getSession().setTimeout(ConstantFetchUtil.hour_8);
        AuthorizationInfo info = userRealm.doGetAuthorizationInfo(subject.getPrincipals());
        String permission = StringUtils.join(info.getStringPermissions(), ",");
        CookieUtil.addCookie(response, "permission", permission);
        CookieUtil.addCookie(response, "displayName", URLEncoder.encode(sysUserDTO.getDisplayName(), "UTF-8"));
        sysUserBiz.updateLastLoginInfo(sysUserDTO.getId(), IpAdrressUtil.getIpAdrress(request));
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据登陆名，发送验证码", httpMethod = "GET", response = SendMsgResp.class, notes = "根据登陆名，发送验证码")
    @RequestMapping(value = "/usersendcode")
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
