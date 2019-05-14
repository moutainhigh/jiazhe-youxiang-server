package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tu
 * @description：物料管理页面跳转
 * @date 2019-05-12
 */
@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController {

    /**
     * 跳转到物料管理的汇总页面
     * @return
     */
    @RequiresPermissions(PermissionConstant.MATERIAL_MANAGEMENT)
    @RequestMapping(value = "/summaryindex")
    public String summaryIndex() {
        return "material/summaryindex";
    }

    /**
     * 跳转到转账明细页面
     * @param payerIds
     * @param payeeId
     * @param model
     * @return
     */
    @RequiresPermissions(PermissionConstant.MATERIAL_TRANSFER_MONEY_DETAIL)
    @RequestMapping(value = "/index")
    public String index(String payerIds,String payeeId,Model model) {
        model.addAttribute("payerIds",payerIds);
        model.addAttribute("payeeId",payeeId);
        return "material/index";
    }
}
