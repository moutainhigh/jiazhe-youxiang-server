/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.base.util.CacheKeyGenerator;
import com.jiazhe.youxiang.server.adapter.SysCityAdapter;
import com.jiazhe.youxiang.server.biz.SysCityBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CityStatusEnum;
import com.jiazhe.youxiang.server.common.enums.SysCityCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.SysCityException;
import com.jiazhe.youxiang.server.dao.mapper.SysCityPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysCityPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import com.jiazhe.youxiang.server.domain.po.SysCityPOExample;
import com.jiazhe.youxiang.server.dto.syscity.SysCityDTO;
import com.jiazhe.youxiang.server.service.SysCityService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 城市管理ServiceImpl
 *
 * @author niexiao
 * @created 2018/10/16
 */
@CacheConfig(cacheNames = "sys_city_cache")
@Service("sysCityService")
public class SysCityServiceImpl implements SysCityService {

    @Autowired
    private SysCityPOMapper sysCityPOMapper;

    @Autowired
    private SysCityPOManualMapper sysCityPOManualMapper;
    @Autowired
    CacheKeyGenerator cacheKeyGenerator;

    @Override
    public List<SysCityDTO> getList(String parentCode, Integer level, Paging paging) {
        Integer count = sysCityPOManualMapper.count(parentCode, level);
        List<SysCityPO> sysCityPOList = sysCityPOManualMapper.query(parentCode, level, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return sysCityPOList.stream().map(SysCityAdapter::sysCityPO2DTO).collect(Collectors.toList());

    }

    @Cacheable(keyGenerator = "cacheKeyGenerator")
    @Override
    public List<SysCityDTO> getOpenList() {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andStatusEqualTo(CommonConstant.CODE_CITY_OPEN);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysCityPO> sysCityPOList = sysCityPOMapper.selectByExample(sysCityPOExample);
        return sysCityPOList.stream().map(SysCityAdapter::sysCityPO2DTO).collect(Collectors.toList());
    }

    @Cacheable(keyGenerator = "cacheKeyGenerator")
    @Override
    public Map<String, String> getCityMapByCodes(List<String> cityCodes) {
        List<SysCityPO> sysCityPOList = getPOListByCityCodes(cityCodes);
        return sysCityPOList.stream().collect(Collectors.toMap(SysCityPO::getCityCode, SysCityPO::getCityName));
    }

    @Cacheable(keyGenerator = "cacheKeyGenerator")
    @Override
    public List<SysCityDTO> getProvinceList() {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andCityLevelEqualTo(SysCityBiz.CITY_LEVEL_1);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysCityPO> sysCityPOList = sysCityPOMapper.selectByExample(sysCityPOExample);
        return sysCityPOList.stream().map(SysCityAdapter::sysCityPO2DTO).collect(Collectors.toList());
    }

    @Cacheable(keyGenerator = "cacheKeyGenerator")
    @Override
    public List<SysCityDTO> getCityList(String cityCode) {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andCityLevelEqualTo(SysCityBiz.CITY_LEVEL_2);
        criteria.andParentCodeEqualTo(cityCode);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysCityPO> sysCityPOList = sysCityPOMapper.selectByExample(sysCityPOExample);
        return sysCityPOList.stream().map(SysCityAdapter::sysCityPO2DTO).collect(Collectors.toList());
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional
    @Override
    public void updateStatusByCityCode(String cityCode, Byte status) {
        sysCityPOManualMapper.updateStatusByCityCode(cityCode, status, true);
        //判断其同级城市状态，继而修改上级城市状态
        SysCityPO cityPO = getPOListByCityCode(cityCode);
        if (cityPO == null) {
            throw new SysCityException(SysCityCodeEnum.CITY_ERROR);
        }
        SysCityPO parentCityPO = getPOListByCityCode(cityPO.getParentCode());
        if (parentCityPO == null) {
            //说明是一级城市，无上级，直接返回
            return;
        }
        if (CityStatusEnum.OPEN.getId().byteValue() == status && CityStatusEnum.CLOSE.getId().byteValue() == parentCityPO.getStatus()) {
            //如果是二级城市开通操作，那么其上级城市就是一定开通的
            sysCityPOManualMapper.updateStatusByCityCode(parentCityPO.getCityCode(), CityStatusEnum.OPEN.getId().byteValue(), false);
        } else {
            List<SysCityPO> brothers = getPOListByParentCode(cityPO.getParentCode());
            if (CollectionUtils.isNotEmpty(brothers)) {
                //由于此时数据库操作未提交，所以要排除掉被操作的城市
                boolean hasOpen = brothers.stream().anyMatch(item -> CityStatusEnum.OPEN.getId().byteValue() == item.getStatus() && !item.getCityCode().equals(cityCode));
                if (!hasOpen && CityStatusEnum.OPEN.getId().byteValue() == parentCityPO.getStatus()) {
                    //如果所有兄弟都是关闭的，那么上级城市就关闭
                    sysCityPOManualMapper.updateStatusByCityCode(parentCityPO.getCityCode(), CityStatusEnum.CLOSE.getId().byteValue(), false);
                }
            }
        }
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void updateStatusByCityCodes(List<String> cityCodes, Byte status) {
        sysCityPOManualMapper.updateStatusByCityCodes(cityCodes, status);
    }


    /**
     * 根据城市编码获得城市信息
     *
     * @param cityCode
     * @return
     */
    private SysCityPO getPOListByCityCode(String cityCode) {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andCityCodeEqualTo(cityCode);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysCityPO> sysCityPOList = sysCityPOMapper.selectByExample(sysCityPOExample);
        if (CollectionUtils.isNotEmpty(sysCityPOList)) {
            return sysCityPOMapper.selectByExample(sysCityPOExample).stream().findFirst().get();
        }
        return null;
    }

    /**
     * 根据城市编码集合获得城市信息集合
     *
     * @param cityCodes
     * @return
     */
    private List<SysCityPO> getPOListByCityCodes(List<String> cityCodes) {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andCityCodeIn(cityCodes);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        return sysCityPOMapper.selectByExample(sysCityPOExample);
    }

    /**
     * 根据上级城市编码获得城市信息集合
     *
     * @param parentCode
     * @return
     */
    private List<SysCityPO> getPOListByParentCode(String parentCode) {
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andParentCodeEqualTo(parentCode);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        return sysCityPOMapper.selectByExample(sysCityPOExample);
    }
}