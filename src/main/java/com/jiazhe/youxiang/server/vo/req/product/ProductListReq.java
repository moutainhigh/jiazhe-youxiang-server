/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.product;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductListReq extends PageSizeNumReq {

    private static final long serialVersionUID = 6324763709129013284L;
    @ApiModelProperty("商品大类Id")
    private Integer productCategoryId;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品分类，0-服务，1-电子卡")
    private Integer productType;
    @ApiModelProperty("可用城市code集合")
    private List<String> cityCodes;
    @ApiModelProperty("状态：0:下架,1:上架")
    private Integer status;

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

    public List<String> getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(List<String> cityCodes) {
        this.cityCodes = cityCodes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}