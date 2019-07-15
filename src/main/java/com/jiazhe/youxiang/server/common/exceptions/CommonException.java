/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * 异常基类
 *
 * @author niexiao
 * @created 2018/10/15
 */
public class CommonException extends RuntimeException {
    private Integer code;
    private String type;
    private String message;


    public CommonException(Integer code, String type, String message) {
        super(message);
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public CommonException(CommonCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
        this.type = codeEnum.getType();
        this.message = codeEnum.getMessage();
    }

    public CommonException(ResponseMsg responseMsg) {
        this(responseMsg.getCode(), responseMsg.getType(), responseMsg.getMessage());
    }

    public CommonException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum.getMessage(), cause);
        this.code = codeEnum.getCode();
        this.type = codeEnum.getType();
        this.message = codeEnum.getMessage();
    }

    public CommonException(Integer code, String type, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public CommonException(ResponseMsg responseMsg, Throwable cause) {
        this(responseMsg.getCode(), responseMsg.getType(), responseMsg.getMessage(), cause);
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCustomMsg() {
        return message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getMessage() {
        StringBuffer msg = new StringBuffer("");

        if (null != code) {
            msg.append("Exception errCode is [").append(this.code).append("];\n");
        }

        if (this.type != null) {
            msg.append("Exception Type   is [").append(this.type).append("];\n");
        }

        msg.append("Exception Message is [").append(this.message).append("];\n");

        // 打印错误异常堆栈
        if (getCause() != null) {
            getCause().printStackTrace();
        }

        return msg.toString();
    }
}