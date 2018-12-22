package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class AppendOrderReq extends BaseVO{

    @ApiModelProperty("订单")
    private Integer orderId;

    @ApiModelProperty("追加数量")
    private Integer count;

    @ApiModelProperty("订单成本")
    private BigDecimal cost;

    /**
     * 使用的积分卡ids
     */
    @ApiModelProperty("积分卡ids")
    private String pointIds;

    /**
     * 使用的代金券ids
     */
    @ApiModelProperty("代金券ids")
    private String voucherIds;

    /**
     * 使用的充值卡ids
     */
    @ApiModelProperty("充值卡ids")
    private String rechargeCardIds;

    /**
     * 每张充值卡使用的金额
     */
    @ApiModelProperty("充值卡金额")
    private String cardMoneys;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getVoucherIds() {
        return voucherIds;
    }

    public void setVoucherIds(String voucherIds) {
        this.voucherIds = voucherIds;
    }

    public String getRechargeCardIds() {
        return rechargeCardIds;
    }

    public void setRechargeCardIds(String rechargeCardIds) {
        this.rechargeCardIds = rechargeCardIds;
    }

    public String getCardMoneys() {
        return cardMoneys;
    }

    public void setCardMoneys(String cardMoneys) {
        this.cardMoneys = cardMoneys;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPointIds() {
        return pointIds;
    }

    public void setPointIds(String pointIds) {
        this.pointIds = pointIds;
    }
}
