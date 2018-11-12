package com.jiazhe.youxiang.server.service.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherPO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public interface VoucherService {
    /**
     * 根据代金券ids，修改对应的代金券信息
     * @param ids
     * @param batchSaveDTO
     */
    void batchUpdate(List<Integer> ids, VoucherExchangeCodeBatchSaveDTO batchSaveDTO);

    /**
     * 根据代金券ids，批量启用停用
     * @param ids
     * @param status
     */
    void batchChangeStatus(List<Integer> ids, Byte status);

    /**
     * 根据客户电话，代金券状态，是否过期和分页参数查询充值卡信息
     * @param mobile
     * @param exchangeType
     * @param status
     * @param expiry
     * @param paging
     * @return
     */
    List<VoucherDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging);

    /**
     * 启用、停用代金券
     * @param id
     * @param status
     */
    void changeStatus(Integer id, Byte status);

    /**
     * 查询代金券
     * @param id
     * @return
     */
    VoucherDTO getById(Integer id);

    void editSave(VoucherEditDTO dto);

    void insert(VoucherPO voucherPO);

    void update(VoucherPO voucherPO);

    void batchChangeUsed(List<Integer> ids, Byte aByte);

    List<VoucherDTO> findByIds(List<Integer> voucherIds);
}
