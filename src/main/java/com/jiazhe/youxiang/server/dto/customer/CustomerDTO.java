/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.customer;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/23
 */
public class CustomerDTO {

    /**
     * id
     */
    private Integer id;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 客户备注
     */
    private String remark;
    /**
     * 默认地址ID
     */
    //TODO niexiao 修改成默认地址
    private AddressDTO defaultAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public AddressDTO getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(AddressDTO defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}