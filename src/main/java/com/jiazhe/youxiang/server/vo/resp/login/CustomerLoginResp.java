package com.jiazhe.youxiang.server.vo.resp.login;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/11/27.
 */
public class CustomerLoginResp extends BaseVO {

    @ApiModelProperty("sessionId")
    private String sessionId;

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("客户名")
    private String customerName;

    @ApiModelProperty("客户电话")
    private String customerMobile;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }
}
