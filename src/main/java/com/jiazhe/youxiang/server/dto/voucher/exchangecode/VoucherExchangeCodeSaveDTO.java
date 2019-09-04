package com.jiazhe.youxiang.server.dto.voucher.exchangecode;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public class VoucherExchangeCodeSaveDTO extends BaseObject{

    private static final long serialVersionUID = -4618334852757292949L;

    private Integer batchId;

    private String batchName;

    private String voucherName;

    private String batchDescription;

    private Integer projectId;

    private String cityCodes;

    private String productIds;

    private Integer count;

    private String code;

    private String keyt;

    private Date expiryTime;

    private Date voucherEffectiveTime;

    private Date voucherExpiryTime;

    private Integer validityPeriod;

    private Byte expiryType;

    private String bocccProductId;

    private Byte status;

    private Byte used;

    private Integer customerId;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getBatchDescription() {
        return batchDescription;
    }

    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String cityCodes) {
        this.cityCodes = cityCodes;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
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

    public String getBocccProductId() {
        return bocccProductId;
    }

    public void setBocccProductId(String bocccProductId) {
        this.bocccProductId = bocccProductId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getUsed() {
        return used;
    }

    public void setUsed(Byte used) {
        this.used = used;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getVoucherEffectiveTime() {
        return voucherEffectiveTime;
    }

    public void setVoucherEffectiveTime(Date voucherEffectiveTime) {
        this.voucherEffectiveTime = voucherEffectiveTime;
    }
}
