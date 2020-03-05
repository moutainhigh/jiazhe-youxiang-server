/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.ChargeOffPO;
import com.jiazhe.youxiang.server.domain.po.ChargeOffPointPO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffDTO;
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
        chargeOffInfoResp.setCityName(dto.getCityName());
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
        chargeOffQueryDTO.setSubmitterName(req.getSubmitterName());
        chargeOffQueryDTO.setTotalPoint(req.getTotalPoint());
        return chargeOffQueryDTO;
    }

    public static ChargeOffPO chargeOffDTO2PO(ChargeOffDTO dto) {
        if (dto == null) {
            return null;
        }
        ChargeOffPO chargeOffPO = new ChargeOffPO();
        chargeOffPO.setCityCode(dto.getCityCode());
        chargeOffPO.setCityName(dto.getCityName());
        chargeOffPO.setBankOutletsName(dto.getBankOutletsName());
        chargeOffPO.setChargeOffType(dto.getChargeOffType().byteValue());
        chargeOffPO.setTotalPoint(dto.getTotalPoint());
        chargeOffPO.setCustomerName(dto.getCustomerName());
        chargeOffPO.setCustomerMobile(dto.getCustomerMobile());
        chargeOffPO.setProductValue(dto.getProductValue());
        chargeOffPO.setStatus(dto.getStatus().byteValue());
        chargeOffPO.setRemark(dto.getRemark());
        chargeOffPO.setSubmitterId(dto.getSubmitterId());
        chargeOffPO.setSubmitterName(dto.getSubmitterName());
        chargeOffPO.setChargeOffPoint(String.join(",", dto.getKeytList()));
        return chargeOffPO;
    }

    public static ChargeOffPointPO chargeOffPointDTO2PO(ChargeOffPointDTO chargeOffPointDTO) {
        if (chargeOffPointDTO == null) {
            return null;
        }
        ChargeOffPointPO chargeOffPointPO = new ChargeOffPointPO();
        chargeOffPointPO.setPointExchangeCodeId(chargeOffPointDTO.getPointExchangeCodeId());
        chargeOffPointPO.setPointName(chargeOffPointDTO.getPointName());
        chargeOffPointPO.setPointValue(chargeOffPointDTO.getPointValue());
        chargeOffPointPO.setPointExchangeCodeCode(chargeOffPointDTO.getPointExchangeCodeCode());
        chargeOffPointPO.setPointExchangeCodeKeyt(chargeOffPointDTO.getPointExchangeCodeKeyt());
        return chargeOffPointPO;
    }

    public static ChargeOffPO chargeOffUpdateDTO2PO(ChargeOffUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        ChargeOffPO chargeOffPO = chargeOffDTO2PO(dto);
        chargeOffPO.setId(dto.getId());
        return chargeOffPO;
    }

    public static ChargeOffInfoDTO chargeOffPO2DTO(ChargeOffPO po) {
        if (po == null) {
            return null;
        }
        ChargeOffInfoDTO chargeOffInfoDTO = new ChargeOffInfoDTO();
        chargeOffInfoDTO.setId(po.getId());
        chargeOffInfoDTO.setCityCode(po.getCityCode());
        chargeOffInfoDTO.setCityName(po.getCityName());
        chargeOffInfoDTO.setBankOutletsName(po.getBankOutletsName());
        chargeOffInfoDTO.setChargeOffType(po.getChargeOffType().intValue());
        chargeOffInfoDTO.setTotalPoint(po.getTotalPoint());
        chargeOffInfoDTO.setCustomerName(po.getCustomerName());
        chargeOffInfoDTO.setCustomerMobile(po.getCustomerMobile());
        chargeOffInfoDTO.setProductValue(po.getProductValue());
        chargeOffInfoDTO.setStatus(po.getStatus().intValue());
        chargeOffInfoDTO.setSubmitterId(po.getSubmitterId());
        chargeOffInfoDTO.setSubmitterName(po.getSubmitterName());
        chargeOffInfoDTO.setSubmitterTime(po.getSubmitterTime().getTime());
        chargeOffInfoDTO.setRemark(po.getRemark());
        return chargeOffInfoDTO;
    }

    public static ChargeOffPointDTO chargeOffPointPO2DTO(ChargeOffPointPO chargeOffPointPO) {
        if (chargeOffPointPO == null) {
            return null;
        }
        ChargeOffPointDTO chargeOffPointDTO = new ChargeOffPointDTO();
        chargeOffPointDTO.setId(chargeOffPointPO.getId());
        chargeOffPointDTO.setChargeOffId(chargeOffPointPO.getChargeOffId());
        chargeOffPointDTO.setPointExchangeCodeId(chargeOffPointPO.getPointExchangeCodeId());
        chargeOffPointDTO.setPointName(chargeOffPointPO.getPointName());
        chargeOffPointDTO.setPointValue(chargeOffPointPO.getPointValue());
        chargeOffPointDTO.setPointExchangeCodeCode(chargeOffPointPO.getPointExchangeCodeCode());
        chargeOffPointDTO.setPointExchangeCodeKeyt(chargeOffPointPO.getPointExchangeCodeKeyt());
        return chargeOffPointDTO;
    }
}
