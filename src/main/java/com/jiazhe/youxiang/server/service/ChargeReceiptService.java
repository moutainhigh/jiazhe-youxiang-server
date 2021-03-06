package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public interface ChargeReceiptService {
    /**
     * 根据条件，获取充值凭证列表
     * @param auditRecordId
     * @param customerName
     * @param cardNo
     * @param posCode
     * @param tradeStartTime
     * @param tradeEndTime
     * @param paging
     * @return
     */
    List<ChargeReceiptDTO> getList(Integer auditRecordId,String customerName,String cardNo,String posCode,Date tradeStartTime,Date tradeEndTime, Paging paging);

    /**
     * 删除消费凭证
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 保存消费凭证
     *
     * @param dto
     */
    void save(ChargeReceiptSaveDTO dto);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    ChargeReceiptDTO getById(Integer id);

    /**
     * 根据条件查询列表 不分页
     *
     * @param customerInfo
     * @param submitterName
     * @param status
     * @param chargeReceiptStatus
     * @param submitterName
     * @param submitStartTime
     * @param submitEndTime
     * @param exchangeType
     * @param exchangePoint
     * @param pointCodes
     * @param cityCode
     * @return
     */
    List<ChargeReceiptDTO> getList(String customerInfo, String submitterName, Byte status, Byte chargeReceiptStatus, String pointCodes,String exchangePoint, Date submitStartTime, Date submitEndTime,String exchangeType,String cityCode);

    /**
     * 通过消费记录id，查找小票集合
     *
     * @param auditRecordId
     * @return
     */
    List<ChargeReceiptDTO> getByAuditRecordId(Integer auditRecordId);

    /**
     * 判断此小票凭证是否已经存在
     * @param dto
     * @return
     */
    boolean hasExisted(ChargeReceiptSaveDTO dto);
}
