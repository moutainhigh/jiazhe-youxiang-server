/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.djbx;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author tu
 * @version 1.0
 * @description 积分查询接口请求参数
 * @created 2020-05-18 22:54
 */
public class PointsQueryReq extends BaseVO {

    private HeaderReq header;

    private PointsQuery pointsQuery;

    public PointsQueryReq(String serialNo, String transCode, String sysCode, String agentCode) {
        HeaderReq header = new HeaderReq(serialNo, transCode, sysCode);
        this.header = header;
        PointsQuery pointsQuery = new PointsQuery(agentCode);
        this.pointsQuery = pointsQuery;
    }


    public HeaderReq getHeader() {
        return header;
    }

    public void setHeader(HeaderReq header) {
        this.header = header;
    }

    public PointsQuery getPointsQuery() {
        return pointsQuery;
    }

    public void setPointsQuery(PointsQuery pointsQuery) {
        this.pointsQuery = pointsQuery;
    }

}

class PointsQuery {

    private String agentCode;

    public PointsQuery(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}
