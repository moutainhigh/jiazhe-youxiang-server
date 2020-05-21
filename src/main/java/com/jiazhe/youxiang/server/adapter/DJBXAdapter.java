package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.djbx.DJBXPlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.djbx.PointsQueryDTO;
import com.jiazhe.youxiang.server.vo.req.djbx.DJBXPlaceOrderReq;
import com.jiazhe.youxiang.server.vo.resp.djbx.AgentInfoResp;
import com.jiazhe.youxiang.server.vo.resp.djbx.PointsQueryResp;
import com.jiazhe.youxiang.server.dto.djbx.AgentInfoDTO;

import java.util.Date;

/**
 * @author TU
 * @description 大家保险
 * @date 2020-05-19.
 */
public class DJBXAdapter {

    public static PointsQueryResp PointQueryDTO2Resp(PointsQueryDTO dto) {
        if (dto == null) {
            return null;
        }
        PointsQueryResp pointsQueryResp = new PointsQueryResp();
        pointsQueryResp.setAgentCode(dto.getAgentCode());
        pointsQueryResp.setPoints(dto.getPoints());
        return pointsQueryResp;
    }

    public static DJBXPlaceOrderDTO ReqDJBXPlaceOrder2DTOPlaceOrder(DJBXPlaceOrderReq req) {
        if (req == null) {
            return null;
        }
        DJBXPlaceOrderDTO dJBXPlaceOrderDTO = new DJBXPlaceOrderDTO();
        dJBXPlaceOrderDTO.setAgentCode(req.getAgentCode());
        dJBXPlaceOrderDTO.setVerifiCode(req.getVerifiCode());
        dJBXPlaceOrderDTO.setCustomerId(req.getCustomerId());
        dJBXPlaceOrderDTO.setProductId(req.getProductId());
        dJBXPlaceOrderDTO.setCustomerCityCode(req.getCustomerCityCode());
        dJBXPlaceOrderDTO.setCount(req.getCount());
        dJBXPlaceOrderDTO.setCustomerAddress(req.getCustomerAddress());
        dJBXPlaceOrderDTO.setCustomerMobile(req.getCustomerMobile());
        dJBXPlaceOrderDTO.setCustomerName(req.getCustomerName());
        dJBXPlaceOrderDTO.setCustomerRemark(req.getCustomerRemark());
        dJBXPlaceOrderDTO.setServiceTime(new Date(req.getServiceTime()));
        dJBXPlaceOrderDTO.setCashSupport(req.getCashSupport());
        return dJBXPlaceOrderDTO;
    }

    public static AgentInfoResp agentInfoDTO2Resp(AgentInfoDTO agentInfoDTO) {
        if (agentInfoDTO == null) {
            return null;
        }
        AgentInfoResp agentInfoResp = new AgentInfoResp();
        agentInfoResp.setCustomerId(agentInfoDTO.getCustomerId());
        agentInfoResp.setAgentCode(agentInfoDTO.getAgentCode());
        agentInfoResp.setMobile(agentInfoDTO.getMobile());
        return agentInfoResp;
    }
}
