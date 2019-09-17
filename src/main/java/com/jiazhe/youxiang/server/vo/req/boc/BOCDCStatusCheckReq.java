/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * 中行储蓄卡使用状态核对实时接口Req
 *
 * @author niexiao
 * @created 2019-09-09
 */
public class BOCDCStatusCheckReq extends BaseVO {

    private static final long serialVersionUID = 559440030438666625L;
    /**
     * 订单编号（需要加密） 不为空
     */
    private String orderNo;

    /**
     * 使用状态 不为空
     * 未使用：00
     * 已使用：01
     */
    private String useStatus;

    /**
     * 客户使用时间 可为空（已使用不为空）
     */
    private String useTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }
}