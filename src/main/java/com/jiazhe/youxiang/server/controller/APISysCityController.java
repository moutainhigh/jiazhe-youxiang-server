/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.SysCityAdapter;
import com.jiazhe.youxiang.server.biz.SysCityBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.CityCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.exceptions.CityException;
import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.syscity.CityCodeReq;
import com.jiazhe.youxiang.server.vo.req.syscity.CityCodesReq;
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
    @CustomLog(moduleName = ModuleEnum.CITY,operate = "城市查询",level = LogLevelEnum.LEVEL_1)
    public Object getList(@ModelAttribute SysCityListReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        //调用BIZ方法
        List<SysCityDTO> sysCityDTOS = sysCityBiz.getList(req.getParentCode(),paging);
        //将DTO转成VO
        List<SysCityResp> result = sysCityDTOS.stream().map(SysCityAdapter::sysCityDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }

    /**
     * 获取已开通城市列表
     *
     * @return
     */
    @ApiOperation(value = "获取已开通城市列表", httpMethod = "GET", response = SysCityResp.class, responseContainer = "List", notes = "获取已开通城市列表")
    @RequestMapping(value = "getopenlist", method = RequestMethod.GET)
    public Object getOpenList() {
        //调用BIZ方法
        List<SysCityDTO> sysCityDTOS = sysCityBiz.getOpenList();
        //将DTO转成VO
        List<SysCityResp> result = sysCityDTOS.stream().map(SysCityAdapter::sysCityDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(result);
    }

    /**
     * 开通省份（其下级城市全部开通）
     *
     * @return
     */
    @ApiOperation(value = "开通城市（其下级城市全部开通）", httpMethod = "POST", notes = "开通省份（其下级城市全部开通）")
    @RequestMapping(value = "open", method = RequestMethod.POST)
    public Object open(@ModelAttribute CityCodeReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getCityCode(),new CityException(CityCodeEnum.CITY_CODE_IS_NULL));
        //调用BIZ方法
        sysCityBiz.open(req.getCityCode());
        //用ResponseFactory将返回值包装,简单的返回成功
        return ResponseFactory.buildSuccess();
    }

    /**
     * 关闭省份（其下级城市全部关闭）
     *
     * @return
     */
    @ApiOperation(value = "关闭城市（其下级城市全部关闭）", httpMethod = "POST", notes = "关闭省份（其下级城市全部关闭）")
    @RequestMapping(value = "close", method = RequestMethod.POST)
    public Object close(@ModelAttribute CityCodeReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getCityCode(),new CityException(CityCodeEnum.CITY_CODE_IS_NULL));
        //调用BIZ方法
        sysCityBiz.close(req.getCityCode());
        //用ResponseFactory将返回值包装,简单的返回成功
        return ResponseFactory.buildSuccess();
    }

    /**
     * 批量开通城市
     *
     * @return
     */
    @ApiOperation(value = "批量开通城市", httpMethod = "POST", notes = "批量开通城市")
    @RequestMapping(value = "batchopen", method = RequestMethod.POST)
    public Object batchOpen(@ModelAttribute CityCodesReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getCityCodes(),new CityException(CityCodeEnum.CITY_CODE_IS_NULL));
        //调用BIZ方法
        sysCityBiz.open(req.getCityCodes());
        //用ResponseFactory将返回值包装,简单的返回成功
        return ResponseFactory.buildSuccess();
    }

    /**
     * 批量关闭城市
     *
     * @return
     */
    @ApiOperation(value = "批量关闭城市", httpMethod = "POST", notes = "批量关闭城市")
    @RequestMapping(value = "batchclose", method = RequestMethod.POST)
    public Object batchClose(@ModelAttribute CityCodesReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getCityCodes(),new CityException(CityCodeEnum.CITY_CODE_IS_NULL));
        //调用BIZ方法
        sysCityBiz.close(req.getCityCodes());
        //用ResponseFactory将返回值包装,简单的返回成功
        return ResponseFactory.buildSuccess();
    }

}