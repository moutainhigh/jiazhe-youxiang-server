package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author tu
 * @description：后台查询订单输入参数
 * @date 2018/10/24
 */
public class OrderInfoPageReq extends PageSizeNumReq {

    @ApiModelProperty("订单号")
    private String orderCode;

    @ApiModelProperty("订单状态")
    private Byte status ;

    @ApiModelProperty("下单客户手机号")
    private String mobile;

    @ApiModelProperty("收货手机号")
    private String customerMobile;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("下单期间起")
    private Date orderStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("下单时间止")
    private Date orderEndTime;

    @ApiModelProperty("服务人员电话")
    private String workerMobile;


    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }
}
