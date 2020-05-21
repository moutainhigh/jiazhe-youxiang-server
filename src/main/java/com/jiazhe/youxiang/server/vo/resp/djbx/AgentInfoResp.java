/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.djbx;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 代理人信息resp
 *
 * @author niexiao
 * @created 2020-05-19
 */
public class AgentInfoResp extends BaseVO {

    private static final long serialVersionUID = -8506257116596815644L;
    @ApiModelProperty("sessionId")
    private String sessionId;
    @ApiModelProperty("客户ID")
    private Integer customerId;
    @ApiModelProperty("代理人编码")
    private String agentCode;
    @ApiModelProperty("客户名 与代理人编码一致")
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

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
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
