/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.djbx;

/**
 * 代理人信息resp
 *
 * @author niexiao
 * @created 2020-05-19
 */
public class AgentInfoDTO {

    /**
     * 客户ID
     */
    private Integer customerId;
    /**
     * 代理人编码
     */
    private String agentCode;
    /**
     * 手机号码
     */
    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
