package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.realm.AuthToken;
import com.jiazhe.youxiang.base.realm.UserRealm;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.CookieUtil;
import com.jiazhe.youxiang.base.util.EncryptPasswordUtil;
import com.jiazhe.youxiang.base.util.IpAdrressUtil;
import com.jiazhe.youxiang.base.util.MsgUtils;
import com.jiazhe.youxiang.base.util.RandomUtil;
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
import com.jiazhe.youxiang.server.vo.resp.login.SendVerificationCodeResp;
import com.jiazhe.youxiang.server.vo.resp.login.SessionResp;
import io.swagger.annotations.ApiOperation;
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

    /**
     * 员工cookie失效时间8小时【以秒计】
     */
    private static final int USER_COOKIE_EXPIRY = 8 * 60 * 60;
    /**
     * 客户cookie失效时间1年【以秒计】
     */
    private static final int CUSTOMER_COOKIE_EXPIRY = 365 * 24 * 60 * 60;

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
    public Object userSignIn(@ModelAttribute UserLoginReq req, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loginName = req.getLoginname();
        String password = req.getPassword();
        String identifyingCode = req.getIdentifyingCode();
        String bizId = req.getBizId();
        boolean rememberMe = "true".equals(req.getRememberMe());
        CommonValidator.validateNull(req.getLoginname(), new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        CommonValidator.validateNull(req.getPassword(), new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(loginName);
        if (sysUserDTOList.size() != 1) {
            throw new LoginException(LoginCodeEnum.LOGIN_USER_ILLEGAL);
        }
        SysUserDTO sysUserDTO = sysUserDTOList.get(0);
        //前置判断一下密码是否正确，以免发了验证码再告诉密码错误，不符合思维逻辑
        if (!sysUserDTO.getPassword().equals(EncryptPasswordUtil.encrypt(sysUserDTO.getSalt(), req.getPassword()))) {
            throw new LoginException(LoginCodeEnum.LOGIN_PASSWRLD_WRONG);
        }
        // 判断白名单里是否有该ip，没有发验证码
        //TODO niexiao 记得放开注释
//        if (!IpAdrressUtil.ipIsWhite(IpAdrressUtil.getIpAddress(request), sysUserDTO.getLastLoginIp())) {
//            //判断有没有短信bizId传过来
//            if(Strings.isEmpty(req.getIdentifyingCode())){
//                CommonValidator.validateNull(bizId, new LoginException(LoginCodeEnum.LOGIN_DIFFERENT_CLIENT));
//            }
//            CommonValidator.validateNull(identifyingCode, new LoginException(LoginCodeEnum.LOGIN_IDENTIFYING_CODE_EMPTY));
//            //判断验证码是否正确
//            MsgUtils.isVerified(sysUserDTO.getMobile(), identifyingCode);
//            logger.info("登陆ip为：" + IpAdrressUtil.getIpAddress(request));
//            sysUserBiz.updateLastLoginInfo(sysUserDTO.getId(), IpAdrressUtil.getIpAddress(request));
//        }
        logger.info("登陆ip为：" + IpAdrressUtil.getIpAddress(request));
        sysUserBiz.updateLastLoginInfo(sysUserDTO.getId(), IpAdrressUtil.getIpAddress(request));
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
        int userCookieExpiry = rememberMe ? USER_COOKIE_EXPIRY : -1;
        CookieUtil.addCookie(response, "permission", permission, userCookieExpiry);
        CookieUtil.addCookie(response, "displayName", URLEncoder.encode(sysUserDTO.getDisplayName(), "UTF-8"), userCookieExpiry);
        CookieUtil.addCookie(response, "JSESSIONID", sessionResp.getSessionId(), userCookieExpiry);
        return ResponseFactory.buildResponse(sessionResp);
    }

    @ApiOperation(value = "根据登陆名，发送验证码", httpMethod = "GET", response = SendVerificationCodeResp.class, notes = "根据登陆名，发送验证码")
    @RequestMapping(value = "/usersendcode")
    @CustomLog(moduleName = ModuleEnum.REGISTER, operate = "根据登陆名，发送验证码", level = LogLevelEnum.LEVEL_2)
    public Object userSendCode(@ModelAttribute SendMsgToUserReq req) {
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(req.getLoginname());
        if (sysUserDTOList.size() != 1) {
            throw new LoginException(LoginCodeEnum.LOGIN_USER_ILLEGAL);
        }
        CommonValidator.validateMobile(sysUserDTOList.get(0).getMobile(), new LoginException(LoginCodeEnum.LOGIN_MOBILE_ILLEGAL));
        String code = RandomUtil.generateVerifyCode(CommonConstant.VER_CODE_LENGTH);
        SendVerificationCodeResp sendMsgResp = MsgUtils.sendVerificationCodeMsg(sysUserDTOList.get(0).getMobile(), code);
        return ResponseFactory.buildResponse(sendMsgResp);

    }

    @AppApi
    @ApiOperation(value = "客户登录", httpMethod = "GET", response = CustomerLoginResp.class, notes = "客户登录")
    @RequestMapping(value = "/customersignin")
    @CustomLog(moduleName = ModuleEnum.REGISTER, operate = "客户登录", level = LogLevelEnum.LEVEL_2)
    public Object customerSignIn(@ModelAttribute CustomerLoginReq req, HttpServletResponse response) {
        String mobile = req.getMobile();
        String identifyingCode = req.getIdentifyingCode();
        CommonValidator.validateNull(req.getMobile(), new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        CommonValidator.validateNull(req.getIdentifyingCode(), new LoginException(LoginCodeEnum.LOGIN_LOGININFO_INCOMPLETE));
        CommonValidator.validateMobile(req.getMobile(), new LoginException(LoginCodeEnum.LOGIN_MOBILE_ILLEGAL));
        MsgUtils.isVerified(req.getMobile(), identifyingCode);
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
        CookieUtil.addCookie(response, "JSESSIONID", subject.getSession().getId().toString(), CUSTOMER_COOKIE_EXPIRY);
        customerLoginResp.setSessionId(subject.getSession().getId().toString());
        customerLoginResp.setCustomerId(customerDTO.getId());
        customerLoginResp.setCustomerMobile(customerDTO.getMobile());
        customerLoginResp.setCustomerName(customerDTO.getName());
        return ResponseFactory.buildResponse(customerLoginResp);
    }

    @AppApi
    @ApiOperation(value = "根据电话号码，发送验证码", httpMethod = "GET", notes = "根据电话号码，发送验证码")
    @RequestMapping(value = "/customersendcode")
    @CustomLog(moduleName = ModuleEnum.REGISTER, operate = "根据电话号码，发送验证码", level = LogLevelEnum.LEVEL_2)
    public Object customerSendCode(@ModelAttribute SendMsgToCustomerReq req) {
        CommonValidator.validateMobile(req.getMobile(), new LoginException(LoginCodeEnum.LOGIN_MOBILE_ILLEGAL));
        String code = RandomUtil.generateVerifyCode(CommonConstant.VER_CODE_LENGTH);
        SendVerificationCodeResp sendMsgResp = MsgUtils.sendVerificationCodeMsg(req.getMobile(), code);
        return ResponseFactory.buildResponse(sendMsgResp);
    }
}
