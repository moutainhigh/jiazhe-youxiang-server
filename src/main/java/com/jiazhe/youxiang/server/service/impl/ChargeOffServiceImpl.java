/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.server.adapter.ChargeOffAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.ChargeOffCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ChargeOffStatusEnum;
import com.jiazhe.youxiang.server.common.exceptions.ChargeOffException;
import com.jiazhe.youxiang.server.dao.mapper.ChargeOffPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.ChargeOffPointPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.CityExchangeRatioPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.ChargeOffPOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.ChargeOffPointPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPO;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPointPO;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPointPOExample;
import com.jiazhe.youxiang.server.domain.po.CityExchangeRatioPO;
import com.jiazhe.youxiang.server.domain.po.CityExchangeRatioPOExample;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffInfoDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffPointDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffUpdateDTO;
import com.jiazhe.youxiang.server.service.ChargeOffService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-03-04
 */
@Service
public class ChargeOffServiceImpl implements ChargeOffService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeOffServiceImpl.class);

    @Autowired
    private ChargeOffPOMapper chargeOffPOMapper;

    @Autowired
    private ChargeOffPOManualMapper chargeOffPOManualMapper;

    @Autowired
    private ChargeOffPointPOMapper chargeOffPointPOMapper;

    @Autowired
    private ChargeOffPointPOManualMapper chargeOffPointPOManualMapper;

    @Autowired
    private CityExchangeRatioPOMapper cityExchangeRatioPOMapper;


    @Override
    public Integer add(ChargeOffAddDTO dto) {
        LOGGER.info("Service调用[add]方法,入参:{}", JacksonUtil.toJSon(dto));
        ChargeOffPO chargeOffPO = ChargeOffAdapter.chargeOffDTO2PO(dto);
        //如果是提交 则需要更新提交时间
        if (ChargeOffStatusEnum.COMMITTED.equals(ChargeOffStatusEnum.getByCode(dto.getStatus()))) {
            chargeOffPO.setSubmitterTime(null);
        }
        chargeOffPOManualMapper.insertSelective(chargeOffPO);
        return chargeOffPO.getId();
    }

    @Override
    public void addDetail(Integer chargeOffId, List<ChargeOffPointDTO> chargeOffPointDTOList) {
        LOGGER.info("Service调用[addDetail]方法,入参:{}", JacksonUtil.toJSon(chargeOffPointDTOList));
        if (CollectionUtils.isEmpty(chargeOffPointDTOList)) {
            return;
        }
        List<ChargeOffPointPO> poList = chargeOffPointDTOList.stream().map(ChargeOffAdapter::chargeOffPointDTO2PO).collect(toList());
        poList.stream().forEach(item -> item.setChargeOffId(chargeOffId));
        chargeOffPointPOManualMapper.batchInsert(poList);
    }

    @Override
    public void update(ChargeOffUpdateDTO dto) {
        LOGGER.info("Service调用[update]方法,入参:{}", JacksonUtil.toJSon(dto));
        ChargeOffInfoDTO chargeOffInfoDTO = queryById(dto.getId(), false);
        if (chargeOffInfoDTO == null) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_NOT_EXIST);
        }
        //如果核销记录已提交 则无法修改
        if (ChargeOffStatusEnum.COMMITTED.equals(ChargeOffStatusEnum.getByCode(dto.getStatus()))) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_IS_COMMITTED);
        }
        ChargeOffPO chargeOffPO = ChargeOffAdapter.chargeOffUpdateDTO2PO(dto);
        chargeOffPOMapper.updateByPrimaryKeySelective(chargeOffPO);
    }

    @Override
    public void updateDetail(Integer chargeOffId, List<ChargeOffPointDTO> chargeOffPointDTOList) {
        LOGGER.info("Service调用[updateDetail]方法,入参:{}", JacksonUtil.toJSon(chargeOffPointDTOList));
        //先硬删之前的详情记录
        ChargeOffPointPOExample example = new ChargeOffPointPOExample();
        example.createCriteria().andChargeOffIdEqualTo(chargeOffId);
        chargeOffPointPOMapper.deleteByExample(example);
        //然后再重新插入进去
        addDetail(chargeOffId, chargeOffPointDTOList);
    }


    @Override
    public void delete(Integer chargeOffId) {
        LOGGER.info("Service调用[delete]方法,chargeOffId:{}", chargeOffId);
        ChargeOffInfoDTO chargeOffInfoDTO = queryById(chargeOffId, false);
        if (chargeOffInfoDTO == null) {
            throw new ChargeOffException(ChargeOffCodeEnum.CHARGE_OFF_NOT_EXIST);
        }
        //软删除核销记录
        ChargeOffPO po = new ChargeOffPO();
        po.setId(chargeOffId);
        po.setIsDeleted(CommonConstant.CODE_DELETED);
        po.setModTime(null);
        chargeOffPOMapper.updateByPrimaryKeySelective(po);

        //硬删除核销记录详情
        ChargeOffPointPOExample pointPOExample = new ChargeOffPointPOExample();
        pointPOExample.createCriteria().andChargeOffIdEqualTo(chargeOffId);
        chargeOffPointPOMapper.deleteByExample(pointPOExample);
    }


    @Override
    public ChargeOffInfoDTO queryById(Integer chargeOffId) {
        return queryById(chargeOffId, true);
    }

    @Override
    public ChargeOffInfoDTO queryById(Integer chargeOffId, boolean includeDetail) {
        LOGGER.info("Service调用[queryById]方法,chargeOffId:{}", chargeOffId);
        ChargeOffPO po = chargeOffPOMapper.selectByPrimaryKey(chargeOffId);
        if (po == null) {
            return null;
        }
        ChargeOffInfoDTO dto = ChargeOffAdapter.chargeOffPO2DTO(po);
        ChargeOffPointPOExample chargeOffPointPOExample = new ChargeOffPointPOExample();
        chargeOffPointPOExample.createCriteria()
                .andChargeOffIdEqualTo(chargeOffId)
                .andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        if (includeDetail) {
            //如果包含详情，则查询后放到dto里
            List<ChargeOffPointPO> chargeOffPointPOList = chargeOffPointPOMapper.selectByExample(chargeOffPointPOExample);
            dto.setPointList(chargeOffPointPOList.stream().map(ChargeOffAdapter::chargeOffPointPO2DTO).collect(toList()));
        }
        return dto;
    }

    @Override
    public List<ChargeOffInfoDTO> fuzzyQuery(ChargeOffFuzzyQueryDTO dto, Paging paging) {
        LOGGER.info("Service调用[fuzzyQuery]方法,入参:{}", JacksonUtil.toJSon(dto));
        List<ChargeOffPO> poList = chargeOffPOManualMapper.fuzzyQuery(dto, paging.getOffset(), paging.getLimit());
        int count = chargeOffPOManualMapper.fuzzyQueryCount(dto);
        paging.setTotal(count);
        return buildChargeOffInfoDTOList(poList);
    }

    @Override
    public List<ChargeOffInfoDTO> query(ChargeOffQueryDTO dto, Paging paging) {
        LOGGER.info("Service调用[query]方法,入参:{}", JacksonUtil.toJSon(dto));
        List<ChargeOffPO> poList = chargeOffPOManualMapper.query(dto, paging.getOffset(), paging.getLimit());
        int count = chargeOffPOManualMapper.queryCount(dto);
        paging.setTotal(count);
        return buildChargeOffInfoDTOList(poList);
    }

    @Override
    public BigDecimal querySummary(ChargeOffQueryDTO dto) {
        LOGGER.info("Service调用[querySummary]方法,入参:{}", JacksonUtil.toJSon(dto));
        return chargeOffPOManualMapper.querySummary(dto);
    }


    @Override
    public BigDecimal queryCityExchangeRatio(String cityCode) {
        LOGGER.info("Service调用[queryCityExchangeRatio]方法,cityCode:{}", cityCode);
        CityExchangeRatioPOExample example = new CityExchangeRatioPOExample();
        example.createCriteria()
                .andCityCodeEqualTo(cityCode)
                .andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<CityExchangeRatioPO> poList = cityExchangeRatioPOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(poList)) {
            return null;
        }
        if (poList.size() > 1) {
            throw new ChargeOffException(ChargeOffCodeEnum.CITY_EXCHANGE_RATIO_ERROR);
        }
        return poList.get(0).getExchangeRatio();
    }


    /**
     * 拼接完整的ChargeOffInfoDTO对象，包括核销详情
     *
     * @param poList
     * @return
     */
    private List<ChargeOffInfoDTO> buildChargeOffInfoDTOList(List<ChargeOffPO> poList) {
        List<ChargeOffInfoDTO> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(poList)) {
            List<Integer> chargeOffIdList = poList.stream().map(ChargeOffPO::getId).collect(toList());
            Map<Integer, List<ChargeOffPointPO>> chargeOffPointPOMap = batchQueryDetail(chargeOffIdList);
            result = poList.stream().map(ChargeOffAdapter::chargeOffPO2DTO).collect(Collectors.toList());
            result.forEach(item -> {
                List<ChargeOffPointPO> pointPOList = chargeOffPointPOMap.get(item.getId());
                if (CollectionUtils.isNotEmpty(pointPOList)) {
                    List<ChargeOffPointDTO> pointDTOList = pointPOList.stream().map(ChargeOffAdapter::chargeOffPointPO2DTO).collect(toList());
                    item.setPointList(pointDTOList);
                }
            });
        }
        return result;
    }


    /**
     * 批量查询核销详情
     *
     * @param chargeOffIdList
     * @return
     */
    private Map<Integer, List<ChargeOffPointPO>> batchQueryDetail(List<Integer> chargeOffIdList) {
        LOGGER.info("Service调用[batchQueryDetail]方法,入参:{}", JacksonUtil.toJSon(chargeOffIdList));
        ChargeOffPointPOExample chargeOffPointPOExample = new ChargeOffPointPOExample();
        chargeOffPointPOExample.createCriteria()
                .andChargeOffIdIn(chargeOffIdList)
                .andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<ChargeOffPointPO> pointPOList = chargeOffPointPOMapper.selectByExample(chargeOffPointPOExample);
        Map<Integer, List<ChargeOffPointPO>> result = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(pointPOList)) {
            pointPOList.forEach(item -> {
                result.merge(item.getChargeOffId(), Lists.newArrayList(item), (oldValue, newValue) -> {
                    if (oldValue == null) {
                        oldValue = Lists.newArrayList();
                    }
                    oldValue.addAll(newValue);
                    return oldValue;
                });
            });
        }
        return result;
    }

}
