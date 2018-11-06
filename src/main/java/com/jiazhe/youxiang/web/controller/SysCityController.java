package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于城市管理相关页面转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/syscity")
public class SysCityController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysCityController.class);

    @RequestMapping(value = "/index")
    public String index() {
        return "system/syscity/index";
    }
}
