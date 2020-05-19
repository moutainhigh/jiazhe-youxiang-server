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
 * @description 积分核销接口请求参数
 * @created 2020-05-19 7:47
 */
public class PointsConsumeReq extends BaseVO {

    private HeaderReq header;

    private PointsConsumeParam pointsConsume;

    public PointsConsumeReq(HeaderReq header, PointsConsumeParam pointsConsume) {
        this.header = header;
        this.pointsConsume = pointsConsume;
    }

    public HeaderReq getHeader() {
        return header;
    }

    public void setHeader(HeaderReq header) {
        this.header = header;
    }

    public PointsConsumeParam getPointsConsume() {
        return pointsConsume;
    }

    public void setPointsConsume(PointsConsumeParam pointsConsume) {
        this.pointsConsume = pointsConsume;
    }

}

