/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.chargeoff;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 编辑核销记录Req
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffUpdateReq extends BaseVO {

    private static final long serialVersionUID = -5978850408317122281L;
    @ApiModelProperty("核销记录id")
    private Integer id;

    @ApiModelProperty("兑换城市code")
    private String cityCode;

    @ApiModelProperty("兑换银行信息")
    private String bankOutletsName;

    @ApiModelProperty("兑换类型 0-换商品，1-充积分")
    private Integer chargeOffType;

    @ApiModelProperty("核销密码集合")
    private List<String> keytList;

    @ApiModelProperty("核销总积分")
    private BigDecimal totalPoint;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户手机")
    private String customerMobile;

    @ApiModelProperty("兑换商品价值")
    private BigDecimal productValue;

    @ApiModelProperty("提交状态 0-保存不提交，1-提交")
    private Integer status;

    @ApiModelProperty("备注信息")
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
