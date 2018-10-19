/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.product;

import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类列表查询Req
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductCategoryListReq extends OffsetLimitReq {

    private static final long serialVersionUID = 32206764090603605L;
    @ApiModelProperty("商品分类名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}