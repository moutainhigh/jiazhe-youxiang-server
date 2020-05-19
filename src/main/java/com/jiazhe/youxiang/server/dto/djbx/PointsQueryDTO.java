package com.jiazhe.youxiang.server.dto.djbx;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * @author TU
 * @description
 * @date 2020-05-19.
 */
public class PointsQueryDTO extends BaseObject {

    private String agentCode;

    private Integer points;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
