package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class AuditRecordSaveReq extends BaseVO {

    private static final long serialVersionUID = 7162909158722201511L;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户电话")
    private String customerMobile;

    @ApiModelProperty("兑换积分数量")
    private BigDecimal exchangePoint;

    @ApiModelProperty("兑换类型")
    private String exchangeType;

    @ApiModelProperty("赠送积分数量")
    private BigDecimal givingPoint;

    @ApiModelProperty("赠送类型")
    private String givingType;

    @ApiModelProperty("消费凭证urls，用逗号连接")
    private String imgUrls;

    @ApiModelProperty("备注")
    private String remark;

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

    public BigDecimal getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(BigDecimal exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public BigDecimal getGivingPoint() {
        return givingPoint;
    }

    public void setGivingPoint(BigDecimal givingPoint) {
        this.givingPoint = givingPoint;
    }

    public String getGivingType() {
        return givingType;
    }

    public void setGivingType(String givingType) {
        this.givingType = givingType;
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
