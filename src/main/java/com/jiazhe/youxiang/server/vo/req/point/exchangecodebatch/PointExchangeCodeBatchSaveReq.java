package com.jiazhe.youxiang.server.vo.req.point.exchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public class PointExchangeCodeBatchSaveReq extends BaseVO{

    private static final long serialVersionUID = -2217871613469615457L;

    @ApiModelProperty("批次id，0为添加，其他为修改")
    private Integer id;

    @ApiModelProperty("批次名称")
    private String name;

    @ApiModelProperty("兑换成积分卡的名称")
    private String pointName;

    @ApiModelProperty("是否是虚拟批次")
    private Byte isVirtual;

    @ApiModelProperty("绑定的商户号")
    private String extInfo;

    @ApiModelProperty("兑换码数量")
    private Integer amount;

    @ApiModelProperty("面额")
    private BigDecimal faceValue;

    @ApiModelProperty("对应项目id")
    private Integer projectId;

    @ApiModelProperty("对应城市codes")
    private String cityCodes;

    @ApiModelProperty("对应商品ids")
    private String productIds;

    @ApiModelProperty("批次过期时间")
    private Long expiryTime;

    @ApiModelProperty("积分卡生效时间")
    private Long pointEffectiveTime;

    @ApiModelProperty("积分卡过期时间")
    private Long pointExpiryTime;

    @ApiModelProperty("自兑换之日起，积分卡**天内有效")
    private Integer validityPeriod;

    @ApiModelProperty("积分卡过期类型")
    private Byte expiryType;

    @ApiModelProperty("描述信息")
    private String description;

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

    public Byte getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Byte isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public Long getPointExpiryTime() {
        return pointExpiryTime;
    }

    public void setPointExpiryTime(Long pointExpiryTime) {
        this.pointExpiryTime = pointExpiryTime;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public Long getPointEffectiveTime() {
        return pointEffectiveTime;
    }

    public void setPointEffectiveTime(Long pointEffectiveTime) {
        this.pointEffectiveTime = pointEffectiveTime;
    }
}
