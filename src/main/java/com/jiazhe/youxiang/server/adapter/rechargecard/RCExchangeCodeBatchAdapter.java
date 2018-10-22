package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchListDTO;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchListResp;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public class RCExchangeCodeBatchAdapter {

    public static RCExchangeCodeBatchListResp DTOList2RespList(RCExchangeCodeBatchListDTO rcExchangeCodeBatchListDTO) {
        if (rcExchangeCodeBatchListDTO == null) {
            return null;
        }
        RCExchangeCodeBatchListResp rCExchangeCodeBatchListResp = new RCExchangeCodeBatchListResp();
        rCExchangeCodeBatchListResp.setId(rcExchangeCodeBatchListDTO.getId());
        rCExchangeCodeBatchListResp.setName(rcExchangeCodeBatchListDTO.getName());
        rCExchangeCodeBatchListResp.setAmount(rcExchangeCodeBatchListDTO.getAmount());
        rCExchangeCodeBatchListResp.setFaceValue(rcExchangeCodeBatchListDTO.getFaceValue());
        rCExchangeCodeBatchListResp.setExpiryTime(rcExchangeCodeBatchListDTO.getExpiryTime());
        rCExchangeCodeBatchListResp.setStatus(rcExchangeCodeBatchListDTO.getStatus());
        return rCExchangeCodeBatchListResp;
    }

    public static RCExchangeCodeBatchListDTO PO2DTOList(RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO) {
        if (rechargeCardExchangeCodeBatchPO == null) {
            return null;
        }
        RCExchangeCodeBatchListDTO rCExchangeCodeBatchListDTO = new RCExchangeCodeBatchListDTO();
        rCExchangeCodeBatchListDTO.setId(rechargeCardExchangeCodeBatchPO.getId());
        rCExchangeCodeBatchListDTO.setName(rechargeCardExchangeCodeBatchPO.getName());
        rCExchangeCodeBatchListDTO.setAmount(rechargeCardExchangeCodeBatchPO.getAmount());
        rCExchangeCodeBatchListDTO.setFaceValue(rechargeCardExchangeCodeBatchPO.getFaceValue());
        rCExchangeCodeBatchListDTO.setExpiryTime(rechargeCardExchangeCodeBatchPO.getExpiryTime());
        rCExchangeCodeBatchListDTO.setStatus(rechargeCardExchangeCodeBatchPO.getStatus());
        return rCExchangeCodeBatchListDTO;
    }

    public static RCExchangeCodeBatchAddDTO ReqAdd2DTOAdd(RCExchangeCodeBatchAddReq req) {
        if (req == null) {
            return null;
        }
        RCExchangeCodeBatchAddDTO rCExchangeCodeBatchSaveDTO = new RCExchangeCodeBatchAddDTO();
        rCExchangeCodeBatchSaveDTO.setName(req.getName());
        rCExchangeCodeBatchSaveDTO.setDescription(req.getDescription());
        rCExchangeCodeBatchSaveDTO.setAmount(req.getAmount());
        rCExchangeCodeBatchSaveDTO.setProjectId(req.getProjectId());
        rCExchangeCodeBatchSaveDTO.setCityIds(req.getCityIds());
        rCExchangeCodeBatchSaveDTO.setProductIds(req.getProductIds());
        rCExchangeCodeBatchSaveDTO.setFaceValue(req.getFaceValue());
        rCExchangeCodeBatchSaveDTO.setExpiryTime(req.getExpiryTime());
        rCExchangeCodeBatchSaveDTO.setRechargeCardExpiryTime(req.getRechargeCardExpiryTime());
        rCExchangeCodeBatchSaveDTO.setValidityPeriod(req.getValidityPeriod());
        rCExchangeCodeBatchSaveDTO.setExpiryType(req.getExpiryType());
        return rCExchangeCodeBatchSaveDTO;
    }

    public static RechargeCardExchangeCodeBatchPO DTOSave2PO(RCExchangeCodeBatchAddDTO rcExchangeCodeBatchAddDTO) {
        if (rcExchangeCodeBatchAddDTO == null) {
            return null;
        }
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = new RechargeCardExchangeCodeBatchPO();
        rechargeCardExchangeCodeBatchPO.setName(rcExchangeCodeBatchAddDTO.getName());
        rechargeCardExchangeCodeBatchPO.setDescription(rcExchangeCodeBatchAddDTO.getDescription());
        rechargeCardExchangeCodeBatchPO.setAmount(rcExchangeCodeBatchAddDTO.getAmount());
        rechargeCardExchangeCodeBatchPO.setProjectId(rcExchangeCodeBatchAddDTO.getProjectId());
        rechargeCardExchangeCodeBatchPO.setCityIds(rcExchangeCodeBatchAddDTO.getCityIds());
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
        rCExchangeCodeBatchEditResp.setDescription(rcExchangeCodeBatchEditDTO.getDescription());
        rCExchangeCodeBatchEditResp.setProjectId(rcExchangeCodeBatchEditDTO.getProjectId());
        rCExchangeCodeBatchEditResp.setCityIds(rcExchangeCodeBatchEditDTO.getCityIds());
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
        rCExchangeCodeBatchEditDTO.setDescription(req.getDescription());
        rCExchangeCodeBatchEditDTO.setProjectId(req.getProjectId());
        rCExchangeCodeBatchEditDTO.setCityIds(req.getCityIds());
        rCExchangeCodeBatchEditDTO.setProductIds(req.getProductIds());
        rCExchangeCodeBatchEditDTO.setExpiryTime(req.getExpiryTime());
        rCExchangeCodeBatchEditDTO.setRechargeCardExpiryTime(req.getRechargeCardExpiryTime());
        rCExchangeCodeBatchEditDTO.setValidityPeriod(req.getValidityPeriod());
        rCExchangeCodeBatchEditDTO.setExpiryType(req.getExpiryType());
        return rCExchangeCodeBatchEditDTO;
    }
}
