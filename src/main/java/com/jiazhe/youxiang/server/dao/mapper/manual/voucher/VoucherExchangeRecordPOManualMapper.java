package com.jiazhe.youxiang.server.dao.mapper.manual.voucher;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/03
 */
public interface VoucherExchangeRecordPOManualMapper {

    /**
     * 根据codeIDs查询兑换记录
     * @param codeIds
     * @return
     */
    List<VoucherExchangeRecordPO> findByCodeIds(List<Integer> codeIds);

    /**
     * 插入，有id返回
     * @param voucherExchangeRecordPO
     */
    void insert(VoucherExchangeRecordPO voucherExchangeRecordPO);
}
