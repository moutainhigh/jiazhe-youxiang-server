package com.jiazhe.youxiang.server.vo.req.chargereceipt;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public class ChargeReceiptPageReq extends PageSizeNumReq {

    @ApiModelProperty("充值记录id")
    private Integer auditRecordId ;

    @ApiModelProperty("客户姓名")
    private String customerName ;

    @ApiModelProperty("银行卡后四位")
    private String cardNo ;

    @ApiModelProperty("pos机编号")
    private String posCode ;

    @ApiModelProperty("交易时间起")
    private Long tradeStartTime ;

    @ApiModelProperty("交易时间止")
    private Long tradeEndTime ;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public Long getTradeStartTime() {
        return tradeStartTime;
    }

    public void setTradeStartTime(Long tradeStartTime) {
        this.tradeStartTime = tradeStartTime;
    }

    public Long getTradeEndTime() {
        return tradeEndTime;
    }

    public void setTradeEndTime(Long tradeEndTime) {
        this.tradeEndTime = tradeEndTime;
    }

    public Integer getAuditRecordId() {
        return auditRecordId;
    }

    public void setAuditRecordId(Integer auditRecordId) {
        this.auditRecordId = auditRecordId;
    }
}
