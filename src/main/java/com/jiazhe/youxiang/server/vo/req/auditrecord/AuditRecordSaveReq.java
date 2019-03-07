package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class AuditRecordSaveReq extends BaseVO {

    private static final long serialVersionUID = 7162909158722201511L;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("保存还是提交【1保存 2提交】")
    private Byte status;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("银行信息")
    private String bankOutletsName;

    @ApiModelProperty("兑换类型")
    private Byte exchangeType;

    @ApiModelProperty("兑换积分数量")
    private BigDecimal exchangePoint;

    @ApiModelProperty("客户电话")
    private String customerMobile;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("兑换的积分卡卡号集合，逗号连接")
    private String pointCodes;

    @ApiModelProperty("实物价值")
    private BigDecimal productValue;

    @ApiModelProperty("消费凭证urls，用逗号连接")
    private String imgUrls;

    @ApiModelProperty("备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getBankOutletsName() {
        return bankOutletsName;
    }

    public void setBankOutletsName(String bankOutletsName) {
        this.bankOutletsName = bankOutletsName;
    }

    public Byte getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Byte exchangeType) {
        this.exchangeType = exchangeType;
    }

    public BigDecimal getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(BigDecimal exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPointCodes() {
        return pointCodes;
    }

    public void setPointCodes(String pointCodes) {
        this.pointCodes = pointCodes;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
