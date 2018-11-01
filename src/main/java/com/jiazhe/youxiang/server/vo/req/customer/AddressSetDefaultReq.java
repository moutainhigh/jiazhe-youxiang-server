/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.customer;

import com.jiazhe.youxiang.server.vo.req.IdReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/11/1
 */
public class AddressSetDefaultReq extends IdReq {

    private static final long serialVersionUID = 4063242401269947872L;
    @ApiModelProperty("是否是默认，0-非默认，1-默认")
    private Integer isDefault;

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}