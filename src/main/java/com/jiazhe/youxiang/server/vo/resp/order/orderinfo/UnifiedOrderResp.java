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
    private String prepay_id;

    @ApiModelProperty("签名")
    private String sign;

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
