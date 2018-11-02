package com.jiazhe.youxiang.server.vo.req.rechargecard.rc;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/26.
 */
public class RCPageReq extends PageSizeNumReq {

    private static final long serialVersionUID = -1480391007324919283L;

    @ApiModelProperty("客户电话")
    private String mobile;

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
