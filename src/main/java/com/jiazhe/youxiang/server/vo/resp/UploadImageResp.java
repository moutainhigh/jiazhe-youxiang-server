/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 上传图片返回值
 *
 * @author niexiao
 * @created 2018/10/21
 */
public class UploadImageResp extends BaseVO {

    private static final long serialVersionUID = -5448851955635064583L;
    @ApiModelProperty("图片URL")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}