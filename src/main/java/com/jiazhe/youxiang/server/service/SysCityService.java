/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * 城市管理Service
 *
 * @author niexiao
 * @created 2018/10/16
 */
public interface SysCityService {

    void updateStatusByCityCodes(List<String> cityCodes, Byte status);

    void updateStatusByCityCode(String parentCode, Byte status);

    List<SysCityDTO> getList(String parentCode, Integer level, Paging paging);

    List<SysCityDTO> getOpenList();

    Map<String, String> getCityMapByCodes(List<String> cityCodes);

    List<SysCityDTO> getProvinceList();

    List<SysCityDTO> getCityList(String cityCode);

    SysCityPO getCityByCityCode(String cityCode);
}
