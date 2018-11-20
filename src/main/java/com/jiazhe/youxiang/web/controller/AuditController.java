package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于审核管理功能模块相关页面的转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/auditrecord")
public class AuditController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditController.class);

    @RequestMapping(value = "/index")
    public String index(Integer status,Model model) {
        model.addAttribute("status",status);
        return "auditrecord/index";
    }
}
