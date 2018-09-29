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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/9/29
 */
@Controller
public class SystemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    @RequestMapping("/system/department/index")
    public String department(String username, String password, HttpSession session, HttpServletResponse response) throws IOException {
        LOGGER.info("department");
        return "/system/department/index";
    }
}