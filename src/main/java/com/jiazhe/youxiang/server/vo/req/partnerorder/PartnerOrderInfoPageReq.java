package com.jiazhe.youxiang.server.vo.req.partnerorder;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：后台查询订单输入参数
 * @date 2018/10/24
 */
public class PartnerOrderInfoPageReq extends PageSizeNumReq {

    @ApiModelProperty("订单状态")
    private Byte status ;

    @ApiModelProperty("客户手机号")
    private String customerMobile;

    @ApiModelProperty("城市")
    private String customerCityCode;

    @ApiModelProperty("服务商家id")
    private Integer partnerId;

    @ApiModelProperty("服务项目id")
    private Integer serviceItemId;

    @ApiModelProperty("预约时间起")
    private Long serviceTimeStart;

    @ApiModelProperty("预约时间止")
    private Long serviceTimeEnd;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(Integer serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public Long getServiceTimeStart() {
        return serviceTimeStart;
    }

    public void setServiceTimeStart(Long serviceTimeStart) {
        this.serviceTimeStart = serviceTimeStart;
    }

    public Long getServiceTimeEnd() {
        return serviceTimeEnd;
    }

    public void setServiceTimeEnd(Long serviceTimeEnd) {
        this.serviceTimeEnd = serviceTimeEnd;
    }
}
