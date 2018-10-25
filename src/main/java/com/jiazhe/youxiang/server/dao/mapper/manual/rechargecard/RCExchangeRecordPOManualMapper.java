package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;

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
    int insert(RechargeCardExchangeRecordPO rechargeCardRecordPO);
}
