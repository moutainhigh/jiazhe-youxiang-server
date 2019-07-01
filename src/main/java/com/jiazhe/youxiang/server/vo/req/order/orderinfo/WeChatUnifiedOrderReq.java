package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：统一下单请求参数
 * @date 2018/12/9
 */
public class WeChatUnifiedOrderReq extends BaseVO{

    @ApiModelProperty("商品描述，必填")
    private String body;

    @ApiModelProperty("订单id，必填")
    private Integer orderId;

    @ApiModelProperty("订单号，必填")
    private String orderNo;

    @ApiModelProperty("支付金额，以分为单位，必填")
    private Integer totalFee;

    @ApiModelProperty("支付用户open_id，必填")
    private String openId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
