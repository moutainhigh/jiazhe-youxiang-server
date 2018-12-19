package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于项目管理相关页面的转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    /**
     * 项目管理页面跳转
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PROJECT_MANAGEMENT)
    @RequestMapping(value = "/index")
    public String index() {
        return "project/index";
    }
}
