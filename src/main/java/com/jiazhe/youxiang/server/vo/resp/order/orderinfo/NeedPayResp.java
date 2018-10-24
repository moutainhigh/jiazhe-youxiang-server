package com.jiazhe.youxiang.server.vo.resp.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class NeedPayResp extends BaseObject {

    private BigDecimal payRechargeCard;

    private BigDecimal payCash;

    public BigDecimal getPayRechargeCard() {
        return payRechargeCard;
    }

    public void setPayRechargeCard(BigDecimal payRechargeCard) {
        this.payRechargeCard = payRechargeCard;
    }

    public BigDecimal getPayCash() {
        return payCash;
    }

    public void setPayCash(BigDecimal payCash) {
        this.payCash = payCash;
    }
}
