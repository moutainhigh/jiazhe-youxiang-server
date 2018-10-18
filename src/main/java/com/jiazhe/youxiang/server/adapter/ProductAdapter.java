/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.vo.resp.product.ProductCategoryResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductResp;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductAdapter {

    public static ProductCategoryResp productCategoryDTO2VO(ProductCategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductCategoryResp productTypeResp = new ProductCategoryResp();
        productTypeResp.setId(dto.getId());
        productTypeResp.setName(dto.getName());
        productTypeResp.setDescription(dto.getDescription());
        productTypeResp.setThumbnailUrl(dto.getThumbnailUrl());
        productTypeResp.setDetailImgUrl(dto.getDetailImgUrl());
        productTypeResp.setPriority(dto.getPriority());
        productTypeResp.setStatus(dto.getStatus());
        return productTypeResp;
    }

    public static ProductResp productDTO2VO(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductResp productResp = new ProductResp();
        productResp.setId(dto.getId());
        productResp.setProductCategory(productCategoryDTO2VO(dto.getProductCategory()));
        productResp.setName(dto.getName());
        productResp.setDescription(dto.getDescription());
        productResp.setDelayDays(dto.getDelayDays());
        productResp.setThumbnailUrl(dto.getThumbnailUrl());
        productResp.setDetailImgUrl(dto.getDetailImgUrl());
        productResp.setProductType(dto.getProductType());
        productResp.setUnitName(dto.getUnitName());
        productResp.setLastNum(dto.getLastNum());
        productResp.setStatus(dto.getStatus());
        return productResp;
    }
}