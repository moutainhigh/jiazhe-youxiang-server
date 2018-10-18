/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.product.ProductTypeDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductBiz {

    /*************商品分类相关******************/

    public static ProductTypeDTO getTypeById(Integer id) {
        return null;
    }

    public static List<ProductTypeDTO> getTypeList(String name, Paging paging) {
        return null;
    }

    public static void updateType(Integer id, String name, String description, String thumbnailUrl, String detailImgUrl, Integer priority, Byte status) {
    }

    public static void deleteType(Integer id) {
    }
}