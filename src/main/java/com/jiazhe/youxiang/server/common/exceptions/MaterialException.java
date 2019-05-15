package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.MaterialCodeEnum;

/**
 * @author tu
 * @description：
 * @date 2019-05-13
 */
public class MaterialException extends CommonException {

    public MaterialException(MaterialCodeEnum messageCodeEnum) {
        super(messageCodeEnum.getCode(), messageCodeEnum.getType(), messageCodeEnum.getMessage());
    }

    public MaterialException(Integer code, String type, String message) {
        super(code, type, message);
    }
}
