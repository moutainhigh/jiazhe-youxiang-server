/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/9/28
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    //系统登陆
    @RequestMapping("/login")
    public String login() {
        return "login/index";
    }

    //系统退出
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login/index";
    }

    /*无权限跳转*/
    @RequestMapping("/403")
    public String noPermission(){
        return "error/403";
    }
}