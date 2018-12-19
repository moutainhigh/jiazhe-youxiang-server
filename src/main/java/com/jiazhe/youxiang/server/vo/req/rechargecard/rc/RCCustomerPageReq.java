package com.jiazhe.youxiang.server.vo.req.rechargecard.rc;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/11/8.
 */
public class RCCustomerPageReq extends PageSizeNumReq {

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("0为所有，1为不可用【包括过期、停用和余额为0】，2为可用")
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
