/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;
import com.jiazhe.youxiang.server.vo.resp.syscity.SysCityResp;

/**
 * 城市管理Adapter
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class SysCityAdapter {
    public static SysCityResp sysCityDTO2VO(SysCityDTO dto) {
        if (dto == null) {
            return null;
        }
        SysCityResp sysCityResp = new SysCityResp();
        sysCityResp.setId(dto.getId());
        sysCityResp.setCityCode(dto.getCityCode());
        sysCityResp.setCityName(dto.getCityName());
        sysCityResp.setCityPinyin(dto.getCityPinyin());
        sysCityResp.setCityLevel(dto.getCityLevel());
        sysCityResp.setPriority(dto.getPriority());
        sysCityResp.setParentCode(dto.getParentCode());
        sysCityResp.setStatus(dto.getStatus());
        return sysCityResp;
    }

    public static SysCityDTO sysCityPO2DTO(SysCityPO po) {
        if (po == null) {
            return null;
        }
        SysCityDTO sysCityDTO = new SysCityDTO();
        sysCityDTO.setId(po.getId());
        sysCityDTO.setCityCode(po.getCityCode());
        sysCityDTO.setCityName(po.getCityName());
        sysCityDTO.setCityPinyin(po.getCityPinyin());
        sysCityDTO.setCityLevel(po.getCityLevel());
        sysCityDTO.setPriority(po.getPriority());
        sysCityDTO.setParentCode(po.getParentCode());
        sysCityDTO.setStatus(po.getStatus());
        return sysCityDTO;
    }
}