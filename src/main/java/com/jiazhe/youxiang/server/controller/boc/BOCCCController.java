package com.jiazhe.youxiang.server.controller.boc;

import com.alibaba.fastjson.JSONObject;
import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.biz.BOCCCBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCCCResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TU
 * @description 中行信用卡接口  Bank Of China Credit Card
 * @date 2019-09-02.
 */
@RestController
@RequestMapping("externalapi/boccc")
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
    @ApiOperation(value = "退货信息接口、退货验证【中行请求第三方退货】", response = BOCCCResp.class, notes = "退货信息接口、退货验证【中行请求第三方退货】")
    @RequestMapping(value = "/refundcheck")
    @CustomLog(moduleName = ModuleEnum.BOCCC, operate = "中行请求第三方退货", level = LogLevelEnum.LEVEL_3)
    public Object refundCheck(@RequestParam("data") String data) {
        LOGGER.error("HTTP调用[refundCheck]方法，参数:{}", data);
        BOCCCResp resp = bocccBiz.bocccRefundCheck(data);
        LOGGER.error("HTTP调用[refundCheck]方法，成功。返回:{}", JSONObject.toJSON(resp));
        return JSONObject.toJSON(resp);
    }

}
