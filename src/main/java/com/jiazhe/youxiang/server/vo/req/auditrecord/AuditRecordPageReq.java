package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class AuditRecordPageReq extends PageSizeNumReq {

    @ApiModelProperty("客户信息，姓名或电话")
    private String customerInfo;

    @ApiModelProperty("消费记录状态")
    private Byte status;

    @ApiModelProperty("凭证完成状态")
    private Byte chargeReceiptStatus;

    @ApiModelProperty("提交人")
    private String submitterName;

    @ApiModelProperty("积分卡卡号")
    private String pointCodes;

    @ApiModelProperty("兑换积分")
    private String exchangePoint;

    @ApiModelProperty("提交时间起")
    private Long submitStartTime;

    @ApiModelProperty("提交时间止")
    private Long submitEndTime;

    @ApiModelProperty("兑换类型")
    private String exchangeType;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getChargeReceiptStatus() {
        return chargeReceiptStatus;
    }

    public void setChargeReceiptStatus(Byte chargeReceiptStatus) {
        this.chargeReceiptStatus = chargeReceiptStatus;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public Long getSubmitStartTime() {
        return submitStartTime;
    }

    public void setSubmitStartTime(Long submitStartTime) {
        this.submitStartTime = submitStartTime;
    }

    public Long getSubmitEndTime() {
        return submitEndTime;
    }

    public void setSubmitEndTime(Long submitEndTime) {
        this.submitEndTime = submitEndTime;
    }

    public String getPointCodes() {
        return pointCodes;
    }

    public void setPointCodes(String pointCodes) {
        this.pointCodes = pointCodes;
    }

    public String getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(String exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }
}
