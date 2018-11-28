/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.common.enums.CityStatusEnum;
import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;
import com.jiazhe.youxiang.server.service.impl.SysCityServiceImpl;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市管理Biz
 *
 * @author niexiao
 * @created 2018/10/16
 */
@Service("sysCityBiz")
public class SysCityBiz {

    /**
     * 一级城市级别
     */
    public static final Integer CITY_LEVEL_1 = 1;
    /**
     * 二级城市级别
     */
    public static final Integer CITY_LEVEL_2 = 2;
    /**
     * 三级城市级别
     */
    public static final Integer CITY_LEVEL_3 = 3;


    @Autowired
    private SysCityServiceImpl sysCityService;

    public List<SysCityDTO> getList(String parentCode, Paging paging) {
        //如果上级code为空则默认查询一级城市，否则查询二级城市
        if (Strings.isBlank(parentCode)) {
            return sysCityService.getList(parentCode, CITY_LEVEL_1, paging);
        } else {
            return sysCityService.getList(parentCode, CITY_LEVEL_2, paging);
        }
    }

    public void open(String cityCode) {
        sysCityService.updateStatusByCityCode(cityCode, CityStatusEnum.OPEN.getId().byteValue());
    }

    public void close(String cityCode) {
        sysCityService.updateStatusByCityCode(cityCode, CityStatusEnum.CLOSE.getId().byteValue());
    }

    public void batchOpen(List<String> cityCodes) {
        sysCityService.updateStatusByCityCodes(cityCodes, CityStatusEnum.OPEN.getId().byteValue());
    }

    public void batchClose(List<String> cityCodes) {
        sysCityService.updateStatusByCityCodes(cityCodes, CityStatusEnum.CLOSE.getId().byteValue());
    }

    public List<SysCityDTO> getOpenList() {
        return sysCityService.getOpenList();
    }

    public List<SysCityDTO> getProvinceList() {
        return sysCityService.getProvinceList();
    }

    public List<SysCityDTO> getCityList(String cityCode) {
        return sysCityService.getCityList(cityCode);
    }
}