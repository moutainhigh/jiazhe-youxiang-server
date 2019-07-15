/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl.product;

import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.ProductCategoryPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.product.ProductCategoryPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ProductCategoryPO;
import com.jiazhe.youxiang.server.domain.po.ProductCategoryPOExample;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.service.product.ProductCategoryService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryPOMapper productCategoryPOMapper;

    @Autowired
    private ProductCategoryPOManualMapper productCategoryPOManualMapper;


    @Override
    public void addCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryPO productCategoryPO = ProductAdapter.productCategoryDTO2PO(productCategoryDTO);
        productCategoryPOMapper.insertSelective(productCategoryPO);
    }

    @Override
    public ProductCategoryDTO getCategoryById(Integer id) {
        return ProductAdapter.productCategoryPO2DTO(productCategoryPOMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<ProductCategoryDTO> getCategoryList(String name, Integer status, Paging paging) {
        Integer count = productCategoryPOManualMapper.count(name, status);
        List<ProductCategoryPO> productCategoryPOList = productCategoryPOManualMapper.query(name, status, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return productCategoryPOList.stream().map(ProductAdapter::productCategoryPO2DTO).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Integer id) {
        ProductCategoryPO record = new ProductCategoryPO();
        record.setId(id);
        record.setModTime(new Date());
        record.setIsDeleted(CommonConstant.CODE_DELETED);
        productCategoryPOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryPO productCategoryPO = ProductAdapter.productCategoryDTO2PO(productCategoryDTO);
        productCategoryPO.setModTime(new Date());
        productCategoryPOMapper.updateByPrimaryKeySelective(productCategoryPO);
    }

    @Override
    public void updateCategoryStatus(Integer id, Integer status) {
        ProductCategoryPO record = new ProductCategoryPO();
        record.setId(id);
        record.setModTime(new Date());
        record.setStatus(status.byteValue());
        productCategoryPOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Map<Integer, ProductCategoryPO> getCategoryMap() {
        ProductCategoryPOExample productCategoryPOExample = new ProductCategoryPOExample();
        ProductCategoryPOExample.Criteria criteria = productCategoryPOExample.createCriteria();
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<ProductCategoryPO> productCategoryPOList = productCategoryPOMapper.selectByExample(productCategoryPOExample);
        return productCategoryPOList.stream().collect(toMap(ProductCategoryPO::getId, Function.identity()));
    }
}