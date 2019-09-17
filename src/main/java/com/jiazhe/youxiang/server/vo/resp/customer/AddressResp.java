/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.customer;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/31
 */
public class AddressResp extends BaseVO{

    private static final long serialVersionUID = -9026002638300500984L;
    @ApiModelProperty("地址id")
    private Integer id;
    @ApiModelProperty("关联的客户ID")
    private Integer customerId;
    @ApiModelProperty("联系人性别")
    private Integer gender;
    @ApiModelProperty("联系人地址所在城市code")
    private String cityCode;
    @ApiModelProperty("联系人地址所在城市名称")
    private String cityName;
    @ApiModelProperty("联系人地址详细")
    private String address;
    @ApiModelProperty("联系人电话号码")
    private String mobile;
    @ApiModelProperty("联系人姓名")
    private String name;
    @ApiModelProperty("联系人地址备注")
    private String remark;
    @ApiModelProperty("是否是默认，0-非默认，1-默认")
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