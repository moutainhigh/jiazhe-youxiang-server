package com.jiazhe.youxiang.server.vo.resp.order.orderinfo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2019-06-23
 */
public class OpenIdResp {

    @ApiModelProperty("网页授权接口调用凭证")
    private String access_token;

    @ApiModelProperty("access_token接口调用凭证超时时间，单位（秒）")
    private Integer expires_in;

    @ApiModelProperty("用户刷新access_token")
    private String refresh_token;

    @ApiModelProperty("用户唯一标识")
    private String openid;

    @ApiModelProperty("用户授权作用域")
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
