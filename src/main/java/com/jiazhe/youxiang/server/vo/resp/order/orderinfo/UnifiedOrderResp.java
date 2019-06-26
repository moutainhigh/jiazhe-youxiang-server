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

    @ApiModelProperty("appId")
    private String appId;

    @ApiModelProperty("timeStamp")
    private String timeStamp;

    @ApiModelProperty("nonceStr")
    private String nonceStr;

    @ApiModelProperty("签名")
    private String paySign;

    @ApiModelProperty("签名算法")
    private String signType;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
