/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffInfoDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffPointDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffUpdateDTO;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffAddReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffFuzzyQueryReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffQueryReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffUpdateReq;
import com.jiazhe.youxiang.server.vo.resp.chargeoff.ChargeOffInfoResp;
import com.jiazhe.youxiang.server.vo.resp.chargeoff.ChargeOffPointResp;
import org.apache.commons.collections.CollectionUtils;

import java.util.stream.Collectors;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffAdapter {
    public static ChargeOffAddDTO chargeOffAddReq2DTO(ChargeOffAddReq req) {
        if (req == null) {
            return null;
        }
        ChargeOffAddDTO chargeOffAddDTO = new ChargeOffAddDTO();
        chargeOffAddDTO.setCityCode(req.getCityCode());
        chargeOffAddDTO.setBankOutletsName(req.getBankOutletsName());
        chargeOffAddDTO.setChargeOffType(req.getChargeOffType());
        chargeOffAddDTO.setKeytList(req.getKeytList());
        chargeOffAddDTO.setTotalPoint(req.getTotalPoint());
        chargeOffAddDTO.setCustomerName(req.getCustomerName());
        chargeOffAddDTO.setCustomerMobile(req.getCustomerMobile());
        chargeOffAddDTO.setProductValue(req.getProductValue());
        chargeOffAddDTO.setStatus(req.getStatus());
        chargeOffAddDTO.setRemark(req.getRemark());
        return chargeOffAddDTO;
    }

    public static ChargeOffUpdateDTO chargeOffUpdateReq(ChargeOffUpdateReq req) {
        if (req == null) {
            return null;
        }
        ChargeOffUpdateDTO chargeOffUpdateDTO = new ChargeOffUpdateDTO();
        chargeOffUpdateDTO.setId(req.getId());
        chargeOffUpdateDTO.setCityCode(req.getCityCode());
        chargeOffUpdateDTO.setBankOutletsName(req.getBankOutletsName());
        chargeOffUpdateDTO.setChargeOffType(req.getChargeOffType());
        chargeOffUpdateDTO.setKeytList(req.getKeytList());
        chargeOffUpdateDTO.setTotalPoint(req.getTotalPoint());
        chargeOffUpdateDTO.setCustomerName(req.getCustomerName());
        chargeOffUpdateDTO.setCustomerMobile(req.getCustomerMobile());
        chargeOffUpdateDTO.setProductValue(req.getProductValue());
        chargeOffUpdateDTO.setStatus(req.getStatus());
        chargeOffUpdateDTO.setRemark(req.getRemark());
        return chargeOffUpdateDTO;
    }

    public static ChargeOffInfoResp chargeOffInfoDTO2Resp(ChargeOffInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        ChargeOffInfoResp chargeOffInfoResp = new ChargeOffInfoResp();
        chargeOffInfoResp.setId(dto.getId());
        chargeOffInfoResp.setCityCode(dto.getCityCode());
        chargeOffInfoResp.setBankOutletsName(dto.getBankOutletsName());
        chargeOffInfoResp.setChargeOffType(dto.getChargeOffType());
        if (CollectionUtils.isNotEmpty(dto.getPointList())) {
            chargeOffInfoResp.setPointList(dto.getPointList().stream().map(ChargeOffAdapter::chargeOffPointDTO2Resp).collect(Collectors.toList()));
        }
        chargeOffInfoResp.setTotalPoint(dto.getTotalPoint());
        chargeOffInfoResp.setCustomerName(dto.getCustomerName());
        chargeOffInfoResp.setCustomerMobile(dto.getCustomerMobile());
        chargeOffInfoResp.setProductValue(dto.getProductValue());
        chargeOffInfoResp.setStatus(dto.getStatus());
        chargeOffInfoResp.setSubmitterId(dto.getSubmitterId());
        chargeOffInfoResp.setSubmitterName(dto.getSubmitterName());
        chargeOffInfoResp.setRemark(dto.getRemark());
        chargeOffInfoResp.setSubmitterTime(dto.getSubmitterTime());
        return chargeOffInfoResp;
    }

    public static ChargeOffPointResp chargeOffPointDTO2Resp(ChargeOffPointDTO dto) {
        if (dto == null) {
            return null;
        }
        ChargeOffPointResp chargeOffPointResp = new ChargeOffPointResp();
        chargeOffPointResp.setPointExchangeCodeId(dto.getPointExchangeCodeId());
        chargeOffPointResp.setPointName(dto.getPointName());
        chargeOffPointResp.setPointValue(dto.getPointValue());
        chargeOffPointResp.setPointExchangeCodeCode(dto.getPointExchangeCodeCode());
        chargeOffPointResp.setPointExchangeCodeKeyt(dto.getPointExchangeCodeKeyt());
        return chargeOffPointResp;
    }

    public static ChargeOffFuzzyQueryDTO ChargeOffFuzzyQueryReq2DTO(ChargeOffFuzzyQueryReq req) {
        if (req == null) {
            return null;
        }
        ChargeOffFuzzyQueryDTO chargeOffFuzzyQueryDTO = new ChargeOffFuzzyQueryDTO();
        chargeOffFuzzyQueryDTO.setSubmitterTimeBegin(req.getSubmitterTimeBegin());
        chargeOffFuzzyQueryDTO.setSubmitterTimeEnd(req.getSubmitterTimeEnd());
        chargeOffFuzzyQueryDTO.setStauts(req.getStauts());
        chargeOffFuzzyQueryDTO.setSubmitterId(req.getSubmitterId());
        chargeOffFuzzyQueryDTO.setCondition(req.getCondition());
        return chargeOffFuzzyQueryDTO;
    }

    public static ChargeOffQueryDTO ChargeOffQueryReq2DTO(ChargeOffQueryReq req) {
        if (req == null) {
            return null;
        }
        ChargeOffQueryDTO chargeOffQueryDTO = new ChargeOffQueryDTO();
        chargeOffQueryDTO.setSubmitterTimeBegin(req.getSubmitterTimeBegin());
        chargeOffQueryDTO.setSubmitterTimeEnd(req.getSubmitterTimeEnd());
        chargeOffQueryDTO.setStauts(req.getStauts());
        chargeOffQueryDTO.setCityCode(req.getCityCode());
        chargeOffQueryDTO.setChargeOffType(req.getChargeOffType());
        chargeOffQueryDTO.setSubmitterId(req.getSubmitterId());
        return chargeOffQueryDTO;
    }
}
