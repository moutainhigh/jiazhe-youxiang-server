package com.jiazhe.youxiang.server.dto.order.orderpayment;

import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/11/7
 */
public class OrderPaymentDTO {

    private Integer id;

    private String orderCode;

    private Integer orderId;

    private Byte payType;

    private Integer rechargeCardId;

    private Integer voucherId;

    private BigDecimal payMoney;

    private String serialNumber;

    private OrderInfoDTO orderInfoDTO;

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

    public OrderInfoDTO getOrderInfoDTO() {
        return orderInfoDTO;
    }

    public void setOrderInfoDTO(OrderInfoDTO orderInfoDTO) {
        this.orderInfoDTO = orderInfoDTO;
    }
}
