/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.djbx;

/**
 * @author tu
 * @version 1.0
 * @description 积分查询的body参数
 * @created 2020-05-19 23:03
 */
public class PointsQueryParam {

    private String agentCode;

    public PointsQueryParam(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

}
