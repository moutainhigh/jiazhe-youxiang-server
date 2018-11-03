package com.jiazhe.youxiang.server.service.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public interface VoucherExchangeCodeBatchService {
    /**
     * 根据参数查找批次信息
     * @param projectId
     * @param name
     * @param paging
     * @return
     */
    List<VoucherExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging);

    /**
     * 保存代金券批次信息
     * @param voucherExchangeCodeBatchSaveDTO
     */
    void addSave(VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO);

    /**
     * 修改代金券批次信息
     * @param voucherExchangeCodeBatchSaveDTO
     */

    void editSave(VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO);

    /**
     * 回显代金券批次信息
     * @param id
     * @return
     */
    VoucherExchangeCodeBatchEditDTO getById(Integer id);

    /**
     * 生成代金券兑换码
     * @param id
     */
    void generateCode(Integer id);
}
