package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于角色管理相关页面转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/sysrole")
public class SysRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

    //跳转到角色管理页面
    @RequiresPermissions("sysrole:index")
    @RequestMapping(value = "/index")
    public String main() {
        return "system/sysrole/index";
    }
}
