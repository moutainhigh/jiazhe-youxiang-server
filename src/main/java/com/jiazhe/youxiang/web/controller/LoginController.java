/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.web.controller;

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
public class LoginController {

    @RequestMapping("/index")
    public String index(){
        return "login/index";
    }

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