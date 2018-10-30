package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchResp;

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
        rCExchangeCodeBatchResp.setExpiryTime(dto.getExpiryTime());
        rCExchangeCodeBatchResp.setStatus(dto.getStatus());
        rCExchangeCodeBatchResp.setIsVirtual(dto.getIsVirtual());
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
        rCExchangeCodeBatchDTO.setAmount(rechargeCardExchangeCodeBatchPO.getAmount());
        rCExchangeCodeBatchDTO.setFaceValue(rechargeCardExchangeCodeBatchPO.getFaceValue());
        rCExchangeCodeBatchDTO.setExpiryTime(rechargeCardExchangeCodeBatchPO.getExpiryTime());
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
        rCExchangeCodeBatchSaveDTO.setExpiryTime(req.getExpiryTime());
        rCExchangeCodeBatchSaveDTO.setRechargeCardExpiryTime(req.getRechargeCardExpiryTime());
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
        rCExchangeCodeBatchEditResp.setIsVirtual(rcExchangeCodeBatchEditDTO.getIsVirtual());
        rCExchangeCodeBatchEditResp.setDescription(rcExchangeCodeBatchEditDTO.getDescription());
        rCExchangeCodeBatchEditResp.setProjectId(rcExchangeCodeBatchEditDTO.getProjectId());
        rCExchangeCodeBatchEditResp.setCityIds(rcExchangeCodeBatchEditDTO.getCityCodes());
        rCExchangeCodeBatchEditResp.setProductIds(rcExchangeCodeBatchEditDTO.getProductIds());
        rCExchangeCodeBatchEditResp.setExpiryTime(rcExchangeCodeBatchEditDTO.getExpiryTime());
        rCExchangeCodeBatchEditResp.setRechargeCardExpiryTime(rcExchangeCodeBatchEditDTO.getRechargeCardExpiryTime());
        rCExchangeCodeBatchEditResp.setValidityPeriod(rcExchangeCodeBatchEditDTO.getValidityPeriod());
        rCExchangeCodeBatchEditResp.setExpiryType(rcExchangeCodeBatchEditDTO.getExpiryType());
        return rCExchangeCodeBatchEditResp;
    }

    public static RCExchangeCodeBatchEditDTO ReqEdit2DTOEdit(RCExchangeCodeBatchEditReq req) {
        if (req == null) {
            return null;
        }
        RCExchangeCodeBatchEditDTO rCExchangeCodeBatchEditDTO = new RCExchangeCodeBatchEditDTO();
        rCExchangeCodeBatchEditDTO.setId(req.getId());
        rCExchangeCodeBatchEditDTO.setName(req.getName());
        rCExchangeCodeBatchEditDTO.setRechargeCardName(req.getRechargeCardName());
        rCExchangeCodeBatchEditDTO.setIsVirtual(req.getIsVirtual());
        rCExchangeCodeBatchEditDTO.setDescription(req.getDescription());
        rCExchangeCodeBatchEditDTO.setProjectId(req.getProjectId());
        rCExchangeCodeBatchEditDTO.setCityCodes(req.getCityIds());
        rCExchangeCodeBatchEditDTO.setProductIds(req.getProductIds());
        rCExchangeCodeBatchEditDTO.setExpiryTime(req.getExpiryTime());
        rCExchangeCodeBatchEditDTO.setRechargeCardExpiryTime(req.getRechargeCardExpiryTime());
        rCExchangeCodeBatchEditDTO.setValidityPeriod(req.getValidityPeriod());
        rCExchangeCodeBatchEditDTO.setExpiryType(req.getExpiryType());
        return rCExchangeCodeBatchEditDTO;
    }
}
