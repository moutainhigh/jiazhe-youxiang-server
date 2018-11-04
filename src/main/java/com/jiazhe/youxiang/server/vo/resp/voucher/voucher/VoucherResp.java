package com.jiazhe.youxiang.server.vo.resp.voucher.voucher;

import com.jiazhe.youxiang.server.vo.BaseObject;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangerecord.VoucherExchangeRecordResp;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherResp extends BaseObject {

    private Integer id;

    private String name;

    private String description;

    private Integer customerId;

    private Integer projectId;

    private String cityCodes;

    private String productIds;

    private Integer count;

    private Date expiryTime;

    private Byte status;

    private Byte used;

    private Date addTime;

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

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
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
}
