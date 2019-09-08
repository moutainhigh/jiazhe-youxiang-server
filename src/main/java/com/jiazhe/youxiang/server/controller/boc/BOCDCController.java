/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller.boc;

import com.jiazhe.youxiang.base.util.BOCDCUtils;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.base.util.ShaUtils;
import com.jiazhe.youxiang.server.adapter.point.PointAdapter;
import com.jiazhe.youxiang.server.biz.point.PointExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.point.point.PointPageReq;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCQueryStockResp;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCDCReverseValueResp;
import com.jiazhe.youxiang.server.vo.resp.point.point.PointResp;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @version 1.0
 * @description 中行储蓄卡（BOCDC）controller
 * @created 2019-09-08 11:11
 */
@RestController
@RequestMapping("bocdc")
public class BOCDCController {

    @Autowired
    private PointExchangeCodeBiz pointExchangeCodeBiz;

    private static String[] bizCode = {"0000", "2222", "9999"};
    private static String[] bizDesc = {"交易成功", "商户返回已使用", "报文格式错误"};

    @ApiOperation(value = "中行储蓄卡获取可用码", httpMethod = "POST", response = BOCDCQueryStockResp.class, notes = "中行储蓄卡获取可用码")
    @RequestMapping(value = "/queryStock", method = RequestMethod.POST)
    public Object queryStock(@RequestParam("param") String param, @RequestParam("sign") String sign) {
        BOCDCQueryStockResp resp = new BOCDCQueryStockResp();
        if (!ShaUtils.getSha256(param).equals(sign)) {
            resp.setBizCode(bizCode[2]);
            resp.setBizDesc(bizDesc[2]);
        }
        String respStr = JacksonUtil.toJSon(resp);
        return BOCDCUtils.generateReturn(respStr);
    }

    @ApiOperation(value = "中行储蓄卡获取可用码", httpMethod = "POST", response = BOCDCReverseValueResp.class, notes = "中行储蓄卡获取可用码")
    @RequestMapping(value = "/reverseValue", method = RequestMethod.POST)
    public Object reverseValue(@RequestParam("param") String param, @RequestParam("sign") String sign) {
        BOCDCQueryStockResp resp = new BOCDCQueryStockResp();
        if (!ShaUtils.getSha256(param).equals(sign)) {

        }
        String respStr = JacksonUtil.toJSon(resp);
        return BOCDCUtils.generateReturn(respStr);
    }
}
