package com.jiazhe.youxiang.server.vo.req.voucher.voucher;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2018/11/4
 */
public class VoucherPageReq extends PageSizeNumReq {

    @ApiModelProperty("客户电话")
    private String mobile;

    @ApiModelProperty("兑换种类")
    private Integer exchangeType;

    @ApiModelProperty("充值卡停用/启用状态")
    private Byte status;

    @ApiModelProperty("是否过期")
    private Byte expiry;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getExpiry() {
        return expiry;
    }

    public void setExpiry(Byte expiry) {
        this.expiry = expiry;
    }
}
