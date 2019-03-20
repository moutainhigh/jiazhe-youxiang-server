package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("auditRecordBiz")
public class AuditRecordBiz {

    @Autowired
    private AuditRecordService auditRecordService;

    public void auditRecordPass(Integer auditRecordId, Integer version, Integer exchangeBatchId) {
        auditRecordService.auditRecordPass(auditRecordId, version, exchangeBatchId);
    }

    public void auditRecordUnpass(Integer auditRecordId, Integer version, String reason) {
        auditRecordService.auditRecordUnpass(auditRecordId, version, reason);
    }

    public Integer getWaitCheckCount() {
        return auditRecordService.getCountByStatus(CommonConstant.AUDIT_RECORD_HAS_SUBMITTED);
    }

    public AuditRecordDTO getById(Integer id) {
        return auditRecordService.getById(id);
    }

    public List<AuditRecordDTO> getList(String customerMobile, Byte status, Paging paging) {
        return auditRecordService.getList(customerMobile, null, status, paging);
    }

    public List<AuditRecordDTO> getSubmitterList(Byte status, Integer submitterId, Paging paging) {
        return auditRecordService.getList(null, submitterId, status, paging);
    }

    public void save(AuditRecordDTO auditRecordDTO) {
        auditRecordService.save(auditRecordDTO);
    }

    public void deleteById(Integer id) {
        auditRecordService.deleteById(id);
    }

    public void completeChargeReceipt(Integer id) {
        auditRecordService.completeChargeReceipt(id);
    }
}
