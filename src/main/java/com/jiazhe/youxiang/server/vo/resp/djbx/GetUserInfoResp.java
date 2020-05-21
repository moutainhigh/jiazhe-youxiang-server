/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.djbx;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-05-19
 */
public class GetUserInfoResp extends BaseVO {

    private static final long serialVersionUID = -6399984996281653878L;
    @ApiModelProperty("请求状态,0.正常 1.其余皆异常")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String msg;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("用户编码")
    private String agentCode;
    @ApiModelProperty("是否销管代理人,true:是、false:否")
    private Boolean cmsAgentFlag;
    @ApiModelProperty("大家王牌俱乐部 中 APPID")
    private String appId;
    @ApiModelProperty("重定向地址")
    private String redirectPath;
    @ApiModelProperty("是否隐藏菜单")
    private Boolean menuFlag;
    @ApiModelProperty("是否隐藏标题")
    private Boolean headerFlag;
    @ApiModelProperty("来源,默认 eWechat")
    private String sourceCode;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public Boolean getCmsAgentFlag() {
        return cmsAgentFlag;
    }

    public void setCmsAgentFlag(Boolean cmsAgentFlag) {
        this.cmsAgentFlag = cmsAgentFlag;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRedirectPath() {
        return redirectPath;
    }

    public void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    public Boolean getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(Boolean menuFlag) {
        this.menuFlag = menuFlag;
    }

    public Boolean getHeaderFlag() {
        return headerFlag;
    }

    public void setHeaderFlag(Boolean headerFlag) {
        this.headerFlag = headerFlag;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
