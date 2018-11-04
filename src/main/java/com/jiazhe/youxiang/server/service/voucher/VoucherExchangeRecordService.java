package com.jiazhe.youxiang.server.service.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public interface VoucherExchangeRecordService {

    /**
     * 根据code的ids查询兑换记录
     * @param usedIds
     * @return
     */
    List<VoucherExchangeRecordPO> findByCodeIds(List<Integer> usedIds);
}
