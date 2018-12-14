package com.jiazhe.youxiang.server.adapter.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.resp.point.exchangerecord.PointExchangeRecordResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord.RCExchangeRecordResp;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public class PointExchangeRecordAdapter {

    public static PointExchangeRecordDTO po2Dto(PointExchangeRecordPO pointExchangeRecordPO) {
        if (pointExchangeRecordPO == null) {
            return null;
        }
        PointExchangeRecordDTO pointExchangeRecordDTO = new PointExchangeRecordDTO();
        pointExchangeRecordDTO.setId(pointExchangeRecordPO.getId());
        pointExchangeRecordDTO.setExchangeCodeId(pointExchangeRecordPO.getExchangeCodeId());
        pointExchangeRecordDTO.setExchangeType(pointExchangeRecordPO.getExchangeType());
        pointExchangeRecordDTO.setOperatorId(pointExchangeRecordPO.getOperatorId());
        pointExchangeRecordDTO.setOperatorName(pointExchangeRecordPO.getOperatorName());
        pointExchangeRecordDTO.setAddTime(pointExchangeRecordPO.getAddTime());
        return pointExchangeRecordDTO;
    }

    public static PointExchangeRecordResp dto2Resp(PointExchangeRecordDTO pointExchangeRecordDTO) {
        if (pointExchangeRecordDTO == null) {
            return null;
        }
        PointExchangeRecordResp pointExchangeRecordResp = new PointExchangeRecordResp();
        pointExchangeRecordResp.setId(pointExchangeRecordDTO.getId());
        pointExchangeRecordResp.setExchangeCodeId(pointExchangeRecordDTO.getExchangeCodeId());
        pointExchangeRecordResp.setExchangeType(pointExchangeRecordDTO.getExchangeType());
        pointExchangeRecordResp.setOperatorName(pointExchangeRecordDTO.getOperatorName());
        pointExchangeRecordResp.setAddTime(pointExchangeRecordDTO.getAddTime().getTime());
        return pointExchangeRecordResp;
    }
}
