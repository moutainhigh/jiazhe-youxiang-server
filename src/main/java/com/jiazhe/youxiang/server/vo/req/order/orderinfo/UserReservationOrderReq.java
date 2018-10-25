package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class UserReservationOrderReq extends IdReq {

    @ApiModelProperty("服务人员姓名")
    private String workerName;

    @ApiModelProperty("服务人联系方式")
    private String workerMobile;

    @ApiModelProperty("真实服务时间")
    private Date realServiceTime;

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }

    public Date getRealServiceTime() {
        return realServiceTime;
    }

    public void setRealServiceTime(Date realServiceTime) {
        this.realServiceTime = realServiceTime;
    }
}
