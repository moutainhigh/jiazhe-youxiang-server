/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dao.mapper.manual.product;

import com.jiazhe.youxiang.server.domain.po.ProductPricePO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
public interface ProductPricePOManualMapper {

    void batchDelete(@Param("ids") List<Integer> ids);

    void batchUpdate(@Param("productId") Integer productId, @Param("cityCodes") List<String> cityCodes, @Param("price") BigDecimal price);

    List<Integer> getProductIdsByCityIds(@Param("cityCodes") List<String> cityCodes);

    void batchAddPrice(@Param("list") List<ProductPricePO> productPricePOList);
}