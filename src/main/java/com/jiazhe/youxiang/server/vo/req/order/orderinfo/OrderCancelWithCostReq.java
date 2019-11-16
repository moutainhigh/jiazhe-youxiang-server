package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/16 14:51
 */
public class OrderCancelWithCostReq extends BaseVO {

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("成本")
    private BigDecimal cost;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
