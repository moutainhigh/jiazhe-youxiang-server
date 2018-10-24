/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.biz.ProductBiz;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.product.GetProductPriceByCity;
import com.jiazhe.youxiang.server.vo.req.product.ProductAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryListReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductListReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductPriceAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductPriceListReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductPriceUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.StatusReq;
import com.jiazhe.youxiang.server.vo.resp.product.ProductCategoryResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductPriceResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductResp;
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
     * 添加商品分类
     *
     * @return
     */
    @ApiOperation(value = "添加商品分类", httpMethod = "POST", notes = "添加商品分类")
    @RequestMapping(value = "addcategory", method = RequestMethod.POST)
    public Object addCategory(@ModelAttribute ProductCategoryAddReq req) {
        //TODO niexiao 参数验证
        ProductCategoryDTO productCategoryDTO = ProductAdapter.productCategoryAddReq2DTO(req);
        //调用BIZ方法
        productBiz.addCategory(productCategoryDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 获得某一商品分类
     *
     * @return
     */
    @ApiOperation(value = "获得商品分类", httpMethod = "GET", response = ProductCategoryResp.class, notes = "获得商品分类")
    @RequestMapping(value = "getcategorybyid", method = RequestMethod.GET)
    public Object getCategoryById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        ProductCategoryDTO productCategoryDTO = productBiz.getCategoryById(req.getId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(ProductAdapter.productCategoryDTO2VO(productCategoryDTO));
    }

    /**
     * 查询商品分类列表
     *
     * @return
     */
    @ApiOperation(value = "查询商品分类列表", httpMethod = "GET", response = ProductCategoryResp.class, responseContainer = "List", notes = "查询商品分类列表")
    @RequestMapping(value = "getcategorylist", method = RequestMethod.GET)
    public Object getCategoryList(@ModelAttribute ProductCategoryListReq req) {
        //TODO niexiao 参数验证
        Paging paging = new Paging();
        paging.setOffset(req.getOffset());
        paging.setLimit(req.getLimit());
        //调用BIZ方法
        List<ProductCategoryDTO> productCategoryDTOList = productBiz.getCategoryList(req.getName(), paging);
        //将DTO转成VO
        List<ProductCategoryResp> result = productCategoryDTOList.stream().map(ProductAdapter::productCategoryDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }

    /**
     * 编辑商品分类
     *
     * @return
     */
    @ApiOperation(value = "编辑商品分类", httpMethod = "POST", notes = "编辑商品分类")
    @RequestMapping(value = "updatecategory", method = RequestMethod.POST)
    public Object updateCategory(@ModelAttribute ProductCategoryUpdateReq req) {
        //TODO niexiao 参数验证
        CommonValidator.validateId(req);
        ProductCategoryDTO productCategoryDTO = ProductAdapter.productCategoryUpdateReq2DTO(req);
        //调用BIZ方法
        productBiz.updateCategory(productCategoryDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 编辑商品分类状态
     *
     * @return
     */
    @ApiOperation(value = "编辑商品分类状态", httpMethod = "POST", notes = "编辑商品分类状态")
    @RequestMapping(value = "updatecategorystatus", method = RequestMethod.POST)
    public Object updateCategoryStatus(@ModelAttribute StatusReq req) {
        //TODO niexiao 参数验证
        validateStatus(req);
        //调用BIZ方法
        productBiz.updateCategoryStatus(req.getId(), req.getStatus());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 删除商品分类
     *
     * @return
     */
    @ApiOperation(value = "删除商品分类", httpMethod = "GET", notes = "删除商品分类")
    @RequestMapping(value = "deletecategory", method = RequestMethod.GET)
    public Object deleteCategory(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        productBiz.deleteCategory(req.getId());
        return ResponseFactory.buildSuccess();
    }

    /*************商品相关******************/

    /**
     * 添加商品
     *
     * @return
     */
    @ApiOperation(value = "添加商品", httpMethod = "POST", notes = "添加商品")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object add(@ModelAttribute ProductAddReq req) {
        //TODO niexiao 参数验证
        ProductDTO productDTO = ProductAdapter.productAddReq2DTO(req);
        //调用BIZ方法
        productBiz.add(productDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 获得某一商品
     *
     * @return
     */
    @ApiOperation(value = "获得某一商品", httpMethod = "GET", response = ProductResp.class, notes = "获得某一商品")
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        ProductDTO productDTO = productBiz.getById(req.getId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(ProductAdapter.productDTO2VO(productDTO));
    }

    /**
     * 查询商品列表
     *
     * @return
     */
    @ApiOperation(value = "查询商品列表", httpMethod = "GET", response = ProductResp.class, responseContainer = "List", notes = "查询商品列表")
    @RequestMapping(value = "getlist", method = RequestMethod.GET)
    public Object getList(@ModelAttribute ProductListReq req) {
        //TODO niexiao 参数验证
        Paging paging = new Paging();
        paging.setOffset(req.getOffset());
        paging.setLimit(req.getLimit());
        //调用BIZ方法
        List<ProductDTO> productDTOList = productBiz.getList(req.getProductCategoryId(), req.getName(), req.getProductType(), req.getStatus(), paging);
        //将DTO转成VO
        List<ProductResp> result = productDTOList.stream().map(ProductAdapter::productDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }

    /**
     * 编辑商品
     *
     * @return
     */
    @ApiOperation(value = "编辑商品", httpMethod = "POST", notes = "编辑商品")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object update(@ModelAttribute ProductUpdateReq req) {
        //TODO niexiao 参数验证
        CommonValidator.validateId(req);
        ProductDTO productDTO = ProductAdapter.productUpdateReq2DTO(req);
        //调用BIZ方法
        productBiz.update(productDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 编辑商品状态
     *
     * @return
     */
    @ApiOperation(value = "编辑商品状态", httpMethod = "POST", notes = "编辑商品状态")
    @RequestMapping(value = "updatestatus", method = RequestMethod.POST)
    public Object updateStatus(@ModelAttribute StatusReq req) {
        //TODO niexiao 参数验证
        validateStatus(req);
        //调用BIZ方法
        productBiz.updateStatus(req.getId(), req.getStatus());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 删除商品
     *
     * @return
     */
    @ApiOperation(value = "删除商品", httpMethod = "GET", notes = "删除商品")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public Object delete(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        productBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }

    /*************商品价格相关******************/

    /**
     * 添加商品价格
     *
     * @return
     */
    @ApiOperation(value = "添加商品价格", httpMethod = "POST", notes = "添加商品价格")
    @RequestMapping(value = "addprice", method = RequestMethod.POST)
    public Object addPrice(@ModelAttribute ProductPriceAddReq req) {
        //TODO niexiao 参数验证
        ProductPriceDTO productPriceDTO = ProductAdapter.productPriceAddReq2DTO(req);
        //调用BIZ方法
        productBiz.addPrice(productPriceDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 获得某一商品价格
     *
     * @return
     */
    @ApiOperation(value = "获得某一商品价格", httpMethod = "GET", response = ProductPriceResp.class, notes = "获得某一商品价格")
    @RequestMapping(value = "getpricebyid", method = RequestMethod.GET)
    public Object getPriceById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        ProductPriceDTO productPriceDTO = productBiz.getPriceById(req.getId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(ProductAdapter.productPriceDTO2VO(productPriceDTO));
    }

    /**
     * 获得某一商品在某城市的价格
     *
     * @return
     */
    @ApiOperation(value = "获得某一商品在某城市的价格", httpMethod = "GET", response = ProductPriceResp.class, notes = "获得某一商品在某城市的价格")
    @RequestMapping(value = "getpricebycity", method = RequestMethod.GET)
    public Object getPriceByCity(@ModelAttribute GetProductPriceByCity req) {
        CommonValidator.validateNull(req);
        //调用BIZ方法
        ProductPriceDTO productPriceDTO = productBiz.getPriceByCity(req.getProductId(), req.getProductId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(ProductAdapter.productPriceDTO2VO(productPriceDTO));
    }

    /**
     * 修改商品的价格列表
     *
     * @return
     */
    @ApiOperation(value = "获得商品的价格列表", httpMethod = "GET", response = ProductPriceResp.class, responseContainer = "List", notes = "获得商品的价格列表")
    @RequestMapping(value = "getpricelistbyproductid", method = RequestMethod.GET)
    public Object getPriceListByProductId(@ModelAttribute ProductPriceListReq req) {
        CommonValidator.validateId(req.getProductId());
        //调用BIZ方法
        List<ProductPriceDTO> productPriceDTOList = productBiz.getPriceListByProductId(req.getProductId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(productPriceDTOList.stream().map(ProductAdapter::productPriceDTO2VO).collect(Collectors.toList()));
    }

    /**
     * 编辑商品的价格
     *
     * @return
     */
    @ApiOperation(value = "编辑商品的价格", httpMethod = "POST", notes = "编辑商品的价格")
    @RequestMapping(value = "updatePrice", method = RequestMethod.POST)
    public Object updatePrice(@ModelAttribute ProductPriceUpdateReq req) {
        //TODO niexiao 参数验证
        CommonValidator.validateId(req);
        //调用BIZ方法
        productBiz.updatePrice(req.getId(), req.getPrice());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 删除商品价格
     *
     * @return
     */
    @ApiOperation(value = "删除商品价格", httpMethod = "GET", notes = "删除商品价格")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public Object deletePrice(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        productBiz.deletePrice(req.getId());
        return ResponseFactory.buildSuccess();
    }


    /*************通用方法******************/

    /**
     * 状态验证
     *
     * @param req
     */
    private void validateStatus(StatusReq req) {
        CommonValidator.validateId(req);
        //TODO niexiao 参数验证
    }
}