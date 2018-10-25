package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCPOManualMapper {
    /**
     * 插入充值卡信息
     * @param rechargeCardPO
     * @return
     */
    int insert(RechargeCardPO rechargeCardPO);
}
