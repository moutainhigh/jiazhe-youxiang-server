package com.jiazhe.youxiang.server.controller.boc;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.alibaba.fastjson.JSONObject;
import com.jiazhe.youxiang.server.biz.BOCCCBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCCCResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author TU
 * @description 中行信用卡接口  Bank Of China Credit Card
 * @date 2019-09-02.
 */
@RestController
@RequestMapping("boccc")
public class BOCCCController extends BaseController {

    public static Logger LOGGER = LoggerFactory.getLogger(BOCCCController.class);

    @Autowired
    private BOCCCBiz bocccBiz;

    /**
     * 中行信用卡退货前请求
     *
     * @param data
     * @return
     */
    @AppApi
    @ApiOperation(value = "退货信息接口、退货验证", httpMethod = "POST", response = BOCCCResp.class, notes = "退货信息接口、退货验证")
    @RequestMapping(value = "/refundcheck", method = RequestMethod.POST)
    public Object refundCheck(@RequestParam("data") String data) {
        LOGGER.error("HTTP调用[refundCheck]方法，参数:{}", data);
        BOCCCResp resp = bocccBiz.bocccRefundCheck(data);
        LOGGER.error("HTTP调用[refundCheck]方法，成功。返回:{}", JSONObject.toJSON(resp));
        return JSONObject.toJSON(resp);
    }

}
