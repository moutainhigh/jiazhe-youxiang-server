package com.jiazhe.youxiang.server.vo.req;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author tu
 * @description：公共参数，只要是修改过期时间，都用这个类
 * @date 2018/10/24
 */
public class ExpiryTimeEditReq extends IdReq {

    private static final long serialVersionUID = 4527253144338952731L;

    @ApiModelProperty("新的过期时间")
    private Date expiryTime;

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }
}
