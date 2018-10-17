/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.syslog.SysLogDTO;
import com.jiazhe.youxiang.server.vo.resp.syslog.SysLogResp;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/17
 */
public class SysLogAdapter {


    public static SysLogResp sysLogDTO2VO(SysLogDTO dto) {
        if (dto == null) {
            return null;
        }
        SysLogResp sysLogResp = new SysLogResp();
        sysLogResp.setId(dto.getId());
        sysLogResp.setAction(dto.getAction());
        sysLogResp.setType(dto.getType());
        sysLogResp.setOperatorId(dto.getOperatorId());
        sysLogResp.setOperatorName(dto.getOperatorName());
        sysLogResp.setIp(dto.getIp());
        sysLogResp.setDetail(dto.getDetail());
        sysLogResp.setAddTime(dto.getAddTime());
        return sysLogResp;
    }
}