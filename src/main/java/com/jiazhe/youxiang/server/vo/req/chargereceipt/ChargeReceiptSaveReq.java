package com.jiazhe.youxiang.server.vo.req.chargereceipt;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2019-03-19
 */
public class ChargeReceiptSaveReq extends BaseVO {

    @ApiModelProperty("id")
    private Integer id ;

    @ApiModelProperty("充值记录id")
    private Integer auditRecordId ;

    @ApiModelProperty("小票积分数量")
    private BigDecimal exchangePoint ;

    @ApiModelProperty("客户姓名")
    private String customerName ;

    @ApiModelProperty("pos机编号")
    private String posCode ;

    @ApiModelProperty("银行卡号")
    private String cardNo ;

    @ApiModelProperty("交易时间")
    private Long tradeTime ;

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

    public Long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Long tradeTime) {
        this.tradeTime = tradeTime;
    }
}
