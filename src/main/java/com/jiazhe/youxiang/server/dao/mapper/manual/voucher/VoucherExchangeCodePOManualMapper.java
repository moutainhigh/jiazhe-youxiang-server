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

    /**
     * 计数
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @return
     */
    Integer count(@Param("batchId")Integer batchId, @Param("code")String code, @Param("keyt")String keyt, @Param("status")Byte status, @Param("used")Byte used);

    /**
     * 分页查询代金券兑换码信息
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @param offset
     * @param limit
     * @return
     */
    List<VoucherExchangeCodePO> query(@Param("batchId")Integer batchId, @Param("code")String code, @Param("keyt")String keyt, @Param("status")Byte status, @Param("used")Byte used, @Param("offset")Integer offset, @Param("limit")Integer limit);
}
