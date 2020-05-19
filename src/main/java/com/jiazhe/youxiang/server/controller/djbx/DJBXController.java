/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller.djbx;

import com.alibaba.fastjson.JSONObject;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.server.adapter.DJBXAdapter;
import com.jiazhe.youxiang.server.biz.djbx.DJBXBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.DJBXCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.exceptions.DJBXException;
import com.jiazhe.youxiang.server.dto.djbx.AgentInfoDTO;
import com.jiazhe.youxiang.server.dto.djbx.PointsQueryDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.djbx.ExternalLoginReq;
import com.jiazhe.youxiang.server.vo.resp.djbx.AgentInfoResp;
import com.jiazhe.youxiang.server.vo.resp.djbx.PointsQueryResp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * @param req
     * @return
     */
    @AppApi
    @ApiOperation(value = "大家保险企业微信登录", httpMethod = "POST", response = AgentInfoResp.class, notes = "大家保险企业微信登录")
    @RequestMapping(value = "/externallogin", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "大家保险企业微信登录", level = LogLevelEnum.LEVEL_2)
    public Object externalLogin(@ModelAttribute ExternalLoginReq req) {
        LOGGER.error("HTTP调用[externalLogin]方法,参数为:{}", JSONObject.toJSON(req));
        // 参数校验
        CommonValidator.validateNull(req);
        if (!APP_VALUE.equals(req.getAppvalue())) {
            throw new DJBXException(DJBXCodeEnum.APPVALUE_ERROR);
        }
        if (StringUtils.isEmpty(req.getCode())) {
            throw new DJBXException(DJBXCodeEnum.CODE_IS_NULL);
        }
        AgentInfoDTO agentInfoDTO = djbxBiz.externalLogin(req.getAppvalue(), req.getCode());
        return ResponseFactory.buildResponse(DJBXAdapter.agentInfoDTO2Resp(agentInfoDTO));
    }

    @AppApi
    @ApiOperation(value = "查询剩余积分", httpMethod = "GET", response = PointsQueryResp.class, notes = "查询剩余积分")
    @RequestMapping(value = "/querypoints")
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "查询剩余积分", level = LogLevelEnum.LEVEL_1)
    public Object queryPoints(@RequestParam("agentCode") String agentCode) {
        PointsQueryDTO dto = djbxBiz.queryPoints(agentCode);
        return ResponseFactory.buildResponse(DJBXAdapter.PointQueryDTO2Resp(dto));
    }

}
