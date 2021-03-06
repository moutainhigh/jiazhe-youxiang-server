/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.syscity;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * 城市列表信息查询
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class SysCityListReq extends PageSizeNumReq {

    private static final long serialVersionUID = 8343280757693372496L;
    @ApiModelProperty("上级城市Code")
    private String parentCode;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}