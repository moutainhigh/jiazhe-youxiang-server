/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.server.adapter.ChargeOffAdapter;
import com.jiazhe.youxiang.server.dao.mapper.ChargeOffPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.ChargeOffPointPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.ChargeOffPointPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPO;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPointPO;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPointPOExample;
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

import java.util.List;

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
    private ChargeOffPointPOMapper chargeOffPointPOMapper;

    @Autowired
    private ChargeOffPointPOManualMapper chargeOffPointPOManualMapper;


    @Override
    public void add(ChargeOffAddDTO dto) {
        LOGGER.info("Service调用[add]方法,入参:{}", JacksonUtil.toJSon(dto));
        ChargeOffPO chargeOffPO = ChargeOffAdapter.chargeOffAddDTO2PO(dto);
        chargeOffPOMapper.insertSelective(chargeOffPO);
    }

    @Override
    public void addDetail(List<ChargeOffPointDTO> chargeOffPointDTOList) {
        LOGGER.info("Service调用[addDetail]方法,入参:{}", JacksonUtil.toJSon(chargeOffPointDTOList));
        if (CollectionUtils.isEmpty(chargeOffPointDTOList)) {
            return;
        }
        List<ChargeOffPointPO> poList = chargeOffPointDTOList.stream().map(ChargeOffAdapter::chargeOffPointDTO2PO).collect(toList());
        chargeOffPointPOManualMapper.batchInsert(poList);
    }



    @Override
    public void update(ChargeOffUpdateDTO dto) {
        LOGGER.info("Service调用[update]方法,入参:{}", JacksonUtil.toJSon(dto));
        ChargeOffPO chargeOffPO = ChargeOffAdapter.chargeOffUpdateDTO2PO(dto);
        chargeOffPOMapper.updateByPrimaryKeySelective(chargeOffPO);
    }

    @Override
    public void updateDetail(List<ChargeOffPointDTO> chargeOffPointDTOList) {
        LOGGER.info("Service调用[updateDetail]方法,入参:{}", JacksonUtil.toJSon(chargeOffPointDTOList));
        //先硬删之前的详情记录
        List<String> keytList = chargeOffPointDTOList.stream().map(ChargeOffPointDTO::getPointExchangeCodeKeyt).collect(toList());
        ChargeOffPointPOExample example = new ChargeOffPointPOExample();
        example.createCriteria().andPointExchangeCodeKeytIn(keytList);
        chargeOffPointPOMapper.deleteByExample(example);
        //然后再重新插入进去
        addDetail(chargeOffPointDTOList);
    }

    @Override
    public void delete(Integer chargeOffId) {
        LOGGER.info("Service调用[delete]方法,chargeOffId:{}", chargeOffId);

    }

    @Override
    public ChargeOffInfoDTO queryById(Integer chargeOffId) {
        LOGGER.info("Service调用[queryById]方法,chargeOffId:{}", chargeOffId);
        return null;
    }

    @Override
    public List<ChargeOffInfoDTO> fuzzyQuery(ChargeOffFuzzyQueryDTO dto, Paging paging) {
        LOGGER.info("Service调用[fuzzyQuery]方法,入参:{}", JacksonUtil.toJSon(dto));
        return null;
    }

    @Override
    public List<ChargeOffInfoDTO> query(ChargeOffQueryDTO dto, Paging paging) {
        LOGGER.info("Service调用[query]方法,入参:{}", JacksonUtil.toJSon(dto));
        return null;
    }

    @Override
    public ChargeOffPointDTO validateKeyt(String keyt) {
        LOGGER.info("Service调用[validateKeyt]方法,keyt:{}", keyt);
        return null;
    }


}
