package com.jiazhe.youxiang.server.adapter.point;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.adapter.ProjectAdapter;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.vo.req.point.exchangecode.PointExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.resp.point.exchangecode.PointExchangeCodeResp;

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
        pointExchangeCodeDTO.setPointEffectiveTime(pointExchangeCodePO.getPointEffectiveTime());
        pointExchangeCodeDTO.setPointExpiryTime(pointExchangeCodePO.getPointExpiryTime());
        pointExchangeCodeDTO.setValidityPeriod(pointExchangeCodePO.getValidityPeriod());
        pointExchangeCodeDTO.setExpiryType(pointExchangeCodePO.getExpiryType());
        pointExchangeCodeDTO.setStatus(pointExchangeCodePO.getStatus());
        pointExchangeCodeDTO.setUsed(pointExchangeCodePO.getUsed());
        pointExchangeCodeDTO.setOutOrderCode(pointExchangeCodePO.getOutOrderCode());
        pointExchangeCodeDTO.setCustomerId(pointExchangeCodePO.getCustomerId());
        return pointExchangeCodeDTO;
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
        pointExchangeCodePO.setPointEffectiveTime(pointExchangeCodeSaveDTO.getPointEffectiveTime());
        pointExchangeCodePO.setPointExpiryTime(pointExchangeCodeSaveDTO.getPointExpiryTime());
        pointExchangeCodePO.setValidityPeriod(pointExchangeCodeSaveDTO.getValidityPeriod());
        pointExchangeCodePO.setExpiryType(pointExchangeCodeSaveDTO.getExpiryType());
        pointExchangeCodePO.setStatus(pointExchangeCodeSaveDTO.getStatus());
        pointExchangeCodePO.setUsed(pointExchangeCodeSaveDTO.getUsed());
        pointExchangeCodePO.setCustomerId(pointExchangeCodeSaveDTO.getCustomerId());
        return pointExchangeCodePO;
    }

    public static PointExchangeCodeResp dto2Resp(PointExchangeCodeDTO pointExchangeCodeDTO) {
        if (pointExchangeCodeDTO == null) {
            return null;
        }
        PointExchangeCodeResp pointExchangeCodeResp = new PointExchangeCodeResp();
        pointExchangeCodeResp.setId(pointExchangeCodeDTO.getId());
        pointExchangeCodeResp.setBatchName(pointExchangeCodeDTO.getBatchName());
        pointExchangeCodeResp.setPointName(pointExchangeCodeDTO.getPointName());
        pointExchangeCodeResp.setBatchDescription(pointExchangeCodeDTO.getBatchDescription());
        pointExchangeCodeResp.setCityCodes(pointExchangeCodeDTO.getCityCodes());
        pointExchangeCodeResp.setProductIds(pointExchangeCodeDTO.getProductIds());
        pointExchangeCodeResp.setCode(pointExchangeCodeDTO.getCode());
        pointExchangeCodeResp.setKeyt(pointExchangeCodeDTO.getKeyt());
        pointExchangeCodeResp.setPointExpiryTime(pointExchangeCodeDTO.getPointExpiryTime().getTime());
        pointExchangeCodeResp.setValidityPeriod(pointExchangeCodeDTO.getValidityPeriod());
        pointExchangeCodeResp.setExpiryType(pointExchangeCodeDTO.getExpiryType());
        pointExchangeCodeResp.setFaceValue(pointExchangeCodeDTO.getFaceValue());
        pointExchangeCodeResp.setExpiryTime(pointExchangeCodeDTO.getExpiryTime().getTime());
        pointExchangeCodeResp.setPointEffectiveTime(pointExchangeCodeDTO.getPointEffectiveTime().getTime());
        pointExchangeCodeResp.setStatus(pointExchangeCodeDTO.getStatus());
        pointExchangeCodeResp.setUsed(pointExchangeCodeDTO.getUsed());
        pointExchangeCodeResp.setOutOrderCode(pointExchangeCodeDTO.getOutOrderCode());
        pointExchangeCodeResp.setProjectResp(ProjectAdapter.projectDTO2VO(pointExchangeCodeDTO.getProjectDTO()));
        pointExchangeCodeResp.setCustomerResp(CustomerAdapter.customerDTO2VO(pointExchangeCodeDTO.getCustomerDTO()));
        return pointExchangeCodeResp;
    }

    public static PointExchangeCodeEditDTO editReq2EditDto(PointExchangeCodeEditReq req) {
        if (req == null) {
            return null;
        }
        PointExchangeCodeEditDTO pointExchangeCodeEditDTO = new PointExchangeCodeEditDTO();
        pointExchangeCodeEditDTO.setId(req.getId());
        pointExchangeCodeEditDTO.setPointName(req.getPointName());
        pointExchangeCodeEditDTO.setBatchDescription(req.getBatchDescription());
        pointExchangeCodeEditDTO.setCityCodes(req.getCityCodes());
        pointExchangeCodeEditDTO.setProductIds(req.getProductIds());
        pointExchangeCodeEditDTO.setExpiryTime(new Date(req.getExpiryTime()));
        pointExchangeCodeEditDTO.setPointEffectiveTime(new Date(req.getPointEffectiveTime()));
        pointExchangeCodeEditDTO.setPointExpiryTime(new Date(req.getPointExpiryTime()));
        pointExchangeCodeEditDTO.setValidityPeriod(req.getValidityPeriod());
        pointExchangeCodeEditDTO.setExpiryType(req.getExpiryType());
        return pointExchangeCodeEditDTO;
    }

    public static PointExchangeCodePO dto2Po(PointExchangeCodeDTO pointExchangeCodeDTO) {
        if (pointExchangeCodeDTO == null) {
            return null;
        }
        PointExchangeCodePO pointExchangeCodePO = new PointExchangeCodePO();
        pointExchangeCodePO.setId(pointExchangeCodeDTO.getId());
        pointExchangeCodePO.setBatchId(pointExchangeCodeDTO.getBatchId());
        pointExchangeCodePO.setBatchName(pointExchangeCodeDTO.getBatchName());
        pointExchangeCodePO.setPointName(pointExchangeCodeDTO.getPointName());
        pointExchangeCodePO.setBatchDescription(pointExchangeCodeDTO.getBatchDescription());
        pointExchangeCodePO.setProjectId(pointExchangeCodeDTO.getProjectId());
        pointExchangeCodePO.setCityCodes(pointExchangeCodeDTO.getCityCodes());
        pointExchangeCodePO.setProductIds(pointExchangeCodeDTO.getProductIds());
        pointExchangeCodePO.setCode(pointExchangeCodeDTO.getCode());
        pointExchangeCodePO.setKeyt(pointExchangeCodeDTO.getKeyt());
        pointExchangeCodePO.setPointEffectiveTime(pointExchangeCodeDTO.getPointEffectiveTime());
        pointExchangeCodePO.setPointExpiryTime(pointExchangeCodeDTO.getPointExpiryTime());
        pointExchangeCodePO.setValidityPeriod(pointExchangeCodeDTO.getValidityPeriod());
        pointExchangeCodePO.setExpiryType(pointExchangeCodeDTO.getExpiryType());
        pointExchangeCodePO.setFaceValue(pointExchangeCodeDTO.getFaceValue());
        pointExchangeCodePO.setExpiryTime(pointExchangeCodeDTO.getExpiryTime());
        pointExchangeCodePO.setStatus(pointExchangeCodeDTO.getStatus());
        pointExchangeCodePO.setUsed(pointExchangeCodeDTO.getUsed());
        pointExchangeCodePO.setCustomerId(pointExchangeCodeDTO.getCustomerId());
        return pointExchangeCodePO;
    }
}
