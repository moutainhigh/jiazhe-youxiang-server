package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord.RCExchangeRecordResp;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public class RCExchangeRecordAdapter {
    public static RCExchangeRecordResp DTO2Resp(RCExchangeRecordDTO dto) {
        if (dto == null) {
            return null;
        }
        RCExchangeRecordResp rCExchangeRecordListResp = new RCExchangeRecordResp();
        rCExchangeRecordListResp.setId(dto.getId());
        rCExchangeRecordListResp.setExchangeCodeId(dto.getExchangeCodeId());
        rCExchangeRecordListResp.setExchangeType(dto.getExchangeType());
        rCExchangeRecordListResp.setOperatorName(dto.getOperatorName());
        rCExchangeRecordListResp.setAddTime(dto.getAddTime());
        return rCExchangeRecordListResp;
    }

    public static RCExchangeRecordDTO PO2DTO(RechargeCardExchangeRecordPO rechargeCardExchangeRecordPO) {
        if (rechargeCardExchangeRecordPO == null) {
            return null;
        }
        RCExchangeRecordDTO rCExchangeRecordDTO = new RCExchangeRecordDTO();
        rCExchangeRecordDTO.setId(rechargeCardExchangeRecordPO.getId());
        rCExchangeRecordDTO.setRechargeCardId(rechargeCardExchangeRecordPO.getRechargeCardId());
        rCExchangeRecordDTO.setExchangeCodeId(rechargeCardExchangeRecordPO.getExchangeCodeId());
        rCExchangeRecordDTO.setExchangeType(rechargeCardExchangeRecordPO.getExchangeType());
        rCExchangeRecordDTO.setOperatorId(rechargeCardExchangeRecordPO.getOperatorId());
        rCExchangeRecordDTO.setOperatorName(rechargeCardExchangeRecordPO.getOperatorName());
        rCExchangeRecordDTO.setAddTime(rechargeCardExchangeRecordPO.getAddTime());
        return rCExchangeRecordDTO;
    }
}
