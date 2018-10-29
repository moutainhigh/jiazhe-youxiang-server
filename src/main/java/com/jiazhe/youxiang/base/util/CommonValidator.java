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
import org.apache.commons.collections.CollectionUtils;

/**
 * 通用参数验证器
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class CommonValidator {

    public static void validateNull(BaseVO req) {
        if (req == null) {
            throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "参数不能为空");
        }
    }

    public static void validateId(Integer id) {
        if (id == null || id < 0) {
            throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "ID不合法");
        }
    }

    public static void validateId(IdReq id) {
        if (id == null || id.getId() == null || id.getId() < 0) {
            throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "ID不合法");
        }
    }

    public static void validateIdList(IdListReq idList) {
        if (idList == null || CollectionUtils.isEmpty(idList.getIds())) {
            throw new CommonException(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode(), CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType(), "ID集合不能为空");
        }
    }
}
