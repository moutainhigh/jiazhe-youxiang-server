/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.IdListReq;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 通用参数验证器
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class CommonValidator {

    public static void validateNull(BaseVO req) {
        validateNull(req, null);
    }

    public static void validateNull(BaseVO req, CommonException exception) {
        if (req == null) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "ID不合法");
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

    public static void validateNull(String str) {
        validateNull(str, null);
    }

    public static void validateNull(String str, CommonException exception) {
        if (Strings.isBlank(str)) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "参数不能为空");
            }
        }
    }

    public  static void validateNull(Date date){
        validateNull(date,null);
    }

    public static void validateNull(Date date, CommonException exception) {
        if (null == date) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "参数不能为空");
            }
        }
    }

    public  static void validateNull(Integer integer){
        validateNull(integer,null);
    }

    public static void validateNull(Integer integer, CommonException exception) {
        if (null == integer) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "参数不能为空");
            }
        }
    }

    public  static void validateNull(BigDecimal bigDecimal){
        validateNull(bigDecimal,null);
    }

    public static void validateNull(BigDecimal bigDecimal, CommonException exception) {
        if (null == bigDecimal) {
            if (exception != null) {
                throw exception;
            } else {
                throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "参数不能为空");
            }
        }
    }
}
