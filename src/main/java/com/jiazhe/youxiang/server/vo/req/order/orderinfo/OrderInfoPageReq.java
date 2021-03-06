package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

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

    @ApiModelProperty("扣分商品id")
    private Integer productId;

    @ApiModelProperty("服务商品id")
    private Integer serviceProductId;

    @ApiModelProperty("服务时间起")
    private Long realServiceTimeStart;

    @ApiModelProperty("服务时间止")
    private Long realServiceTimeEnd;

    @ApiModelProperty("城市")
    private String customerCityCode;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getServiceProductId() {
        return serviceProductId;
    }

    public void setServiceProductId(Integer serviceProductId) {
        this.serviceProductId = serviceProductId;
    }

    public Long getRealServiceTimeStart() {
        return realServiceTimeStart;
    }

    public void setRealServiceTimeStart(Long realServiceTimeStart) {
        this.realServiceTimeStart = realServiceTimeStart;
    }

    public Long getRealServiceTimeEnd() {
        return realServiceTimeEnd;
    }

    public void setRealServiceTimeEnd(Long realServiceTimeEnd) {
        this.realServiceTimeEnd = realServiceTimeEnd;
    }

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
    }
}
