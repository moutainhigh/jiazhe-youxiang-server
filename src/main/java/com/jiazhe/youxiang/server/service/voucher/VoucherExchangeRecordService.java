package com.jiazhe.youxiang.server.service.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;
import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;

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
    List<VoucherExchangeRecordDTO> findByCodeIds(List<Integer> usedIds);

    VoucherExchangeRecordDTO findByVoucherId(Integer id);

    void insert(VoucherExchangeRecordPO voucherExchangeRecordPO);

    /**
     * 根据code的id查询兑换记录
     * @param id
     * @return
     */
    VoucherExchangeRecordDTO findByCodeId(Integer id);
}
