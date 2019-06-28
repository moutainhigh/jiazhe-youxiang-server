package com.jiazhe.youxiang.server.vo.resp.order.orderinfo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2019-06-28
 */
public class TenpayQureyResp {

    /**
     * SUCCESS—支付成功
     REFUND—转入退款
     NOTPAY—未支付
     CLOSED—已关闭
     REVOKED—已撤销（付款码支付）
     USERPAYING--用户支付中（付款码支付）
     PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    @ApiModelProperty("支付状态")
    private String tradeState;

    @ApiModelProperty("微信支付订单号")
    private String transactionId;

    @ApiModelProperty("微信支付金额，分为单位")
    private Integer totalFee;

    @ApiModelProperty("理由")
    private  String reason;

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
