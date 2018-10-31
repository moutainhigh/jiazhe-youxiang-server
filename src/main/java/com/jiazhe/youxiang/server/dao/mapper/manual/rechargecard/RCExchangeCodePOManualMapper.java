package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCExchangeCodePOManualMapper {

    int batchInsert(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList);

    RechargeCardExchangeCodePO findByKeyt(String keyt);

    int batchUpdate(List<RechargeCardExchangeCodePO> poList);

    /**
     * 根据批次id，批量修改兑换码的启用停用状态
     * @param batchId
     * @param status
     * @return
     */
    int batchChangeStatus(@Param("batchId") Integer batchId, @Param("status")Byte status);
}
