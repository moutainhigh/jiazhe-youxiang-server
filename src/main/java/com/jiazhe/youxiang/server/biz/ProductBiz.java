/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.stereotype.Service;

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

    public static ProductCategoryDTO getCategoryById(Integer id) {
        return null;
    }

    public static List<ProductCategoryDTO> getCategoryList(String name, Paging paging) {
        return null;
    }

    public static void deleteCategory(Integer id) {
    }

    public static void updateCategory(Integer id, String name, String description, String thumbnailUrl, String detailImgUrl, Integer priority) {
    }

    public static void updateCategoryStatus(Integer id, Byte status) {
    }

    /*************商品相关******************/
    public static ProductDTO getById(Integer id) {
        return null;
    }

    public static List<ProductDTO> getList(Integer productCategoryId, String name, Integer productType, Integer status, Paging paging) {
        return null;
    }

    public static void update(Integer id, String name, String description, Integer delayDays, String thumbnailUrl, String detailImgUrl, Integer productType, String unitName, Integer lastNum) {
    }

    public static void delete(Integer id) {

    }

    public static void updateStatus(Integer id, Byte status) {
    }
}