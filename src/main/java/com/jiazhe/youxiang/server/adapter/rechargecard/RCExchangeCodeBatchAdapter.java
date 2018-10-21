package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchListDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveReq;
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

    public static RCExchangeCodeBatchSaveDTO ReqSave2DTOSave(RCExchangeCodeBatchSaveReq req) {
        if (req == null) {
            return null;
        }
        RCExchangeCodeBatchSaveDTO rCExchangeCodeBatchSaveDTO = new RCExchangeCodeBatchSaveDTO();
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
}
