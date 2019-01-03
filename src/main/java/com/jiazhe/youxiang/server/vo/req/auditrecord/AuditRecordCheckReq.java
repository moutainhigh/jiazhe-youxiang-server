package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class AuditRecordCheckReq extends BaseVO {

    @ApiModelProperty("记录id")
    private Integer id ;

    @ApiModelProperty("版本号")
    private Integer version ;

    @ApiModelProperty("是否通过 1不通过 2通过")
    private Byte status ;

    @ApiModelProperty("pos机编号")
    private String posCode ;

    @ApiModelProperty("银行卡号")
    private String cardNo ;

    @ApiModelProperty("交易日期")
    private Long tradeTime ;

    @ApiModelProperty("兑换积分批次id")
    private Integer exchangeBatchId;

    @ApiModelProperty("赠送积分批次id")
    private Integer givingBatchId;

    @ApiModelProperty("审核不通过理由")
    private String auditReason ;



    public Integer getExchangeBatchId() {
        return exchangeBatchId;
    }

    public void setExchangeBatchId(Integer exchangeBatchId) {
        this.exchangeBatchId = exchangeBatchId;
    }

    public Integer getGivingBatchId() {
        return givingBatchId;
    }

    public void setGivingBatchId(Integer givingBatchId) {
        this.givingBatchId = givingBatchId;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
