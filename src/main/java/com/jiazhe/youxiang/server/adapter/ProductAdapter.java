/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceBatchAddDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.vo.req.product.ProductAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductPriceBatchAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductUpdateReq;
import com.jiazhe.youxiang.server.vo.resp.product.ProductCategoryResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductPriceResp;
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

    public static ProductDTO productAddReq2DTO(ProductAddReq req) {
        if (req == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        return productDTO;
    }

    public static ProductCategoryDTO productCategoryAddReq2DTO(ProductCategoryAddReq req) {
        if (req == null) {
            return null;
        }
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setName(req.getName());
        productCategoryDTO.setDescription(req.getDescription());
        productCategoryDTO.setThumbnailUrl(req.getThumbnailUrl());
        productCategoryDTO.setDetailImgUrl(req.getDetailImgUrl());
        productCategoryDTO.setPriority(req.getPriority());
        return productCategoryDTO;
    }

    public static ProductCategoryDTO productCategoryUpdateReq2DTO(ProductCategoryUpdateReq req) {
        if (req == null) {
            return null;
        }
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setName(req.getName());
        productCategoryDTO.setDescription(req.getDescription());
        productCategoryDTO.setThumbnailUrl(req.getThumbnailUrl());
        productCategoryDTO.setDetailImgUrl(req.getDetailImgUrl());
        productCategoryDTO.setPriority(req.getPriority());
        productCategoryDTO.setId(req.getId());
        return productCategoryDTO;
    }

    public static ProductDTO productUpdateReq2DTO(ProductUpdateReq req) {
        if (req == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(req.getName());
        productDTO.setDescription(req.getDescription());
        productDTO.setDelayDays(req.getDelayDays());
        productDTO.setThumbnailUrl(req.getThumbnailUrl());
        productDTO.setDetailImgUrl(req.getDetailImgUrl());
        productDTO.setProductType(req.getProductType());
        productDTO.setUnitName(req.getUnitName());
        productDTO.setLastNum(req.getLastNum());
        productDTO.setId(req.getId());
        return productDTO;
    }

    public static ProductPriceBatchAddDTO productPriceBatchAddReq2DTO(ProductPriceBatchAddReq req) {
        if (req == null) {
            return null;
        }
        ProductPriceBatchAddDTO productPriceBatchAddDTO = new ProductPriceBatchAddDTO();
        productPriceBatchAddDTO.setCityIds(req.getCityIds());
        productPriceBatchAddDTO.setProductId(req.getProductId());
        productPriceBatchAddDTO.setPrice(req.getPrice());
        return productPriceBatchAddDTO;
    }

    public static ProductPriceResp productPriceDTO2VO(ProductPriceDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductPriceResp productPriceResp = new ProductPriceResp();
        productPriceResp.setId(dto.getId());
        productPriceResp.setCityId(dto.getCityId());
        productPriceResp.setProductId(dto.getProductId());
        productPriceResp.setPrice(dto.getPrice());
        return productPriceResp;
    }
}