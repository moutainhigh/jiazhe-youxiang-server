package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchResp;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public class RCExchangeCodeBatchAdapter {

    public static RCExchangeCodeBatchResp DTO2Resp(RCExchangeCodeBatchDTO dto) {
        if (dto == null) {
            return null;
        }
        RCExchangeCodeBatchResp rCExchangeCodeBatchResp = new RCExchangeCodeBatchResp();
        rCExchangeCodeBatchResp.setId(dto.getId());
        rCExchangeCodeBatchResp.setName(dto.getName());
        rCExchangeCodeBatchResp.setRechargeCardName(dto.getRechargeCardName());
        rCExchangeCodeBatchResp.setAmount(dto.getAmount());
        rCExchangeCodeBatchResp.setFaceValue(dto.getFaceValue());
        rCExchangeCodeBatchResp.setExpiryTime(dto.getExpiryTime().getTime());
        rCExchangeCodeBatchResp.setExpiryType(dto.getExpiryType());
        rCExchangeCodeBatchResp.setRechargeCardEffectiveTime(dto.getRechargeCardEffectiveTime().getTime());
        rCExchangeCodeBatchResp.setRechargeCardExpiryTime(dto.getRechargeCardExpiryTime().getTime());
        rCExchangeCodeBatchResp.setValidityPeriod(dto.getValidityPeriod());
        rCExchangeCodeBatchResp.setStatus(dto.getStatus());
        rCExchangeCodeBatchResp.setIsVirtual(dto.getIsVirtual());
        rCExchangeCodeBatchResp.setIsMade(dto.getIsMade());
        return rCExchangeCodeBatchResp;
    }

    public static RCExchangeCodeBatchDTO PO2DTO(RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO) {
        if (rechargeCardExchangeCodeBatchPO == null) {
            return null;
        }
        RCExchangeCodeBatchDTO rCExchangeCodeBatchDTO = new RCExchangeCodeBatchDTO();
        rCExchangeCodeBatchDTO.setId(rechargeCardExchangeCodeBatchPO.getId());
        rCExchangeCodeBatchDTO.setName(rechargeCardExchangeCodeBatchPO.getName());
        rCExchangeCodeBatchDTO.setRechargeCardName(rechargeCardExchangeCodeBatchPO.getRechargeCardName());
        rCExchangeCodeBatchDTO.setIsVirtual(rechargeCardExchangeCodeBatchPO.getIsVirtual());
        rCExchangeCodeBatchDTO.setIsMade(rechargeCardExchangeCodeBatchPO.getIsMade());
        rCExchangeCodeBatchDTO.setAmount(rechargeCardExchangeCodeBatchPO.getAmount());
        rCExchangeCodeBatchDTO.setFaceValue(rechargeCardExchangeCodeBatchPO.getFaceValue());
        rCExchangeCodeBatchDTO.setExpiryTime(rechargeCardExchangeCodeBatchPO.getExpiryTime());
        rCExchangeCodeBatchDTO.setExpiryType(rechargeCardExchangeCodeBatchPO.getExpiryType());
        rCExchangeCodeBatchDTO.setRechargeCardEffectiveTime(rechargeCardExchangeCodeBatchPO.getRechargeCardEffectiveTime());
        rCExchangeCodeBatchDTO.setRechargeCardExpiryTime(rechargeCardExchangeCodeBatchPO.getRechargeCardExpiryTime());
        rCExchangeCodeBatchDTO.setValidityPeriod(rechargeCardExchangeCodeBatchPO.getValidityPeriod());
        rCExchangeCodeBatchDTO.setStatus(rechargeCardExchangeCodeBatchPO.getStatus());
        return rCExchangeCodeBatchDTO;
    }

    public static RCExchangeCodeBatchSaveDTO ReqSave2DTOSave(RCExchangeCodeBatchSaveReq req) {
        if (req == null) {
            return null;
        }
        RCExchangeCodeBatchSaveDTO rCExchangeCodeBatchSaveDTO = new RCExchangeCodeBatchSaveDTO();
        rCExchangeCodeBatchSaveDTO.setId(req.getId());
        rCExchangeCodeBatchSaveDTO.setName(req.getName());
        rCExchangeCodeBatchSaveDTO.setRechargeCardName(req.getRechargeCardName());
        rCExchangeCodeBatchSaveDTO.setIsVirtual(req.getIsVirtual());
        rCExchangeCodeBatchSaveDTO.setAmount(req.getAmount());
        rCExchangeCodeBatchSaveDTO.setFaceValue(req.getFaceValue());
        rCExchangeCodeBatchSaveDTO.setProjectId(req.getProjectId());
        rCExchangeCodeBatchSaveDTO.setCityCodes(req.getCityCodes());
        rCExchangeCodeBatchSaveDTO.setProductIds(req.getProductIds());
        rCExchangeCodeBatchSaveDTO.setExpiryTime(new Date(req.getExpiryTime()));
        rCExchangeCodeBatchSaveDTO.setRechargeCardEffectiveTime(new Date(req.getRechargeCardEffectiveTime()));
        rCExchangeCodeBatchSaveDTO.setRechargeCardExpiryTime(new Date(req.getRechargeCardExpiryTime()));
        rCExchangeCodeBatchSaveDTO.setValidityPeriod(req.getValidityPeriod());
        rCExchangeCodeBatchSaveDTO.setExpiryType(req.getExpiryType());
        rCExchangeCodeBatchSaveDTO.setDescription(req.getDescription());
        return rCExchangeCodeBatchSaveDTO;
    }

    public static RechargeCardExchangeCodeBatchPO DTOSave2PO(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO) {
        if (rcExchangeCodeBatchSaveDTO == null) {
            return null;
        }
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = new RechargeCardExchangeCodeBatchPO();
        rechargeCardExchangeCodeBatchPO.setId(rcExchangeCodeBatchSaveDTO.getId());
        rechargeCardExchangeCodeBatchPO.setName(rcExchangeCodeBatchSaveDTO.getName());
        rechargeCardExchangeCodeBatchPO.setRechargeCardName(rcExchangeCodeBatchSaveDTO.getRechargeCardName());
        rechargeCardExchangeCodeBatchPO.setIsVirtual(rcExchangeCodeBatchSaveDTO.getIsVirtual());
        rechargeCardExchangeCodeBatchPO.setDescription(rcExchangeCodeBatchSaveDTO.getDescription());
        rechargeCardExchangeCodeBatchPO.setAmount(rcExchangeCodeBatchSaveDTO.getAmount());
        rechargeCardExchangeCodeBatchPO.setProjectId(rcExchangeCodeBatchSaveDTO.getProjectId());
        rechargeCardExchangeCodeBatchPO.setCityCodes(rcExchangeCodeBatchSaveDTO.getCityCodes());
        rechargeCardExchangeCodeBatchPO.setProductIds(rcExchangeCodeBatchSaveDTO.getProductIds());
        rechargeCardExchangeCodeBatchPO.setFaceValue(rcExchangeCodeBatchSaveDTO.getFaceValue());
        rechargeCardExchangeCodeBatchPO.setExpiryTime(rcExchangeCodeBatchSaveDTO.getExpiryTime());
        rechargeCardExchangeCodeBatchPO.setRechargeCardEffectiveTime(rcExchangeCodeBatchSaveDTO.getRechargeCardEffectiveTime());
        rechargeCardExchangeCodeBatchPO.setRechargeCardExpiryTime(rcExchangeCodeBatchSaveDTO.getRechargeCardExpiryTime());
        rechargeCardExchangeCodeBatchPO.setValidityPeriod(rcExchangeCodeBatchSaveDTO.getValidityPeriod());
        rechargeCardExchangeCodeBatchPO.setExpiryType(rcExchangeCodeBatchSaveDTO.getExpiryType());
        return rechargeCardExchangeCodeBatchPO;
    }

    public static RCExchangeCodeBatchEditResp DTOEdit2RespEdit(RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO) {
        if (rcExchangeCodeBatchEditDTO == null) {
            return null;
        }
        RCExchangeCodeBatchEditResp rCExchangeCodeBatchEditResp = new RCExchangeCodeBatchEditResp();
        rCExchangeCodeBatchEditResp.setId(rcExchangeCodeBatchEditDTO.getId());
        rCExchangeCodeBatchEditResp.setName(rcExchangeCodeBatchEditDTO.getName());
        rCExchangeCodeBatchEditResp.setRechargeCardName(rcExchangeCodeBatchEditDTO.getRechargeCardName());
        rCExchangeCodeBatchEditResp.setIsMade(rcExchangeCodeBatchEditDTO.getIsMade());
        rCExchangeCodeBatchEditResp.setIsVirtual(rcExchangeCodeBatchEditDTO.getIsVirtual());
        rCExchangeCodeBatchEditResp.setDescription(rcExchangeCodeBatchEditDTO.getDescription());
        rCExchangeCodeBatchEditResp.setProjectId(rcExchangeCodeBatchEditDTO.getProjectId());
        rCExchangeCodeBatchEditResp.setCityCodes(rcExchangeCodeBatchEditDTO.getCityCodes());
        rCExchangeCodeBatchEditResp.setProductIds(rcExchangeCodeBatchEditDTO.getProductIds());
        rCExchangeCodeBatchEditResp.setAmount(rcExchangeCodeBatchEditDTO.getAmount());
        rCExchangeCodeBatchEditResp.setFaceValue(rcExchangeCodeBatchEditDTO.getFaceValue());
        rCExchangeCodeBatchEditResp.setExpiryTime(rcExchangeCodeBatchEditDTO.getExpiryTime().getTime());
        rCExchangeCodeBatchEditResp.setRechargeCardEffectiveTime(rcExchangeCodeBatchEditDTO.getRechargeCardEffectiveTime().getTime());
        rCExchangeCodeBatchEditResp.setRechargeCardExpiryTime(rcExchangeCodeBatchEditDTO.getRechargeCardExpiryTime().getTime());
        rCExchangeCodeBatchEditResp.setValidityPeriod(rcExchangeCodeBatchEditDTO.getValidityPeriod());
        rCExchangeCodeBatchEditResp.setExpiryType(rcExchangeCodeBatchEditDTO.getExpiryType());
        return rCExchangeCodeBatchEditResp;
    }

    public static RCExchangeCodeBatchEditDTO po2DtoEdit(RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO) {
        if (rechargeCardExchangeCodeBatchPO == null) {
            return null;
        }
        RCExchangeCodeBatchEditDTO rCExchangeCodeBatchEditDTO = new RCExchangeCodeBatchEditDTO();
        rCExchangeCodeBatchEditDTO.setId(rechargeCardExchangeCodeBatchPO.getId());
        rCExchangeCodeBatchEditDTO.setName(rechargeCardExchangeCodeBatchPO.getName());
        rCExchangeCodeBatchEditDTO.setRechargeCardName(rechargeCardExchangeCodeBatchPO.getRechargeCardName());
        rCExchangeCodeBatchEditDTO.setIsVirtual(rechargeCardExchangeCodeBatchPO.getIsVirtual());
        rCExchangeCodeBatchEditDTO.setIsMade(rechargeCardExchangeCodeBatchPO.getIsMade());
        rCExchangeCodeBatchEditDTO.setStatus(rechargeCardExchangeCodeBatchPO.getStatus());
        rCExchangeCodeBatchEditDTO.setDescription(rechargeCardExchangeCodeBatchPO.getDescription());
        rCExchangeCodeBatchEditDTO.setAmount(rechargeCardExchangeCodeBatchPO.getAmount());
        rCExchangeCodeBatchEditDTO.setProjectId(rechargeCardExchangeCodeBatchPO.getProjectId());
        rCExchangeCodeBatchEditDTO.setCityCodes(rechargeCardExchangeCodeBatchPO.getCityCodes());
        rCExchangeCodeBatchEditDTO.setProductIds(rechargeCardExchangeCodeBatchPO.getProductIds());
        rCExchangeCodeBatchEditDTO.setFaceValue(rechargeCardExchangeCodeBatchPO.getFaceValue());
        rCExchangeCodeBatchEditDTO.setExpiryTime(rechargeCardExchangeCodeBatchPO.getExpiryTime());
        rCExchangeCodeBatchEditDTO.setRechargeCardEffectiveTime(rechargeCardExchangeCodeBatchPO.getRechargeCardEffectiveTime());
        rCExchangeCodeBatchEditDTO.setRechargeCardExpiryTime(rechargeCardExchangeCodeBatchPO.getRechargeCardExpiryTime());
        rCExchangeCodeBatchEditDTO.setValidityPeriod(rechargeCardExchangeCodeBatchPO.getValidityPeriod());
        rCExchangeCodeBatchEditDTO.setExpiryType(rechargeCardExchangeCodeBatchPO.getExpiryType());
        return rCExchangeCodeBatchEditDTO;
    }

}
