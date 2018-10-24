/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.vo.Paging;
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

    /*************商品分类相关******************/

    public void addCategory(ProductCategoryDTO productCategoryDTO) {
    }

    public ProductCategoryDTO getCategoryById(Integer id) {
        return null;
    }

    public List<ProductCategoryDTO> getCategoryList(String name, Paging paging) {
        return null;
    }

    public void deleteCategory(Integer id) {
    }

    public void updateCategory(ProductCategoryDTO productCategoryDTO) {
    }

    public void updateCategoryStatus(Integer id, Byte status) {
    }

    /*************商品相关******************/

    public void add(ProductDTO productDTO) {
    }

    public ProductDTO getById(Integer id) {
        return null;
    }

    public List<ProductDTO> getList(Integer productCategoryId, String name, Integer productType, Integer status, Paging paging) {
        return null;
    }

    public void update(ProductDTO productDTO) {
    }

    public void delete(Integer id) {

    }

    public void updateStatus(Integer id, Byte status) {
    }

    /*************商品价格相关******************/

    public void addPrice(ProductPriceDTO productPriceDTO) {
    }

    public ProductPriceDTO getPriceById(Integer id) {
        return null;
    }

    public ProductPriceDTO getPriceByCity(Integer productId, Integer productId1) {
        return null;
    }

    public List<ProductPriceDTO> getPriceListByProductId(Integer productId) {
        return Lists.newArrayList();
    }

    public void updatePrice(Integer id, BigDecimal price) {
    }

    public void deletePrice(Integer id) {
    }


}