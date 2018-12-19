/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.product;

import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类列表查询Req
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductCategoryListReq extends PageSizeNumReq {

    private static final long serialVersionUID = 32206764090603605L;
    @ApiModelProperty("商品大类名称")
    private String name;

    @ApiModelProperty("商品大类上架状态 0:下架,1:上架")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}