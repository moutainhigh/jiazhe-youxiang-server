package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TU
 * @description  用于各类信息查询
 * @date 2018/11/2.
 */
@Controller
@RequestMapping("/infosearch")
public class InfoSerach extends BaseController {

    //跳转到充值卡兑换码查询页面
    @RequestMapping(value = "/rechargecardcodeindex")
    public String rechargeCardCodeIndex() {
        return "infosearch/rechargecardcodeindex";
    }

    //跳转到充值卡查询页面
    @RequestMapping(value = "/rechargecardindex")
    public String rechargeCardIndex() {
        return "infosearch/rechargecardindex";
    }

    //跳转到代金券兑换码查询页面
    @RequestMapping(value = "/vouchercodeindex")
    public String voucherCodeIndex() {
        return "infosearch/vouchercodeindex";
    }

    //跳转到代金券查询页面
    @RequestMapping(value = "/voucherindex")
    public String voucherIndex() {
        return "infosearch/voucherindex";
    }
}
