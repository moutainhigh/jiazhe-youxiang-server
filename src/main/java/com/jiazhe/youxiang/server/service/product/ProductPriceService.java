/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.product;

import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.vo.Paging;

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

    List<ProductPriceDTO> getAllPriceList(Integer productId);

    List<ProductPriceDTO> getPriceList(Integer productId, String cityName, String cityCode, Integer status, Paging paging);

    List<Integer> getProductIdsByCityCodes(List<String> cityCodes);

    void batchAddAndUpdatePrice(Integer productId, List<String> cityCodes, BigDecimal price);

    ProductPriceDTO getPriceById(Integer id);

    ProductPriceDTO getPriceByCity(Integer productId, String cityCode);

    void batchDeletePrice(List<Integer> ids);

    Map<Integer, List<ProductPriceDTO>> getPriceMap(List<Integer> productIds, List<String> cityCodes, Integer status);

    void updatePriceStatus(Integer id, Integer status);

    void updatePrice(Integer id, BigDecimal price, Integer status);

    void batchUpdatePriceStatus(Integer productId, Integer status);
}