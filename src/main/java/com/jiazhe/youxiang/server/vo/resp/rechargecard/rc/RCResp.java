package com.jiazhe.youxiang.server.vo.resp.rechargecard.rc;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord.RCExchangeRecordResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class RCResp extends BaseVO{

    @ApiModelProperty("充值卡id")
    private Integer id;

    @ApiModelProperty("充值卡名称")
    private String name;

    @ApiModelProperty("充值卡描述信息")
    private String description;

    @ApiModelProperty("所属客户id")
    private Integer customerId;

    @ApiModelProperty("所属项目id")
    private Integer projectId;

    @ApiModelProperty("适用城市code，多个用,连接")
    private String cityCodes;

    @ApiModelProperty("适用商品id，多个用,连接")
    private String productIds;

    @ApiModelProperty("充值卡面额")
    private BigDecimal faceValue;

    @ApiModelProperty("充值卡余额")
    private BigDecimal balance;

    @ApiModelProperty("充值卡过期时间")
    private Date expiryTime;

    @ApiModelProperty("充值卡状态 0停用 1启用")
    private Byte status;

    @ApiModelProperty("充值时间")
    private Date addTime;

    private CustomerResp customerResp;

    private RCExchangeRecordResp rcExchangeRecordResp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public CustomerResp getCustomerResp() {
        return customerResp;
    }

    public void setCustomerResp(CustomerResp customerResp) {
        this.customerResp = customerResp;
    }

    public RCExchangeRecordResp getRcExchangeRecordResp() {
        return rcExchangeRecordResp;
    }

    public void setRcExchangeRecordResp(RCExchangeRecordResp rcExchangeRecordResp) {
        this.rcExchangeRecordResp = rcExchangeRecordResp;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
