package com.jiazhe.youxiang.server.adapter.point;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.domain.po.PointPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
import com.jiazhe.youxiang.server.vo.req.point.point.PointEditReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.RCEditReq;
import com.jiazhe.youxiang.server.vo.resp.point.point.PointResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rc.RCResp;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public class PointAdapter {

    public static PointResp dto2Resp(PointDTO pointDTO) {
        if (pointDTO == null) {
            return null;
        }
        PointResp pointResp = new PointResp();
        pointResp.setId(pointDTO.getId());
        pointResp.setName(pointDTO.getName());
        pointResp.setDescription(pointDTO.getDescription());
        pointResp.setCustomerId(pointDTO.getCustomerId());
        pointResp.setProjectId(pointDTO.getProjectId());
        pointResp.setCityCodes(pointDTO.getCityCodes());
        pointResp.setProductIds(pointDTO.getProductIds());
        pointResp.setFaceValue(pointDTO.getFaceValue());
        pointResp.setBalance(pointDTO.getBalance());
        pointResp.setExpiryTime(pointDTO.getExpiryTime().getTime());
        pointResp.setStatus(pointDTO.getStatus());
        pointResp.setAddTime(pointDTO.getAddTime().getTime());
        pointResp.setCustomerResp(CustomerAdapter.customerDTO2VO(pointDTO.getCustomerDTO()));
        pointResp.setPointExchangeRecordResp(PointExchangeRecordAdapter.dto2Resp(pointDTO.getPointExchangeRecordDTO()));
        return pointResp;
    }

    public static PointDTO po2Dto(PointPO pointPO) {
        if (pointPO == null) {
            return null;
        }
        PointDTO pointDTO = new PointDTO();
        pointDTO.setId(pointPO.getId());
        pointDTO.setName(pointPO.getName());
        pointDTO.setDescription(pointPO.getDescription());
        pointDTO.setCustomerId(pointPO.getCustomerId());
        pointDTO.setProjectId(pointPO.getProjectId());
        pointDTO.setCityCodes(pointPO.getCityCodes());
        pointDTO.setProductIds(pointPO.getProductIds());
        pointDTO.setFaceValue(pointPO.getFaceValue());
        pointDTO.setBalance(pointPO.getBalance());
        pointDTO.setExpiryTime(pointPO.getExpiryTime());
        pointDTO.setStatus(pointPO.getStatus());
        pointDTO.setAddTime(pointPO.getAddTime());
        return pointDTO;
    }

    public static PointResp Dto2Resp(PointDTO pointDTO) {
        if (pointDTO == null) {
            return null;
        }
        PointResp pointResp = new PointResp();
        pointResp.setId(pointDTO.getId());
        pointResp.setName(pointDTO.getName());
        pointResp.setDescription(pointDTO.getDescription());
        pointResp.setCustomerId(pointDTO.getCustomerId());
        pointResp.setProjectId(pointDTO.getProjectId());
        pointResp.setCityCodes(pointDTO.getCityCodes());
        pointResp.setProductIds(pointDTO.getProductIds());
        pointResp.setFaceValue(pointDTO.getFaceValue());
        pointResp.setBalance(pointDTO.getBalance());
        pointResp.setExpiryTime(pointDTO.getExpiryTime().getTime());
        pointResp.setStatus(pointDTO.getStatus());
        pointResp.setAddTime(pointDTO.getAddTime().getTime());
        pointResp.setCustomerResp(CustomerAdapter.customerDTO2VO(pointDTO.getCustomerDTO()));
        pointResp.setPointExchangeRecordResp(PointExchangeRecordAdapter.dto2Resp(pointDTO.getPointExchangeRecordDTO()));
        return pointResp;
    }

    public static PointEditDTO editReq2EditDTO(PointEditReq req) {
        if (req == null) {
            return null;
        }
        PointEditDTO pointEditDTO = new PointEditDTO();
        pointEditDTO.setId(req.getId());
        pointEditDTO.setCityCodes(req.getCityCodes());
        pointEditDTO.setProductIds(req.getProductIds());
        pointEditDTO.setName(req.getName());
        pointEditDTO.setDescription(req.getDescription());
        pointEditDTO.setExpiryTime(new Date(req.getExpiryTime()));
        return pointEditDTO;
    }
}
