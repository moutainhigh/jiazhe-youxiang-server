/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/11/5
 */
@Controller
@RequestMapping("/syslog")
public class SysLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    //跳转到用户管理页面
    @RequestMapping(value = "/index")
    public String main() {
        return "system/syslog/index";
    }
}