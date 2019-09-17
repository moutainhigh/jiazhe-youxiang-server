/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片req
 *
 * @author niexiao
 * @created 2018/10/21
 */
public class UploadImageReq extends BaseVO {

    private static final long serialVersionUID = -3977150269908323564L;
    @ApiModelProperty("图片")
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}