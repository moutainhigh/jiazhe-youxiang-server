/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.syscity;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.sun.tools.javac.util.List;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class OpenCityReq extends BaseVO {

    private static final long serialVersionUID = 6769706536153569478L;
    @ApiModelProperty("要开通的城市ID")
    private List<Integer> cityIds;

    public List<Integer> getCityIds() {
        return cityIds;
    }

    public void setCityIds(List<Integer> cityIds) {
        this.cityIds = cityIds;
    }
}