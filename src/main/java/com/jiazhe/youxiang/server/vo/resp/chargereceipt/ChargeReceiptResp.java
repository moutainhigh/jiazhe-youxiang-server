package com.jiazhe.youxiang.server.vo.resp.chargereceipt;

import com.jiazhe.youxiang.server.vo.BaseVO;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public class ChargeReceiptResp extends BaseVO {

    private Integer id;

    private Integer auditRecordId;

    private BigDecimal exchangePoint;

    private String posCode;

    private Long tradeTime;

    private String cardNo;

    private String customerName;

    private Integer inputerId;

    private String inputerName;

    private String imgUrl;

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

    public Integer getAuditRecordId() {
        return auditRecordId;
    }

    public void setAuditRecordId(Integer auditRecordId) {
        this.auditRecordId = auditRecordId;
    }

    public BigDecimal getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(BigDecimal exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public Long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getInputerId() {
        return inputerId;
    }

    public void setInputerId(Integer inputerId) {
        this.inputerId = inputerId;
    }

    public String getInputerName() {
        return inputerName;
    }

    public void setInputerName(String inputerName) {
        this.inputerName = inputerName;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
