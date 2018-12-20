package com.jiazhe.youxiang.server.vo.resp.partnerorder;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description 预付金额、消费总金额和余额
 * @date 2018/12/10.
 */
public class OverviewMoneyResp extends BaseVO {

    public OverviewMoneyResp(){
        this.total = BigDecimal.ZERO;
        this.spend = BigDecimal.ZERO;
        this.left = BigDecimal.ZERO;
        this.intervalTotal = BigDecimal.ZERO;
        this.intervalSpend = BigDecimal.ZERO;
        this.intervalLeft = BigDecimal.ZERO;
    }

    @ApiModelProperty("总预付款")
    private BigDecimal total;

    @ApiModelProperty("总支出")
    private BigDecimal spend;

    @ApiModelProperty("总余额")
    private BigDecimal left;

    @ApiModelProperty("区间预付款")
    private BigDecimal intervalTotal;

    @ApiModelProperty("区间支出")
    private BigDecimal intervalSpend;

    @ApiModelProperty("区间余额")
    private BigDecimal intervalLeft;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSpend() {
        return spend;
    }

    public void setSpend(BigDecimal spend) {
        this.spend = spend;
    }

    public BigDecimal getLeft() {
        return left;
    }

    public void setLeft(BigDecimal left) {
        this.left = left;
    }

    public BigDecimal getIntervalTotal() {
        return intervalTotal;
    }

    public void setIntervalTotal(BigDecimal intervalTotal) {
        this.intervalTotal = intervalTotal;
    }

    public BigDecimal getIntervalSpend() {
        return intervalSpend;
    }

    public void setIntervalSpend(BigDecimal intervalSpend) {
        this.intervalSpend = intervalSpend;
    }

    public BigDecimal getIntervalLeft() {
        return intervalLeft;
    }

    public void setIntervalLeft(BigDecimal intervalLeft) {
        this.intervalLeft = intervalLeft;
    }
}
