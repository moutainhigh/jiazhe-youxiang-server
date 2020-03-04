/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.chargeoff;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 核销密码积分信息集合
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffPointResp extends BaseVO {

    private static final long serialVersionUID = 3903682752567433853L;
    @ApiModelProperty("核销积分兑换码id")
    private Integer pointExchangeCodeId;

    @ApiModelProperty("核销积分卡名称")
    private String pointName;

    @ApiModelProperty("核销积分分值")
    private Integer pointValue;

    @ApiModelProperty("核销积分兑换码卡号")
    private String pointExchangeCodeCode;

    @ApiModelProperty("核销积分兑换码密码")
    private String pointExchangeCodeKeyt;

    public Integer getPointExchangeCodeId() {
        return pointExchangeCodeId;
    }

    public void setPointExchangeCodeId(Integer pointExchangeCodeId) {
        this.pointExchangeCodeId = pointExchangeCodeId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public Integer getPointValue() {
        return pointValue;
    }

    public void setPointValue(Integer pointValue) {
        this.pointValue = pointValue;
    }

    public String getPointExchangeCodeCode() {
        return pointExchangeCodeCode;
    }

    public void setPointExchangeCodeCode(String pointExchangeCodeCode) {
        this.pointExchangeCodeCode = pointExchangeCodeCode;
    }

    public String getPointExchangeCodeKeyt() {
        return pointExchangeCodeKeyt;
    }

    public void setPointExchangeCodeKeyt(String pointExchangeCodeKeyt) {
        this.pointExchangeCodeKeyt = pointExchangeCodeKeyt;
    }
}
