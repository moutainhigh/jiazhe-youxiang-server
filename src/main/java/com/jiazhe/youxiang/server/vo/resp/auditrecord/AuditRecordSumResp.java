package com.jiazhe.youxiang.server.vo.resp.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2019-06-02.
 */
public class AuditRecordSumResp extends BaseVO {

    @ApiModelProperty("兑换积分之和")
    private BigDecimal exchangePointSum;

    public BigDecimal getExchangePointSum() {
        return exchangePointSum;
    }

    public void setExchangePointSum(BigDecimal exchangePointSum) {
        this.exchangePointSum = exchangePointSum;
    }
}
