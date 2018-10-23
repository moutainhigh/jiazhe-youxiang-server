package com.jiazhe.youxiang.server.service.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/22.
 */
public interface RCExchangeCodeService {
    /**
     * 批量保存兑换码信息
     * @param rechargeCardExchangeCodePOList
     * @return
     */
    int batchSave(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList);

    /**
     * 修改兑换码启用停用状态
     * @param id
     * @param b
     * @return
     */
    int changeCodeStatus(Integer id, Byte status);

    int changeExpiryTime(Integer id, Date expiryTime);
}
