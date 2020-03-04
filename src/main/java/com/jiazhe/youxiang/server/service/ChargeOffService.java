/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffInfoDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffPointDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffUpdateDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * 核销管理Service
 *
 * @author niexiao
 * @created 2020-03-04
 */
public interface ChargeOffService {

    void add(ChargeOffAddDTO dto);

    void update(ChargeOffUpdateDTO dto);

    void delete(Integer chargeOffId);

    ChargeOffInfoDTO queryById(Integer chargeOffId);

    List<ChargeOffInfoDTO> fuzzyQuery(ChargeOffFuzzyQueryDTO dto, Paging paging);

    List<ChargeOffInfoDTO> query(ChargeOffQueryDTO dto, Paging paging);

    ChargeOffPointDTO validateKeyt(String keyt);


}
