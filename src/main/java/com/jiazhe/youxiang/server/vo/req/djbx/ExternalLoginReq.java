/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.djbx;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 外部登录Req
 *
 * @author niexiao
 * @created 2020-05-19
 */
public class ExternalLoginReq extends BaseVO {

    private static final long serialVersionUID = -6825428047945342273L;
    @ApiModelProperty("appvalue")
    private String appvalue;
    @ApiModelProperty("code")
    private String code;

    public String getAppvalue() {
        return appvalue;
    }

    public void setAppvalue(String appvalue) {
        this.appvalue = appvalue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
