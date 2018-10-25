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
    @ApiModelProperty("模块名称")
    private String moduleName;
    @ApiModelProperty("操作")
    private String operate;
    @ApiModelProperty("日志级别")
    private Integer level;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}