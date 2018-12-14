package com.jiazhe.youxiang.server.service.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public interface PointExchangeCodeService {

    List<PointExchangeCodeDTO> getByBatchId(Integer id);

    void updateWithBatch(PointExchangeCodeBatchSaveDTO batchSaveDTO);

    void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList);

    void batchChangeStatus(Integer id, Byte status);

    List<PointExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging);
}
