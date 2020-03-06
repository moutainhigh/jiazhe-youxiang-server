/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.chargeoff;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 汇总数据Resp
 *
 * @author niexiao
 * @created 2020-03-06
 */
public class QuerySummaryResp extends BaseVO {

    private static final long serialVersionUID = 5295059737464632339L;
    @ApiModelProperty("总积分")
    private BigDecimal totalPoint;

    public BigDecimal getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(BigDecimal totalPoint) {
        this.totalPoint = totalPoint;
    }
}
