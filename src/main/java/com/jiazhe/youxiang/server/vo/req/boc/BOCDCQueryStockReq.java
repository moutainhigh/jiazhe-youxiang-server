/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author tu
 * @version 1.0
 * @description 中行储蓄卡（BOCDC）查询可用库存请求参数
 * @created 2019-09-08 11:16
 */
public class BOCDCQueryStockReq extends BaseVO {

    /**
     * 订单号（需要加密）
     */
    private String orderNo;

    /**
     * 下单时间
     */
    private String tranDate;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 礼品编号（需要加密）
     */
    private String giftNo;
    /**
     * 卡密有效天数
     */
    private String validDate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getGiftNo() {
        return giftNo;
    }

    public void setGiftNo(String giftNo) {
        this.giftNo = giftNo;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
}
