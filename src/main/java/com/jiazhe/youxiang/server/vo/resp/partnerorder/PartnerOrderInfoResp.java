package com.jiazhe.youxiang.server.vo.resp.partnerorder;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.partner.PartnerResp;
import com.jiazhe.youxiang.server.vo.resp.serviceitem.ServiceItemResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public class PartnerOrderInfoResp extends BaseVO {

    @ApiModelProperty("商家订单id")
    private Integer id;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户电话")
    private String customerMobile;

    @ApiModelProperty("服务地址")
    private String customerAddress;

    @ApiModelProperty("客户城市code")
    private String customerCityCode;

    @ApiModelProperty("客户城市名称")
    private String customerCityName;

    @ApiModelProperty("密钥")
    private String keyt;

    @ApiModelProperty("兑换时间")
    private Long orderTime;

    @ApiModelProperty("服务时间")
    private Long serviceTime;

    @ApiModelProperty("订单来源")
    private String orderSource;

    @ApiModelProperty("工作人姓名")
    private String workerName;

    @ApiModelProperty("工作人电话")
    private String workerMobile;

    @ApiModelProperty("服务项目")
    private Integer serviceItemId;

    private ServiceItemResp serviceItemResp;

    @ApiModelProperty("预付金额")
    private BigDecimal prePay;

    @ApiModelProperty("二次支付金额")
    private BigDecimal appendPay;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("合作伙伴id")
    private Integer partnerId;

    private PartnerResp partnerResp;

    @ApiModelProperty("状态 1待服务 2已完成 3已取消 ")
    private Byte status;

    @ApiModelProperty("附加字段")
    private String extInfo;

    @ApiModelProperty("是否删除")
    private Byte isDeleted;

    @ApiModelProperty("添加时间")
    private Long addTime;

    @ApiModelProperty("修改时间")
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
