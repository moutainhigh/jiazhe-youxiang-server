package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeAdapter;
import com.jiazhe.youxiang.server.domain.po.AuditRecordPO;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordSumDTO;
import com.jiazhe.youxiang.server.dto.auditrecord.StatisticsDTO;
import com.jiazhe.youxiang.server.vo.resp.auditrecord.AuditRecordResp;
import com.jiazhe.youxiang.server.vo.resp.auditrecord.AuditRecordSumResp;
import com.jiazhe.youxiang.server.vo.resp.auditrecord.StatisticsResp;

import java.util.stream.Collectors;

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
        auditRecordResp.setChargeReceiptPoint(auditRecordDTO.getChargeReceiptPoint());
        auditRecordResp.setGivingPoint(auditRecordDTO.getGivingPoint());
        auditRecordResp.setExchangeType(auditRecordDTO.getExchangeType());
        auditRecordResp.setChargeReceiptStatus(auditRecordDTO.getChargeReceiptStatus());
        auditRecordResp.setPointCodes(auditRecordDTO.getPointCodes());
        auditRecordResp.setPointExchangeCodeRespList(null == auditRecordDTO.getPointExchangeCodeDTOList() ? null : auditRecordDTO.getPointExchangeCodeDTOList().stream().map(PointExchangeCodeAdapter::dto2Resp).collect(Collectors.toList()));
        auditRecordResp.setProductValue(auditRecordDTO.getProductValue());
        auditRecordResp.setAuditReason(auditRecordDTO.getAuditReason());
        auditRecordResp.setRemark(auditRecordDTO.getRemark());
        auditRecordResp.setImgUrls(auditRecordDTO.getImgUrls());
        auditRecordResp.setPosCode(auditRecordDTO.getPosCode());
        auditRecordResp.setCardNo(auditRecordDTO.getCardNo());
        auditRecordResp.setCityCode(auditRecordDTO.getCityCode());
        auditRecordResp.setCityName(auditRecordDTO.getCityName());
        auditRecordResp.setBankOutletsName(auditRecordDTO.getBankOutletsName());
        auditRecordResp.setTradeTime(auditRecordDTO.getTradeTime().getTime());
        auditRecordResp.setSubmitterId(auditRecordDTO.getSubmitterId());
        auditRecordResp.setSubmitterName(auditRecordDTO.getSubmitterName());
        auditRecordResp.setSubmitTime(auditRecordDTO.getSubmitTime().getTime());
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
        auditRecordDTO.setGivingPoint(auditRecordPO.getGivingPoint());
        auditRecordDTO.setExchangeType(auditRecordPO.getExchangeType());
        auditRecordDTO.setChargeReceiptStatus(auditRecordPO.getChargeReceiptStatus());
        auditRecordDTO.setPointCodes(auditRecordPO.getPointCodes());
        auditRecordDTO.setProductValue(auditRecordPO.getProductValue());
        auditRecordDTO.setAuditReason(auditRecordPO.getAuditReason());
        auditRecordDTO.setRemark(auditRecordPO.getRemark());
        auditRecordDTO.setPosCode(auditRecordPO.getPosCode());
        auditRecordDTO.setCardNo(auditRecordPO.getCardNo());
        auditRecordDTO.setCityCode(auditRecordPO.getCityCode());
        auditRecordDTO.setCityName(auditRecordPO.getCityName());
        auditRecordDTO.setBankOutletsName(auditRecordPO.getBankOutletsName());
        auditRecordDTO.setTradeTime(auditRecordPO.getTradeTime());
        auditRecordDTO.setImgUrls(auditRecordPO.getImgUrls());
        auditRecordDTO.setSubmitTime(auditRecordPO.getSubmitTime());
        auditRecordDTO.setSubmitterId(auditRecordPO.getSubmitterId());
        auditRecordDTO.setSubmitterName(auditRecordPO.getSubmitterName());
        auditRecordDTO.setStatus(auditRecordPO.getStatus());
        auditRecordDTO.setAuditTime(auditRecordPO.getAuditTime());
        auditRecordDTO.setAuditorId(auditRecordPO.getAuditorId());
        auditRecordDTO.setAuditorName(auditRecordPO.getAuditorName());
        auditRecordDTO.setVersion(auditRecordPO.getVersion());
        auditRecordDTO.setAddTime(auditRecordPO.getAddTime());
        auditRecordDTO.setModTime(auditRecordPO.getModTime());
        auditRecordDTO.setIsDeleted(auditRecordPO.getIsDeleted());
        return auditRecordDTO;
    }

    public static AuditRecordPO dto2Po(AuditRecordDTO auditRecordDTO) {
        if (auditRecordDTO == null) {
            return null;
        }
        AuditRecordPO auditRecordPO = new AuditRecordPO();
        auditRecordPO.setId(auditRecordDTO.getId());
        auditRecordPO.setPointIds(auditRecordDTO.getPointIds());
        auditRecordPO.setCustomerName(auditRecordDTO.getCustomerName());
        auditRecordPO.setCustomerMobile(auditRecordDTO.getCustomerMobile());
        auditRecordPO.setExchangePoint(auditRecordDTO.getExchangePoint());
        auditRecordPO.setGivingPoint(auditRecordDTO.getGivingPoint());
        auditRecordPO.setExchangeType(auditRecordDTO.getExchangeType());
        auditRecordPO.setChargeReceiptStatus(auditRecordDTO.getChargeReceiptStatus());
        auditRecordPO.setPointCodes(auditRecordDTO.getPointCodes());
        auditRecordPO.setProductValue(auditRecordDTO.getProductValue());
        auditRecordPO.setRemark(auditRecordDTO.getRemark());
        auditRecordPO.setAuditReason(auditRecordDTO.getAuditReason());
        auditRecordPO.setImgUrls(auditRecordDTO.getImgUrls());
        auditRecordPO.setPosCode(auditRecordDTO.getPosCode());
        auditRecordPO.setCardNo(auditRecordDTO.getCardNo());
        auditRecordPO.setCityCode(auditRecordDTO.getCityCode());
        auditRecordPO.setCityName(auditRecordDTO.getCityName());
        auditRecordPO.setBankOutletsName(auditRecordDTO.getBankOutletsName());
        auditRecordPO.setTradeTime(auditRecordDTO.getTradeTime());
        auditRecordPO.setSubmitTime(auditRecordDTO.getSubmitTime());
        auditRecordPO.setSubmitterId(auditRecordDTO.getSubmitterId());
        auditRecordPO.setSubmitterName(auditRecordDTO.getSubmitterName());
        auditRecordPO.setStatus(auditRecordDTO.getStatus());
        auditRecordPO.setAuditTime(auditRecordDTO.getAuditTime());
        auditRecordPO.setAuditorId(auditRecordDTO.getAuditorId());
        auditRecordPO.setAuditorName(auditRecordDTO.getAuditorName());
        auditRecordPO.setVersion(auditRecordDTO.getVersion());
        auditRecordPO.setAddTime(auditRecordDTO.getAddTime());
        auditRecordPO.setModTime(auditRecordDTO.getModTime());
        return auditRecordPO;
    }

    public static AuditRecordSumResp sumDto2SumResp(AuditRecordSumDTO dto) {
        if (dto == null) {
            return null;
        }
        AuditRecordSumResp auditRecordSumResp = new AuditRecordSumResp();
        auditRecordSumResp.setExchangePointSum(dto.getExchangePointSum());
        auditRecordSumResp.setProductValueSum(dto.getProductValueSum());
        return auditRecordSumResp;
    }

    public static StatisticsResp statisticsDto2Resp(StatisticsDTO dto) {
        if (dto == null) {
            return null;
        }
        StatisticsResp statisticsResp = new StatisticsResp();
        statisticsResp.setRecordNum(dto.getRecordNum());
        statisticsResp.setTotalExchangePoint(dto.getTotalExchangePoint());
        statisticsResp.setTotalGivingPoint(dto.getTotalGivingPoint());
        statisticsResp.setTotalProductValue(dto.getTotalProductValue());
        return statisticsResp;
    }
}
