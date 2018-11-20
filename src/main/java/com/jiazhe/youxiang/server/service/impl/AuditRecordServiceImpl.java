package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.AuditRecordAdapter;
import com.jiazhe.youxiang.server.dao.mapper.AuditRecordPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.AuditRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.AuditRecordPO;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/11/20.
 */
@Service("auditRecordService")
@Transactional(rollbackFor = Exception.class)
public class AuditRecordServiceImpl implements AuditRecordService {

    @Autowired
    private AuditRecordPOMapper auditRecordPOMapper;
    @Autowired
    private AuditRecordPOManualMapper auditRecordPOManualMapper;

    @Override
    public List<AuditRecordDTO> getList(Byte status, Paging paging) {
        Integer count = auditRecordPOManualMapper.count(status);
        List<AuditRecordPO> auditRecordPOList = auditRecordPOManualMapper.query(status,paging.getOffset(),paging.getLimit());
        paging.setTotal(count);
        return auditRecordPOList.stream().map(AuditRecordAdapter::PO2DTO).collect(Collectors.toList());
    }
}
