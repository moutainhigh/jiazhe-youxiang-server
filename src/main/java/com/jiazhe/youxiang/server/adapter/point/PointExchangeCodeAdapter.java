package com.jiazhe.youxiang.server.adapter.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.RCExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode.RCExchangeCodeResp;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public class PointExchangeCodeAdapter {

    public static PointExchangeCodeDTO po2Dto(PointExchangeCodePO pointExchangeCodePO) {
        if (pointExchangeCodePO == null) {
            return null;
        }
        PointExchangeCodeDTO pointExchangeCodeDTO = new PointExchangeCodeDTO();
        pointExchangeCodeDTO.setId(pointExchangeCodePO.getId());
        pointExchangeCodeDTO.setBatchId(pointExchangeCodePO.getBatchId());
        pointExchangeCodeDTO.setBatchName(pointExchangeCodePO.getBatchName());
        pointExchangeCodeDTO.setPointName(pointExchangeCodePO.getPointName());
        pointExchangeCodeDTO.setBatchDescription(pointExchangeCodePO.getBatchDescription());
        pointExchangeCodeDTO.setProjectId(pointExchangeCodePO.getProjectId());
        pointExchangeCodeDTO.setCityCodes(pointExchangeCodePO.getCityCodes());
        pointExchangeCodeDTO.setProductIds(pointExchangeCodePO.getProductIds());
        pointExchangeCodeDTO.setCode(pointExchangeCodePO.getCode());
        pointExchangeCodeDTO.setKeyt(pointExchangeCodePO.getKeyt());
        pointExchangeCodeDTO.setFaceValue(pointExchangeCodePO.getFaceValue());
        pointExchangeCodeDTO.setExpiryTime(pointExchangeCodePO.getExpiryTime());
        pointExchangeCodeDTO.setPointExpiryTime(pointExchangeCodePO.getPointExpiryTime());
        pointExchangeCodeDTO.setValidityPeriod(pointExchangeCodePO.getValidityPeriod());
        pointExchangeCodeDTO.setExpiryType(pointExchangeCodePO.getExpiryType());
        pointExchangeCodeDTO.setStatus(pointExchangeCodePO.getStatus());
        pointExchangeCodeDTO.setUsed(pointExchangeCodePO.getUsed());
        return pointExchangeCodeDTO;
    }

    public static PointExchangeCodeBatchEditDTO po2DtoEdit(PointExchangeCodeBatchPO pointExchangeCodeBatchPO) {
        if (pointExchangeCodeBatchPO == null) {
            return null;
        }
        PointExchangeCodeBatchEditDTO pointExchangeCodeBatchEditDTO = new PointExchangeCodeBatchEditDTO();
        pointExchangeCodeBatchEditDTO.setId(pointExchangeCodeBatchPO.getId());
        pointExchangeCodeBatchEditDTO.setName(pointExchangeCodeBatchPO.getName());
        pointExchangeCodeBatchEditDTO.setPointName(pointExchangeCodeBatchPO.getPointName());
        pointExchangeCodeBatchEditDTO.setIsVirtual(pointExchangeCodeBatchPO.getIsVirtual());
        pointExchangeCodeBatchEditDTO.setIsMade(pointExchangeCodeBatchPO.getIsMade());
        pointExchangeCodeBatchEditDTO.setDescription(pointExchangeCodeBatchPO.getDescription());
        pointExchangeCodeBatchEditDTO.setAmount(pointExchangeCodeBatchPO.getAmount());
        pointExchangeCodeBatchEditDTO.setProjectId(pointExchangeCodeBatchPO.getProjectId());
        pointExchangeCodeBatchEditDTO.setCityCodes(pointExchangeCodeBatchPO.getCityCodes());
        pointExchangeCodeBatchEditDTO.setProductIds(pointExchangeCodeBatchPO.getProductIds());
        pointExchangeCodeBatchEditDTO.setFaceValue(pointExchangeCodeBatchPO.getFaceValue());
        pointExchangeCodeBatchEditDTO.setExpiryTime(pointExchangeCodeBatchPO.getExpiryTime());
        pointExchangeCodeBatchEditDTO.setPointExpiryTime(pointExchangeCodeBatchPO.getPointExpiryTime());
        pointExchangeCodeBatchEditDTO.setValidityPeriod(pointExchangeCodeBatchPO.getValidityPeriod());
        pointExchangeCodeBatchEditDTO.setExpiryType(pointExchangeCodeBatchPO.getExpiryType());
        return pointExchangeCodeBatchEditDTO;
    }

    public static PointExchangeCodePO DtoSave2Po(PointExchangeCodeSaveDTO pointExchangeCodeSaveDTO) {
        if (pointExchangeCodeSaveDTO == null) {
            return null;
        }
        PointExchangeCodePO pointExchangeCodePO = new PointExchangeCodePO();
        pointExchangeCodePO.setBatchId(pointExchangeCodeSaveDTO.getBatchId());
        pointExchangeCodePO.setBatchName(pointExchangeCodeSaveDTO.getBatchName());
        pointExchangeCodePO.setPointName(pointExchangeCodeSaveDTO.getPointName());
        pointExchangeCodePO.setBatchDescription(pointExchangeCodeSaveDTO.getBatchDescription());
        pointExchangeCodePO.setProjectId(pointExchangeCodeSaveDTO.getProjectId());
        pointExchangeCodePO.setCityCodes(pointExchangeCodeSaveDTO.getCityCodes());
        pointExchangeCodePO.setProductIds(pointExchangeCodeSaveDTO.getProductIds());
        pointExchangeCodePO.setCode(pointExchangeCodeSaveDTO.getCode());
        pointExchangeCodePO.setKeyt(pointExchangeCodeSaveDTO.getKeyt());
        pointExchangeCodePO.setFaceValue(pointExchangeCodeSaveDTO.getFaceValue());
        pointExchangeCodePO.setExpiryTime(pointExchangeCodeSaveDTO.getExpiryTime());
        pointExchangeCodePO.setPointExpiryTime(pointExchangeCodeSaveDTO.getPointExpiryTime());
        pointExchangeCodePO.setValidityPeriod(pointExchangeCodeSaveDTO.getValidityPeriod());
        pointExchangeCodePO.setExpiryType(pointExchangeCodeSaveDTO.getExpiryType());
        pointExchangeCodePO.setStatus(pointExchangeCodeSaveDTO.getStatus());
        pointExchangeCodePO.setUsed(pointExchangeCodeSaveDTO.getUsed());
        pointExchangeCodePO.setCustomerId(pointExchangeCodeSaveDTO.getCustomerId());
        return pointExchangeCodePO;
    }
}
