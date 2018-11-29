/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.SysLogAdapter;
import com.jiazhe.youxiang.server.biz.SysLogBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.syslog.SysLogDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.syslog.SysLogListReq;
import com.jiazhe.youxiang.server.vo.resp.syslog.SysLogResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志管理Controller
 *
 * @author niexiao
 * @created 2018/10/17
 */
@RestController
@RequestMapping("api/syslog")
public class APISysLogController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysLogController.class);

    @Autowired
    private SysLogBiz sysLogBiz;

    /**
     * 查询日志信息
     *
     * @return
     */
    @AppApi
    @ApiOperation(value = "查询日志信息", httpMethod = "GET", response = SysLogResp.class, responseContainer = "List", notes = "查询日志信息")
    @RequestMapping(value = "getlist", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.LOG, operate = "日志查询", level = LogLevelEnum.LEVEL_1)
    public Object getList(@ModelAttribute SysLogListReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        //调用BIZ方法
        List<SysLogDTO> sysLogDTOList = sysLogBiz.getList(req.getModuleName(), req.getOperate(), req.getLevel(), req.getOperatorName(), req.getIp(), req.getStartTime(), req.getEndTime(), paging);
        //将DTO转成VO
        List<SysLogResp> result = sysLogDTOList.stream().map(SysLogAdapter::sysLogDTO2VO).collect(Collectors.toList());

        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }
}