/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller.djbx;

import com.jiazhe.youxiang.server.adapter.DJBXAdapter;
import com.jiazhe.youxiang.server.biz.djbx.DJBXBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.djbx.PointsQueryDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCCCResp;
import com.jiazhe.youxiang.server.vo.resp.djbx.PointsQueryResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-05-19
 */
@RestController
@RequestMapping("externalapi/djbx")
public class DJBXController {

    public static Logger LOGGER = LoggerFactory.getLogger(DJBXController.class);

    @Autowired
    private DJBXBiz djbxBiz;

    private final String APP_VALUE = "pointsshop";

    /**
     * 大家保险企业微信登录
     *
     * @param appvalue
     * @param code
     * @return
     */
    @AppApi
    @ApiOperation(value = "大家保险企业微信登录", response = BOCCCResp.class, notes = "大家保险企业微信登录")
    @RequestMapping(value = "/externallogin")
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "中行请求第三方退货", level = LogLevelEnum.LEVEL_3)
    public Object externalLogin(@RequestParam("appvalue") String appvalue, @RequestParam("code") String code) {
        LOGGER.error("HTTP调用[externalLogin]方法，appvalue:{},code:{}", appvalue, code);
        //TODO niexiao 参数校验
        djbxBiz.externalLogin(appvalue, code);


        return null;
    }

    @AppApi
    @ApiOperation(value = "查询剩余积分", httpMethod = "GET",response = PointsQueryResp.class, notes = "查询剩余积分")
    @RequestMapping(value = "/querypoints")
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "查询剩余积分", level = LogLevelEnum.LEVEL_1)
    public Object queryPoints(@RequestParam("agentCode") String agentCode) {
        PointsQueryDTO dto = djbxBiz.queryPoints(agentCode);
        return ResponseFactory.buildResponse(DJBXAdapter.PointQueryDTO2Resp(dto));
    }

}
