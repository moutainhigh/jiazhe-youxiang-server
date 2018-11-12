package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TU
 * @description 订单相关页面转发
 * @date 2018/11/2.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{

    //跳转到充订单管理页面
    @RequestMapping(value = "/orderindex")
    public String orderIndex(String mobile,Model model) {
        model.addAttribute("mobile",mobile);
        return "order/index";
    }
}
