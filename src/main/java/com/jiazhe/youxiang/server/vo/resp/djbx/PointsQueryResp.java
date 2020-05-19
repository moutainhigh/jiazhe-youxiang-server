/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.djbx;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * @author tu
 * @version 1.0
 * @description 积分查询接口返回结果
 * @created 2020-05-18 22:54
 */
public class PointsQueryResp extends BaseObject {

    private HeaderResp header;

    private PointsQueryResult pointsQuery;

    public PointsQueryResp() {
        HeaderResp header = new HeaderResp();
        this.header = header;
        PointsQueryResult pointsQuery = new PointsQueryResult();
        this.pointsQuery = pointsQuery;
    }

    public HeaderResp getHeader() {
        return header;
    }

    public void setHeader(HeaderResp header) {
        this.header = header;
    }

    public PointsQueryResult getPointsQuery() {
        return pointsQuery;
    }

    public void setPointsQuery(PointsQueryResult pointsQuery) {
        this.pointsQuery = pointsQuery;
    }

}

class PointsQueryResult {

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

    public static void main(String[] args) {
        PointsQueryResp resp = new PointsQueryResp();
        resp.getHeader().setResultCode("00");
        resp.getHeader().setResultMessage("成功");
        System.out.println(resp.toString());
    }
}
