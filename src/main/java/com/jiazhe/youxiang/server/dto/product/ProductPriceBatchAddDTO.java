/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.product;

import java.math.BigDecimal;
import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/26
 */
public class ProductPriceBatchAddDTO {
    /**
     * 城市id集合
     */
    private List<String> cityCodes;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 价格
     */
    private BigDecimal price;

    public List<String> getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(List<String> cityCodes) {
        this.cityCodes = cityCodes;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}