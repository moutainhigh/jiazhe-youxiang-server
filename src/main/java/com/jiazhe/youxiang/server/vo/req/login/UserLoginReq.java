package com.jiazhe.youxiang.server.vo.req.login;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description 员工登陆用的请求封装
 * @date 2018/10/29.
 */
public class UserLoginReq extends BaseVO {

    @ApiModelProperty("登录名")
    private String loginname;

    @ApiModelProperty("密码")
    private String password ;

    @ApiModelProperty("验证码")
    private String identifyingCode ;

    @ApiModelProperty("短信bizId")
    private String bizId ;

    @ApiModelProperty("是否记住我")
    private String rememberMe;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentifyingCode() {
        return identifyingCode;
    }

    public void setIdentifyingCode(String identifyingCode) {
        this.identifyingCode = identifyingCode;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
