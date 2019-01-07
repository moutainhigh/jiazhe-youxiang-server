package com.jiazhe.youxiang.server.dto.voucher.exchangecode;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/11/4
 */
public class VoucherExchangeCodeEditDTO extends BaseObject {

    private static final long serialVersionUID = -4753245377896796359L;

    private Integer id;

    private String voucherName;

    private String batchDescription;

    private String cityCodes;

    private String productIds;

    private Date expiryTime;

    private Date voucherEffectiveTime;

    private Date voucherExpiryTime;

    private Integer validityPeriod;

    private Byte expiryType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getVoucherEffectiveTime() {
        return voucherEffectiveTime;
    }

    public void setVoucherEffectiveTime(Date voucherEffectiveTime) {
        this.voucherEffectiveTime = voucherEffectiveTime;
    }
}
