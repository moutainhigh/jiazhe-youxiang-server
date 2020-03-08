/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tu
 * @version 1.0
 * @description 核销页面
 * @created 2020-03-07 20:37
 */
@Controller
@RequestMapping("/chargeoff")
public class ChargeOffController extends BaseController{

    @RequestMapping(value = "/index")
    public String index() {
        return "chargeoff/index";
    }
}
