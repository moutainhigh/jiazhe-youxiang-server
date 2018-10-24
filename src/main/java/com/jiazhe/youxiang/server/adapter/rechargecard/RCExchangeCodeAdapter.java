package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeListDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode.RCExchangeCodeListResp;

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
        rechargeCardExchangeCodePO.setBatchDescription(rcExchangeCodeSaveDTO.getBatchDescription());
        rechargeCardExchangeCodePO.setProjectId(rcExchangeCodeSaveDTO.getProjectId());
        rechargeCardExchangeCodePO.setCityIds(rcExchangeCodeSaveDTO.getCityIds());
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
        rCExchangeCodeBatchEditDTO.setDescription(rechargeCardExchangeCodeBatchPO.getDescription());
        rCExchangeCodeBatchEditDTO.setProjectId(rechargeCardExchangeCodeBatchPO.getProjectId());
        rCExchangeCodeBatchEditDTO.setCityIds(rechargeCardExchangeCodeBatchPO.getCityIds());
        rCExchangeCodeBatchEditDTO.setProductIds(rechargeCardExchangeCodeBatchPO.getProductIds());
        rCExchangeCodeBatchEditDTO.setExpiryTime(rechargeCardExchangeCodeBatchPO.getExpiryTime());
        rCExchangeCodeBatchEditDTO.setRechargeCardExpiryTime(rechargeCardExchangeCodeBatchPO.getRechargeCardExpiryTime());
        rCExchangeCodeBatchEditDTO.setValidityPeriod(rechargeCardExchangeCodeBatchPO.getValidityPeriod());
        rCExchangeCodeBatchEditDTO.setExpiryType(rechargeCardExchangeCodeBatchPO.getExpiryType());
        return rCExchangeCodeBatchEditDTO;
    }

    public static RCExchangeCodeListResp DTOList2RespList(RCExchangeCodeListDTO rcExchangeCodeListDTO) {
        if (rcExchangeCodeListDTO == null) {
            return null;
        }
        RCExchangeCodeListResp rCExchangeCodeListResp = new RCExchangeCodeListResp();
        return rCExchangeCodeListResp;
    }
}
