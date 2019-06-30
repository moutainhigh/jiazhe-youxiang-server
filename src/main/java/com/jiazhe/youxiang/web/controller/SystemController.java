package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于系统登录、退出、无权限相关页面转发
 *
 * @author niexiao
 * @created 2018/9/29
 */
@Controller
public class SystemController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    /**
     * 进入登陆页面
     */
    @RequestMapping(value = {"/system/index"})
    public String main() {
        return "login/index";
    }

    /**
     * 系统退出
     */
    @RequestMapping("/system/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login/index";
    }

    /**
     * 无权限
     */
    @RequestMapping("/system/403")
    public String noPermission() {
        return "error/403";
    }

}