package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tu
 * @description：跳转到商家订单记录
 * @date 2018/12/9
 */

@Controller
@RequestMapping("/partnerorder")
public class PartnerOrderController extends BaseController {
    /**
     * 跳转到商家订单记录
     */
    @RequiresPermissions(PermissionConstant.PARTNER_ORDER_MANAGEMENT)
    @RequestMapping(value = "/index")
    public String index() {
        return "partnerorder/index";
    }
}
