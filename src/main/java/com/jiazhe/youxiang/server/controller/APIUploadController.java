/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.UploadUtil;
import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.UploadImageReq;
import com.jiazhe.youxiang.server.vo.resp.UploadImageResp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件上传Controller
 *
 * @author niexiao
 * @created 2018/10/21
 */
@RestController
@RequestMapping("api/upload")
public class APIUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIUploadController.class);

    /**
     * 上传图片
     *
     * @return
     */
    @ApiOperation(value = "上传图片", httpMethod = "POST", response = UploadImageResp.class, notes = "上传图片")
    @RequestMapping(value = "uploadimage", method = RequestMethod.POST)
    public Object uploadImage(@ModelAttribute UploadImageReq req) {
        //TODO niexiao 参数验证
        CommonValidator.validateNull(req);
        validateEmpty(req);
        validateFileType(req);
        String root_fileName = req.getImage().getOriginalFilename();
        LOGGER.info("上传图片:name={},type={}", root_fileName, req.getImage().getContentType());
        //TODO niexiao 处理图片 获取路径 存入指定路径
        String imagePath = "";
        String url = UploadUtil.uploadImage(req.getImage(), imagePath);

        UploadImageResp result = new UploadImageResp();
        result.setUrl(url);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(result);
    }

    private void validateFileType(@ModelAttribute UploadImageReq req) {
        String contentType = req.getImage().getContentType();
        //TODO niexiao 判断文件类型

    }

    private void validateEmpty(@ModelAttribute UploadImageReq req) {
        if (req.getImage().isEmpty() || StringUtils.isBlank(req.getImage().getOriginalFilename())) {
            throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "上传文件不能为空");
        }
    }


}