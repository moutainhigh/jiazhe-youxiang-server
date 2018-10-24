package com.jiazhe.youxiang.server.vo.req.voucher.exchangecode;

import com.jiazhe.youxiang.server.vo.req.IdReq;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class ExpiryTimeEditReq extends IdReq {

    private Date expiryTime ;

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }
}
