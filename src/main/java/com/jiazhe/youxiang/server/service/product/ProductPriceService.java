/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.product;

import com.jiazhe.youxiang.server.dto.product.ProductPriceBatchAddDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
public interface ProductPriceService {

    List<ProductPriceDTO> getListByProductId(Integer id);

    List<Integer> getProductIdsByCityCodes(List<String> cityCodes);

    void batchAddPrice(ProductPriceBatchAddDTO productPriceBatchAddDTO);

    ProductPriceDTO getPriceById(Integer id);

    ProductPriceDTO getPriceByCity(Integer productId, String cityCode);

    void batchDeletePrice(List<Integer> ids);

    void batchUpdatePrice(Integer productId, List<String> cityCodes, BigDecimal price);
}