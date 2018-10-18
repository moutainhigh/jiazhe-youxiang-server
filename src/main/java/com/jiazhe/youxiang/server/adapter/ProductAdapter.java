/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.product.ProductTypeDTO;
import com.jiazhe.youxiang.server.vo.resp.product.ProductTypeResp;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductAdapter {

    public static ProductTypeResp productTypeDTO2VO(ProductTypeDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductTypeResp productTypeResp = new ProductTypeResp();
        productTypeResp.setId(dto.getId());
        productTypeResp.setName(dto.getName());
        productTypeResp.setDescription(dto.getDescription());
        productTypeResp.setThumbnailUrl(dto.getThumbnailUrl());
        productTypeResp.setDetailImgUrl(dto.getDetailImgUrl());
        productTypeResp.setPriority(dto.getPriority());
        productTypeResp.setStatus(dto.getStatus());
        return productTypeResp;
    }
}