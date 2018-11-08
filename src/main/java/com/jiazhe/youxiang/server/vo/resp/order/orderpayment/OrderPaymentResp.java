package com.jiazhe.youxiang.server.vo.resp.order.orderpayment;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/11/7
 */
public class OrderPaymentResp extends BaseVO {

    private Integer id;

    private String orderCode;

    private Integer orderId;

    private Byte payType;

    private Integer rechargeCardId;

    private Integer voucherId;

    private BigDecimal payMoney;

    private String serialNumber;

    private OrderInfoResp orderInfoResp;

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

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
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

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public OrderInfoResp getOrderInfoResp() {
        return orderInfoResp;
    }

    public void setOrderInfoResp(OrderInfoResp orderInfoResp) {
        this.orderInfoResp = orderInfoResp;
    }
}
