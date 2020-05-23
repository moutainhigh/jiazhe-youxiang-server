package com.jiazhe.youxiang.server.dto.djbx;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2020-05-19.
 */
public class PointsQueryDTO extends BaseObject {

    private String agentCode;

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
