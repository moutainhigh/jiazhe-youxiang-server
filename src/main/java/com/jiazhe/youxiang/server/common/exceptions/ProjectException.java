/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.ProjectCodeEnum;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/29
 */
public class ProjectException extends CommonException {
    public ProjectException(ProjectCodeEnum projectCodeEnum) {
        super(projectCodeEnum.getCode(), projectCodeEnum.getType(), projectCodeEnum.getMessage());
    }
}