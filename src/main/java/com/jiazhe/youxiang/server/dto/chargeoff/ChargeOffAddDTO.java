/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.chargeoff;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 添加核销记录
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffAddDTO {

    /**
     * 兑换城市code
     */
    private String cityCode;

    /**
     * 兑换银行信息
     */
    private String bankOutletsName;

    /**
     * 兑换类型 0-换商品，1-充积分
     */
    private Integer chargeOffType;

    /**
     * 核销密码集合
     */
    private List<String> keytList;

    /**
     * 核销总积分
     */
    private Integer totalPoint;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户电话
     */
    private String customerMobile;

    /**
     * 兑换商品价值
     */
    private BigDecimal productValue;

    /**
     * 提交状态 0-保存不提交，1-提交
     */
    private Integer status;

    /**
     * 备注信息
     */
    private String remark;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getBankOutletsName() {
        return bankOutletsName;
    }

    public void setBankOutletsName(String bankOutletsName) {
        this.bankOutletsName = bankOutletsName;
    }

    public Integer getChargeOffType() {
        return chargeOffType;
    }

    public void setChargeOffType(Integer chargeOffType) {
        this.chargeOffType = chargeOffType;
    }

    public List<String> getKeytList() {
        return keytList;
    }

    public void setKeytList(List<String> keytList) {
        this.keytList = keytList;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
