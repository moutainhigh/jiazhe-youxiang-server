package com.jiazhe.youxiang.server.vo.resp.point.exchangecode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import com.jiazhe.youxiang.server.vo.resp.project.ProjectResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2018/12/14.
 */
public class PointExchangeCodeResp extends BaseVO {
    @ApiModelProperty("兑换码id")
    private Integer id;

    @ApiModelProperty("所属批次名称")
    private String batchName;

    @ApiModelProperty("充值卡名称")
    private String pointName;

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
    private Long expiryTime;

    @ApiModelProperty("积分卡生效时间")
    private Long pointEffectiveTime;

    @ApiModelProperty("积分卡过期时间")
    private Long pointExpiryTime;

    @ApiModelProperty("自兑换之日起，有效期的天数")
    private Integer validityPeriod;

    @ApiModelProperty("充值卡过期时间指定类型")
    private Byte expiryType;

    @ApiModelProperty("兑换码状态，1为启用 0为停用")
    private Byte status;

    @ApiModelProperty("是否已经使用，1为已经使用，0为未使用")
    private Byte used;

    @ApiModelProperty("对应外部订单号")
    private String outOrderCode;

    private ProjectResp projectResp;

    private CustomerResp customerResp;

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

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
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

    public String getOutOrderCode() {
        return outOrderCode;
    }

    public void setOutOrderCode(String outOrderCode) {
        this.outOrderCode = outOrderCode;
    }

    public ProjectResp getProjectResp() {
        return projectResp;
    }

    public void setProjectResp(ProjectResp projectResp) {
        this.projectResp = projectResp;
    }

    public CustomerResp getCustomerResp() {
        return customerResp;
    }

    public void setCustomerResp(CustomerResp customerResp) {
        this.customerResp = customerResp;
    }

    public Long getPointEffectiveTime() {
        return pointEffectiveTime;
    }

    public void setPointEffectiveTime(Long pointEffectiveTime) {
        this.pointEffectiveTime = pointEffectiveTime;
    }
}
