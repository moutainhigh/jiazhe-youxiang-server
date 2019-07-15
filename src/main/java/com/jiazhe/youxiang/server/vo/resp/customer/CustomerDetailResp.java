/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.customer;

import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/23
 */
public class CustomerDetailResp extends CustomerResp {
    private static final long serialVersionUID = -610322010297002842L;

    //TODO nieixao 修改返回值类型

    @ApiModelProperty("充值卡信息")
    private String rechargeCards;

    @ApiModelProperty("订单信息")
    private String orderInfos;

    @ApiModelProperty("地址信息")
    private String address;

    public String getRechargeCards() {
        return rechargeCards;
    }

    public void setRechargeCards(String rechargeCards) {
        this.rechargeCards = rechargeCards;
    }

    public String getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(String orderInfos) {
        this.orderInfos = orderInfos;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}