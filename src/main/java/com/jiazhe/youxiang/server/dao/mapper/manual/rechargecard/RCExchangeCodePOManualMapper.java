package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCExchangeCodePOManualMapper {

    int batchSave(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList);
}
