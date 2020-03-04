/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffInfoDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffPointDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffUpdateDTO;
import com.jiazhe.youxiang.server.service.ChargeOffService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 核销管理Biz
 *
 * @author niexiao
 * @created 2020-03-04
 */
@Service
public class ChargeOffBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeOffBiz.class);

    @Autowired
    private ChargeOffService chargeOffService;

    @Transactional
    public void add(ChargeOffAddDTO dto) {
        LOGGER.info("Biz调用[add]方法,入参:{}", JacksonUtil.toJSon(dto));
        chargeOffService.add(dto);
        //TODO niexiao

    }

    @Transactional
    public void update(ChargeOffUpdateDTO dto) {
        LOGGER.info("Biz调用[update]方法,入参:{}", JacksonUtil.toJSon(dto));
        chargeOffService.update(dto);
        //TODO niexiao

    }

    public void delete(Integer chargeOffId) {
        LOGGER.info("Biz调用[delete]方法,chargeOffId:{}", chargeOffId);
        chargeOffService.delete(chargeOffId);
    }

    public ChargeOffInfoDTO queryById(Integer chargeOffId) {
        LOGGER.info("Biz调用[queryById]方法,chargeOffId:{}", chargeOffId);
        return chargeOffService.queryById(chargeOffId);
    }

    public List<ChargeOffInfoDTO> fuzzyQuery(ChargeOffFuzzyQueryDTO dto, Paging paging) {
        LOGGER.info("Biz调用[fuzzyQuery]方法,入参:{}", JacksonUtil.toJSon(dto));
        return chargeOffService.fuzzyQuery(dto, paging);
    }

    public List<ChargeOffInfoDTO> query(ChargeOffQueryDTO dto, Paging paging) {
        LOGGER.info("Biz调用[query]方法,入参:{}", JacksonUtil.toJSon(dto));
        return chargeOffService.query(dto, paging);
    }

    public ChargeOffPointDTO validateKeyt(String keyt) {
        LOGGER.info("Biz调用[validateKeyt]方法,keyt:{}", keyt);
        return chargeOffService.validateKeyt(keyt);
    }

    public void exportDetail(ChargeOffQueryDTO dto) {
        LOGGER.info("Biz调用[exportDetail]方法,入参:{}", JacksonUtil.toJSon(dto));
        //TODO niexiao
    }
}
