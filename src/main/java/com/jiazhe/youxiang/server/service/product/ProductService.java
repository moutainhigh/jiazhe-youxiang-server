/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.product;

import com.jiazhe.youxiang.server.dto.product.ProductAddDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductUpdateDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
public interface ProductService {
    void add(ProductAddDTO productDTO);

    ProductDTO getById(Integer id);

    List<ProductDTO> getList(Integer productCategoryId, String name, Integer productType, List<String> cityCodes, Integer status, Paging paging, boolean detail);

    /**
     * 查询可售卖的商品（客户可见）
     * @param productCategoryId 商品分类id
     * @param name 商品名称（可选）
     * @param productType 商品类型
     * @param cityCode 客户所在城市code
     * @param paging
     * @return
     */
    List<ProductDTO> getListForCustomer(Integer productCategoryId, String name, Integer productType, String cityCode, Paging paging);

    void update(ProductUpdateDTO productUpdateDTO);

    void delete(Integer id);

    void updateStatus(Integer id, Integer status);

    /**
     * 获取商品列表,不包含价格
     * @return
     * @param productType
     * @param status
     */
    List<ProductDTO> getAllList(Integer productType, Integer status);
}
