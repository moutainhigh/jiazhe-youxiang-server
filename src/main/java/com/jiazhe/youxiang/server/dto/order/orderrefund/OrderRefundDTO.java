package com.jiazhe.youxiang.server.dto.order.orderrefund;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/11/9.
 */
public class OrderRefundDTO extends BaseObject {

    private static final long serialVersionUID = 6589158636539180860L;

    private Integer id;

    private String orderCode;

    private Integer orderId;

    private Byte refundType;

    private Integer rechargeCardId;

    private Integer voucherId;

    private BigDecimal refundMoney;

    private String serialNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Byte getRefundType() {
        return refundType;
    }

    public void setRefundType(Byte refundType) {
        this.refundType = refundType;
    }

    public Integer getRechargeCardId() {
        return rechargeCardId;
    }

    public void setRechargeCardId(Integer rechargeCardId) {
        this.rechargeCardId = rechargeCardId;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
