/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.web.controller;

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

    @RequestMapping(value = "/index")
    public String index() {
        return "system/syslog/index";
    }
}