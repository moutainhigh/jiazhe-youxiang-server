package com.jiazhe.youxiang.server.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.realm.AuthToken;
import com.jiazhe.youxiang.base.realm.UserRealm;
import com.jiazhe.youxiang.base.util.*;
import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LoginType;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.login.CustomerLoginReq;
import com.jiazhe.youxiang.server.vo.req.login.SendMsgToCustomerReq;
import com.jiazhe.youxiang.server.vo.req.login.SendMsgToUserReq;
import com.jiazhe.youxiang.server.vo.req.login.UserLoginReq;
import com.jiazhe.youxiang.server.vo.resp.login.CustomerLoginResp;
import com.jiazhe.youxiang.server.vo.resp.login.SendMsgResp;
import com.jiazhe.youxiang.server.vo.resp.login.SessionResp;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationInfo;
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
    @Autowired
    private CustomerBiz customerBiz;

    @ApiOperation(value = "员工登录", httpMethod = "GET", response = SessionResp.class, notes = "员工登录")
    @RequestMapping(value = "/usersignin")
    @CustomLog(moduleName = ModuleEnum.REGISTER, operate = "员工登录", level = LogLevelEnum.LEVEL_2)
    public Object userSignin(@ModelAttribute UserLoginReq req, HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException {
        String loginName = req.getLoginname();
        String password = req.getPassword();
        String identifyingCode = req.getIdentifyingCode();
        String bizId = req.getBizId();
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
                if (s.getPrincipal() instanceof SysUserDTO) {
                    SysUserDTO temp = (SysUserDTO) s.getPrincipal();
                    if (loginName.equals(temp.getLoginName())) {
                        //CookieUtil.getUid(request, "JSESSIONID"))可能为null，所以注意equals方法的比较顺序
                        if (!session.getId().equals(CookieUtil.getUid(request, "JSESSIONID"))) {
                            logger.info(("删除员工" + temp.getLoginName() + "的登陆session"));
                            sessionDAO.delete(session);
                        }
                    }
                }
            }
        }
        try {
            AuthToken authToken = new AuthToken(loginName, password, LoginType.USER.toString());
            subject.login(authToken);
        } catch (Exception e) {
            if (e instanceof IncorrectCredentialsException) {
                throw new LoginException(LoginCodeEnum.LOGIN_PASSWRLD_WRONG);
            }
        }
        SessionResp sessionResp = new SessionResp();
        subject.getSession().setTimeout(CommonConstant.EIGHT_HOUR);
        sessionResp.setSessionId(subject.getSession().getId().toString());
        AuthorizationInfo info = userRealm.doGetAuthorizationInfo(subject.getPrincipals());
        String permission = StringUtils.join(info.getStringPermissions(), "#");
        CookieUtil.addCookie(response, "permission", permission);
        CookieUtil.addCookie(response, "displayName", URLEncoder.encode(sysUserDTO.getDisplayName(), "UTF-8"));
        logger.info("登陆ip为：" + IpAdrressUtil.getIpAdrress(request));
        sysUserBiz.updateLastLoginInfo(sysUserDTO.getId(), IpAdrressUtil.getIpAdrress(request));
        return ResponseFactory.buildResponse(sessionResp);
    }

    @ApiOperation(value = "根据登陆名，发送验证码", httpMethod = "GET", response = SendMsgResp.class, notes = "根据登陆名，发送验证码")
    @RequestMapping(value = "/usersendcode")
    @CustomLog(moduleName = ModuleEnum.REGISTER, operate = "根据登陆名，发送验证码", level = LogLevelEnum.LEVEL_2)
    public Object userSendCode(@ModelAttribute SendMsgToUserReq req) throws ClientException {
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(req.getLoginname());
        if (sysUserDTOList.size() != 1) {
            throw new LoginException(LoginCodeEnum.LOGIN_USER_ILLEGAL);
        }
        CommonValidator.validateMobile(sysUserDTOList.get(0).getMobile(),new LoginException(LoginCodeEnum.LOGIN_MOBILE_ILLEGAL));
        SendSmsResponse res = AliUtils.sendMsg(sysUserDTOList.get(0).getMobile());
        SendMsgResp sendMsgResp = new SendMsgResp();
        sendMsgResp.setBizId(res.getBizId());
        return ResponseFactory.buildResponse(sendMsgResp);
    }

    @AppApi
    @ApiOperation(value = "客户登录", httpMethod = "GET", response = CustomerLoginResp.class, notes = "客户登录")
    @RequestMapping(value = "/customersignin")
    @CustomLog(moduleName = ModuleEnum.REGISTER, operate = "客户登录", level = LogLevelEnum.LEVEL_2)
    public Object customerSignin(@ModelAttribute CustomerLoginReq req,HttpServletRequest request) throws IOException, ClientException {
        String mobile = req.getMobile();
        String identifyingCode = req.getIdentifyingCode();
        String bizId = req.getBizId();
        CommonValidator.validateNull(req.getMobile(), new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        CommonValidator.validateNull(req.getIdentifyingCode(), new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        CommonValidator.validateMobile(req.getMobile(),new LoginException(LoginCodeEnum.LOGIN_MOBILE_ILLEGAL));
        //判断验证码是否正确
        if (!AliUtils.isVerified(req.getMobile(), identifyingCode, bizId)) {
            throw new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_ERROR);
        }
        CustomerDTO customerDTO = customerBiz.getByMobile(mobile);
        if (null == customerDTO) {
            CustomerAddDTO customerAddDTO = new CustomerAddDTO();
            customerAddDTO.setMobile(req.getMobile());
            customerBiz.add(customerAddDTO);
            customerDTO = customerBiz.getByMobile(mobile);
        }
        Subject subject = SecurityUtils.getSubject();
//        Collection<Session> sessions = sessionDAO.getActiveSessions();
//        for (Session session : sessions) {
//            if (null != session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) {
//                Subject s = new Subject.Builder().session(session).buildSubject();
//                if (s.getPrincipal() instanceof CustomerDTO) {
//                    CustomerDTO temp = (CustomerDTO) s.getPrincipal();
//                    if (mobile.equals(temp.getMobile())) {
//                        if (!session.getId().equals(CookieUtil.getUid(request, "JSESSIONID"))) {
//                            logger.info(("删除客户" + temp.getMobile() + "的登陆session"));
//                            sessionDAO.delete(session);
//                        }
//                    }
//                }
//            }
//        }
        try {
            AuthToken authToken = new AuthToken(mobile, "", LoginType.CUSTOMER.toString());
            subject.login(authToken);
        } catch (Exception e) {
            if (e instanceof IncorrectCredentialsException) {
                throw new LoginException(LoginCodeEnum.LOGIN_PASSWRLD_WRONG);
            }
        }
        CustomerLoginResp customerLoginResp = new CustomerLoginResp();
        subject.getSession().setTimeout(CommonConstant.NEVER);
        customerLoginResp.setSessionId(subject.getSession().getId().toString());
        customerLoginResp.setCustomerId(customerDTO.getId());
        customerLoginResp.setCustomerMobile(customerDTO.getMobile());
        customerLoginResp.setCustomerName(customerDTO.getName());
        return ResponseFactory.buildResponse(customerLoginResp);
    }

    @AppApi
    @ApiOperation(value = "根据电话号码，发送验证码", httpMethod = "GET", response = SendMsgResp.class, notes = "根据电话号码，发送验证码")
    @RequestMapping(value = "/customersendcode")
    @CustomLog(moduleName = ModuleEnum.REGISTER, operate = "根据电话号码，发送验证码", level = LogLevelEnum.LEVEL_2)
    public Object customerSendCode(@ModelAttribute SendMsgToCustomerReq req) throws ClientException {
        CommonValidator.validateMobile(req.getMobile(),new LoginException(LoginCodeEnum.LOGIN_MOBILE_ILLEGAL));
        SendSmsResponse res = AliUtils.sendMsg(req.getMobile());
        SendMsgResp sendMsgResp = new SendMsgResp();
        sendMsgResp.setBizId(res.getBizId());
        return ResponseFactory.buildResponse(sendMsgResp);
    }
}
