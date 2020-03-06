/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.validator;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.server.common.enums.ChargeOffCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ChargeOffStatusEnum;
import com.jiazhe.youxiang.server.common.enums.ChargeOffTypeEnum;
import com.jiazhe.youxiang.server.common.exceptions.ChargeOffException;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffAddReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffFuzzyQueryReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffQueryReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffUpdateReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ValidateKeytReq;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffValidator extends CommonValidator {

    public static void validateChargeOffAddReq(ChargeOffAddReq req) {
        validateNull(req);
        validateNull(req.getCityCode(), new ChargeOffException(ChargeOffCodeEnum.CITY_CODE_IS_NULL));
        validateNull(req.getBankOutletsName(), new ChargeOffException(ChargeOffCodeEnum.BANK_OUTLETS_NAME_IS_NULL));
        validateNull(req.getKeytList(), new ChargeOffException(ChargeOffCodeEnum.KEYT_LIST_IS_NULL));
        validateNull(req.getTotalPoint(), new ChargeOffException(ChargeOffCodeEnum.TOTAL_POINT_IS_NULL));
        validateNull(req.getCustomerName(), new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_NAME_IS_NULL));
        validateNull(req.getCustomerMobile(), new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_MOBILE_IS_NULL));
        validateMobile(req.getCustomerMobile(), new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_MOBILE_ERROR));
        validateNull(req.getCustomerMobile(), new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_MOBILE_IS_NULL));
        if (null == req.getStatus() || null == ChargeOffStatusEnum.getByCode(req.getStatus())) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_STATUS_ERROR);
        }
        if (null == req.getChargeOffType() || null == ChargeOffTypeEnum.getByCode(req.getChargeOffType())) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_TYPE_ERROR);
        }
        if (ChargeOffTypeEnum.PRODUCT.equals(ChargeOffTypeEnum.getByCode(req.getChargeOffType()))) {
            validateNull(req.getProductValue(), new ChargeOffException(ChargeOffCodeEnum.PRODUCT_VALUE_IS_NULL));
        }
    }

    public static void validateChargeOffUpdateReq(ChargeOffUpdateReq req) {
        validateNull(req);
        validateNull(req.getId(), new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_ID_IS_NULL));
        validateNull(req.getCityCode(), new ChargeOffException(ChargeOffCodeEnum.CITY_CODE_IS_NULL));
        validateNull(req.getBankOutletsName(), new ChargeOffException(ChargeOffCodeEnum.BANK_OUTLETS_NAME_IS_NULL));
        validateNull(req.getKeytList(), new ChargeOffException(ChargeOffCodeEnum.KEYT_LIST_IS_NULL));
        validateNull(req.getTotalPoint(), new ChargeOffException(ChargeOffCodeEnum.TOTAL_POINT_IS_NULL));
        validateNull(req.getCustomerName(), new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_NAME_IS_NULL));
        validateNull(req.getCustomerMobile(), new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_MOBILE_IS_NULL));
        validateMobile(req.getCustomerMobile(), new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_MOBILE_ERROR));
        validateNull(req.getCustomerMobile(), new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_MOBILE_IS_NULL));
        if (null == req.getStatus() || null == ChargeOffStatusEnum.getByCode(req.getStatus())) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_STATUS_ERROR);
        }
        if (null == req.getChargeOffType() || null == ChargeOffTypeEnum.getByCode(req.getChargeOffType())) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_TYPE_ERROR);
        }
        if (ChargeOffTypeEnum.PRODUCT.equals(ChargeOffTypeEnum.getByCode(req.getChargeOffType()))) {
            validateNull(req.getProductValue(), new ChargeOffException(ChargeOffCodeEnum.PRODUCT_VALUE_IS_NULL));
        }
    }

    public static void validateChargeOffFuzzyQueryReq(ChargeOffFuzzyQueryReq req) {
        validateNull(req);
        validatePaging(req);
        if (null != req.getStatus() || null == ChargeOffStatusEnum.getByCode(req.getStatus())) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_STATUS_ERROR);
        }
        if (req.getSubmitterTimeBegin() != null
                && req.getSubmitterTimeEnd() != null
                && req.getSubmitterTimeBegin() > req.getSubmitterTimeEnd()) {
            throw new ChargeOffException(ChargeOffCodeEnum.SUBMITTER_TIME_ERROR);
        }
    }

    public static void validateChargeOffQueryReq(ChargeOffQueryReq req, boolean hasPaging) {
        validateNull(req);
        if (hasPaging) {
            validatePaging(req);
        }
        if (null != req.getStatus() && null == ChargeOffStatusEnum.getByCode(req.getStatus())) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_STATUS_ERROR);
        }
        if (null != req.getChargeOffType() && null == ChargeOffTypeEnum.getByCode(req.getChargeOffType())) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_TYPE_ERROR);
        }
        if (req.getSubmitterTimeBegin() != null
                && req.getSubmitterTimeEnd() != null
                && req.getSubmitterTimeBegin() > req.getSubmitterTimeEnd()) {
            throw new ChargeOffException(ChargeOffCodeEnum.SUBMITTER_TIME_ERROR);
        }
    }


    public static void validateValidateKeytReq(ValidateKeytReq req) {
        validateNull(req);
        validateNull(req.getKeyt(), new ChargeOffException(ChargeOffCodeEnum.KEYT_IS_NULL));
    }
}
