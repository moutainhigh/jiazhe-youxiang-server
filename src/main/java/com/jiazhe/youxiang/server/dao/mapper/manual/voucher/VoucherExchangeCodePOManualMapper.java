package com.jiazhe.youxiang.server.dao.mapper.manual.voucher;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface VoucherExchangeCodePOManualMapper {

    /**
     * 批量插入代金券兑换码
     * @param voucherExchangeCodePOList
     */
    void batchInsert(List<VoucherExchangeCodePO> voucherExchangeCodePOList);
}
