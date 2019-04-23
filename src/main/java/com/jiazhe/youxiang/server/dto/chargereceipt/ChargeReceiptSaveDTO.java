package com.jiazhe.youxiang.server.dto.chargereceipt;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2019-03-19
 */
public class ChargeReceiptSaveDTO extends BaseObject{

    /**
     * 消费凭证id
     */
    private Integer id ;

    /**
     * 消费记录id
     */
    private Integer auditRecordId ;

    /**
     * 小票积分
     */
    private BigDecimal exchangePoint ;

    /**
     * 客户姓名
     */
    private String customerName ;

    /**
     * pos机编号
     */
    private String posCode ;

    /**
     * 银行卡号后四位
     */
    private String cardNo ;

    /**
     * 交易时间
     */
    private Date tradeTime ;

    /**
     * 小票图片
     */
    private String imgUrl ;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
