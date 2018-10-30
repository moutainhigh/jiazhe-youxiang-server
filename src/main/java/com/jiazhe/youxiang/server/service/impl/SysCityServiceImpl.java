/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysCityAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.SysCityPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysCityPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import com.jiazhe.youxiang.server.domain.po.SysCityPOExample;
import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;
import com.jiazhe.youxiang.server.service.SysCityService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 城市管理ServiceImpl
 *
 * @author niexiao
 * @created 2018/10/16
 */
@Service("sysCityService")
public class SysCityServiceImpl implements SysCityService {

    @Autowired
    private SysCityPOMapper sysCityPOMapper;

    @Autowired
    private SysCityPOManualMapper sysCityPOManualMapper;

    @Override
    public List<SysCityDTO> getList(String parentCode) {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        if (Strings.isBlank(parentCode)) {
            criteria.andCityLevelEqualTo(CommonConstant.CITY_LEVEL_1);
        } else {
            criteria.andParentCodeEqualTo(parentCode);
        }
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysCityPO> sysCityPOList = sysCityPOMapper.selectByExample(sysCityPOExample);

        return sysCityPOList.stream().map(SysCityAdapter::sysCityPO2DTO).collect(Collectors.toList());
    }

    @Override
    public List<SysCityDTO> getOpenList() {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andStatusEqualTo(CommonConstant.CODE_CITY_OPEN);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysCityPO> sysCityPOList = sysCityPOMapper.selectByExample(sysCityPOExample);
        return sysCityPOList.stream().map(SysCityAdapter::sysCityPO2DTO).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getCityMapByCodes(List<String> cityCodes) {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andCityCodeIn(cityCodes);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysCityPO> sysCityPOList = sysCityPOMapper.selectByExample(sysCityPOExample);

        return sysCityPOList.stream().collect(Collectors.toMap(SysCityPO::getCityCode, SysCityPO::getCityName));
    }

    @Override
    public void updateStatusByParentCode(String parentCode, Byte status) {
        sysCityPOManualMapper.updateStatusByParentCode(parentCode, status);
    }


    @Override
    public void updateStatusByCityCodes(List<String> cityCodes, Byte status) {
        sysCityPOManualMapper.updateStatusByCityCodes(cityCodes, status);
    }

}