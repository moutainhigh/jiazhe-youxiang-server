package com.jiazhe.youxiang.server.vo.resp.partnerorder;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.partner.PartnerResp;
import com.jiazhe.youxiang.server.vo.resp.serviceitem.ServiceItemResp;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public class PartnerOrderInfoResp extends BaseVO {

    private Integer id;

    private String customerName;

    private String customerMobile;

    private String customerAddress;

    private String customerCityCode;

    private String customerCityName;

    private String keyt;

    private Long orderTime;

    private Long serviceTime;

    private String orderSource;

    private String workerName;

    private String workerMobile;

    private Integer serviceItemId;

    private ServiceItemResp serviceItemResp;

    private BigDecimal prePay;

    private BigDecimal appendPay;

    private String remark;

    private Integer partnerId;

    private PartnerResp partnerResp;

    private Byte status;

    private String extInfo;

    private Byte isDeleted;

    private Long addTime;

    private Long modTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
    }

    public String getCustomerCityName() {
        return customerCityName;
    }

    public void setCustomerCityName(String customerCityName) {
        this.customerCityName = customerCityName;
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public Long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Long serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }

    public Integer getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(Integer serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public ServiceItemResp getServiceItemResp() {
        return serviceItemResp;
    }

    public void setServiceItemResp(ServiceItemResp serviceItemResp) {
        this.serviceItemResp = serviceItemResp;
    }

    public BigDecimal getPrePay() {
        return prePay;
    }

    public void setPrePay(BigDecimal prePay) {
        this.prePay = prePay;
    }

    public BigDecimal getAppendPay() {
        return appendPay;
    }

    public void setAppendPay(BigDecimal appendPay) {
        this.appendPay = appendPay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public PartnerResp getPartnerResp() {
        return partnerResp;
    }

    public void setPartnerResp(PartnerResp partnerResp) {
        this.partnerResp = partnerResp;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getModTime() {
        return modTime;
    }

    public void setModTime(Long modTime) {
        this.modTime = modTime;
    }
}
