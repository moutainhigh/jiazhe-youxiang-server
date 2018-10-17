/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.syscity;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 城市列表信息查询
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class SysCityListReq extends BaseVO {

    private static final long serialVersionUID = 8343280757693372496L;
    @ApiModelProperty("上级城市Code，可空，为空时查询所有一级城市")
    private String parentCode ;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}