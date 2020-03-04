/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffInfoDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 核销管理Biz
 *
 * @author niexiao
 * @created 2020-03-04
 */
@Service
public class ChargeOffBiz {


    public void add(ChargeOffAddDTO dto) {
    }

    public void update(ChargeOffUpdateDTO dto) {
    }

    public void delete(Integer chargeOffId) {
    }

    public ChargeOffInfoDTO queryById(Integer id) {
        return null;
    }

    public List<ChargeOffInfoDTO> fuzzyQuery(ChargeOffFuzzyQueryDTO dto) {
        return null;
    }

    public List<ChargeOffInfoDTO> query(ChargeOffQueryDTO dto) {
        return null;
    }

    public void validateKeyt(String keyt) {
    }

    public void exportDetail(ChargeOffQueryDTO dto) {
    }
}
