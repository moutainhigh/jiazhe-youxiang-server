package com.jiazhe.youxiang.server.domain.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AuditRecordPO implements Serializable {
    private Integer id;

    private String pointIds;

    private String customerName;

    private String customerMobile;

    private BigDecimal exchangePoint;

    private BigDecimal givingPoint;

    private String givingType;

    private String auditReason;

    private String remark;

    private String imgUrls;

    private String posCode;

    private Date tradeTime;

    private String cardNo;

    private String bankOutletsName;

    private Integer submitterId;

    private String submitterName;

    private Byte status;

    private Date auditTime;

    private Integer auditorId;

    private String auditorName;

    private Integer version;

    private String extInfo;

    private Byte isDeleted;

    private Date addTime;

    private Date modTime;

    private Byte chargeReceiptStatus;

    private String pointCodes;

    private BigDecimal productValue;

    private Byte exchangeType;

    private Date submitTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPointIds() {
        return pointIds;
    }

    public void setPointIds(String pointIds) {
        this.pointIds = pointIds == null ? null : pointIds.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile == null ? null : customerMobile.trim();
    }

    public BigDecimal getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(BigDecimal exchangePoint) {
        this.exchangePoint = exchangePoint;
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
        this.givingType = givingType == null ? null : givingType.trim();
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason == null ? null : auditReason.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls == null ? null : imgUrls.trim();
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode == null ? null : posCode.trim();
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getBankOutletsName() {
        return bankOutletsName;
    }

    public void setBankOutletsName(String bankOutletsName) {
        this.bankOutletsName = bankOutletsName == null ? null : bankOutletsName.trim();
    }

    public Integer getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Integer submitterId) {
        this.submitterId = submitterId;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName == null ? null : submitterName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo == null ? null : extInfo.trim();
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Byte getChargeReceiptStatus() {
        return chargeReceiptStatus;
    }

    public void setChargeReceiptStatus(Byte chargeReceiptStatus) {
        this.chargeReceiptStatus = chargeReceiptStatus;
    }

    public String getPointCodes() {
        return pointCodes;
    }

    public void setPointCodes(String pointCodes) {
        this.pointCodes = pointCodes == null ? null : pointCodes.trim();
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public Byte getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Byte exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}