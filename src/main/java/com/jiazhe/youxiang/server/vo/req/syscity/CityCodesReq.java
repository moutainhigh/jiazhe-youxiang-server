/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.syscity;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class CityCodesReq extends BaseVO {

    private static final long serialVersionUID = 6769706536153569478L;
    @ApiModelProperty("城市Code集合")
    private List<String> cityCodes;

    public List<String> getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(List<String> cityCodes) {
        this.cityCodes = cityCodes;
    }
}