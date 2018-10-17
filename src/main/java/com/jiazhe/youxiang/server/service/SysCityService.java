/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;

import java.util.List;

/**
 * 城市管理Service
 *
 * @author niexiao
 * @created 2018/10/16
 */
public interface SysCityService {
    List<SysCityDTO> getList(String parentCode);

    void updateStatusByCityCodes(List<String> cityCodes, Byte status);

    void updateStatusByParentCode(String parentCode, Byte status);
}
