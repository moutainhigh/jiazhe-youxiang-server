package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author tu
 * @description：充值卡兑换码批次信息修改请求参数
 * @date 2018/10/21
 */
public class RCExchangeCodeBatchEditReq extends BaseVO{

    @ApiModelProperty("充值卡兑换码批次id")
    private Integer id;

    @ApiModelProperty("批次名称")
    private String name;

    @ApiModelProperty("批次描述")
    private String description;

    @ApiModelProperty("项目id")
    private Integer projectId;

    @ApiModelProperty("城市ids，用逗号连接")
    private String cityIds;

    @ApiModelProperty("商品ds，用逗号连接")
    private String productIds;

    @ApiModelProperty("过期时间")
    private Date expiryTime;

    @ApiModelProperty("兑换后，充值卡过期时间")
    private Date rechargeCardExpiryTime;

    @ApiModelProperty("自兑换之日起，***天有效")
    private Integer validityPeriod;

    @ApiModelProperty("充值卡过期类型")
    private Byte expiryType;

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
}
