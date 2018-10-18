/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.project;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProjectListReq extends OffsetLimitReq {

    private static final long serialVersionUID = 403060575380013152L;
    @ApiModelProperty("项目名称")
    private Integer name;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }


}