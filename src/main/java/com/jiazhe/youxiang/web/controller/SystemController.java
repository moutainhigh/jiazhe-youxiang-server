package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/9/29
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    //进入登陆页面
    @RequestMapping(value = "/index")
    public String main() {
        return "login/index";
    }

    //系统退出
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login/index";
    }
}