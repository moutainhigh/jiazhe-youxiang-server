package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void auditRecordPass(Integer auditRecordId, Integer version, Integer exchangeBatchId,Integer givingBatchId,String posCode,String cardNo,Long tradeTime)  {
        auditRecordService.auditRecordPass(auditRecordId, version, exchangeBatchId,givingBatchId,posCode,cardNo,new Date(tradeTime));
    }

    public void auditRecordUnpass(Integer auditRecordId, Integer version, String reason) {
        auditRecordService.auditRecordUnpass(auditRecordId, version, reason);
    }

    public Integer getWaitCheckCount() {
        return auditRecordService.getCountByStatus(Byte.valueOf("0"));
    }

    public AuditRecordDTO getById(Integer id) {
        return auditRecordService.getById(id);
    }

    public List<AuditRecordDTO> getList(Byte status, Paging paging) {
        return auditRecordService.getList(null, status, paging);
    }

    public List<AuditRecordDTO> getSubmitterList(Byte status,Integer submitterId, Paging paging) {
        return auditRecordService.getList(submitterId, status, paging);
    }


    public void editSave(Integer id, Integer version, String customerName, String customerMobile, BigDecimal exchangePoint, String exchangeType, BigDecimal givingPoint, String givingType, String remark, String imgUrls) {
        auditRecordService.editSave(id, version, customerName, customerMobile, exchangePoint,exchangeType,givingPoint,givingType,remark, imgUrls);
    }

    public void addSave(String customerName, String customerMobile, BigDecimal exchangePoint, String exchangeType, BigDecimal givingPoint, String givingType, String remark, String imgUrls) {
        auditRecordService.addSave(customerName, customerMobile, exchangePoint, exchangeType, givingPoint, givingType, remark, imgUrls);
    }
}
