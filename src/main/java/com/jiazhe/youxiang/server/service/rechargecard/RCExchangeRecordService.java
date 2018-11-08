package com.jiazhe.youxiang.server.service.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public interface RCExchangeRecordService {

    void insert(RechargeCardExchangeRecordPO rechargeCardRecordPO);

    /**
     * 根据兑换码的ids，查找兑换记录
     * @param usedIds
     * @return
     */
    List<RechargeCardExchangeRecordPO> findByCodeIds(List<Integer> usedIds);

    /**
     * 根据充值卡id，查询兑换记录
     * @param cardId
     * @return
     */
    RCExchangeRecordDTO findByCardId(Integer cardId);

    /**
     * 根据兑换码id查找兑换记录
     * @param id
     * @return
     */
    RCExchangeRecordDTO findByCodeId(Integer id);
}
