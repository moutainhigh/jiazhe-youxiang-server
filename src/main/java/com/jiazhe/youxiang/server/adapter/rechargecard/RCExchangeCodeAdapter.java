package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode.RCExchangeCodeResp;

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
        rechargeCardExchangeCodePO.setRechargeCardExpiryTime(rcExchangeCodeSaveDTO.getRechargeCardExpiryTime());
        rechargeCardExchangeCodePO.setValidityPeriod(rcExchangeCodeSaveDTO.getValidityPeriod());
        rechargeCardExchangeCodePO.setExpiryType(rcExchangeCodeSaveDTO.getExpiryType());
        rechargeCardExchangeCodePO.setStatus(rcExchangeCodeSaveDTO.getStatus());
        rechargeCardExchangeCodePO.setUsed(rcExchangeCodeSaveDTO.getUsed());
        return rechargeCardExchangeCodePO;
    }

    public static RCExchangeCodeBatchEditDTO PO2DTOEdit(RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO) {
        if (rechargeCardExchangeCodeBatchPO == null) {
            return null;
        }
        RCExchangeCodeBatchEditDTO rCExchangeCodeBatchEditDTO = new RCExchangeCodeBatchEditDTO();
        rCExchangeCodeBatchEditDTO.setId(rechargeCardExchangeCodeBatchPO.getId());
        rCExchangeCodeBatchEditDTO.setName(rechargeCardExchangeCodeBatchPO.getName());
        rCExchangeCodeBatchEditDTO.setRechargeCardName(rechargeCardExchangeCodeBatchPO.getRechargeCardName());
        rCExchangeCodeBatchEditDTO.setIsVirtual(rechargeCardExchangeCodeBatchPO.getIsVirtual());
        rCExchangeCodeBatchEditDTO.setDescription(rechargeCardExchangeCodeBatchPO.getDescription());
        rCExchangeCodeBatchEditDTO.setAmount(rechargeCardExchangeCodeBatchPO.getAmount());
        rCExchangeCodeBatchEditDTO.setProjectId(rechargeCardExchangeCodeBatchPO.getProjectId());
        rCExchangeCodeBatchEditDTO.setCityCodes(rechargeCardExchangeCodeBatchPO.getCityCodes());
        rCExchangeCodeBatchEditDTO.setProductIds(rechargeCardExchangeCodeBatchPO.getProductIds());
        rCExchangeCodeBatchEditDTO.setFaceValue(rechargeCardExchangeCodeBatchPO.getFaceValue());
        rCExchangeCodeBatchEditDTO.setExpiryTime(rechargeCardExchangeCodeBatchPO.getExpiryTime());
        rCExchangeCodeBatchEditDTO.setRechargeCardExpiryTime(rechargeCardExchangeCodeBatchPO.getRechargeCardExpiryTime());
        rCExchangeCodeBatchEditDTO.setValidityPeriod(rechargeCardExchangeCodeBatchPO.getValidityPeriod());
        rCExchangeCodeBatchEditDTO.setExpiryType(rechargeCardExchangeCodeBatchPO.getExpiryType());
        return rCExchangeCodeBatchEditDTO;
    }

    public static RCExchangeCodeResp DTO2Resp(RCExchangeCodeDTO dto) {
        if (dto == null) {
            return null;
        }
        RCExchangeCodeResp rCExchangeCodeResp = new RCExchangeCodeResp();
        rCExchangeCodeResp.setId(dto.getId());
        rCExchangeCodeResp.setBatchName(dto.getBatchName());
        rCExchangeCodeResp.setCode(dto.getCode());
        rCExchangeCodeResp.setKeyt(dto.getKeyt());
        rCExchangeCodeResp.setFaceValue(dto.getFaceValue());
        rCExchangeCodeResp.setExpiryTime(dto.getExpiryTime());
        rCExchangeCodeResp.setStatus(dto.getStatus());
        rCExchangeCodeResp.setUsed(dto.getUsed());
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
        rCExchangeCodeDTO.setRechargeCardExpiryTime(rechargeCardExchangeCodePO.getRechargeCardExpiryTime());
        rCExchangeCodeDTO.setValidityPeriod(rechargeCardExchangeCodePO.getValidityPeriod());
        rCExchangeCodeDTO.setExpiryType(rechargeCardExchangeCodePO.getExpiryType());
        rCExchangeCodeDTO.setStatus(rechargeCardExchangeCodePO.getStatus());
        rCExchangeCodeDTO.setUsed(rechargeCardExchangeCodePO.getUsed());
        return rCExchangeCodeDTO;
    }
}
