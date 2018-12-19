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
    }

    @ApiModelProperty("总预付款")
    private BigDecimal total;

    @ApiModelProperty("总支出")
    private BigDecimal spend;

    @ApiModelProperty("总余额")
    private BigDecimal left;

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
}
