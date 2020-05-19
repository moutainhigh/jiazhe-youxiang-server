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
    @ApiModelProperty("客户ID")
    private Integer customerId;
    @ApiModelProperty("代理人编码")
    private String agentCode;
    @ApiModelProperty("手机号码")
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
