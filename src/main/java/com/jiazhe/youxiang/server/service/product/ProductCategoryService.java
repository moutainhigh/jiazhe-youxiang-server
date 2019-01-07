/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.product;

import com.jiazhe.youxiang.server.domain.po.ProductCategoryPO;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;
import java.util.Map;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
public interface ProductCategoryService {

    void addCategory(ProductCategoryDTO productCategoryDTO);

    ProductCategoryDTO getCategoryById(Integer id);

    List<ProductCategoryDTO> getCategoryList(String name, Integer status, Paging paging);

    void deleteCategory(Integer id);

    void updateCategory(ProductCategoryDTO productCategoryDTO);

    void updateCategoryStatus(Integer id, Integer status);

    /**
     * 获得商品大类map，key是大类id，value是值
     * @return
     */
    Map<Integer,ProductCategoryPO> getCategoryMap();
}