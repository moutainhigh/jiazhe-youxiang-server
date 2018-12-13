package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public class PointException extends CommonException { public PointException(PointCodeEnum pointCodeEnum) {
    super(pointCodeEnum.getCode(), pointCodeEnum.getType(), pointCodeEnum.getMessage());
}
}