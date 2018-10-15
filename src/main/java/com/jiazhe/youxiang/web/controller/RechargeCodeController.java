package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于充值卡兑换码管理相关页面转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/rechargecode")
public class RechargeCodeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeCodeController.class);
}
