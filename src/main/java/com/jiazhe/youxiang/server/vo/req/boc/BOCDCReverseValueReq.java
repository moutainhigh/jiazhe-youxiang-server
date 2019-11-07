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
 * @description 中行储蓄卡（BOCDC）退货查询请求参数
 * @created 2019-09-08 11:30
 */
public class BOCDCReverseValueReq extends BaseVO {

    private String orderNo;

    private String channel;

    private String ebuyId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getEbuyId() {
        return ebuyId;
    }

    public void setEbuyId(String ebuyId) {
        this.ebuyId = ebuyId;
    }
}
