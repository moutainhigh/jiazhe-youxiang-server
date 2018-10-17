/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.syslog;

import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/17
 */
public class SysLogListReq extends OffsetLimitReq {

    private static final long serialVersionUID = 8827889561488904043L;
    @ApiModelProperty("日志类型")
    private Integer type;

    public Integer getType() {

        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}