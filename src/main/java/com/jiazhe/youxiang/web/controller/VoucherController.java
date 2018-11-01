package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TU
 * @description 用于优惠券相关管理页面转发
 * @date 2018/10/13.
 */
@Controller
@RequestMapping("/voucher")
public class VoucherController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherController.class);
}
