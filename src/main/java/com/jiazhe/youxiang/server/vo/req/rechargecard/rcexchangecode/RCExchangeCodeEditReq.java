package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/11/1
 */
public class RCExchangeCodeEditReq extends BaseVO {

    @ApiModelProperty("兑换码id")
    private Integer id;

    @ApiModelProperty("充值卡名称")
    private String rechargeCardName;

    @ApiModelProperty("批次描述信息")
    private String batchDescription;

    @ApiModelProperty("适用的城市code，多个用,连接")
    private String cityCodes;

    @ApiModelProperty("适用的商品id，多个用,连接")
    private String productIds;

    @ApiModelProperty("兑换码本身过期时间")
    private Long expiryTime;

    @ApiModelProperty("充值卡生效时间")
    private Long rechargeCardEffectiveTime;

    @ApiModelProperty("充值卡过期时间")
    private Long rechargeCardExpiryTime;

    @ApiModelProperty("自兑换之日起，充值卡有效期天数")
    private Integer validityPeriod;

    @ApiModelProperty("充值卡过期时间指定类型")
    private Byte expiryType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getRechargeCardExpiryTime() {
        return rechargeCardExpiryTime;
    }

    public void setRechargeCardExpiryTime(Long rechargeCardExpiryTime) {
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

    public Long getRechargeCardEffectiveTime() {
        return rechargeCardEffectiveTime;
    }

    public void setRechargeCardEffectiveTime(Long rechargeCardEffectiveTime) {
        this.rechargeCardEffectiveTime = rechargeCardEffectiveTime;
    }
}
