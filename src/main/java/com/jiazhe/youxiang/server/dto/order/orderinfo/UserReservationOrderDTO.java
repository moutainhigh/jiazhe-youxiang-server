package com.jiazhe.youxiang.server.dto.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
public class UserReservationOrderDTO extends BaseObject {

    private static final long serialVersionUID = -5146329092962661235L;

    private Integer orderId;

    private String workerName;

    private String workerMobile;

    private Date realServiceTime;

    private BigDecimal cost;

    private String comments;

    private Byte status;

    private Date serviceTime;

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

    public Date getRealServiceTime() {
        return realServiceTime;
    }

    public void setRealServiceTime(Date realServiceTime) {
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

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }
}
