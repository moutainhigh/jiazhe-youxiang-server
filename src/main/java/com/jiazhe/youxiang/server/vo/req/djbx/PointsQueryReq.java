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

    private PointsQueryParam pointsQuery;

    public PointsQueryReq(HeaderReq header, PointsQueryParam pointsQuery) {
        this.header = header;
        this.pointsQuery = pointsQuery;
    }


    public HeaderReq getHeader() {
        return header;
    }

    public void setHeader(HeaderReq header) {
        this.header = header;
    }

    public PointsQueryParam getPointsQuery() {
        return pointsQuery;
    }

    public void setPointsQuery(PointsQueryParam pointsQuery) {
        this.pointsQuery = pointsQuery;
    }

}

class PointsQueryParam {

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
