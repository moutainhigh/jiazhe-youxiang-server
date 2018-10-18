/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.SysLogPO;
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
        sysLogResp.setModuleName(dto.getModuleName());
        sysLogResp.setOperate(dto.getOperate());
        sysLogResp.setLevel(dto.getLevel());
        sysLogResp.setOperatorId(dto.getOperatorId());
        sysLogResp.setOperatorName(dto.getOperatorName());
        sysLogResp.setIp(dto.getIp());
        sysLogResp.setDetail(dto.getDetail());
        sysLogResp.setAddTime(dto.getAddTime());
        return sysLogResp;
    }

    public static SysLogPO sysLogDTO2PO(SysLogDTO dto) {
        if (dto == null) {
            return null;
        }
        SysLogPO sysLogPO = new SysLogPO();
        sysLogPO.setId(dto.getId());
        sysLogPO.setModuleName(dto.getModuleName());
        sysLogPO.setOperate(dto.getOperate());
        sysLogPO.setLevel(dto.getLevel());
        sysLogPO.setOperatorId(dto.getOperatorId());
        sysLogPO.setOperatorName(dto.getOperatorName());
        sysLogPO.setIp(dto.getIp());
        sysLogPO.setDetail(dto.getDetail());
        sysLogPO.setAddTime(dto.getAddTime());
        return sysLogPO;
    }
}