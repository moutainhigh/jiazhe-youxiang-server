/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.biz.ProductBiz;
import com.jiazhe.youxiang.server.dto.product.ProductTypeDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductTypeListReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductTypeUpdateReq;
import com.jiazhe.youxiang.server.vo.resp.product.ProductTypeResp;
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
 * 商品管理Contorller
 *
 * @author niexiao
 * @created 2018/10/18
 */
@RestController
@RequestMapping("api/product")
public class APIProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIProductController.class);

    @Autowired
    private ProductBiz productBiz;

    /*************商品分类相关******************/

    /**
     * 获得某一商品分类
     *
     * @return
     */
    @ApiOperation(value = "获得商品分类", httpMethod = "GET", response = ProductTypeResp.class, notes = "获得商品分类")
    @RequestMapping(value = "getTypeById", method = RequestMethod.GET)
    public Object getTypeById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        ProductTypeDTO productTypeDTO = ProductBiz.getTypeById(req.getId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(ProductAdapter.productTypeDTO2VO(productTypeDTO));
    }

    /**
     * 查询商品分类列表
     *
     * @return
     */
    @ApiOperation(value = "查询商品分类列表", httpMethod = "GET", response = ProductTypeResp.class, responseContainer = "List", notes = "查询商品分类列表")
    @RequestMapping(value = "gettypelist", method = RequestMethod.GET)
    public Object getTypeList(@ModelAttribute ProductTypeListReq req) {
        //TODO niexiao 参数验证
        Paging paging = new Paging();
        paging.setOffset(req.getOffset());
        paging.setLimit(req.getLimit());
        //调用BIZ方法
        List<ProductTypeDTO> productTypeDTOList = ProductBiz.getTypeList(req.getName(), paging);
        //将DTO转成VO
        List<ProductTypeResp> result = productTypeDTOList.stream().map(ProductAdapter::productTypeDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }

    /**
     * 编辑商品分类
     *
     * @return
     */
    @ApiOperation(value = "编辑商品分类", httpMethod = "POST", notes = "编辑商品分类")
    @RequestMapping(value = "updatetype", method = RequestMethod.POST)
    public Object updateType(@ModelAttribute ProductTypeUpdateReq req) {
        //TODO niexiao 参数验证
        CommonValidator.validate(req);
        CommonValidator.validateId(req.getId());
        //调用BIZ方法
        ProductBiz.updateType(req.getId(), req.getName(), req.getDescription(), req.getThumbnailUrl(), req.getDetailImgUrl(), req.getPriority(), req.getStatus());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 编辑商品分类
     *
     * @return
     */
    @ApiOperation(value = "编辑商品分类", httpMethod = "GET", notes = "编辑商品分类")
    @RequestMapping(value = "deletetype", method = RequestMethod.GET)
    public Object deleteType(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        ProductBiz.deleteType(req.getId());
        return ResponseFactory.buildSuccess();
    }
}