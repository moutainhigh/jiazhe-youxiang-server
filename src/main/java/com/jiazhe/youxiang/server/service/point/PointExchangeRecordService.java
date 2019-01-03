package com.jiazhe.youxiang.server.service.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public interface PointExchangeRecordService {

    List<PointExchangeRecordDTO> findByCodeIds(List<Integer> usedIds);

    void insert(PointExchangeRecordPO pointRecordPO);

    PointExchangeRecordDTO findByPointId(Integer id);

    PointExchangeRecordDTO findByCodeId(Integer id);

    /**
     * 判断是否充过值
     * @param purchaseOrderStr
     * @return
     */
    boolean hasCharged(String purchaseOrderStr);
}
