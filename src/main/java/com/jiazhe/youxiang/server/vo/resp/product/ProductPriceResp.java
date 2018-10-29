/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.product;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/19
 */
public class ProductPriceResp extends BaseVO {

    private static final long serialVersionUID = 8314427313494152647L;
    @ApiModelProperty("商品价格D")
    private Integer id;

    @ApiModelProperty("城市id")
    private Integer cityId;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("价格")
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