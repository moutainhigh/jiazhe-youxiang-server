/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl.product;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.ProductPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.product.ProductPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ProductPO;
import com.jiazhe.youxiang.server.dto.product.ProductAddDTO;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.dto.product.ProductUpdateDTO;
import com.jiazhe.youxiang.server.service.product.ProductCategoryService;
import com.jiazhe.youxiang.server.service.product.ProductPriceService;
import com.jiazhe.youxiang.server.service.product.ProductService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductPOMapper productPOMapper;

    @Autowired
    private ProductPOManualMapper productPOManualMapper;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductPriceService productPriceService;


    @Override
    public void add(ProductAddDTO productAddDTO) {
        ProductPO productPO = ProductAdapter.productAddDTO2PO(productAddDTO);
        productPOMapper.insertSelective(productPO);
    }

    @Override
    public ProductDTO getById(Integer id) {
        ProductPO productPO = productPOMapper.selectByPrimaryKey(id);
        ProductCategoryDTO productCategory = productCategoryService.getCategoryById(productPO.getProductCategoryId());
        List<ProductPriceDTO> productPriceList = productPriceService.getListByProductId(productPO.getId());
        ProductDTO productDTO = ProductAdapter.productPO2DTO(productPO);
        productDTO.setProductCategory(productCategory);
        productDTO.setProductPriceList(productPriceList);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getList(Integer productCategoryId, String name, Integer productType, List<String> cityCodes, Integer status, Paging paging) {
        List<Integer> productIds = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(cityCodes)) {
            // 获取城市列表中有价格的商品ID集合
            productIds = productPriceService.getProductIdsByCityCodes(cityCodes);
        }
        Integer count = productPOManualMapper.count(productCategoryId, name, productType, status, productIds);
        List<ProductPO> productPOList = productPOManualMapper.query(productCategoryId, name, productType, status, productIds, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return productPOList.stream().map(ProductAdapter::productPO2DTO).collect(Collectors.toList());
    }

    @Override
    public void update(ProductUpdateDTO productUpdateDTO) {
        ProductPO productPO = ProductAdapter.productUpdateDTO2PO(productUpdateDTO);
        productPO.setModTime(new Date());
        productPOMapper.updateByPrimaryKeySelective(productPO);
    }

    @Override
    public void delete(Integer id) {
        ProductPO productPO = new ProductPO();
        productPO.setId(id);
        productPO.setModTime(new Date());
        productPO.setIsDeleted(CommonConstant.CODE_DELETED);
        productPOMapper.updateByPrimaryKeySelective(productPO);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        ProductPO productPO = new ProductPO();
        productPO.setId(id);
        productPO.setModTime(new Date());
        productPO.setStatus(status.byteValue());
        productPOMapper.updateByPrimaryKeySelective(productPO);
    }
}