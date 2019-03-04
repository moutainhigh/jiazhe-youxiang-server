package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.AuditRecordPO;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.vo.resp.auditrecord.AuditRecordResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class AuditRecordAdapter {
    public static AuditRecordResp DTO2Resp(AuditRecordDTO auditRecordDTO) {
        if (auditRecordDTO == null) {
            return null;
        }
        AuditRecordResp auditRecordResp = new AuditRecordResp();
        auditRecordResp.setId(auditRecordDTO.getId());
        auditRecordResp.setPointIds(auditRecordDTO.getPointIds());
        auditRecordResp.setCustomerName(auditRecordDTO.getCustomerName());
        auditRecordResp.setCustomerMobile(auditRecordDTO.getCustomerMobile());
        auditRecordResp.setExchangePoint(auditRecordDTO.getExchangePoint());
        auditRecordResp.setExchangeType(auditRecordDTO.getExchangeType());
        auditRecordResp.setGivingPoint(auditRecordDTO.getGivingPoint());
        auditRecordResp.setGivingType(auditRecordDTO.getGivingType());
        auditRecordResp.setAuditReason(auditRecordDTO.getAuditReason());
        auditRecordResp.setRemark(auditRecordDTO.getRemark());
        auditRecordResp.setImgUrls(auditRecordDTO.getImgUrls());
        auditRecordResp.setPosCode(auditRecordDTO.getPosCode());
        auditRecordResp.setCardNo(auditRecordDTO.getCardNo());
        auditRecordResp.setTradeTime(auditRecordDTO.getTradeTime().getTime());
        auditRecordResp.setSubmitterId(auditRecordDTO.getSubmitterId());
        auditRecordResp.setSubmitterName(auditRecordDTO.getSubmitterName());
        auditRecordResp.setStatus(auditRecordDTO.getStatus());
        auditRecordResp.setAuditTime(auditRecordDTO.getAuditTime().getTime());
        auditRecordResp.setAuditorId(auditRecordDTO.getAuditorId());
        auditRecordResp.setAuditorName(auditRecordDTO.getAuditorName());
        auditRecordResp.setVersion(auditRecordDTO.getVersion());
        auditRecordResp.setAddTime(auditRecordDTO.getAddTime().getTime());
        auditRecordResp.setModTime(auditRecordDTO.getModTime().getTime());
        return auditRecordResp;
    }

    public static AuditRecordDTO PO2DTO(AuditRecordPO auditRecordPO) {
        if (auditRecordPO == null) {
            return null;
        }
        AuditRecordDTO auditRecordDTO = new AuditRecordDTO();
        auditRecordDTO.setId(auditRecordPO.getId());
        auditRecordDTO.setPointIds(auditRecordPO.getPointIds());
        auditRecordDTO.setCustomerName(auditRecordPO.getCustomerName());
        auditRecordDTO.setCustomerMobile(auditRecordPO.getCustomerMobile());
        auditRecordDTO.setExchangePoint(auditRecordPO.getExchangePoint());
//        auditRecordDTO.setExchangeType(auditRecordPO.getExchangeType());
        auditRecordDTO.setGivingPoint(auditRecordPO.getGivingPoint());
        auditRecordDTO.setGivingType(auditRecordPO.getGivingType());
        auditRecordDTO.setAuditReason(auditRecordPO.getAuditReason());
        auditRecordDTO.setRemark(auditRecordPO.getRemark());
        auditRecordDTO.setPosCode(auditRecordPO.getPosCode());
        auditRecordDTO.setCardNo(auditRecordPO.getCardNo());
        auditRecordDTO.setTradeTime(auditRecordPO.getTradeTime());
        auditRecordDTO.setImgUrls(auditRecordPO.getImgUrls());
        auditRecordDTO.setSubmitterId(auditRecordPO.getSubmitterId());
        auditRecordDTO.setSubmitterName(auditRecordPO.getSubmitterName());
        auditRecordDTO.setStatus(auditRecordPO.getStatus());
        auditRecordDTO.setAuditTime(auditRecordPO.getAuditTime());
        auditRecordDTO.setAuditorId(auditRecordPO.getAuditorId());
        auditRecordDTO.setAuditorName(auditRecordPO.getAuditorName());
        auditRecordDTO.setVersion(auditRecordPO.getVersion());
        auditRecordDTO.setAddTime(auditRecordPO.getAddTime());
        auditRecordDTO.setModTime(auditRecordPO.getModTime());
        return auditRecordDTO;
    }
}
