/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/26
 */
public class IdListReq extends BaseVO {

    @ApiModelProperty("ID集合")
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}