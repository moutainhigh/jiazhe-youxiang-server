package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public class VoucherException extends CommonException {

    public VoucherException(VoucherCodeEnum voucherCodeEnum) {
        super(voucherCodeEnum.getCode(), voucherCodeEnum.getType(), voucherCodeEnum.getMessage());
    }
}
