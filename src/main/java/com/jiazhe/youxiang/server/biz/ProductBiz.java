/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.product.ProductAddDTO;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.dto.product.ProductUpdateDTO;
import com.jiazhe.youxiang.server.service.product.ProductCategoryService;
import com.jiazhe.youxiang.server.service.product.ProductPriceService;
import com.jiazhe.youxiang.server.service.product.ProductService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
@Service("productBiz")
public class ProductBiz {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPriceService productPriceService;

    /**
     * 商品上架状态代码
     */
    public static final Integer CODE_PRODUCT_SELL = Integer.valueOf(1);
    /**
     * 商品下架状态代码
     */
    public static final Integer CODE_PRODUCT_NOT_SELL = Integer.valueOf(0);

    /**
     * 电子商品类型码
     */
    public static final Integer CODE_TYPE_ELEPRODUCT = Integer.valueOf(1);


    /*************商品分类相关******************/

    public void addCategory(ProductCategoryDTO productCategoryDTO) {
        productCategoryService.addCategory(productCategoryDTO);
    }

    public ProductCategoryDTO getCategoryById(Integer id) {
        return productCategoryService.getCategoryById(id);
    }

    public List<ProductCategoryDTO> getCategoryList(String name, Integer status, Paging paging) {
        return productCategoryService.getCategoryList(name, status, paging);
    }

    public void deleteCategory(Integer id) {
        productCategoryService.deleteCategory(id);
    }

    public void updateCategory(ProductCategoryDTO productCategoryDTO) {
        productCategoryService.updateCategory(productCategoryDTO);
    }

    public void updateCategoryStatus(Integer id, Integer status) {
        productCategoryService.updateCategoryStatus(id, status);
    }

    /*************商品相关******************/

    public void add(ProductAddDTO productDTO) {
        productService.add(productDTO);
    }

    public ProductDTO getById(Integer id) {
        return productService.getById(id);
    }

    public List<ProductDTO> getList(Integer productCategoryId, String name, Integer productType, List<String> cityCodes, Integer status, Paging paging) {
        return productService.getList(productCategoryId, name, productType, cityCodes, status, paging, false);
    }

    public List<ProductDTO> getAllList(Integer productType,  Integer status) {
        return productService.getAllList(productType,status);
    }

    public List<ProductDTO> getListForCustomer(Integer productCategoryId, String name, Integer productType, String cityCode, Paging paging) {
        return productService.getListForCustomer(productCategoryId, name, productType, cityCode, paging);
    }

    public void update(ProductUpdateDTO productUpdateDTO) {
        productService.update(productUpdateDTO);
    }

    public void delete(Integer id) {
        productService.delete(id);
    }

    public void updateStatus(Integer id, Integer status) {
        productService.updateStatus(id, status);
    }

    /*************商品价格相关******************/

    public void batchAddOrUpdatePrice(Integer productId, List<String> cityCodes, BigDecimal price) {
        productPriceService.batchAddAndUpdatePrice(productId, cityCodes, price);
    }

    public ProductPriceDTO getPriceById(Integer id) {
        return productPriceService.getPriceById(id);
    }

    public ProductPriceDTO getPriceByCity(Integer productId, String cityCode) {
        return productPriceService.getPriceByCity(productId, cityCode);
    }

    public List<ProductPriceDTO> getPriceList(Integer productId, String cityName, String cityCode, Integer status, Paging paging) {
        return productPriceService.getPriceList(productId, cityName, cityCode, status, paging);
    }

    public void batchDeletePrice(List<Integer> ids) {
        productPriceService.batchDeletePrice(ids);
    }


    public void updatePriceStatus(Integer id, Integer status) {
        productPriceService.updatePrice(id, null, status);
    }

    public void updatePrice(Integer id, BigDecimal price) {
        productPriceService.updatePrice(id, price, null);
    }

    public void batchUpdatePriceStatus(Integer productId, Integer status) {
        productPriceService.batchUpdatePriceStatus(productId,status);
    }
}