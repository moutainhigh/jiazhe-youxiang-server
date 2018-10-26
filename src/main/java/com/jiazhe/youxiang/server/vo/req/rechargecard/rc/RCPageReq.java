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

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("充值卡状态，分可用和不可用，不可用包括余额为0和过期的")
    private Byte status;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
