/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.chargeoff;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 汇总数据Resp
 *
 * @author niexiao
 * @created 2020-03-06
 */
public class QuerySummaryDTO {

    /**
     * 总积分
     */
    private BigDecimal totalPoint;

    /**
     * 总商品价值
     */
    private BigDecimal totalProductValue;

    public BigDecimal getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(BigDecimal totalPoint) {
        this.totalPoint = totalPoint;
    }

    public BigDecimal getTotalProductValue() {
        return totalProductValue;
    }

    public void setTotalProductValue(BigDecimal totalProductValue) {
        this.totalProductValue = totalProductValue;
    }
}
