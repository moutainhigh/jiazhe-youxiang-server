package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.PartnerOrderCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/12/19.
 */
public class PartnerOrderException extends CommonException {

    public PartnerOrderException(PartnerOrderCodeEnum partnerOrderCodeEnum) {
        super(partnerOrderCodeEnum.getCode(), partnerOrderCodeEnum.getType(), partnerOrderCodeEnum.getMessage());
    }
}
