package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
@Controller
@RequestMapping("/eleproductcode")
public class EleProductCodeController extends BaseController {

    /**
     * 跳转到商品电子兑换码页面
     */
    @RequiresPermissions(PermissionConstant.ELE_CODE_MANAGEMENT)
    @RequestMapping(value = "/index")
    public String voucherCodeIndex() {
        return "eleproductcode/index";
    }
}
