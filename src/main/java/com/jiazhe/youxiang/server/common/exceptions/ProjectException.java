/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ProjectCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/29
 */
public class ProjectException extends CommonException {
    public ProjectException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public ProjectException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public ProjectException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public ProjectException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public ProjectException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public ProjectException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public ProjectException(ProjectCodeEnum projectCodeEnum) {
        super(projectCodeEnum.getCode(), projectCodeEnum.getType(), projectCodeEnum.getMessage());
    }
}