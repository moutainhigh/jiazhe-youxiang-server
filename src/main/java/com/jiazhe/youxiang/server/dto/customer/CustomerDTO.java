/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.customer;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/23
 */
public class CustomerDTO extends BaseObject{

    private static final long serialVersionUID = -765735040551457029L;
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
     * 充值卡余额
     */
    private BigDecimal rechargeCardBalance;
    /**
     * 代金劵张数
     */
    private Integer voucherCount;
    /**
     * 客户备注
     */
    private String remark;
    /**
     * 默认地址ID
     */
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

    public BigDecimal getRechargeCardBalance() {
        return rechargeCardBalance;
    }

    public void setRechargeCardBalance(BigDecimal rechargeCardBalance) {
        this.rechargeCardBalance = rechargeCardBalance;
    }

    public Integer getVoucherCount() {
        return voucherCount;
    }

    public void setVoucherCount(Integer voucherCount) {
        this.voucherCount = voucherCount;
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