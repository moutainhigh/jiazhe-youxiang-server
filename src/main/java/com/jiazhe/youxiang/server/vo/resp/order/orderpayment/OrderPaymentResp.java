package com.jiazhe.youxiang.server.vo.resp.order.orderpayment;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/11/7
 */
public class OrderPaymentResp extends BaseVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("订单号")
    private String orderCode;

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("支付类型 1为充值卡支付，2为代金券支付，3为在线支付 ")
    private Byte payType;

    @ApiModelProperty("充值卡id")
    private Integer rechargeCardId;

    @ApiModelProperty("代金券id")
    private Integer voucherId;

    @ApiModelProperty("支付金额")
    private BigDecimal payMoney;

    @ApiModelProperty("第三方支付流水号")
    private String serialNumber;

    @ApiModelProperty("订单信息")
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
