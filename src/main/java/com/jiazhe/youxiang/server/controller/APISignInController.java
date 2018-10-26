package com.jiazhe.youxiang.server.controller;

import com.aliyuncs.exceptions.ClientException;
import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.realm.AuthRealm;
import com.jiazhe.youxiang.base.util.*;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
/*import com.jiazhe.youxiang.server.domain.po.SysUserPO;*/
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
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

    /**
     * 验证用户名密码成功后，给员工发送短信
     * @param request
     * @param response
     * @throws IOException
     * @throws ClientException
     */
    @RequestMapping(value = "/sendsignincode")
    public void sendIdentifyingCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException {
        String code = "";
        String msg = "";
        JSONObject data = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByName(name);
        if (sysUserDTOList.size() == 1) {
            //根据用户名和密码判断，是否有该用户，有该用户，给该用户手机发验证短信
            SysUserDTO sysUserDTO = sysUserDTOList.get(0);
            String saltPassword = EncryptPasswordUtil.encrypt(sysUserDTO.getSalt(), password);
            if (saltPassword.equals(sysUserDTO.getPassword())) {
                //密码加密后一致
                if (!ValidateUtils.phoneValidate(sysUserDTO.getMobile())) {
                    //判断该用户的绑定的手机号是否合法
                    code = "000001";
                    msg = "您还没有绑定合法的手机号码，请联系后台管理员";
                } else {//合法发送验证码
                   /*SendSmsResponse res = AliUtils.sendMsg(sysUserPO.getMobile());
                    if (res.getCode() != null && res.getCode().equals("OK")) {
                        code = "000000";
                        msg = "发送验证码成功";
                        data.put("bizId", res.getBizId());
                        data.put("phone", sysUserPO.getMobile());
                    } else {
                        code = "000001";
                        msg = "发送短信失败，原因：" + res.getMessage();//发送失败的原因
                    }*/
                    code = "000000";
                    msg = "发送验证码成功";
                }
            } else {
                code = "000001";
                msg = "密码错误";
            }
        } else {
            code = "000001";
            msg = "用户不存在";
        }
        ResponseUtil.responseUtils(response, ResultPackage.resultPackage(code, data, msg));
    }

    /**
     * 后台用户通过验证码和密码登录
     * @param request
     * @param response
     * @throws IOException
     * @throws ClientException
     * @throws ParseException
     */
    @RequestMapping(value = "/signin")
    public void sigin(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException, ParseException {
        String code = "";
        String msg = "";
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String identifyingCode = request.getParameter("code");
        String bizId = request.getParameter("bizId");
        //短信验证码验证通过
        if (AliUtils.isVerified(phone, identifyingCode, bizId)) {
            Subject subject = SecurityUtils.getSubject();
            try {
                Collection<Session> sessions = sessionDAO.getActiveSessions();
                for (Session session : sessions) {
                    if (null != session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)) {
                        logger.info("登录用户" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                        SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                        SysUserDTO sysUserDTO = (SysUserDTO) simplePrincipalCollection.getPrimaryPrincipal();
                        if (name.equals(sysUserDTO.getLoginName())) {
                            // session.setTimeout(0); //这里就把session清除
                            logger.info(("删除用户seesion" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString()));
                            // session清除，
                            sessionDAO.delete(session);
                        }
                    }
                }
                subject.login(new UsernamePasswordToken(name, password));
                // 将seesion过期时间设置为8小时
                subject.getSession().setTimeout(ConstantFetchUtil.hour_8);
                AuthorizationInfo info = authRealm.doGetAuthorizationInfo(subject.getPrincipals());
                String permission = StringUtils.join(info.getStringPermissions(), ",");
                CookieUtil.addCookie(response,"permission",permission);
                CookieUtil.addCookie(response, "name", name);
                //更新最后登录时间
                SysUserDTO sysUserDTO = (SysUserDTO) subject.getPrincipals().getPrimaryPrincipal();
                sysUserBiz.updateLastLoginTime(sysUserDTO.getId());
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
