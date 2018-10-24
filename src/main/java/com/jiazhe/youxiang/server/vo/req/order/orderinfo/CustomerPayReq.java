package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.req.IdReq;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class CustomerPayReq extends IdReq {

    private BigDecimal payCash;

    private BigDecimal payRechargeCard;

    public BigDecimal getPayCash() {
        return payCash;
    }

    public void setPayCash(BigDecimal payCash) {
        this.payCash = payCash;
    }

    public BigDecimal getPayRechargeCard() {
        return payRechargeCard;
    }

    public void setPayRechargeCard(BigDecimal payRechargeCard) {
        this.payRechargeCard = payRechargeCard;
    }
}
