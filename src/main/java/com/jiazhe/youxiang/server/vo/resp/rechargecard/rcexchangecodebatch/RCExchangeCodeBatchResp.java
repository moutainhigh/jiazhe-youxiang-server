package com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public class RCExchangeCodeBatchResp extends BaseVO{

    private static final long serialVersionUID = -2706189259377017376L;

    @ApiModelProperty("批次id")
    private Integer id;

    @ApiModelProperty("批次名称")
    private String name;

    @ApiModelProperty("兑换后充值卡名称")
    private String rechargeCardName;

    @ApiModelProperty("批次下兑换码数量")
    private Integer amount;

    @ApiModelProperty("面额")
    private BigDecimal faceValue;

    @ApiModelProperty("批次过期时间")
    private Long expiryTime;

    @ApiModelProperty("充值卡过期类型")
    private Byte expiryType;

    @ApiModelProperty("充值卡生效时间")
    private Long rechargeCardEffectiveTime;

    @ApiModelProperty("充值卡过期时间")
    private Long rechargeCardExpiryTime;

    @ApiModelProperty("过期间隔时间")
    private Integer validityPeriod;

    @ApiModelProperty("批次状态，0停用、1启用")
    private Byte status;

    @ApiModelProperty("是否是虚拟批次")
    private Byte isVirtual;

    @ApiModelProperty("批次下兑换码是否已经制作，0为未制作，1为已制作")
    private Byte isMade;

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
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Byte isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Byte getIsMade() {
        return isMade;
    }

    public void setIsMade(Byte isMade) {
        this.isMade = isMade;
    }

    public String getRechargeCardName() {
        return rechargeCardName;
    }

    public void setRechargeCardName(String rechargeCardName) {
        this.rechargeCardName = rechargeCardName;
    }

    public Long getRechargeCardEffectiveTime() {
        return rechargeCardEffectiveTime;
    }

    public void setRechargeCardEffectiveTime(Long rechargeCardEffectiveTime) {
        this.rechargeCardEffectiveTime = rechargeCardEffectiveTime;
    }

    public Byte getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(Byte expiryType) {
        this.expiryType = expiryType;
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
}
