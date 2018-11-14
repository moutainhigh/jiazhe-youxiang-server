package com.jiazhe.youxiang.server.dto.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class AppendOrderDTO{

    private Integer orderId;

    private Integer count;

    private BigDecimal cost;

    /**
     * 使用的代金券ids
     */
    private String voucherIds;

    /**
     * 使用的充值卡ids
     */
    private String rechargeCardIds;

    /**
     * 每张充值卡使用的金额
     */
    private String cardMoneys;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getVoucherIds() {
        return voucherIds;
    }

    public void setVoucherIds(String voucherIds) {
        this.voucherIds = voucherIds;
    }

    public String getRechargeCardIds() {
        return rechargeCardIds;
    }

    public void setRechargeCardIds(String rechargeCardIds) {
        this.rechargeCardIds = rechargeCardIds;
    }

    public String getCardMoneys() {
        return cardMoneys;
    }

    public void setCardMoneys(String cardMoneys) {
        this.cardMoneys = cardMoneys;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
