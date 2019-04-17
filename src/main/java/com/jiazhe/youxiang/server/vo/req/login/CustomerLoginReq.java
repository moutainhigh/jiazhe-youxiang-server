package com.jiazhe.youxiang.server.vo.req.login;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/11/6.
 */
public class CustomerLoginReq extends BaseVO {

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("验证码")
    private String identifyingCode ;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentifyingCode() {
        return identifyingCode;
    }

    public void setIdentifyingCode(String identifyingCode) {
        this.identifyingCode = identifyingCode;
    }

}
