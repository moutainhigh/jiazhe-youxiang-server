package com.jiazhe.youxiang.server.dto.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/11/10
 */
public class PlaceOrderDTO extends BaseObject {

    private static final long serialVersionUID = 1198479968361607256L;

    private Byte type;

    private Integer customerId;

    private Integer productId;

    private Integer serviceProductId;

    private String customerCityCode;

    private Integer count;

    private String customerAddress;

    private String customerMobile;

    private String customerName;

    private String customerRemark;

    private String workerName;

    private String workerMobile;

    private Date serviceTime;

    private Date realServiceTime;

    /**
     * 使用的积分卡ids
     */
    private String pointIds;

    /**
     * 使用的代金券ids
     */
    private String voucherIds;

    /**
     * 使用的充值卡ids
     */
    private String rechargeCardIds;

    /**
     * 是否支持在线支付
     */
    private String cashSupport;

    private BigDecimal cost;

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

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
    }

    public String getPointIds() {
        return pointIds;
    }

    public void setPointIds(String pointIds) {
        this.pointIds = pointIds;
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

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getRealServiceTime() {
        return realServiceTime;
    }

    public void setRealServiceTime(Date realServiceTime) {
        this.realServiceTime = realServiceTime;
    }

    public String getCashSupport() {
        return cashSupport;
    }

    public void setCashSupport(String cashSupport) {
        this.cashSupport = cashSupport;
    }
}
