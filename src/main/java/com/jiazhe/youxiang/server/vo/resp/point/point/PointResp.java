package com.jiazhe.youxiang.server.vo.resp.point.point;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import com.jiazhe.youxiang.server.vo.resp.point.exchangerecord.PointExchangeRecordResp;
import com.jiazhe.youxiang.server.vo.resp.project.ProjectResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2018/12/14.
 */
public class PointResp extends BaseVO{

    @ApiModelProperty("积分卡id")
    private Integer id;

    @ApiModelProperty("积分卡名称")
    private String name;

    @ApiModelProperty("积分卡描述信息")
    private String description;

    @ApiModelProperty("所属客户id")
    private Integer customerId;

    @ApiModelProperty("所属项目id")
    private Integer projectId;

    @ApiModelProperty("适用城市code，多个用,连接")
    private String cityCodes;

    @ApiModelProperty("适用商品id，多个用,连接")
    private String productIds;

    @ApiModelProperty("积分卡面额")
    private BigDecimal faceValue;

    @ApiModelProperty("积分卡余额")
    private BigDecimal balance;

    @ApiModelProperty("积分卡过期时间")
    private Long expiryTime;

    @ApiModelProperty("积分卡状态 0停用 1启用")
    private Byte status;

    @ApiModelProperty("充值时间")
    private Long addTime;

    private ProjectResp projectResp;

    private CustomerResp customerResp;

    private PointExchangeRecordResp pointExchangeRecordResp;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
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

    public PointExchangeRecordResp getPointExchangeRecordResp() {
        return pointExchangeRecordResp;
    }

    public void setPointExchangeRecordResp(PointExchangeRecordResp pointExchangeRecordResp) {
        this.pointExchangeRecordResp = pointExchangeRecordResp;
    }
}
