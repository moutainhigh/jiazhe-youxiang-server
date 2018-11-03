package com.jiazhe.youxiang.server.service.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public interface VoucherExchangeCodeService {

    /**
     * 根据批次id获取批次下代金券兑换码
     * @param id
     * @return
     */
    List<VoucherExchangeCodeDTO> getByBatchId(Integer id);

    /**
     * 批量插入代金券兑换码
     * @param voucherExchangeCodePOList
     */
    void batchInsert(List<VoucherExchangeCodePO> voucherExchangeCodePOList);
}
