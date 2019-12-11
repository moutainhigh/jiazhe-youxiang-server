package com.jiazhe.youxiang.server.dto.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2019-06-02.
 */
public class AuditRecordSumDTO extends BaseObject {

    /**
     * 兑换积分之和
     */
    private BigDecimal exchangePointSum;

    /**
     * 商品价值之和
     */
    private BigDecimal productValueSum;

    public BigDecimal getExchangePointSum() {
        return exchangePointSum;
    }

    public void setExchangePointSum(BigDecimal exchangePointSum) {
        this.exchangePointSum = exchangePointSum;
    }

    public BigDecimal getProductValueSum() {
        return productValueSum;
    }

    public void setProductValueSum(BigDecimal productValueSum) {
        this.productValueSum = productValueSum;
    }
}
