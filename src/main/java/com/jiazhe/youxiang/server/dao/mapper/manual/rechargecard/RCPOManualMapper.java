package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据id获取充值卡
     * @param ids
     * @return
     */
    List<RechargeCardPO> findByIds(List<Integer> ids);

    /**
     * 批量更新充值卡信息
     * @param rcPOList
     * @return
     */
    int batchUpdate(List<RechargeCardPO> rcPOList);

    /**
     * 根据充值卡ids 批量启用、停用充值卡
     * @param map
     * @return
     */
    Integer batchChangeStatus(Map<String,Object> map);
}
