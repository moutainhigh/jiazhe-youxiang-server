/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.product;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;

/**
 * 商品价格DTO
 *
 * @author niexiao
 * @created 2018/10/19
 */
public class ProductPriceDTO extends BaseObject{

    private static final long serialVersionUID = -3643888829745678544L;
    /**
     * id
     */
    private Integer id;

    /**
     * 城市code
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品id
     */
    private Integer status;

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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}