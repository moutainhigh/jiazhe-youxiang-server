package com.jiazhe.youxiang.server.service.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

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

    /**
     * 分页查询代金券兑换码信息
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @param paging
     * @return
     */
    List<VoucherExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging);

    /**
     * 根据批次信息，修改批次下面兑换码信息
     * @param batchSaveDTO
     */
    void updateWithBatch(VoucherExchangeCodeBatchSaveDTO batchSaveDTO);

    /**
     * 根据批次id，修改批次下兑换码状态
     * @param batchId
     * @param status
     */
    void batchChangeStatus(Integer batchId, Byte status);
}
