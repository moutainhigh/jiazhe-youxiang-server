package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class UserReservationOrderReq {

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("服务人员姓名")
    private String workerName;

    @ApiModelProperty("服务人联系方式")
    private String workerMobile;

    @ApiModelProperty("真实服务时间")
    private Long realServiceTime;

    @ApiModelProperty("订单成本")
    private BigDecimal cost;

    @ApiModelProperty("订单备注")
    private String comments;

    @ApiModelProperty("订单状态")
    private Byte status;

    @ApiModelProperty("预约时间")
    private Long serviceTime;

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }

    public Long getRealServiceTime() {
        return realServiceTime;
    }

    public void setRealServiceTime(Long realServiceTime) {
        this.realServiceTime = realServiceTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Long serviceTime) {
        this.serviceTime = serviceTime;
    }
}
