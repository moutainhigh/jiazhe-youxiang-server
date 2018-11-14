/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.ProductCategoryPO;
import com.jiazhe.youxiang.server.domain.po.ProductPO;
import com.jiazhe.youxiang.server.domain.po.ProductPricePO;
import com.jiazhe.youxiang.server.dto.product.ProductAddDTO;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.dto.product.ProductUpdateDTO;
import com.jiazhe.youxiang.server.vo.req.product.ProductAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryAddReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductCategoryUpdateReq;
import com.jiazhe.youxiang.server.vo.req.product.ProductUpdateReq;
import com.jiazhe.youxiang.server.vo.resp.product.ProductCategoryResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductPriceResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductResp;
import org.apache.commons.collections.CollectionUtils;

import java.util.stream.Collectors;

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
        ProductCategoryResp productCategoryResp = new ProductCategoryResp();
        productCategoryResp.setId(dto.getId());
        productCategoryResp.setName(dto.getName());
        productCategoryResp.setDescription(dto.getDescription());
        productCategoryResp.setThumbnailUrl(dto.getThumbnailUrl());
        productCategoryResp.setDetailImgUrl(dto.getDetailImgUrl());
        productCategoryResp.setPriority(dto.getPriority());
        productCategoryResp.setStatus(dto.getStatus());
        return productCategoryResp;
    }

    public static ProductResp productDTO2VO(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductResp productResp = new ProductResp();
        productResp.setId(dto.getId());
        productResp.setName(dto.getName());
        productResp.setDescription(dto.getDescription());
        productResp.setDelayDays(dto.getDelayDays());
        productResp.setThumbnailUrl(dto.getThumbnailUrl());
        productResp.setDetailImgUrl(dto.getDetailImgUrl());
        productResp.setProductType(dto.getProductType());
        productResp.setUnitName(dto.getUnitName());
        productResp.setLastNum(dto.getLastNum());
        productResp.setStatus(dto.getStatus());
        productResp.setSmsTemplate(dto.getSmsTemplate());
        productResp.setEffectiveDays(dto.getEffectiveDays());
        productResp.setPriority(dto.getPriority());
        productResp.setProductCategory(productCategoryDTO2VO(dto.getProductCategory()));
        if (CollectionUtils.isNotEmpty(dto.getProductPriceList())) {
            productResp.setProductPriceList(dto.getProductPriceList().stream().map(ProductAdapter::productPriceDTO2VO).collect(Collectors.toList()));
        }
        return productResp;
    }


    public static ProductAddDTO productAddReq2DTO(ProductAddReq req) {
        if (req == null) {
            return null;
        }
        ProductAddDTO productAddDTO = new ProductAddDTO();
        productAddDTO.setProductCategoryId(req.getProductCategoryId());
        productAddDTO.setName(req.getName());
        productAddDTO.setDescription(req.getDescription());
        productAddDTO.setDelayDays(req.getDelayDays());
        productAddDTO.setThumbnailUrl(req.getThumbnailUrl());
        productAddDTO.setDetailImgUrl(req.getDetailImgUrl());
        productAddDTO.setProductType(req.getProductType());
        productAddDTO.setUnitName(req.getUnitName());
        productAddDTO.setLastNum(req.getLastNum());
        productAddDTO.setSmsTemplate(req.getSmsTemplate());
        productAddDTO.setEffectiveDays(req.getEffectiveDays());
        productAddDTO.setPriority(req.getPriority());
        return productAddDTO;
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

    public static ProductUpdateDTO productUpdateReq2DTO(ProductUpdateReq req) {
        if (req == null) {
            return null;
        }
        ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO();
        productUpdateDTO.setName(req.getName());
        productUpdateDTO.setDescription(req.getDescription());
        productUpdateDTO.setDelayDays(req.getDelayDays());
        productUpdateDTO.setThumbnailUrl(req.getThumbnailUrl());
        productUpdateDTO.setDetailImgUrl(req.getDetailImgUrl());
        productUpdateDTO.setProductType(req.getProductType());
        productUpdateDTO.setUnitName(req.getUnitName());
        productUpdateDTO.setLastNum(req.getLastNum());
        productUpdateDTO.setPriority(req.getPriority());
        productUpdateDTO.setId(req.getId());
        return productUpdateDTO;
    }

    public static ProductPriceResp productPriceDTO2VO(ProductPriceDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductPriceResp productPriceResp = new ProductPriceResp();
        productPriceResp.setId(dto.getId());
        productPriceResp.setCityCode(dto.getCityCode());
        productPriceResp.setCityName(dto.getCityName());
        productPriceResp.setProductId(dto.getProductId());
        productPriceResp.setStatus(dto.getStatus());
        productPriceResp.setPrice(dto.getPrice());
        return productPriceResp;
    }

    public static ProductCategoryPO productCategoryDTO2PO(ProductCategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductCategoryPO productCategoryPO = new ProductCategoryPO();
        productCategoryPO.setId(dto.getId());
        productCategoryPO.setName(dto.getName());
        productCategoryPO.setDescription(dto.getDescription());
        productCategoryPO.setThumbnailUrl(dto.getThumbnailUrl());
        productCategoryPO.setDetailImgUrl(dto.getDetailImgUrl());
        productCategoryPO.setPriority(dto.getPriority());
        if(dto.getStatus()!=null){
            productCategoryPO.setStatus(dto.getStatus().byteValue());
        }
        return productCategoryPO;
    }

    public static ProductCategoryDTO productCategoryPO2DTO(ProductCategoryPO po) {
        if (po == null) {
            return null;
        }
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setId(po.getId());
        productCategoryDTO.setName(po.getName());
        productCategoryDTO.setDescription(po.getDescription());
        productCategoryDTO.setThumbnailUrl(po.getThumbnailUrl());
        productCategoryDTO.setDetailImgUrl(po.getDetailImgUrl());
        productCategoryDTO.setPriority(po.getPriority());
        if(po.getStatus()!=null){
            productCategoryDTO.setStatus(po.getStatus().intValue());
        }
        return productCategoryDTO;
    }

    public static ProductPO productAddDTO2PO(ProductAddDTO productAddDTO) {
        if (productAddDTO == null) {
            return null;
        }
        ProductPO productPO = new ProductPO();
        productPO.setProductCategoryId(productAddDTO.getProductCategoryId());
        productPO.setName(productAddDTO.getName());
        productPO.setDescription(productAddDTO.getDescription());
        productPO.setDelayDays(productAddDTO.getDelayDays());
        productPO.setThumbnailUrl(productAddDTO.getThumbnailUrl());
        productPO.setDetailImgUrl(productAddDTO.getDetailImgUrl());
        productPO.setProductType(productAddDTO.getProductType());
        productPO.setUnitName(productAddDTO.getUnitName());
        productPO.setLastNum(productAddDTO.getLastNum());
        productPO.setStatus(productAddDTO.getStatus());
        productPO.setSmsTemplate(productAddDTO.getSmsTemplate());
        productPO.setEffectiveDays(productAddDTO.getEffectiveDays());
        productPO.setPriority(productAddDTO.getPriority());
        return productPO;
    }

    public static ProductDTO productPO2DTO(ProductPO productPO) {
        if (productPO == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productPO.getId());
        productDTO.setName(productPO.getName());
        productDTO.setDescription(productPO.getDescription());
        productDTO.setDelayDays(productPO.getDelayDays());
        productDTO.setThumbnailUrl(productPO.getThumbnailUrl());
        productDTO.setDetailImgUrl(productPO.getDetailImgUrl());
        productDTO.setProductType(productPO.getProductType());
        productDTO.setUnitName(productPO.getUnitName());
        productDTO.setLastNum(productPO.getLastNum());
        productDTO.setStatus(productPO.getStatus());
        productDTO.setPriority(productPO.getPriority());
        productDTO.setSmsTemplate(productPO.getSmsTemplate());
        productDTO.setEffectiveDays(productPO.getEffectiveDays());
        return productDTO;
    }

    public static ProductPO productUpdateDTO2PO(ProductUpdateDTO productUpdateDTO) {
        if (productUpdateDTO == null) {
            return null;
        }
        ProductPO productPO = new ProductPO();
        productPO.setId(productUpdateDTO.getId());
        productPO.setName(productUpdateDTO.getName());
        productPO.setDescription(productUpdateDTO.getDescription());
        productPO.setDelayDays(productUpdateDTO.getDelayDays());
        productPO.setThumbnailUrl(productUpdateDTO.getThumbnailUrl());
        productPO.setDetailImgUrl(productUpdateDTO.getDetailImgUrl());
        productPO.setProductType(productUpdateDTO.getProductType());
        productPO.setUnitName(productUpdateDTO.getUnitName());
        productPO.setLastNum(productUpdateDTO.getLastNum());
        productPO.setPriority(productUpdateDTO.getPriority());
        return productPO;
    }

    public static ProductPriceDTO productPricePO2DTO(ProductPricePO po) {
        if (po == null) {
            return null;
        }
        ProductPriceDTO productPriceDTO = new ProductPriceDTO();
        productPriceDTO.setId(po.getId());
        productPriceDTO.setCityCode(po.getCityCode());
        productPriceDTO.setCityName(po.getCityName());
        productPriceDTO.setProductId(po.getProductId());
        productPriceDTO.setPrice(po.getPrice());
        productPriceDTO.setStatus(po.getStatus().intValue());
        return productPriceDTO;
    }
}