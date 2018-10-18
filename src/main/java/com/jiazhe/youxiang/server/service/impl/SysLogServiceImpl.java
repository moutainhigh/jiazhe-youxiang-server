/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysLogAdapter;
import com.jiazhe.youxiang.server.dao.mapper.SysLogPOMapper;
import com.jiazhe.youxiang.server.dto.syslog.SysLogDTO;
import com.jiazhe.youxiang.server.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public int insert(SysLogDTO sysLogDTO) {
        return sysLogPOMapper.insertSelective(SysLogAdapter.sysLogDTO2PO(sysLogDTO));
    }
}