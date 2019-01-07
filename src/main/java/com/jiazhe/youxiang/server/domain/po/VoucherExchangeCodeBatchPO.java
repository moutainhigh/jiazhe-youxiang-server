package com.jiazhe.youxiang.server.domain.po;

import java.io.Serializable;
import java.util.Date;

public class VoucherExchangeCodeBatchPO implements Serializable {
    private Integer id;

    private String name;

    private String voucherName;

    private Integer projectId;

    private Byte isMade;

    private String description;

    private Integer amount;

    private String cityCodes;

    private String productIds;

    private Integer count;

    private Date expiryTime;

    private Date voucherEffectiveTime;

    private Date voucherExpiryTime;

    private Integer validityPeriod;

    private Byte expiryType;

    private Byte status;

    private String extInfo;

    private Byte isDeleted;

    private Date addTime;

    private Date modTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName == null ? null : voucherName.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Byte getIsMade() {
        return isMade;
    }

    public void setIsMade(Byte isMade) {
        this.isMade = isMade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String cityCodes) {
        this.cityCodes = cityCodes == null ? null : cityCodes.trim();
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds == null ? null : productIds.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getVoucherEffectiveTime() {
        return voucherEffectiveTime;
    }

    public void setVoucherEffectiveTime(Date voucherEffectiveTime) {
        this.voucherEffectiveTime = voucherEffectiveTime;
    }

    public Date getVoucherExpiryTime() {
        return voucherExpiryTime;
    }

    public void setVoucherExpiryTime(Date voucherExpiryTime) {
        this.voucherExpiryTime = voucherExpiryTime;
    }

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Byte getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(Byte expiryType) {
        this.expiryType = expiryType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}