package com.jiazhe.youxiang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tu
 * @description：跳转到商家订单记录
 * @date 2018/12/9
 */

@Controller
@RequestMapping("/partnerorder")
public class PartnerOrderController {

    //跳转到商家订单记录
    @RequestMapping(value = "/index")
    public String index() {
        return "partnerorder/index";
    }
}
