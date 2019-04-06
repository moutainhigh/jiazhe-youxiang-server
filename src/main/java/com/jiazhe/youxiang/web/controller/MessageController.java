package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tu
 * @description： 短信业务页面跳转
 * @date 2019-04-06
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    /**
     * 跳转到短信管理页面
     */
    @RequestMapping(value = "/index")
    public String orderIndex() {
        return "message/index";
    }
}
