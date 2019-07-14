package com.jiazhe.youxiang.server.dto.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2019-07-14
 */
public class StatisticsDTO extends BaseObject {

    /**
     * 记录条数
     */
    private Integer recordNum;

    /**
     * 兑换总积分
     */
    private BigDecimal totalExchangePoint;

    /**
     * 后台充值积分
     */
    private BigDecimal totalGivingPoint;

    /**
     * 兑换商品总价值
     */
    private BigDecimal totalProductValue;

    public Integer getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Integer recordNum) {
        this.recordNum = recordNum;
    }

    public BigDecimal getTotalExchangePoint() {
        return totalExchangePoint;
    }

    public void setTotalExchangePoint(BigDecimal totalExchangePoint) {
        this.totalExchangePoint = totalExchangePoint;
    }

    public BigDecimal getTotalGivingPoint() {
        return totalGivingPoint;
    }

    public void setTotalGivingPoint(BigDecimal totalGivingPoint) {
        this.totalGivingPoint = totalGivingPoint;
    }

    public BigDecimal getTotalProductValue() {
        return totalProductValue;
    }

    public void setTotalProductValue(BigDecimal totalProductValue) {
        this.totalProductValue = totalProductValue;
    }
}
