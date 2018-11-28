package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/11/8.
 */
public class CustomerOrderInfoPageReq extends PageSizeNumReq {

    @ApiModelProperty("订单状态【0为全部，1代付款，2待派单，3待服务，4已完成，5取消待审核，6取消审核未通过，7已取消】")
    private Byte status ;

    @ApiModelProperty("客户id")
    private Integer customerId;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
