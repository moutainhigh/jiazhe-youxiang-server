/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.wechatpublic;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019/1/9
 */
public class CheckSignatureResp extends BaseVO {

    private static final long serialVersionUID = 2210381671599333736L;
    @ApiModelProperty("echostr")
    private String echostr;

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }
}