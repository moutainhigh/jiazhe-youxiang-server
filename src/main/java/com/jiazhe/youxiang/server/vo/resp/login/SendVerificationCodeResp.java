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

    @ApiModelProperty("成功1 失败0")
    private Integer success;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
