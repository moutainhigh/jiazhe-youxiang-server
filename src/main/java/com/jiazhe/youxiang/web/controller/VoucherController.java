package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     * 跳转到代金券兑换码批次管理页面
     */
    @RequiresPermissions(PermissionConstant.VOUCHER_BATCH_MANAGEMENT)
    @RequestMapping(value = "/exchangecodebatchindex")
    public String exchangeCodeBatchIndex() {
        return "voucher/voucherexchangecodebatch/index";
    }

    /**
     * 跳转到充值卡兑换码页面
     */
    @RequiresPermissions(PermissionConstant.VOUCHER_CODE_MANAGEMENT)
    @RequestMapping(value = "/exchangecodeindex")
    public String exchangeCodeIndex(String batchId, String batchName, Model model) {
        model.addAttribute("batchId", batchId);
        model.addAttribute("batchName", batchName);
        return "voucher/voucherexchangecode/index";
    }
}
