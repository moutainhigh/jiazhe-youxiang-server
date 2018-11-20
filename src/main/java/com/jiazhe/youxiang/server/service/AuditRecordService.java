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

    List<AuditRecordDTO> getList(Integer submitterId,Byte status, Paging paging);

    AuditRecordDTO getById(Integer id);

    Integer getCountByStatus(Byte status);

    void auditRecordUnpass(Integer auditRecordId, Integer version, String reason);

    void auditRecordPass(Integer auditRecordId, Integer version, Integer rechargeCardCodeBatchId);
}
