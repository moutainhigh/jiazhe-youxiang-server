package com.jiazhe.youxiang.server.service.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public interface PointExchangeRecordService {

    /**
     * 根据兑换码的ids 查找兑换记录
     * @param ids
     * @return
     */
    List<PointExchangeRecordDTO> findByCodeIds(List<Integer> ids);

    /**
     * 插入兑换记录信息
     * @param pointRecordPO
     */
    void insert(PointExchangeRecordPO pointRecordPO);

    PointExchangeRecordDTO findByPointId(Integer id);

    PointExchangeRecordDTO findByCodeId(Integer id);

    /**
     * 判断是否充过值
     * @param purchaseOrderStr
     * @return
     */
    boolean hasCharged(String purchaseOrderStr);

    /**
     * 根据小票信息查询兑换记录
     * @param receiptInfos
     * @return
     */
    List<PointExchangeRecordDTO> findByExtInfos(List<String> receiptInfos);
}
