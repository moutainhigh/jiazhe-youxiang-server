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

    @ApiModelProperty("积分卡ids")
    private String pointIds;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户电话")
    private String customerMobile;

    @ApiModelProperty("兑换积分")
    private BigDecimal exchangePoint;

    @ApiModelProperty("兑换类型")
    private String exchangeType;

    @ApiModelProperty("赠送积分")
    private BigDecimal givingPoint;

    @ApiModelProperty("赠送类型")
    private String givingType;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("审核理由")
    private String auditReason;

    @ApiModelProperty("消费记录图片，多张用,连接")
    private String imgUrls;

    @ApiModelProperty("提交人id")
    private Integer submitterId;

    @ApiModelProperty("提交人姓名")
    private String submitterName;

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

    public String getPointIds() {
        return pointIds;
    }

    public void setPointIds(String pointIds) {
        this.pointIds = pointIds;
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

    public BigDecimal getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(BigDecimal exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public BigDecimal getGivingPoint() {
        return givingPoint;
    }

    public void setGivingPoint(BigDecimal givingPoint) {
        this.givingPoint = givingPoint;
    }

    public String getGivingType() {
        return givingType;
    }

    public void setGivingType(String givingType) {
        this.givingType = givingType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
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
