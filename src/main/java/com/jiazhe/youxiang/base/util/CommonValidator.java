/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.IdReq;

/**
 * 通用参数验证器
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class CommonValidator {

    public static void validate(BaseVO req) {
        if (req == null) {
            throw new CommonException(CommonCodeEnum.INTERNAL_ERROR.getCode(), CommonCodeEnum.INTERNAL_ERROR.getType(), "参数不能为空");
        }
    }

    public static void validateId(Integer id) {
        if (id == null || id < 0) {
            throw new CommonException(CommonCodeEnum.INTERNAL_ERROR.getCode(), CommonCodeEnum.INTERNAL_ERROR.getType(), "ID不合法");
        }
    }

    public static void validateId(IdReq id) {
        if (id == null || id.getId() == null || id.getId() < 0) {
            throw new CommonException(CommonCodeEnum.INTERNAL_ERROR.getCode(), CommonCodeEnum.INTERNAL_ERROR.getType(), "ID不合法");
        }
    }
}
