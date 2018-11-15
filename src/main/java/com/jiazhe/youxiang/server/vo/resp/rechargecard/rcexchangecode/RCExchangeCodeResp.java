package com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class RCExchangeCodeResp extends BaseObject {

    @ApiModelProperty("兑换码id")
    private Integer id;

    @ApiModelProperty("所属批次名称")
    private String batchName;

    @ApiModelProperty("充值卡名称")
    private String rechargeCardName;

    @ApiModelProperty("批次描述信息")
    private String batchDescription;

    @ApiModelProperty("可用的城市code，多个用,连接")
    private String cityCodes;

    @ApiModelProperty("可用的商品i，多个用,连接")
    private String productIds;

    @ApiModelProperty("兑换码")
    private String code;

    @ApiModelProperty("兑换密钥")
    private String keyt;

    @ApiModelProperty("充值卡面额")
    private BigDecimal faceValue;

    @ApiModelProperty("兑换码过期时间")
    private Date expiryTime;

    @ApiModelProperty("充值卡过期时间")
    private Date rechargeCardExpiryTime;

    @ApiModelProperty("自兑换之日起，有效期的天数")
    private Integer validityPeriod;

    @ApiModelProperty("充值卡过期时间指定类型")
    private Byte expiryType;

    @ApiModelProperty("兑换码状态，1为启用 0为停用")
    private Byte status;

    @ApiModelProperty("是否已经使用，1为已经使用，0为未使用")
    private Byte used;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
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

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
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

    public String getRechargeCardName() {
        return rechargeCardName;
    }

    public void setRechargeCardName(String rechargeCardName) {
        this.rechargeCardName = rechargeCardName;
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

    public Date getRechargeCardExpiryTime() {
        return rechargeCardExpiryTime;
    }

    public void setRechargeCardExpiryTime(Date rechargeCardExpiryTime) {
        this.rechargeCardExpiryTime = rechargeCardExpiryTime;
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
}
