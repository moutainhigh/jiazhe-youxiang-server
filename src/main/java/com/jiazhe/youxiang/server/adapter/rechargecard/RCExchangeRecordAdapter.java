package com.jiazhe.youxiang.server.adapter.rechargecard;

import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordListDTO;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord.RCExchangeRecordListResp;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public class RCExchangeRecordAdapter {
    public static RCExchangeRecordListResp DTOList2RespList(RCExchangeRecordListDTO rcExchangeRecordListDTO) {
        if (rcExchangeRecordListDTO == null) {
            return null;
        }
        RCExchangeRecordListResp rCExchangeRecordListResp = new RCExchangeRecordListResp();
        rCExchangeRecordListResp.setId(rcExchangeRecordListDTO.getId());
        rCExchangeRecordListResp.setExchangeCodeId(rcExchangeRecordListDTO.getExchangeCodeId());
        rCExchangeRecordListResp.setExchangeType(rcExchangeRecordListDTO.getExchangeType());
        rCExchangeRecordListResp.setOperatorName(rcExchangeRecordListDTO.getOperatorName());
        rCExchangeRecordListResp.setAddTime(rcExchangeRecordListDTO.getAddTime());
        return rCExchangeRecordListResp;
    }
}
