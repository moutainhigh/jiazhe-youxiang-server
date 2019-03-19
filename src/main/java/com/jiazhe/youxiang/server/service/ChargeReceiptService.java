package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public interface ChargeReceiptService {
    /**
     * 根据充值记录id，获取充值凭证列表
     * @param auditRecordId
     * @param paging
     * @return
     */
    List<ChargeReceiptDTO> getList(Integer auditRecordId, Paging paging);

    /**
     * 删除消费凭证
     * @param id
     */
    void delete(Integer id);

    /**
     * 保存消费凭证
     * @param dto
     */
    void save(ChargeReceiptSaveDTO dto);

    /**
     * 获取详情
     * @param id
     * @return
     */
    ChargeReceiptDTO getById(Integer id);
}
