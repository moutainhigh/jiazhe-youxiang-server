package com.jiazhe.youxiang.server.vo.resp.point.exchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public class PointExchangeCodeBatchEditResp extends BaseVO {

    @ApiModelProperty("批次id")
    private Integer id;

    @ApiModelProperty("批次名称")
    private String name;

    @ApiModelProperty("积分卡名称")
    private String pointName;

    @ApiModelProperty("是否是虚拟批次")
    private Byte isVirtual;

    @ApiModelProperty("批次描述信息")
    private String description;

    @ApiModelProperty("所属项目")
    private Integer projectId;

    @ApiModelProperty("可用城市的code，多个code用,连接")
    private String cityCodes;

    @ApiModelProperty("可用商品的id，多个id用,连接")
    private String productIds;

    @ApiModelProperty("兑换码数量")
    private Integer amount;

    @ApiModelProperty("充值卡面额")
    private BigDecimal faceValue;

    @ApiModelProperty("过期时间")
    private Long expiryTime;

    @ApiModelProperty("积分卡过期时间")
    private Long pointExpiryTime;

    @ApiModelProperty("自兑换之日起，**天有效")
    private Integer validityPeriod;

    @ApiModelProperty("过期类型")
    private Byte expiryType;

    @ApiModelProperty("是否已经制作")
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

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public Byte getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Byte isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getPointExpiryTime() {
        return pointExpiryTime;
    }

    public void setPointExpiryTime(Long pointExpiryTime) {
        this.pointExpiryTime = pointExpiryTime;
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

    public Byte getIsMade() {
        return isMade;
    }

    public void setIsMade(Byte isMade) {
        this.isMade = isMade;
    }
}
