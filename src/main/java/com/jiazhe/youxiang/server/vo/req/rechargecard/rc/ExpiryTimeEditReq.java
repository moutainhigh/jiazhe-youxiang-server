package com.jiazhe.youxiang.server.vo.req.rechargecard.rc;

import com.jiazhe.youxiang.server.vo.req.IdReq;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author TU
 * @description 充值卡过期时间
 * @date 2018/10/23.
 */
public class ExpiryTimeEditReq extends IdReq{

    @ApiModelProperty("新的过期时间")
    private Date expiryTime;

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }
}
