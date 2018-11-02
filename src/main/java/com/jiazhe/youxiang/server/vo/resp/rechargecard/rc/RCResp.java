package com.jiazhe.youxiang.server.vo.resp.rechargecard.rc;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord.RCExchangeRecordResp;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class RCResp extends BaseVO{

    private Integer id;

    private String name;

    private String description;

    private Integer customerId;

    private Integer projectId;

    private String cityCodes;

    private String productIds;

    private BigDecimal faceValue;

    private BigDecimal balance;

    private Date expiryTime;

    private Byte status;

    private Date addTime;

    private CustomerResp customerResp;

    private RCExchangeRecordResp rcExchangeRecordResp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String cityCodes) {
        this.cityCodes = cityCodes;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public CustomerResp getCustomerResp() {
        return customerResp;
    }

    public void setCustomerResp(CustomerResp customerResp) {
        this.customerResp = customerResp;
    }

    public RCExchangeRecordResp getRcExchangeRecordResp() {
        return rcExchangeRecordResp;
    }

    public void setRcExchangeRecordResp(RCExchangeRecordResp rcExchangeRecordResp) {
        this.rcExchangeRecordResp = rcExchangeRecordResp;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
