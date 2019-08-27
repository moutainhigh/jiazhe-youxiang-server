package com.jiazhe.youxiang.server.vo.resp.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2019-07-14
 */
public class StatisticsResp extends BaseVO{

    @ApiModelProperty("提交记录数量")
    private Integer recordNum;

    @ApiModelProperty("兑换总积分之和")
    private BigDecimal totalExchangePoint;

    @ApiModelProperty("后台充值积分之和")
    private BigDecimal totalGivingPoint;

    @ApiModelProperty("消耗实物价值之和")
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
