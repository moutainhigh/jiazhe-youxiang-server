package com.jiazhe.youxiang.server.dto.rechargecard.rc;

import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rc.RCResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode.RCExchangeCodeResp;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class RCDTO {

    private String name;

    private String description;

    private Integer projectId;

    private String cityCodes;

    private String productIds;

    private BigDecimal faceValue;

    private BigDecimal balance;

    private Date expiryTime;

    private CustomerResp customerResp;

    private RCExchangeCodeResp rcExchangeCodeResp;

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

    public RCExchangeCodeResp getRcExchangeCodeResp() {
        return rcExchangeCodeResp;
    }

    public void setRcExchangeCodeResp(RCExchangeCodeResp rcExchangeCodeResp) {
        this.rcExchangeCodeResp = rcExchangeCodeResp;
    }
}
