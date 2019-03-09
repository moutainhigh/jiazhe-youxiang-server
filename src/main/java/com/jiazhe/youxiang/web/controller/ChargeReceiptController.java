package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tu
 * @description：充值凭证管理页面
 * @date 2019-03-09
 */
@Controller
@RequestMapping("/chargereceipt")
public class ChargeReceiptController extends BaseController {

    //@RequiresPermissions(PermissionConstant.POINT_CODE_MANAGEMENT)
    @RequestMapping(value = "/index")
    public String exchangeCodeIndex(String auditRecordId, Model model) {
        model.addAttribute("auditRecordId", auditRecordId);
        return "rechargeceipt/index";
    }

}
