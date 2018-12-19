package com.jiazhe.youxiang.server.vo.resp.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class AuditRecordResp extends BaseVO {

    @ApiModelProperty("消费记录id")
    private Integer id;

    @ApiModelProperty("充值卡id")
    private Integer rechargeCardId;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户电话")
    private String customerMobile;

    @ApiModelProperty("兑换金额")
    private BigDecimal exchangeMoney;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("消费记录图片，多张用,连接")
    private String imgUrls;

    @ApiModelProperty("提交人id")
    private Integer submitterId;

    @ApiModelProperty("提交人姓名")
    private String submitterName;

    @ApiModelProperty("提交人备注")
    private String submitterRemark;

    @ApiModelProperty("状态 0待审核 1未通过 2通过")
    private Byte status;

    @ApiModelProperty("审核时间")
    private Long auditTime;

    @ApiModelProperty("审核人id")
    private Integer auditorId;

    @ApiModelProperty("审核人姓名")
    private String auditorName;

    @ApiModelProperty("版本")
    private Integer version;

    @ApiModelProperty("提交时间")
    private Long addTime;

    @ApiModelProperty("修改时间")
    private Long modTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRechargeCardId() {
        return rechargeCardId;
    }

    public void setRechargeCardId(Integer rechargeCardId) {
        this.rechargeCardId = rechargeCardId;
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

    public BigDecimal getExchangeMoney() {
        return exchangeMoney;
    }

    public void setExchangeMoney(BigDecimal exchangeMoney) {
        this.exchangeMoney = exchangeMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Integer getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Integer submitterId) {
        this.submitterId = submitterId;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public String getSubmitterRemark() {
        return submitterRemark;
    }

    public void setSubmitterRemark(String submitterRemark) {
        this.submitterRemark = submitterRemark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
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
