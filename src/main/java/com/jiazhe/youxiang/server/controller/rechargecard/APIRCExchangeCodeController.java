package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBiz;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.CustomerSelfChargeReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.ExpiryTimeEditReq;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tu
 * @description：充值卡兑换码【rc是rechargecard缩写】
 * @date 2018/10/20
 */
@RestController
@RequestMapping("api/rcexchangecode")
public class APIRCExchangeCodeController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(APIRCExchangeCodeController.class);

    @Autowired
    private RCExchangeCodeBiz rcExchangeCodeBiz;

    @ApiOperation(value = "startusing", httpMethod = "POST",notes = "启用充值卡兑换码")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        rcExchangeCodeBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "stopusing", httpMethod = "POST",notes = "停用充值卡兑换码")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        rcExchangeCodeBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "changeexpirytime", httpMethod = "POST",notes = "修改充值卡兑换码过期时间")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        rcExchangeCodeBiz.changeExpiryTime(req.getId(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "customerselfcodecharge", httpMethod = "POST",notes = "客户用兑换码自行充值")
    @RequestMapping(value = "/customerselfcodecharge", method = RequestMethod.POST)
    public Object customerSelfCodeCharge(@ModelAttribute CustomerSelfChargeReq req) {
        //参数检查
        rcExchangeCodeBiz.customerSelfCharge(req.getCustomerId(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "backstagecodecharge", httpMethod = "POST",notes = "后台用兑换码进行充值")
    @RequestMapping(value = "/backstagecodecharge", method = RequestMethod.POST)
    public Object customerSelfCharge(@ModelAttribute CustomerSelfChargeReq req) {
        //参数检查
        rcExchangeCodeBiz.backstageCodeCharge(req.getCustomerId(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

}
