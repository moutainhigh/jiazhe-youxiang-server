package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditReq;
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

    public static RCExchangeCodeBatchAddDTO ReqAdd2DTOAdd(RCExchangeCodeBatchAddReq req) {
        if (req == null) {
            return null;
        }
        RCExchangeCodeBatchAddDTO rCExchangeCodeBatchAddDTO = new RCExchangeCodeBatchAddDTO();
        rCExchangeCodeBatchAddDTO.setName(req.getName());
        rCExchangeCodeBatchAddDTO.setRechargeCardName(req.getRechargeCardName());
        rCExchangeCodeBatchAddDTO.setIsVirtual(req.getIsVirtual());
        rCExchangeCodeBatchAddDTO.setAmount(req.getAmount());
        rCExchangeCodeBatchAddDTO.setFaceValue(req.getFaceValue());
        rCExchangeCodeBatchAddDTO.setProjectId(req.getProjectId());
        rCExchangeCodeBatchAddDTO.setCityCodes(req.getCityCodes());
        rCExchangeCodeBatchAddDTO.setProductIds(req.getProductIds());
        rCExchangeCodeBatchAddDTO.setExpiryTime(req.getExpiryTime());
        rCExchangeCodeBatchAddDTO.setRechargeCardExpiryTime(req.getRechargeCardExpiryTime());
        rCExchangeCodeBatchAddDTO.setValidityPeriod(req.getValidityPeriod());
        rCExchangeCodeBatchAddDTO.setExpiryType(req.getExpiryType());
        rCExchangeCodeBatchAddDTO.setDescription(req.getDescription());
        return rCExchangeCodeBatchAddDTO;
    }

    public static RechargeCardExchangeCodeBatchPO DTOSave2PO(RCExchangeCodeBatchAddDTO rcExchangeCodeBatchAddDTO) {
        if (rcExchangeCodeBatchAddDTO == null) {
            return null;
        }
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = new RechargeCardExchangeCodeBatchPO();
        rechargeCardExchangeCodeBatchPO.setName(rcExchangeCodeBatchAddDTO.getName());
        rechargeCardExchangeCodeBatchPO.setRechargeCardName(rcExchangeCodeBatchAddDTO.getRechargeCardName());
        rechargeCardExchangeCodeBatchPO.setIsVirtual(rcExchangeCodeBatchAddDTO.getIsVirtual());
        rechargeCardExchangeCodeBatchPO.setDescription(rcExchangeCodeBatchAddDTO.getDescription());
        rechargeCardExchangeCodeBatchPO.setAmount(rcExchangeCodeBatchAddDTO.getAmount());
        rechargeCardExchangeCodeBatchPO.setProjectId(rcExchangeCodeBatchAddDTO.getProjectId());
        rechargeCardExchangeCodeBatchPO.setCityCodes(rcExchangeCodeBatchAddDTO.getCityCodes());
        rechargeCardExchangeCodeBatchPO.setProductIds(rcExchangeCodeBatchAddDTO.getProductIds());
        rechargeCardExchangeCodeBatchPO.setFaceValue(rcExchangeCodeBatchAddDTO.getFaceValue());
        rechargeCardExchangeCodeBatchPO.setExpiryTime(rcExchangeCodeBatchAddDTO.getExpiryTime());
        rechargeCardExchangeCodeBatchPO.setRechargeCardExpiryTime(rcExchangeCodeBatchAddDTO.getRechargeCardExpiryTime());
        rechargeCardExchangeCodeBatchPO.setValidityPeriod(rcExchangeCodeBatchAddDTO.getValidityPeriod());
        rechargeCardExchangeCodeBatchPO.setExpiryType(rcExchangeCodeBatchAddDTO.getExpiryType());
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
