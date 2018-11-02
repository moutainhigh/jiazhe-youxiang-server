package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
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
        rCDTO.setName(rechargeCardPO.getName());
        rCDTO.setDescription(rechargeCardPO.getDescription());
        rCDTO.setProjectId(rechargeCardPO.getProjectId());
        rCDTO.setCityCodes(rechargeCardPO.getCityCodes());
        rCDTO.setProductIds(rechargeCardPO.getProductIds());
        rCDTO.setFaceValue(rechargeCardPO.getFaceValue());
        rCDTO.setBalance(rechargeCardPO.getBalance());
        rCDTO.setExpiryTime(rechargeCardPO.getExpiryTime());
        return rCDTO;
    }

    public static RCResp DTO2Resp(RCDTO rcdto) {
        if (rcdto == null) {
            return null;
        }
        RCResp rCResp = new RCResp();
        rCResp.setName(rcdto.getName());
        rCResp.setDescription(rcdto.getDescription());
        rCResp.setProjectId(rcdto.getProjectId());
        rCResp.setCityCodes(rcdto.getCityCodes());
        rCResp.setProductIds(rcdto.getProductIds());
        rCResp.setFaceValue(rcdto.getFaceValue());
        rCResp.setBalance(rcdto.getBalance());
        rCResp.setExpiryTime(rcdto.getExpiryTime());
        return rCResp;
    }
}
