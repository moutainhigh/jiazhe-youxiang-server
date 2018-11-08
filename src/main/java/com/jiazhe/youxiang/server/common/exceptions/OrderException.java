package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * @author tu
 * @description：
 * @date 2018/11/8
 */
public class OrderException extends CommonException {
    public OrderException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public OrderException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public OrderException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public OrderException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public OrderException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public OrderException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public OrderException(OrderCodeEnum orderCodeEnum) {
        super(orderCodeEnum.getCode(), orderCodeEnum.getType(), orderCodeEnum.getMessage());
    }
}
