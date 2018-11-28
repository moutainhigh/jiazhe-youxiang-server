package com.jiazhe.youxiang.server.vo.req.login;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/11/6.
 */
public class SendMsgToCustomerReq extends BaseVO {

    @ApiModelProperty("电话")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
