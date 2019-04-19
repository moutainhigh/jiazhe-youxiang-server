package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<AuditRecordDTO> getList(String customerInfo, String submitterName, Byte status, Byte chargeReceiptStatus, String pointCodes, Date submitStartTime, Date submitEndTime, Paging paging) {
        return auditRecordService.getList(null, customerInfo, submitterName, status, chargeReceiptStatus, pointCodes, submitStartTime, submitEndTime, paging);
    }

    public List<AuditRecordDTO> getSubmitterList(Integer submitterId, String customerInfo, Byte status, Paging paging) {
        return auditRecordService.getList(submitterId, customerInfo, null, status, null, null, null, null, paging);
    }

    public void save(AuditRecordDTO auditRecordDTO) {
        auditRecordService.save(auditRecordDTO);
    }

    public void deleteById(Integer id) {
        auditRecordService.deleteById(id);
    }

    public void completeChargeReceipt(Integer id) {
        auditRecordService.changeChargeReceiptStatus(id, CommonConstant.CHARGE_RECEIPT_COMPLETE);
    }

    public void uncompleteChargeReceipt(Integer id) {
        auditRecordService.changeChargeReceiptStatus(id, CommonConstant.CHARGE_RECEIPT_UNCOMPLETE);
    }
}
