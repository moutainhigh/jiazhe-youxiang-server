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
 * 核销记录
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffInfoDTO {

    /**
     * 核销记录id
     */
    private Integer id;

    /**
     * 兑换城市code
     */
    private String cityCode;

    /**
     * 兑换城市名称
     */
    private String cityName;

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
    private List<ChargeOffPointDTO> pointList;

    /**
     * 核销总积分
     */
    private BigDecimal totalPoint;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户手机号
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
     * 提交者的id
     */
    private Integer submitterId;

    /**
     * 提交者的姓名
     */
    private String submitterName;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 提交时间
     */
    private Long submitterTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public List<ChargeOffPointDTO> getPointList() {
        return pointList;
    }

    public void setPointList(List<ChargeOffPointDTO> pointList) {
        this.pointList = pointList;
    }

    public BigDecimal getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(BigDecimal totalPoint) {
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

    public Integer getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Integer submitterId) {
        this.submitterId = submitterId;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSubmitterTime() {
        return submitterTime;
    }

    public void setSubmitterTime(Long submitterTime) {
        this.submitterTime = submitterTime;
    }
}
