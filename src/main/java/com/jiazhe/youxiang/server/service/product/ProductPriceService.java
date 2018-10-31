/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.product;

import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
public interface ProductPriceService {

    List<ProductPriceDTO> getListByProductId(Integer id);

    List<Integer> getProductIdsByCityCodes(List<String> cityCodes);

    void batchAddAndUpdatePrice(Integer productId, List<String> cityCodes, BigDecimal price);

    ProductPriceDTO getPriceById(Integer id);

    ProductPriceDTO getPriceByCity(Integer productId, String cityCode);

    void batchDeletePrice(List<Integer> ids);

    Map<Integer, List<ProductPriceDTO>> getPriceMap(List<Integer> productIds, List<String> cityCodes, Integer status);
}