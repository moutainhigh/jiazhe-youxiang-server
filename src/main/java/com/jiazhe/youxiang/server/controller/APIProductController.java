/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.biz.ProductBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.ProductCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.ProductException;
import com.jiazhe.youxiang.server.dto.product.ProductAddDTO;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.dto.product.ProductUpdateDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdListReq;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.product.GetProductPriceByCityReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryListReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductListForCustomerReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductListReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductPriceBatchAddOrUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductPriceListReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductPriceUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.StatusReq;
import com.jiazhe.youxiang.server.vo.resp.product.ProductCategoryResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductPriceResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductResp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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
public class APIProductController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIProductController.class);

    @Autowired
    private ProductBiz productBiz;

    /*************商品分类相关******************/
    /**
     * 【后台】添加商品分类
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PRODUCT_CATEGORY_ADD)
    @ApiOperation(value = "【后台】添加商品分类", httpMethod = "POST", notes = "【后台】添加商品分类")
    @RequestMapping(value = "addcategory", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "添加商品分类", level = LogLevelEnum.LEVEL_2)
    public Object addCategory(@ModelAttribute ProductCategoryAddReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getName(), new ProductException(ProductCodeEnum.PRODUCT_CATEGORY_NAME_IS_NULL));
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
    @AppApi
    @ApiOperation(value = "【后台】获得商品分类", httpMethod = "GET", response = ProductCategoryResp.class, notes = "【后台】获得商品分类")
    @RequestMapping(value = "getcategorybyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "获得商品分类", level = LogLevelEnum.LEVEL_1)
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
    @AppApi
    @ApiOperation(value = "查询商品分类列表", httpMethod = "GET", response = ProductCategoryResp.class, responseContainer = "List", notes = "查询商品分类列表")
    @RequestMapping(value = "getcategorylist", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "查询商品分类列表", level = LogLevelEnum.LEVEL_1)
    public Object getCategoryList(@ModelAttribute ProductCategoryListReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        //调用BIZ方法
        List<ProductCategoryDTO> productCategoryDTOList = productBiz.getCategoryList(req.getName(), req.getStatus(), paging);
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
    @RequiresPermissions(PermissionConstant.PRODUCT_CATEGORY_ADD)
    @ApiOperation(value = "编辑商品分类", httpMethod = "POST", notes = "编辑商品分类")
    @RequestMapping(value = "updatecategory", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "编辑商品分类", level = LogLevelEnum.LEVEL_2)
    public Object updateCategory(@ModelAttribute ProductCategoryUpdateReq req) {
        CommonValidator.validateId(req);
        CommonValidator.validateNull(req.getName(), new ProductException(ProductCodeEnum.PRODUCT_CATEGORY_NAME_IS_NULL));
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
    @RequiresPermissions(PermissionConstant.PRODUCT_CATEGORY_ONOFFLINE)
    @ApiOperation(value = "编辑商品分类状态", httpMethod = "POST", notes = "编辑商品分类状态")
    @RequestMapping(value = "updatecategorystatus", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "编辑商品分类状态", level = LogLevelEnum.LEVEL_2)
    public Object updateCategoryStatus(@ModelAttribute StatusReq req) {
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
    @RequiresPermissions(PermissionConstant.PRODUCT_CATEGORY_DELETE)
    @ApiOperation(value = "删除商品分类", httpMethod = "POST", notes = "删除商品分类")
    @RequestMapping(value = "deletecategory", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "删除商品分类", level = LogLevelEnum.LEVEL_3)
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
    @RequiresPermissions(PermissionConstant.PRODUCT_ADD)
    @ApiOperation(value = "添加商品", httpMethod = "POST", notes = "添加商品")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "添加商品", level = LogLevelEnum.LEVEL_2)
    public Object add(@ModelAttribute ProductAddReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getProductCategoryId(), new ProductException(ProductCodeEnum.PRODUCT_CATEGORY_ID_IS_NULL));
        CommonValidator.validateNull(req.getName(), new ProductException(ProductCodeEnum.PRODUCT_NAME_IS_NULL));
        validateProductType(req.getProductType());
        ProductAddDTO productAddDTO = ProductAdapter.productAddReq2DTO(req);
        //调用BIZ方法
        productBiz.add(productAddDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 获得某一商品
     *
     * @return
     */
    @AppApi
    @ApiOperation(value = "获得某一商品", httpMethod = "GET", response = ProductResp.class, notes = "获得某一商品")
    @RequestMapping(value = "getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "获得某一商品", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        ProductDTO productDTO = productBiz.getById(req.getId());
        //用ResponseFactry将返回值包装p
        return ResponseFactory.buildResponse(ProductAdapter.productDTO2VO(productDTO));
    }

    /**
     * 查询商品列表
     *
     * @return
     */
    @ApiOperation(value = "查询商品列表", httpMethod = "GET", response = ProductResp.class, responseContainer = "List", notes = "查询商品列表")
    @RequestMapping(value = "getlist", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "查询商品列表", level = LogLevelEnum.LEVEL_1)
    public Object getList(@ModelAttribute ProductListReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        //调用BIZ方法
        List<ProductDTO> productDTOList = productBiz.getList(req.getProductCategoryId(), req.getName(), req.getProductType(), req.getCityCodes(), req.getStatus(), paging);
        //将DTO转成VO
        List<ProductResp> result = productDTOList.stream().map(ProductAdapter::productDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }

    /**
     * 查询所有商品列表（包含大类信息）
     *
     * @return
     */
    @ApiOperation(value = "查询所有商品列表（包含大类信息）", httpMethod = "GET", response = ProductResp.class, responseContainer = "List", notes = "查询所有商品列表（包含大类信息）")
    @RequestMapping(value = "getalllist", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "查询所有商品列表（包含大类信息）", level = LogLevelEnum.LEVEL_1)
    public Object getAllList(@ModelAttribute ProductListReq req) {
        //调用BIZ方法
        List<ProductDTO> productDTOList = productBiz.getAllList(req.getProductType(), req.getStatus());
        //将DTO转成VO
        List<ProductResp> result = productDTOList.stream().map(ProductAdapter::productDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(result);
    }

    /**
     * 获得商品列表（前端客户专用）
     *
     * @return
     */
    @AppApi
    @ApiOperation(value = "获得商品列表（前端客户专用）", httpMethod = "GET", response = ProductResp.class, responseContainer = "List", notes = "获得商品列表（前端客户专用）")
    @RequestMapping(value = "getlistforcustomer", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "获得商品列表", level = LogLevelEnum.LEVEL_1)
    public Object getListForCustomer(@ModelAttribute ProductListForCustomerReq req) {
        CommonValidator.validatePaging(req);
        CommonValidator.validateId(req.getProductCategoryId(), new ProductException(ProductCodeEnum.PRODUCT_CATEGORY_ID_IS_NULL));
        CommonValidator.validateNull(req.getCityCode(), new ProductException(ProductCodeEnum.PRODUCT_CITY_CODE_IS_NULL));
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        //调用BIZ方法
        List<ProductDTO> productDTOList = productBiz.getListForCustomer(req.getProductCategoryId(), req.getName(), req.getProductType(), req.getCityCode(), paging);
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
    @RequiresPermissions(PermissionConstant.PRODUCT_EDIT)
    @ApiOperation(value = "编辑商品", httpMethod = "POST", notes = "编辑商品")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "编辑商品", level = LogLevelEnum.LEVEL_2)
    public Object update(@ModelAttribute ProductUpdateReq req) {
        CommonValidator.validateId(req);
        CommonValidator.validateNull(req.getName(), new ProductException(ProductCodeEnum.PRODUCT_NAME_IS_NULL));
        ProductUpdateDTO productUpdateDTO = ProductAdapter.productUpdateReq2DTO(req);
        //调用BIZ方法
        productBiz.update(productUpdateDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 编辑商品状态
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PRODUCT_ONOFFLINE)
    @ApiOperation(value = "编辑商品状态", httpMethod = "POST", notes = "编辑商品状态")
    @RequestMapping(value = "updatestatus", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "编辑商品状态", level = LogLevelEnum.LEVEL_2)
    public Object updateStatus(@ModelAttribute StatusReq req) {
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
    @RequiresPermissions(PermissionConstant.PRODUCT_DELETE)
    @ApiOperation(value = "删除商品", httpMethod = "POST", notes = "删除商品")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "删除商品", level = LogLevelEnum.LEVEL_3)
    public Object delete(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        productBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }

    /*************商品价格相关******************/


    /**
     * 批量添加或修改商品价格
     *
     * @return
     */

    @ApiOperation(value = "批量添加或修改商品价格", httpMethod = "POST", notes = "批量添加或修改商品价格")
    @RequestMapping(value = "batchaddorupdateprice", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "批量添加或修改商品价格", level = LogLevelEnum.LEVEL_2)
    public Object batchAddOrUpdatePrice(@ModelAttribute ProductPriceBatchAddOrUpdateReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getProductId(), new ProductException(ProductCodeEnum.PRODUCT_ID_IS_NULL));
        validateCityCodes(req.getCityCodes());
        validatePrice(req.getPrice());
        //调用BIZ方法
        productBiz.batchAddOrUpdatePrice(req.getProductId(), req.getCityCodes(), req.getPrice());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 根据价格ID获得某一商品价格
     *
     * @return
     */
    @ApiOperation(value = "根据价格ID获得某一商品价格", httpMethod = "GET", response = ProductPriceResp.class, notes = "根据价格ID获得某一商品价格")
    @RequestMapping(value = "getpricebyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "根据价格ID获得某一商品价格", level = LogLevelEnum.LEVEL_1)
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
    @AppApi
    @ApiOperation(value = "获得某一商品在某城市的价格", httpMethod = "GET", response = ProductPriceResp.class, notes = "获得某一商品在某城市的价格")
    @RequestMapping(value = "getpricebycity", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "获得某一商品在某城市的价格", level = LogLevelEnum.LEVEL_1)
    public Object getPriceByCity(@ModelAttribute GetProductPriceByCityReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getProductId(), new ProductException(ProductCodeEnum.PRODUCT_ID_IS_NULL));
        CommonValidator.validateNull(req.getCityCode(), new ProductException(ProductCodeEnum.PRODUCT_CITY_CODE_IS_NULL));
        //调用BIZ方法
        ProductPriceDTO productPriceDTO = productBiz.getPriceByCity(req.getProductId(), req.getCityCode());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(ProductAdapter.productPriceDTO2VO(productPriceDTO));
    }

    /**
     * 修改商品的价格列表
     *
     * @return
     */
    @ApiOperation(value = "获得商品的价格列表", httpMethod = "GET", response = ProductPriceResp.class, responseContainer = "List", notes = "获得商品的价格列表")
    @RequestMapping(value = "getpricelist", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "获得商品的价格列表", level = LogLevelEnum.LEVEL_1)
    public Object getPriceList(@ModelAttribute ProductPriceListReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        CommonValidator.validateId(req.getProductId(), new ProductException(ProductCodeEnum.PRODUCT_ID_IS_NULL));
        //调用BIZ方法
        List<ProductPriceDTO> productPriceDTOList = productBiz.getPriceList(req.getProductId(), req.getCityName(), req.getCityCode(), req.getStatus(), paging);
        List<ProductPriceResp> result = productPriceDTOList.stream().map(ProductAdapter::productPriceDTO2VO).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(result, paging);
    }


    /**
     * 批量删除商品价格
     *
     * @return
     */
    @ApiOperation(value = "批量删除商品价格", httpMethod = "GET", notes = "批量删除商品价格")
    @RequestMapping(value = "batchdeleteprice", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "批量删除商品价格", level = LogLevelEnum.LEVEL_2)
    public Object batchDeletePrice(@ModelAttribute IdListReq req) {
        CommonValidator.validateIdList(req, new ProductException(ProductCodeEnum.PRODUCT_PRICE_ID_IS_NULL));
        //调用BIZ方法
        productBiz.batchDeletePrice(req.getIds());
        return ResponseFactory.buildSuccess();
    }

    /**
     * 更新价格
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PRODUCT_PRICE_EDIT)
    @ApiOperation(value = "更新价格", httpMethod = "POST", notes = "更新价格")
    @RequestMapping(value = "updateprice", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "更新价格", level = LogLevelEnum.LEVEL_2)
    public Object updatePrice(@ModelAttribute ProductPriceUpdateReq req) {
        CommonValidator.validateId(req);
        validatePrice(req.getPrice());
        //调用BIZ方法
        productBiz.updatePrice(req.getId(), req.getPrice());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 更新价格状态
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PRODUCT_PRICE_EFFECT)
    @ApiOperation(value = "更新价格状态", httpMethod = "POST", notes = "更新价格状态")
    @RequestMapping(value = "updatepricestatus", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "更新价格状态", level = LogLevelEnum.LEVEL_2)
    public Object updatePriceStatus(@ModelAttribute StatusReq req) {
        validateStatus(req);
        //调用BIZ方法
        productBiz.updatePriceStatus(req.getId(), req.getStatus());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 批量更新价格状态
     *
     * @return
     */
    @RequiresPermissions(PermissionConstant.PRODUCT_PRICE_EFFECT)
    @ApiOperation(value = "批量更新价格状态", httpMethod = "POST", notes = "批量更新价格状态")
    @RequestMapping(value = "batchupdatepricestatus", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PRODUCT, operate = "批量更新价格状态", level = LogLevelEnum.LEVEL_2)
    public Object batchUpdatePriceStatus(@ModelAttribute StatusReq req) {
        validateStatus(req);
        productBiz.batchUpdatePriceStatus(req.getId(), req.getStatus());
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
        if (req == null || req.getStatus() == null || req.getStatus() < 0 || req.getStatus() > 1) {
            throw new ProductException(ProductCodeEnum.PUTAWAY_STATUS_ERROR);
        }
    }

    /**
     * 验证商品类别
     *
     * @param productType
     */
    private void validateProductType(Integer productType) {
        if (productType == null || productType < 0 || productType > 1) {
            throw new ProductException(ProductCodeEnum.PRODUCT_TYPE_ERROR);
        }
    }

    /**
     * 验证商品价格
     *
     * @param price
     */
    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new ProductException(ProductCodeEnum.PRODUCT_PRICE_ERROR);
        }
    }

    private void validateCityCodes(List<String> codes) {
        if (CollectionUtils.isEmpty(codes)) {
            throw new ProductException(ProductCodeEnum.PRODUCT_CITY_CODE_IS_NULL);
        }
    }
}