package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户员工管理相关页面转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    /**
     * 跳转到用户管理页面
     * @return 页面
     */
    /*@RequiresPermissions("sysuser-index")*/
    @RequestMapping(value = "/index")
    public String main() {
        return "system/sysuser/index";
    }
}
