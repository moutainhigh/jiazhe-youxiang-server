package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;

/**
 * @author tu
 * @description：
 * @date 2018/11/8
 */
public class OrderException extends CommonException {
    public OrderException(OrderCodeEnum orderCodeEnum) {
        super(orderCodeEnum.getCode(), orderCodeEnum.getType(), orderCodeEnum.getMessage());
    }
}
