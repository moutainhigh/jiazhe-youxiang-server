package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.auditrecord.AuditRecordAddReq;
import com.jiazhe.youxiang.server.vo.req.auditrecord.AuditRecordEditReq;
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

    public int auditRecordPass(Integer auditRecordId, Integer rechargeCardCodeBatchId, String reason) {
        return 0;
    }

    public int auditRecordUnpass(Integer auditRecordId, String reason) {
        return 0;
    }

    public Integer getWaitCheckCount() {
        return auditRecordService.getCountByStatus(Byte.valueOf("0"));
    }

    public AuditRecordDTO getById(Integer id) {
        return auditRecordService.getById(id);
    }

    public List<AuditRecordDTO> getList(Byte status,Paging paging) {
        return auditRecordService.getList(null,status,paging);
    }

    public List<AuditRecordDTO> getSubmitterList(Integer submitterId, Paging paging) {
        return auditRecordService.getList(submitterId,null,paging);
    }

    public int addSave(AuditRecordAddReq req) {
        return 0;
    }

    public int editSave(AuditRecordEditReq req) {
        return 0;
    }
}
