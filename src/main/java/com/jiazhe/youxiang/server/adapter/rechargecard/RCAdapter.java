package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.RCEditReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rc.RCResp;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public class RCAdapter {
    public static RCDTO PO2DTO(RechargeCardPO rechargeCardPO) {
        if (rechargeCardPO == null) {
            return null;
        }
        RCDTO rCDTO = new RCDTO();
        rCDTO.setId(rechargeCardPO.getId());
        rCDTO.setCustomerId(rechargeCardPO.getCustomerId());
        rCDTO.setName(rechargeCardPO.getName());
        rCDTO.setDescription(rechargeCardPO.getDescription());
        rCDTO.setProjectId(rechargeCardPO.getProjectId());
        rCDTO.setCityCodes(rechargeCardPO.getCityCodes());
        rCDTO.setProductIds(rechargeCardPO.getProductIds());
        rCDTO.setFaceValue(rechargeCardPO.getFaceValue());
        rCDTO.setBalance(rechargeCardPO.getBalance());
        rCDTO.setExpiryTime(rechargeCardPO.getExpiryTime());
        rCDTO.setStatus(rechargeCardPO.getStatus());
        rCDTO.setAddTime(rechargeCardPO.getAddTime());
        return rCDTO;
    }

    public static RCResp DTO2Resp(RCDTO rcdto) {
        if (rcdto == null) {
            return null;
        }
        RCResp rCResp = new RCResp();
        rCResp.setId(rcdto.getId());
        rCResp.setCustomerId(rcdto.getCustomerId());
        rCResp.setName(rcdto.getName());
        rCResp.setDescription(rcdto.getDescription());
        rCResp.setProjectId(rcdto.getProjectId());
        rCResp.setCityCodes(rcdto.getCityCodes());
        rCResp.setProductIds(rcdto.getProductIds());
        rCResp.setFaceValue(rcdto.getFaceValue());
        rCResp.setBalance(rcdto.getBalance());
        rCResp.setExpiryTime(rcdto.getExpiryTime().getTime());
        rCResp.setStatus(rcdto.getStatus());
        rCResp.setAddTime(rcdto.getAddTime().getTime());
        rCResp.setCustomerResp(CustomerAdapter.customerDTO2VO(rcdto.getCustomerDTO()));
        rCResp.setRcExchangeRecordResp(RCExchangeRecordAdapter.DTO2Resp(rcdto.getRcExchangeRecordDTO()));
        return rCResp;
    }

    public static RCEditDTO EditReq2EditDTO(RCEditReq req) {
        if (req == null) {
            return null;
        }
        RCEditDTO rCEditDTO = new RCEditDTO();
        rCEditDTO.setId(req.getId());
        rCEditDTO.setCityCodes(req.getCityCodes());
        rCEditDTO.setProductIds(req.getProductIds());
        rCEditDTO.setName(req.getName());
        rCEditDTO.setDescription(req.getDescription());
        rCEditDTO.setExpiryTime(req.getExpiryTime());
        return rCEditDTO;
    }

    public static RechargeCardPO DTO2PO(RCDTO rcdto) {
        if (rcdto == null) {
            return null;
        }
        RechargeCardPO rechargeCardPO = new RechargeCardPO();
        rechargeCardPO.setId(rcdto.getId());
        rechargeCardPO.setName(rcdto.getName());
        rechargeCardPO.setDescription(rcdto.getDescription());
        rechargeCardPO.setCustomerId(rcdto.getCustomerId());
        rechargeCardPO.setProjectId(rcdto.getProjectId());
        rechargeCardPO.setCityCodes(rcdto.getCityCodes());
        rechargeCardPO.setProductIds(rcdto.getProductIds());
        rechargeCardPO.setFaceValue(rcdto.getFaceValue());
        rechargeCardPO.setBalance(rcdto.getBalance());
        rechargeCardPO.setExpiryTime(rcdto.getExpiryTime());
        rechargeCardPO.setStatus(rcdto.getStatus());
        rechargeCardPO.setAddTime(rcdto.getAddTime());
        return rechargeCardPO;
    }
}
