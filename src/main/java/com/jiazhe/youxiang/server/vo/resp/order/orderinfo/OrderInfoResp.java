package com.jiazhe.youxiang.server.vo.resp.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseObject;
import com.jiazhe.youxiang.server.vo.BaseVO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class OrderInfoResp extends BaseVO {

    private Integer id;

    private String orderCode;

    private Integer customerId;

    private Integer productId;

    private Integer productPrice;

    private Integer count;

    private String customerAddress;

    private String customerMobile;

    private String customerName;

    private String customerRemark;

    private String workerName;

    private String workerMobile;

    private Date orderTime;

    private Date serviceTime;

    private Date realServiceTime;

    private BigDecimal payRechargeCard;

    private Integer payVoucher;

    private BigDecimal payCash;

    private BigDecimal totalCost;

    private String comments;

    private Byte type;

    private Byte status;

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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Date getRealServiceTime() {
        return realServiceTime;
    }

    public void setRealServiceTime(Date realServiceTime) {
        this.realServiceTime = realServiceTime;
    }

    public BigDecimal getPayRechargeCard() {
        return payRechargeCard;
    }

    public void setPayRechargeCard(BigDecimal payRechargeCard) {
        this.payRechargeCard = payRechargeCard;
    }

    public Integer getPayVoucher() {
        return payVoucher;
    }

    public void setPayVoucher(Integer payVoucher) {
        this.payVoucher = payVoucher;
    }

    public BigDecimal getPayCash() {
        return payCash;
    }

    public void setPayCash(BigDecimal payCash) {
        this.payCash = payCash;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
