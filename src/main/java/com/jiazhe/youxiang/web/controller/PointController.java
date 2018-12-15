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
 * @description
 * @date 2018/12/13.
 */
@Controller
@RequestMapping("/point")
public class PointController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeCardController.class);

    /**
     * 跳转到积分卡兑换码批次管理页面
     */
    @RequiresPermissions(PermissionConstant.POINT_BATCH_MANAGEMENT)
    @RequestMapping(value = "/exchangecodebatchindex")
    public String exchangeCodeBatchIndex() {
        return "point/pointexchangecodebatch/index";
    }

    /**
     * 跳转到积分卡兑换码页面
     */
    @RequiresPermissions(PermissionConstant.POINT_CODE_MANAGEMENT)
    @RequestMapping(value = "/exchangecodeindex")
    public String exchangeCodeIndex(String batchId, String batchName, Model model) {
        model.addAttribute("batchId", batchId);
        model.addAttribute("batchName", batchName);
        return "point/pointexchangecode/index";
    }
}
