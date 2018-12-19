package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TU
 * @description 用于各类信息查询
 * @date 2018/11/2.
 */
@Controller
@RequestMapping("/infosearch")
public class InfoSerachController extends BaseController {

    /**
     * 跳转到充值卡兑换码查询页面
     */
    @RequiresPermissions(PermissionConstant.RECHARGE_CARD_CODE_SEARCH)
    @RequestMapping(value = "/rechargecardcodeindex")
    public String rechargeCardCodeIndex() {
        return "infosearch/rechargecardcodeindex";
    }

    /**
     * 跳转到充值卡查询页面
     */
    @RequiresPermissions(value = {PermissionConstant.RECHARGE_CARD_SEARCH, PermissionConstant.CUSTOMER_RECHARGE_CARD_DETAIL}, logical = Logical.OR)
    @RequestMapping(value = "/rechargecardindex")
    public String rechargeCardIndex(String mobile, Model model) {
        model.addAttribute("mobile", mobile);
        return "infosearch/rechargecardindex";
    }

    /**
     * 跳转到积分卡兑换码查询页面
     */
    @RequiresPermissions(PermissionConstant.POINT_CODE_SEARCH)
    @RequestMapping(value = "/pointcodeindex")
    public String pointCodeIndex() {
        return "infosearch/pointcodeindex";
    }

    /**
     * 跳转到积分卡查询页面
     */
    @RequiresPermissions(value = {PermissionConstant.POINT_SEARCH, PermissionConstant.CUSTOMER_POINT_DETAIL}, logical = Logical.OR)
    @RequestMapping(value = "/pointindex")
    public String pointIndex(String mobile, Model model) {
        model.addAttribute("mobile", mobile);
        return "infosearch/pointindex";
    }

    /**
     * 跳转到代金券兑换码查询页面
     */
    @RequiresPermissions(PermissionConstant.VOUCHER_CODE_SEARCH)
    @RequestMapping(value = "/vouchercodeindex")
    public String voucherCodeIndex() {
        return "infosearch/vouchercodeindex";
    }

    /**
     * 跳转到代金券查询页面
     */
    @RequiresPermissions(value = {PermissionConstant.VOUCHER_SEARCH, PermissionConstant.CUSTOMER_VOUCHER_DETAIL}, logical = Logical.OR)
    @RequestMapping(value = "/voucherindex")
    public String voucherIndex(String mobile, Model model) {
        model.addAttribute("mobile", mobile);
        return "infosearch/voucherindex";
    }
}
