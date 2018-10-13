/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/9/30
 */
public class DemoResp extends BaseObject {

    private static final long serialVersionUID = -7442998035625783167L;
    @ApiModelProperty("参数1")
    private String param1;
    @ApiModelProperty("参数2")
    private String param2;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

}