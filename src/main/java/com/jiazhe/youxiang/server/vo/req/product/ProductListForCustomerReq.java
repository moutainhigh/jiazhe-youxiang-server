/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.product;

import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * 根据商品分类和所在城市获得商品列表Req
 *
 * @author niexiao
 * @created 2018/10/31
 */
public class ProductListForCustomerReq extends OffsetLimitReq {

    private static final long serialVersionUID = 2486773458943698457L;
    @ApiModelProperty("商品大类Id")
    private Integer productCategoryId;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品分类，0-服务，1-电子卡")
    private Integer productType;
    @ApiModelProperty("可用城市code集合")
    private String cityCode;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}