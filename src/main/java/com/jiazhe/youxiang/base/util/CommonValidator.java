/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.vo.req.IdListReq;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用参数验证器
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class CommonValidator {

    public static void validateNull(Object obj) {
        validateNull(obj, null);
    }

    public static void validateNull(Object obj, CommonException exception) {
        if (null == obj) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "参数不能为空");
            }
        } else {
            if (obj instanceof String) {
                validateNull((String) obj, exception);
            } else if (obj instanceof Collection) {
                validateNull((Collection) obj, exception);
            }
        }
    }

    public static void validateNull(String str, CommonException exception) {
        if (Strings.isBlank(str)) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "参数字符串不能为空");
            }
        }
    }

    public static void validateNull(Collection coll, CommonException exception) {
        if (CollectionUtils.isEmpty(coll)) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "集合参数不能为空");
            }
        }
    }

    public static void validateId(Integer id) {
        validateId(id, null);
    }

    public static void validateId(Integer id, CommonException exception) {
        if (id == null || id < 0) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "ID不合法");
            }
        }
    }

    public static void validateId(IdReq id) {
        validateId(id, null);
    }

    public static void validateId(IdReq id, CommonException exception) {
        if (id == null || id.getId() == null || id.getId() < 0) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "ID不合法");
            }
        }
    }

    public static void validateIdList(IdListReq idList) {
        validateIdList(idList, null);
    }

    public static void validateIdList(IdListReq idList, CommonException exception) {
        if (idList == null || CollectionUtils.isEmpty(idList.getIds())) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "ID集合不能为空");
            }
        }
    }

    public static void validateIdList(List<Integer> idList) {
        validateIdList(idList, null);
    }

    public static void validateIdList(List<Integer> idList, CommonException exception) {
        if (CollectionUtils.isEmpty(idList)) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "ID集合不能为空");
            }
        }
    }

    public static void validatePaging(OffsetLimitReq offsetLimitReq) {
        if (offsetLimitReq == null || offsetLimitReq.getLimit() == null || offsetLimitReq.getLimit() < 0 || offsetLimitReq.getOffset() == null || offsetLimitReq.getOffset() < 0) {
            throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "分页信息填写不正确");
        }
    }

    public static void validatePaging(PageSizeNumReq pageSizeNumReq) {
        if (pageSizeNumReq == null || pageSizeNumReq.getPageNum() == null || pageSizeNumReq.getPageNum() < 1 || pageSizeNumReq.getPageSize() == null || pageSizeNumReq.getPageSize() < 1) {
            throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "分页信息填写不正确");
        }
    }


    public static void validateMobile(String mobile, CommonException exception) {
        String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        Pattern p = Pattern.compile(PHONE_NUMBER_REG);
        Matcher m = p.matcher(mobile);
        if (!m.matches()) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "手机号为空或格式错误");
            }
        }
    }
}
