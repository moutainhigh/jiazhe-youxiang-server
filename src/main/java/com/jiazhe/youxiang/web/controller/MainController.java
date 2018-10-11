/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
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
 * @created 2018/9/28
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/main")
    public String main() {
        return "main/main";
    }

    @RequestMapping(value = "/main_header")
    public String main_header() {
        return "main/main_header";
    }

    @RequestMapping(value = "/main_nav")
    public String main_nav() {
        return "main/main_nav";
    }

    @RequestMapping(value = "/main_center")
    public String main_center() {
        return "main/main_center";
    }
}