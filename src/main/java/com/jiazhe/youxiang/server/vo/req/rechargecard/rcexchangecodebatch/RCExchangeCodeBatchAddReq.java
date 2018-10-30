package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：充值卡兑换码批次信息新建请求参数
 * @date 2018/10/21
 */
public class RCExchangeCodeBatchAddReq extends BaseVO {

    private static final long serialVersionUID = 4463662904507337273L;

    @ApiModelProperty("批次名称")
    private String name;

    @ApiModelProperty("兑换成充值卡的名称")
    private String rechargeCardName;

    @ApiModelProperty("是否是虚拟批次")
    private Byte isVirtual;

    @ApiModelProperty("兑换码数量")
    private Integer amount;

    @ApiModelProperty("面额")
    private BigDecimal faceValue;

    @ApiModelProperty("对应项目id")
    private Integer projectId;

    @ApiModelProperty("对应城市ids")
    private String cityIds;

    @ApiModelProperty("对应商品ids")
    private String productIds;

    @ApiModelProperty("批次过期时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryTime;

    @ApiModelProperty("充值卡过期时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rechargeCardExpiryTime;

    @ApiModelProperty("自兑换之日起，充值卡**天内有效")
    private Integer validityPeriod;

    @ApiModelProperty("充值卡过期类型")
    private Byte expiryType;

    @ApiModelProperty("描述信息")
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
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

    public String getRechargeCardName() {
        return rechargeCardName;
    }

    public void setRechargeCardName(String rechargeCardName) {
        this.rechargeCardName = rechargeCardName;
    }

    public Byte getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Byte isVirtual) {
        this.isVirtual = isVirtual;
    }
}
