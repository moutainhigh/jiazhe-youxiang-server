/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.customer;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/23
 */
public class CustomerResp extends BaseVO {
    private static final long serialVersionUID = -4737374455334097327L;
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("剩余积分")
    private BigDecimal pointBalance;
    @ApiModelProperty("充值卡余额")
    private BigDecimal rechargeCardBalance;
    @ApiModelProperty("代金劵张数")
    private Integer voucherCount;
    @ApiModelProperty("客户名称")
    private String name;
    @ApiModelProperty("客户备注")
    private String remark;
    @ApiModelProperty("默认地址")
    private AddressResp defaultAddress;

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

    public BigDecimal getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(BigDecimal pointBalance) {
        this.pointBalance = pointBalance;
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

    public AddressResp getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(AddressResp defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}