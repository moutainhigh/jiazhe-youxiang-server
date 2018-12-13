package com.jiazhe.youxiang.server.adapter.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.req.point.poingexchangecodebatch.PointExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.point.pointexchangecodebatch.PointExchangeCodeBatchResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchResp;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public class PointExchangeCodeBatchAdapter {

    public static PointExchangeCodeBatchResp dto2Resp(PointExchangeCodeBatchDTO pointExchangeCodeBatchDTO) {
        if (pointExchangeCodeBatchDTO == null) {
            return null;
        }
        PointExchangeCodeBatchResp pointExchangeCodeBatchResp = new PointExchangeCodeBatchResp();
        pointExchangeCodeBatchResp.setId(pointExchangeCodeBatchDTO.getId());
        pointExchangeCodeBatchResp.setName(pointExchangeCodeBatchDTO.getName());
        pointExchangeCodeBatchResp.setPointName(pointExchangeCodeBatchDTO.getPointName());
        pointExchangeCodeBatchResp.setAmount(pointExchangeCodeBatchDTO.getAmount());
        pointExchangeCodeBatchResp.setFaceValue(pointExchangeCodeBatchDTO.getFaceValue());
        pointExchangeCodeBatchResp.setExpiryTime(pointExchangeCodeBatchDTO.getExpiryTime().getTime());
        pointExchangeCodeBatchResp.setStatus(pointExchangeCodeBatchDTO.getStatus());
        pointExchangeCodeBatchResp.setIsVirtual(pointExchangeCodeBatchDTO.getIsVirtual());
        pointExchangeCodeBatchResp.setIsMade(pointExchangeCodeBatchDTO.getIsMade());
        return pointExchangeCodeBatchResp;
    }

    public static PointExchangeCodeBatchDTO po2Dto(PointExchangeCodeBatchPO pointExchangeCodeBatchPO) {
        if (pointExchangeCodeBatchPO == null) {
            return null;
        }
        PointExchangeCodeBatchDTO pointExchangeCodeBatchDTO = new PointExchangeCodeBatchDTO();
        pointExchangeCodeBatchDTO.setId(pointExchangeCodeBatchPO.getId());
        pointExchangeCodeBatchDTO.setName(pointExchangeCodeBatchPO.getName());
        pointExchangeCodeBatchDTO.setPointName(pointExchangeCodeBatchPO.getPointName());
        pointExchangeCodeBatchDTO.setIsVirtual(pointExchangeCodeBatchPO.getIsVirtual());
        pointExchangeCodeBatchDTO.setIsMade(pointExchangeCodeBatchPO.getIsMade());
        pointExchangeCodeBatchDTO.setAmount(pointExchangeCodeBatchPO.getAmount());
        pointExchangeCodeBatchDTO.setFaceValue(pointExchangeCodeBatchPO.getFaceValue());
        pointExchangeCodeBatchDTO.setExpiryTime(pointExchangeCodeBatchPO.getExpiryTime());
        pointExchangeCodeBatchDTO.setStatus(pointExchangeCodeBatchPO.getStatus());
        return pointExchangeCodeBatchDTO;
    }

    public static PointExchangeCodeBatchSaveDTO reqSave2dtoSave(PointExchangeCodeBatchSaveReq req) {
        if (req == null) {
            return null;
        }
        PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO = new PointExchangeCodeBatchSaveDTO();
        pointExchangeCodeBatchSaveDTO.setId(req.getId());
        pointExchangeCodeBatchSaveDTO.setName(req.getName());
        pointExchangeCodeBatchSaveDTO.setPointName(req.getPointName());
        pointExchangeCodeBatchSaveDTO.setIsVirtual(req.getIsVirtual());
        pointExchangeCodeBatchSaveDTO.setAmount(req.getAmount());
        pointExchangeCodeBatchSaveDTO.setFaceValue(req.getFaceValue());
        pointExchangeCodeBatchSaveDTO.setProjectId(req.getProjectId());
        pointExchangeCodeBatchSaveDTO.setCityCodes(req.getCityCodes());
        pointExchangeCodeBatchSaveDTO.setProductIds(req.getProductIds());
        pointExchangeCodeBatchSaveDTO.setExpiryTime(new Date(req.getExpiryTime()));
        pointExchangeCodeBatchSaveDTO.setPointExpiryTime(new Date(req.getPointExpiryTime()));
        pointExchangeCodeBatchSaveDTO.setValidityPeriod(req.getValidityPeriod());
        pointExchangeCodeBatchSaveDTO.setExpiryType(req.getExpiryType());
        pointExchangeCodeBatchSaveDTO.setDescription(req.getDescription());
        return pointExchangeCodeBatchSaveDTO;
    }
}
