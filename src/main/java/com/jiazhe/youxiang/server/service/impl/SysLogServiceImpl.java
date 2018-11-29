/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysLogAdapter;
import com.jiazhe.youxiang.server.dao.mapper.SysLogPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysLogPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.SysLogPO;
import com.jiazhe.youxiang.server.dto.syslog.SysLogDTO;
import com.jiazhe.youxiang.server.service.SysLogService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogPOMapper sysLogPOMapper;

    @Autowired
    private SysLogPOManualMapper sysLogPOManualMapper;

    @Override
    public int insert(SysLogDTO sysLogDTO) {
        return sysLogPOMapper.insertSelective(SysLogAdapter.sysLogDTO2PO(sysLogDTO));
    }

    @Override
    public List<SysLogDTO> getList(String moduleName, String operate, Integer level, String operatorName, String ip, Paging paging) {
        Integer count = sysLogPOManualMapper.count(moduleName, operate, level, operatorName, ip);
        List<SysLogPO> sysLogPOList = sysLogPOManualMapper.query(moduleName, operate, level, operatorName, ip, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return sysLogPOList.stream().map(SysLogAdapter::sysLogPO2DTO).collect(Collectors.toList());
    }
}