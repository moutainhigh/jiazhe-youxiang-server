/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.UploadUtil;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.UploadCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.UploadException;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.resp.UploadImageResp;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.jiazhe.youxiang.base.util.UploadUtil.FILE_TYPE_IMAGE;

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

    @Value("${web.upload.image-path}")
    private String imagePath;

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "uploadimage", method = RequestMethod.POST, headers = ("content-type=multipart/*"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CustomLog(moduleName = ModuleEnum.UPLOAD, operate = "上传图片", level = LogLevelEnum.LEVEL_2)
    public Object uploadImage(@RequestParam("file") MultipartFile file) {
        CommonValidator.validateNull(file);
        validateEmpty(file);
        validateFileType(file);
        //处理图片 获取路径 存入指定路径
        String url = UploadUtil.uploadFile2COS(file, FILE_TYPE_IMAGE);
//        String url = UploadUtil.uploadImage(file, imagePath);
        UploadImageResp result = new UploadImageResp();
        result.setUrl(url);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(result);
    }

    private void validateFileType(MultipartFile file) {
        //判断文件类型
        if (file != null && file.getContentType() != null && !file.getContentType().toLowerCase().startsWith("image")) {
            LOGGER.error("文件类型不符");
            throw new UploadException(UploadCodeEnum.IMG_TYPE_ERROR);
        }
    }

    private void validateEmpty(MultipartFile file) {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new UploadException(UploadCodeEnum.IMG_IS_NOT_NULL);
        }
    }


}