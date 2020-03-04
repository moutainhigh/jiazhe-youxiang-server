/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.chargeoff;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 验证密码有效性req
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ValidateKeytReq extends BaseVO {

    private static final long serialVersionUID = 8194977019877054422L;
    @ApiModelProperty("需要验证的密码")
    private String keyt;

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }
}
