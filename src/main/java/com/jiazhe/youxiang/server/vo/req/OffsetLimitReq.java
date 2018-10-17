/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页信息对象
 *
 * @author niexiao
 * @created 2018/10/17
 */
public class OffsetLimitReq extends BaseVO {

    @NotNull
    @Min(0)
    @ApiModelProperty("分页的偏移量")
    private Integer offset;

    @NotNull
    @Min(1)
    @ApiModelProperty("该页显示的数目")
    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}