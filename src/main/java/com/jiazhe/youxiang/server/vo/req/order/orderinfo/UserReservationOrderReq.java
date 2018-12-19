package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class UserReservationOrderReq {

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("服务人员姓名")
    private String workerName;

    @ApiModelProperty("服务人联系方式")
    private String workerMobile;

    @ApiModelProperty("真实服务时间")
    private Long realServiceTime;

    @ApiModelProperty("订单成本")
    private BigDecimal cost;

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

    public Long getRealServiceTime() {
        return realServiceTime;
    }

    public void setRealServiceTime(Long realServiceTime) {
        this.realServiceTime = realServiceTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
