package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.RCExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode.RCExchangeCodeResp;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public class RCExchangeCodeAdapter {
    public static RechargeCardExchangeCodePO DTOSave2PO(RCExchangeCodeSaveDTO rcExchangeCodeSaveDTO) {
        if (rcExchangeCodeSaveDTO == null) {
            return null;
        }
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = new RechargeCardExchangeCodePO();
        rechargeCardExchangeCodePO.setBatchId(rcExchangeCodeSaveDTO.getBatchId());
        rechargeCardExchangeCodePO.setBatchName(rcExchangeCodeSaveDTO.getBatchName());
        rechargeCardExchangeCodePO.setRechargeCardName(rcExchangeCodeSaveDTO.getRechargeCardName());
        rechargeCardExchangeCodePO.setBatchDescription(rcExchangeCodeSaveDTO.getBatchDescription());
        rechargeCardExchangeCodePO.setProjectId(rcExchangeCodeSaveDTO.getProjectId());
        rechargeCardExchangeCodePO.setCityCodes(rcExchangeCodeSaveDTO.getCityCodes());
        rechargeCardExchangeCodePO.setProductIds(rcExchangeCodeSaveDTO.getProductIds());
        rechargeCardExchangeCodePO.setCode(rcExchangeCodeSaveDTO.getCode());
        rechargeCardExchangeCodePO.setKeyt(rcExchangeCodeSaveDTO.getKeyt());
        rechargeCardExchangeCodePO.setFaceValue(rcExchangeCodeSaveDTO.getFaceValue());
        rechargeCardExchangeCodePO.setExpiryTime(rcExchangeCodeSaveDTO.getExpiryTime());
        rechargeCardExchangeCodePO.setRechargeCardEffectiveTime(rcExchangeCodeSaveDTO.getRechargeCardEffectiveTime());
        rechargeCardExchangeCodePO.setRechargeCardExpiryTime(rcExchangeCodeSaveDTO.getRechargeCardExpiryTime());
        rechargeCardExchangeCodePO.setValidityPeriod(rcExchangeCodeSaveDTO.getValidityPeriod());
        rechargeCardExchangeCodePO.setExpiryType(rcExchangeCodeSaveDTO.getExpiryType());
        rechargeCardExchangeCodePO.setStatus(rcExchangeCodeSaveDTO.getStatus());
        rechargeCardExchangeCodePO.setUsed(rcExchangeCodeSaveDTO.getUsed());
        return rechargeCardExchangeCodePO;
    }

    public static RCExchangeCodeResp DTO2Resp(RCExchangeCodeDTO dto) {
        if (dto == null) {
            return null;
        }
        RCExchangeCodeResp rCExchangeCodeResp = new RCExchangeCodeResp();
        rCExchangeCodeResp.setId(dto.getId());
        rCExchangeCodeResp.setBatchName(dto.getBatchName());
        rCExchangeCodeResp.setRechargeCardName(dto.getRechargeCardName());
        rCExchangeCodeResp.setBatchDescription(dto.getBatchDescription());
        rCExchangeCodeResp.setCityCodes(dto.getCityCodes());
        rCExchangeCodeResp.setProductIds(dto.getProductIds());
        rCExchangeCodeResp.setCode(dto.getCode());
        rCExchangeCodeResp.setKeyt(dto.getKeyt());
        rCExchangeCodeResp.setRechargeCardEffectiveTime(dto.getRechargeCardEffectiveTime().getTime());
        rCExchangeCodeResp.setRechargeCardExpiryTime(dto.getRechargeCardExpiryTime().getTime());
        rCExchangeCodeResp.setValidityPeriod(dto.getValidityPeriod());
        rCExchangeCodeResp.setExpiryType(dto.getExpiryType());
        rCExchangeCodeResp.setFaceValue(dto.getFaceValue());
        rCExchangeCodeResp.setExpiryTime(dto.getExpiryTime().getTime());
        rCExchangeCodeResp.setStatus(dto.getStatus());
        rCExchangeCodeResp.setUsed(dto.getUsed());
        rCExchangeCodeResp.setCustomerResp(CustomerAdapter.customerDTO2VO(dto.getCustomerDTO()));
        return rCExchangeCodeResp;
    }

    public static RCExchangeCodeDTO PO2DTO(RechargeCardExchangeCodePO rechargeCardExchangeCodePO) {
        if (rechargeCardExchangeCodePO == null) {
            return null;
        }
        RCExchangeCodeDTO rCExchangeCodeDTO = new RCExchangeCodeDTO();
        rCExchangeCodeDTO.setId(rechargeCardExchangeCodePO.getId());
        rCExchangeCodeDTO.setBatchId(rechargeCardExchangeCodePO.getBatchId());
        rCExchangeCodeDTO.setBatchName(rechargeCardExchangeCodePO.getBatchName());
        rCExchangeCodeDTO.setRechargeCardName(rechargeCardExchangeCodePO.getRechargeCardName());
        rCExchangeCodeDTO.setBatchDescription(rechargeCardExchangeCodePO.getBatchDescription());
        rCExchangeCodeDTO.setProjectId(rechargeCardExchangeCodePO.getProjectId());
        rCExchangeCodeDTO.setCityCodes(rechargeCardExchangeCodePO.getCityCodes());
        rCExchangeCodeDTO.setProductIds(rechargeCardExchangeCodePO.getProductIds());
        rCExchangeCodeDTO.setCode(rechargeCardExchangeCodePO.getCode());
        rCExchangeCodeDTO.setKeyt(rechargeCardExchangeCodePO.getKeyt());
        rCExchangeCodeDTO.setFaceValue(rechargeCardExchangeCodePO.getFaceValue());
        rCExchangeCodeDTO.setExpiryTime(rechargeCardExchangeCodePO.getExpiryTime());
        rCExchangeCodeDTO.setRechargeCardEffectiveTime(rechargeCardExchangeCodePO.getRechargeCardEffectiveTime());
        rCExchangeCodeDTO.setRechargeCardExpiryTime(rechargeCardExchangeCodePO.getRechargeCardExpiryTime());
        rCExchangeCodeDTO.setValidityPeriod(rechargeCardExchangeCodePO.getValidityPeriod());
        rCExchangeCodeDTO.setExpiryType(rechargeCardExchangeCodePO.getExpiryType());
        rCExchangeCodeDTO.setStatus(rechargeCardExchangeCodePO.getStatus());
        rCExchangeCodeDTO.setUsed(rechargeCardExchangeCodePO.getUsed());
        rCExchangeCodeDTO.setCustomerId(rechargeCardExchangeCodePO.getCustomerId());
        return rCExchangeCodeDTO;
    }

    public static RCExchangeCodeEditDTO EditReq2EditDTO(RCExchangeCodeEditReq req) {
        if (req == null) {
            return null;
        }
        RCExchangeCodeEditDTO rCExchangeCodeEditDTO = new RCExchangeCodeEditDTO();
        rCExchangeCodeEditDTO.setId(req.getId());
        rCExchangeCodeEditDTO.setRechargeCardName(req.getRechargeCardName());
        rCExchangeCodeEditDTO.setBatchDescription(req.getBatchDescription());
        rCExchangeCodeEditDTO.setCityCodes(req.getCityCodes());
        rCExchangeCodeEditDTO.setProductIds(req.getProductIds());
        rCExchangeCodeEditDTO.setExpiryTime(new Date(req.getExpiryTime()));
        rCExchangeCodeEditDTO.setRechargeCardEffectiveTime(new Date(req.getRechargeCardEffectiveTime()));
        rCExchangeCodeEditDTO.setRechargeCardExpiryTime(new Date(req.getRechargeCardExpiryTime()));
        rCExchangeCodeEditDTO.setValidityPeriod(req.getValidityPeriod());
        rCExchangeCodeEditDTO.setExpiryType(req.getExpiryType());
        return rCExchangeCodeEditDTO;
    }


    public static RechargeCardExchangeCodePO dto2Po(RCExchangeCodeDTO rcExchangeCodeDTO) {
        if (rcExchangeCodeDTO == null) {
            return null;
        }
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = new RechargeCardExchangeCodePO();
        rechargeCardExchangeCodePO.setId(rcExchangeCodeDTO.getId());
        rechargeCardExchangeCodePO.setBatchId(rcExchangeCodeDTO.getBatchId());
        rechargeCardExchangeCodePO.setBatchName(rcExchangeCodeDTO.getBatchName());
        rechargeCardExchangeCodePO.setRechargeCardName(rcExchangeCodeDTO.getRechargeCardName());
        rechargeCardExchangeCodePO.setBatchDescription(rcExchangeCodeDTO.getBatchDescription());
        rechargeCardExchangeCodePO.setProjectId(rcExchangeCodeDTO.getProjectId());
        rechargeCardExchangeCodePO.setCityCodes(rcExchangeCodeDTO.getCityCodes());
        rechargeCardExchangeCodePO.setProductIds(rcExchangeCodeDTO.getProductIds());
        rechargeCardExchangeCodePO.setCode(rcExchangeCodeDTO.getCode());
        rechargeCardExchangeCodePO.setKeyt(rcExchangeCodeDTO.getKeyt());
        rechargeCardExchangeCodePO.setRechargeCardEffectiveTime(rcExchangeCodeDTO.getRechargeCardEffectiveTime());
        rechargeCardExchangeCodePO.setRechargeCardExpiryTime(rcExchangeCodeDTO.getRechargeCardExpiryTime());
        rechargeCardExchangeCodePO.setValidityPeriod(rcExchangeCodeDTO.getValidityPeriod());
        rechargeCardExchangeCodePO.setExpiryType(rcExchangeCodeDTO.getExpiryType());
        rechargeCardExchangeCodePO.setFaceValue(rcExchangeCodeDTO.getFaceValue());
        rechargeCardExchangeCodePO.setExpiryTime(rcExchangeCodeDTO.getExpiryTime());
        rechargeCardExchangeCodePO.setStatus(rcExchangeCodeDTO.getStatus());
        rechargeCardExchangeCodePO.setUsed(rcExchangeCodeDTO.getUsed());
        rechargeCardExchangeCodePO.setCustomerId(rcExchangeCodeDTO.getCustomerId());
        return rechargeCardExchangeCodePO;
    }
}
