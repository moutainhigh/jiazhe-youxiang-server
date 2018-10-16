/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.SysCityAdapter;
import com.jiazhe.youxiang.server.biz.SysCityBiz;
import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.syscity.OpenCityReq;
import com.jiazhe.youxiang.server.vo.req.syscity.SysCityListReq;
import com.jiazhe.youxiang.server.vo.resp.syscity.SysCityResp;
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
 * 城市管理Controller
 *
 * @author niexiao
 * @created 2018/10/16
 */
@RestController
@RequestMapping("api/syscity")
public class APISysCityController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysCityController.class);

    @Autowired
    private SysCityBiz sysCityBiz;

    /**
     * 获取城市列表信息
     *
     * @return
     */
    @ApiOperation(value = "获取城市列表信息", httpMethod = "GET", response = SysCityResp.class, responseContainer = "List", notes = "获取城市列表信息")
    @RequestMapping(value = "getlist", method = RequestMethod.GET)
    public Object getList(@ModelAttribute SysCityListReq req) {
        //TODO niexiao 参数验证
        if (null == req) {
            req = new SysCityListReq();
        }
        //调用BIZ方法
        List<SysCityDTO> sysCityDTOS = sysCityBiz.getList(req.getParentCode());
        //将DTO转成VO
        List<SysCityResp> result = sysCityDTOS.stream().map(SysCityAdapter::sysCityDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(result);
    }

    /**
     * 获取城市列表信息
     *
     * @return
     */
    @ApiOperation(value = "开通城市", httpMethod = "POST", notes = "开通城市")
    @RequestMapping(value = "opencity", method = RequestMethod.POST)
    public Object openCity(@ModelAttribute OpenCityReq req) {
        //TODO niexiao 参数验证
        //调用BIZ方法
        sysCityBiz.openCity(req.getCityIds());
        //用ResponseFactory将返回值包装,简单的返回成功
        return ResponseFactory.buildSuccess();
    }

}