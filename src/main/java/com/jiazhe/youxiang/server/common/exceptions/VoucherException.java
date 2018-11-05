package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public class VoucherException extends CommonException {
    public VoucherException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public VoucherException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public VoucherException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public VoucherException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public VoucherException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public VoucherException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public VoucherException(VoucherCodeEnum voucherCodeEnum) {
        super(voucherCodeEnum.getCode(), voucherCodeEnum.getType(), voucherCodeEnum.getMessage());
    }
}
