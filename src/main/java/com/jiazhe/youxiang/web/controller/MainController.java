package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于首页iframe每个部分页面的转发
 *
 * @author niexiao
 * @created 2018/9/28
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequiresAuthentication
    @RequestMapping(value = "/main")
    public String main() {
        return "main/main";
    }

    @RequiresAuthentication
    @RequestMapping(value = "/main_header")
    public String main_header() {
        return "main/main_header";
    }

    @RequiresAuthentication
    @RequestMapping(value = "/main_nav")
    public String main_nav() {
        return "main/main_nav";
    }

    @RequiresAuthentication
    @RequestMapping(value = "/main_center")
    public String main_center() {
        return "main/main_center";
    }
}