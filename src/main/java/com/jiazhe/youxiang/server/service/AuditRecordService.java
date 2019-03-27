package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author TU
 * @description 消费记录
 * @date 2018/11/20.
 */
public interface AuditRecordService {

    /**
     * 根据条件查询列表
     *
     * @param customerMobile
     * @param submitterId
     * @param status
     * @param chargeReceiptStatus
     * @param paging
     * @return
     */
    List<AuditRecordDTO> getList(String customerMobile, Integer submitterId, Byte status,Byte chargeReceiptStatus, Paging paging);

    /**
     * 根据id获取详细记录
     *
     * @param id
     * @return
     */
    AuditRecordDTO getById(Integer id);

    /**
     * 获取已提交（也就是待审核）记录条数
     *
     * @param status
     * @return
     */
    Integer getCountByStatus(Byte status);

    /**
     * 充值驳回
     *
     * @param auditRecordId
     * @param version
     * @param reason
     */
    void auditRecordUnpass(Integer auditRecordId, Integer version, String reason);

    /**
     * 充值通过审核
     *
     * @param auditRecordId
     * @param version
     * @param exchangeBatchId
     */
    void auditRecordPass(Integer auditRecordId, Integer version, Integer exchangeBatchId);

    /**
     * 保存充值记录
     *
     * @param auditRecordDTO
     */
    void save(AuditRecordDTO auditRecordDTO);

    /**
     * 通过id软删除记录
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 完成消费凭证的录入
     * @param id
     * @param status
     */
    void changeChargeReceiptStatus(Integer id,Byte status);

    /**
     * 根据条件查询 不分页
     * @param customerMobile
     * @param status
     * @param chargeReceiptStatus
     * @return
     */
   List<AuditRecordDTO> getList(String customerMobile, Byte status,Byte chargeReceiptStatus);
}
