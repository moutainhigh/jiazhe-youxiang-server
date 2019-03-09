package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.Date;
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
     * @param paging
     * @return
     */
    List<AuditRecordDTO> getList(String customerMobile, Integer submitterId, Byte status, Paging paging);

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
     * 充值通过
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

}
