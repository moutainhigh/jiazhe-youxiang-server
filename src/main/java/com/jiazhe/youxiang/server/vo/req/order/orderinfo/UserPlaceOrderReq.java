package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
public class UserPlaceOrderReq extends BaseVO {

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("扣分商品id")
    private Integer productId;

    @ApiModelProperty("服务商品id")
    private Integer serviceProductId;

    @ApiModelProperty("下单城市code")
    private String customerCityCode;

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
    private Long realServiceTime;

    /**
     * 使用的积分卡ids
     */
    @ApiModelProperty("积分卡ids")
    private String pointIds;

    /**
     * 使用的代金券ids
     */
    @ApiModelProperty("代金券ids")
    private String voucherIds;

    /**
     * 使用的充值卡ids
     */
    @ApiModelProperty("充值卡ids")
    private String rechargeCardIds;

    @ApiModelProperty("是否支持微信支付 是：'true' 否：'false'")
    private String cashSupport;

    @ApiModelProperty("订单成本")
    private BigDecimal cost;

    @ApiModelProperty("订单备注")
    private String comments;

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

    public Integer getServiceProductId() {
        return serviceProductId;
    }

    public void setServiceProductId(Integer serviceProductId) {
        this.serviceProductId = serviceProductId;
    }

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
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

    public Long getRealServiceTime() {
        return realServiceTime;
    }

    public void setRealServiceTime(Long realServiceTime) {
        this.realServiceTime = realServiceTime;
    }

    public String getVoucherIds() {
        return voucherIds;
    }

    public void setVoucherIds(String voucherIds) {
        this.voucherIds = voucherIds;
    }

    public String getRechargeCardIds() {
        return rechargeCardIds;
    }

    public void setRechargeCardIds(String rechargeCardIds) {
        this.rechargeCardIds = rechargeCardIds;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPointIds() {
        return pointIds;
    }

    public void setPointIds(String pointIds) {
        this.pointIds = pointIds;
    }

    public String getCashSupport() {
        return cashSupport;
    }

    public void setCashSupport(String cashSupport) {
        this.cashSupport = cashSupport;
    }
}
