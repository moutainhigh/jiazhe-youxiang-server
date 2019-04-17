package com.jiazhe.youxiang.server.vo.resp.login;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description  用于发送短信成功，返回的短信凭证bizId
 * @date 2018/10/29.
 */
public class SendVerificationCodeResp extends BaseVO {

    @ApiModelProperty("短信返回bizId")
    private String bizId;

    @ApiModelProperty("服务商")
    private Byte serviceProvider;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Byte getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(Byte serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
