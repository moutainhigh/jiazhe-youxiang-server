package com.jiazhe.youxiang.server.biz.point;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
@Service("pointExchangeCodeBiz")
public class PointExchangeCodeBiz {

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    public List<PointExchangeCodeDTO> getByBatchId(Integer id) {
        return pointExchangeCodeService.getByBatchId(id);
    }

    public List<PointExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        return pointExchangeCodeService.getList(batchId,code,keyt,status,used,paging);
    }
}
