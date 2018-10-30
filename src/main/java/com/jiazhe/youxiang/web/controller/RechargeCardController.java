package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@author TU
 * @description 用于充值卡相关管理页面转发
 * @date 2018/10/13.
 */
@Controller
@RequestMapping("/rechargecard")
public class RechargeCardController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeCardController.class);

    //跳转到充值卡批次管理页面
    @RequestMapping(value = "/exchangecodebatchindex")
    public String main() {
        return "rc/rcexchangecodebatch/index";
    }
}
