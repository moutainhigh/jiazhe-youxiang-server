package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCExchangeRecordPOManualMapper {
    /**
     * 插入充值卡记录
     * @param rechargeCardRecordPO
     * @return
     */
    void insert(RechargeCardExchangeRecordPO rechargeCardRecordPO);

    /**
     * 根据码ids，查找兑换记录列表
     * @param codeIds
     * @return
     */
    List<RechargeCardExchangeRecordPO> findByCodeIds(List<Integer> codeIds);
}
