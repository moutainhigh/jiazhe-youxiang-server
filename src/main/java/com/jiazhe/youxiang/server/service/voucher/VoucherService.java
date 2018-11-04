package com.jiazhe.youxiang.server.service.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public interface VoucherService {
    /**
     * 根据那些已经使用的兑换码，修改对应的代金券信息
     * @param usedIds
     * @param batchSaveDTO
     */
    void batchUpdate(List<Integer> usedIds, VoucherExchangeCodeBatchSaveDTO batchSaveDTO);

    /**
     * 已经使用的兑换码的ids 查找对应的代金券，并启用停用
     * @param usedIds
     * @param status
     */
    void batchChangeStatus(List<Integer> usedIds, Byte status);
}
