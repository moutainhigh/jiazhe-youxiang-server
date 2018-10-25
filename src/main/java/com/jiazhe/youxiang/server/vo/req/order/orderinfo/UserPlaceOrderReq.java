package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：客户下单请求参数
 * @date 2018/10/24
 */
public class UserPlaceOrderReq extends BaseVO {

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("商品价格")
    private Integer productPrice;

    @ApiModelProperty("下单数量")
    private Integer count;

    @ApiModelProperty("订单地址")
    private String customerAddress;

    @ApiModelProperty("订单联系电话")
    private String customerMobile;

    @ApiModelProperty("订单接收人")
    private String customerName;

    @ApiModelProperty("客户留言")
    private String customerRemark;

    @ApiModelProperty("提供服务人员")
    private String workerName;

    @ApiModelProperty("服务人员电话")
    private String workerMobile;

    @ApiModelProperty("服务时间")
    private Date realServiceTime;

    @ApiModelProperty("代金券id")
    private Integer voucherId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

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

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }
}
