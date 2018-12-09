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

    @ApiModelProperty("商品描述")
    private String body;

    @ApiModelProperty("订单号")
    private String out_trade_no;

    @ApiModelProperty("支付金额，以分为单位")
    private Integer total_fee;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }
}
