/*
 * Copyright (c) 2017 maoyan.com
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

    List<ProductDTO> getList(Integer productCategoryId, String name, Integer productType, List<String> cityIds, Integer status, Paging paging);

    void update(ProductUpdateDTO productUpdateDTO);

    void delete(Integer id);

    void updateStatus(Integer id, Integer status);
}
