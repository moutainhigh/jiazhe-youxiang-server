/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.customer;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户地址添加Req
 *
 * @author niexiao
 * @created 2018/10/31
 */
public class AddressAddReq extends BaseVO {

    private static final long serialVersionUID = 8754219917487948111L;
    @ApiModelProperty("关联的客户ID,必填")
    private Integer customerId;
    @ApiModelProperty("联系人性别,必填")
    private Integer gender;
    @ApiModelProperty("联系人地址所在城市code,必填")
    private String cityCode;
    @ApiModelProperty("联系人地址详细,必填")
    private String address;
    @ApiModelProperty("联系人电话号码,必填")
    private String mobile;
    @ApiModelProperty("联系人姓名,必填")
    private String name;
    @ApiModelProperty("联系人地址备注,选填")
    private String remark;
    @ApiModelProperty("是否是默认，0-非默认，1-默认")
    private Integer isDefault;

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