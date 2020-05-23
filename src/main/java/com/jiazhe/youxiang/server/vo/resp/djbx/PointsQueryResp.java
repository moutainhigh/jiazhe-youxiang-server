/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.djbx;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @version 1.0
 * @description 积分查询接口返回结果
 * @created 2020-05-18 22:54
 */
public class PointsQueryResp extends BaseObject {

    @ApiModelProperty("代理人code")
    private String agentCode;

    @ApiModelProperty("剩余积分数量")
    private BigDecimal points;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

}
