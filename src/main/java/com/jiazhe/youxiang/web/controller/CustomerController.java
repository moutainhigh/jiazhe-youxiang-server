package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于客户管理相关页面的转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @RequiresPermissions(PermissionConstant.CUSTOMER_MANAGEMENT)
    @RequestMapping(value = "/index")
    public String index() {
        return "customer/index";
    }

    @RequestMapping(value = "/unregistered")
    public String unregistered() {
        return "customer/unregistered";
    }
}
