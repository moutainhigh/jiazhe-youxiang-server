/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffInfoDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffPointDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffUpdateDTO;
import com.jiazhe.youxiang.server.service.ChargeOffService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-03-04
 */
@Service
public class ChargeOffServiceImpl implements ChargeOffService {
    @Override
    public void add(ChargeOffAddDTO dto) {
        
    }

    @Override
    public void update(ChargeOffUpdateDTO dto) {

    }

    @Override
    public void delete(Integer chargeOffId) {

    }

    @Override
    public ChargeOffInfoDTO queryById(Integer chargeOffId) {
        return null;
    }

    @Override
    public List<ChargeOffInfoDTO> fuzzyQuery(ChargeOffFuzzyQueryDTO dto, Paging paging) {
        return null;
    }

    @Override
    public List<ChargeOffInfoDTO> query(ChargeOffQueryDTO dto, Paging paging) {
        return null;
    }

    @Override
    public ChargeOffPointDTO validateKeyt(String keyt) {
        return null;
    }
}
