package com.jiazhe.youxiang.server.vo.resp.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.point.exchangecode.PointExchangeCodeResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

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

    @ApiModelProperty("兑换总积分")
    private BigDecimal exchangePoint;

    @ApiModelProperty("小票总积分")
    private BigDecimal chargeReceiptPoint;

    @ApiModelProperty("后台充值总积分")
    private BigDecimal givingPoint;

    @ApiModelProperty("兑换类型")
    private Integer exchangeType;

    @ApiModelProperty("凭证完成状态，0未完成 1已完成")
    private Byte chargeReceiptStatus;

    @ApiModelProperty("兑换码code集合")
    private String pointCodes;

    @ApiModelProperty("兑换码集合")
    private List<PointExchangeCodeResp> pointExchangeCodeRespList;

    @ApiModelProperty("实物价值")
    private BigDecimal productValue;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("审核理由")
    private String auditReason;

    @ApiModelProperty("消费记录图片，多张用,连接")
    private String imgUrls;

    @ApiModelProperty("pos机编号")
    private String posCode;

    @ApiModelProperty("银行卡后四位")
    private String cardNo;

    @ApiModelProperty("兑换城市编码")
    private String cityCode;

    @ApiModelProperty("兑换城市名称")
    private String cityName;

    @ApiModelProperty("银行网点")
    private String bankOutletsName;

    @ApiModelProperty("交易时间")
    private Long tradeTime;

    @ApiModelProperty("提交时间")
    private Long submitTime;

    @ApiModelProperty("提交人id")
    private Integer submitterId;

    @ApiModelProperty("提交人姓名")
    private String submitterName;

    @ApiModelProperty("状态 1未提交 2已提交 3已驳回 4已通过")
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

    public BigDecimal getChargeReceiptPoint() {
        return chargeReceiptPoint;
    }

    public void setChargeReceiptPoint(BigDecimal chargeReceiptPoint) {
        this.chargeReceiptPoint = chargeReceiptPoint;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Byte getChargeReceiptStatus() {
        return chargeReceiptStatus;
    }

    public void setChargeReceiptStatus(Byte chargeReceiptStatus) {
        this.chargeReceiptStatus = chargeReceiptStatus;
    }

    public String getPointCodes() {
        return pointCodes;
    }

    public void setPointCodes(String pointCodes) {
        this.pointCodes = pointCodes;
    }

    public List<PointExchangeCodeResp> getPointExchangeCodeRespList() {
        return pointExchangeCodeRespList;
    }

    public void setPointExchangeCodeRespList(List<PointExchangeCodeResp> pointExchangeCodeRespList) {
        this.pointExchangeCodeRespList = pointExchangeCodeRespList;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
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

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBankOutletsName() {
        return bankOutletsName;
    }

    public void setBankOutletsName(String bankOutletsName) {
        this.bankOutletsName = bankOutletsName;
    }

    public Long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public BigDecimal getGivingPoint() {
        return givingPoint;
    }

    public void setGivingPoint(BigDecimal givingPoint) {
        this.givingPoint = givingPoint;
    }

    public Long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
    }
}
