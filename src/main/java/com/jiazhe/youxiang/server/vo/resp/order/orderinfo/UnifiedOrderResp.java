package com.jiazhe.youxiang.server.vo.resp.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description 统一下单返回
 * @date 2018/12/10.
 */
public class UnifiedOrderResp extends BaseVO {

    @ApiModelProperty("统一下单预付id")
    private String prepayId;

    @ApiModelProperty("签名")
    private String paySign;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
