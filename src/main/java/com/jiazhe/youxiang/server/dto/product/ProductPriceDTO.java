/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.product;

import java.math.BigDecimal;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/19
 */
public class ProductPriceDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 价格
     */
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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