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

import java.math.BigDecimal;
import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductBiz {

    /*************商品分类相关******************/

    public static void addCategory(ProductCategoryDTO productCategoryDTO) {
    }

    public static ProductCategoryDTO getCategoryById(Integer id) {
        return null;
    }

    public static List<ProductCategoryDTO> getCategoryList(String name, Paging paging) {
        return null;
    }

    public static void deleteCategory(Integer id) {
    }

    public static void updateCategory(ProductCategoryDTO productCategoryDTO) {
    }

    public static void updateCategoryStatus(Integer id, Byte status) {
    }

    /*************商品相关******************/

    public static void add(ProductDTO productDTO) {
    }

    public static ProductDTO getById(Integer id) {
        return null;
    }

    public static List<ProductDTO> getList(Integer productCategoryId, String name, Integer productType, Integer status, Paging paging) {
        return null;
    }

    public static void update(ProductDTO productDTO) {
    }

    public static void delete(Integer id) {

    }

    public static void updateStatus(Integer id, Byte status) {
    }

    /*************商品价格相关******************/

    public static void addPrice(ProductPriceDTO productPriceDTO) {
    }

    public static ProductPriceDTO getPriceById(Integer id) {
    }

    public static List<ProductPriceDTO> getPriceListByProductId(Integer productId) {
        return Lists.newArrayList();
    }

    public static void updatePrice(Integer id, BigDecimal price) {
    }
}