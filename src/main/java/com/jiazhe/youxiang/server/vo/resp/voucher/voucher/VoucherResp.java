package com.jiazhe.youxiang.server.vo.resp.voucher.voucher;

import com.jiazhe.youxiang.server.vo.BaseObject;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangerecord.VoucherExchangeRecordResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherResp extends BaseObject {

    @ApiModelProperty("代金券id")
    private Integer id;

    @ApiModelProperty("代金券名称")
    private String name;

    @ApiModelProperty("代金券描述")
    private String description;

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("所属项目id")
    private Integer projectId;

    @ApiModelProperty("适用城市code，多个用,连接")
    private String cityCodes;

    @ApiModelProperty("适用商品id，多个用,连接")
    private String productIds;

    @ApiModelProperty("替代商品数量")
    private Integer count;

    @ApiModelProperty("生效时间")
    private Long effectiveTime;

    @ApiModelProperty("过期时间")
    private Long expiryTime;

    @ApiModelProperty("状态 0停用 1启用")
    private Byte status;

    @ApiModelProperty("使用状态 0未使用 1已使用")
    private Byte used;

    @ApiModelProperty("兑换时间")
    private Long addTime;

    private CustomerResp customerResp;

    private VoucherExchangeRecordResp voucherExchangeRecordResp;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public CustomerResp getCustomerResp() {
        return customerResp;
    }

    public void setCustomerResp(CustomerResp customerResp) {
        this.customerResp = customerResp;
    }

    public VoucherExchangeRecordResp getVoucherExchangeRecordResp() {
        return voucherExchangeRecordResp;
    }

    public void setVoucherExchangeRecordResp(VoucherExchangeRecordResp voucherExchangeRecordResp) {
        this.voucherExchangeRecordResp = voucherExchangeRecordResp;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}
