package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.req.auditrecord.AuditRecordAddReq;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
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

    void auditRecordPass(Integer auditRecordId, Integer version, Integer exchangeBatchId,Integer givingBatchId,String posCode,String cardNo,Date tradeTime) ;

    void addSave(String customerName, String customerMobile, BigDecimal exchangePoint, String exchangeType, BigDecimal givingPoint, String givingType, String remark, String imgUrls);

    void editSave(Integer id, Integer version, String customerName, String customerMobile, BigDecimal exchangePoint, String exchangeType, BigDecimal givingPoint, String givingType, String remark, String imgUrls);
}
