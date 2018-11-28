package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.EleProductCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
public class EleProductCodeException extends CommonException {

    public EleProductCodeException(EleProductCodeEnum eleProductCodeEnum) {
        super(eleProductCodeEnum.getCode(), eleProductCodeEnum.getType(), eleProductCodeEnum.getMessage());
    }
}
