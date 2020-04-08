package com.jiazhe.youxiang.server.dto.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2019-06-28
 */
public class TenpayQureyDTO extends BaseObject {

    /**
    支付/退款 状态
     */
    private String tradeState;

    /**
    微信支付订单号/退款 订单号
     */
    private String transactionId;

    /**
    微信支付/退款金额，分为单位
     */
    private Integer totalFee;

    /**
    失败原因
     */
    private String reason;

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
