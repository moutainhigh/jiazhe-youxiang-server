/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.product;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/21
 */
public class GetProductPriceByCity extends BaseVO {
    private static final long serialVersionUID = 3701138133619850597L;
    @ApiModelProperty("商品Id")
    private Integer productId;

    @ApiModelProperty("城市Id")
    private Integer cityId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}