package com.jiazhe.youxiang.server.vo.resp.message;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description 单条发送短信返回
 * @date 2019-04-08.
 */
public class SendSingleMsgResp extends BaseVO {

    @ApiModelProperty("发送是否成功")
    private boolean success;

    @ApiModelProperty("短信服务商，1腾讯 2阿里")
    private Byte serviceProvider;

    @ApiModelProperty("发送失败原因")
    private String errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Byte getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(Byte serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
