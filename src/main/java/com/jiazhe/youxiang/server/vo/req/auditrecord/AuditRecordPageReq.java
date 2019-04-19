package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class AuditRecordPageReq extends PageSizeNumReq {

    @ApiModelProperty("客户电话")
    private String customerMobile;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("消费记录状态")
    private Byte status;

    @ApiModelProperty("凭证完成状态")
    private Byte chargeReceiptStatus;

    @ApiModelProperty("提交人")
    private String submitterName;

    @ApiModelProperty("卡号")
    private String pointCodes;

    @ApiModelProperty("提交时间起")
    private Long submitStartTime;

    @ApiModelProperty("提交时间止")
    private Long submitEndTime;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public Byte getChargeReceiptStatus() {
        return chargeReceiptStatus;
    }

    public void setChargeReceiptStatus(Byte chargeReceiptStatus) {
        this.chargeReceiptStatus = chargeReceiptStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
}
