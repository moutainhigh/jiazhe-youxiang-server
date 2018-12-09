package com.jiazhe.youxiang.server.controller.order;

import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.WeChatPayConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.WeChatUnifiedOrderReq;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author tu
 * @description：微信支付相关的api
 * @date 2018/12/9
 */

@RestController
@RequestMapping("api/wxpay")
public class WeChatPayController {

    @AppApi
    @ApiOperation(value = "微信统一下单", httpMethod = "POST", notes = "微信统一下单")
    @RequestMapping(value = "/unifiedorder", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "微信统一下单", level = LogLevelEnum.LEVEL_1)
    public Object unifiedOrder(@ModelAttribute WeChatUnifiedOrderReq req) {
        SortedMap<Object, Object> wxparam = new TreeMap<Object, Object>();
        wxparam.put("appid", WeChatPayConstant.APP_ID);
        wxparam.put("mch_id",WeChatPayConstant.MCH_ID);
        wxparam.put("nonce_str", RandomUtil.generateVerifyCode(20));
        wxparam.put("body", req.getBody());
        return ResponseFactory.buildSuccess();
    }



}
