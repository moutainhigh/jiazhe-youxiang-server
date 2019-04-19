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
    private String status ;

    @ApiModelProperty("下单客户手机号")
    private String mobile;

    @ApiModelProperty("收货手机号")
    private String customerMobile;

    @ApiModelProperty("下单时间起")
    private Long orderStartTime;

    @ApiModelProperty("下单时间止")
    private Long orderEndTime;

    @ApiModelProperty("服务人员电话")
    private String workerMobile;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Long getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Long orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Long getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Long orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }
}
