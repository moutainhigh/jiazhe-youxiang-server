/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.customer;

import java.io.Serializable;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/31
 */
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = -3643680631608388108L;
    /**
     * 关联的客户ID,必填
     */
    private Integer id;

    /**
     * 关联的客户ID,必填
     */
    private Integer customerId;
    /**
     * 联系人性别,必填
     */
    private Integer gender;
    /**
     * 联系人地址所在城市code,必填
     */
    private String cityCode;
    /**
     * 联系人地址所在城市名称,必填
     */
    private String cityName;
    /**
     * 联系人地址详细,必填
     */
    private String address;
    /**
     * 联系人电话号码,必填
     */
    private String mobile;
    /**
     * 联系人姓名,必填
     */
    private String name;
    /**
     * 联系人地址备注,选填
     */
    private String remark;
    /**
     * 是否是默认，0-非默认，1-默认
     */
    private Integer isDefault;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}