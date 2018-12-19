package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.RoleCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class RoleException extends CommonException {

    public RoleException(RoleCodeEnum roleCodeEnum) {
        super(roleCodeEnum.getCode(), roleCodeEnum.getType(), roleCodeEnum.getMessage());
    }
}
