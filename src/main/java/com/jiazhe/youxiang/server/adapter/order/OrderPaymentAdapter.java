package com.jiazhe.youxiang.server.adapter.order;

import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.vo.resp.order.orderpayment.OrderPaymentResp;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class OrderPaymentAdapter {

    public static OrderPaymentDTO PO2DTO(OrderPaymentPO orderPaymentPO) {
        if (orderPaymentPO == null) {
            return null;
        }
        OrderPaymentDTO orderPaymentDTO = new OrderPaymentDTO();
        orderPaymentDTO.setId(orderPaymentPO.getId());
        orderPaymentDTO.setOrderCode(orderPaymentPO.getOrderCode());
        orderPaymentDTO.setOrderId(orderPaymentPO.getOrderId());
        orderPaymentDTO.setPayType(orderPaymentPO.getPayType());
        orderPaymentDTO.setPointId(orderPaymentPO.getPointId());
        orderPaymentDTO.setRechargeCardId(orderPaymentPO.getRechargeCardId());
        orderPaymentDTO.setVoucherId(orderPaymentPO.getVoucherId());
        orderPaymentDTO.setPayMoney(orderPaymentPO.getPayMoney());
        orderPaymentDTO.setSerialNumber(orderPaymentPO.getSerialNumber());
        return orderPaymentDTO;
    }

    public static OrderPaymentResp DTO2Resp(OrderPaymentDTO orderPaymentDTO) {
        if (orderPaymentDTO == null) {
            return null;
        }
        OrderPaymentResp orderPaymentResp = new OrderPaymentResp();
        orderPaymentResp.setId(orderPaymentDTO.getId());
        orderPaymentResp.setOrderCode(orderPaymentDTO.getOrderCode());
        orderPaymentResp.setOrderId(orderPaymentDTO.getOrderId());
        orderPaymentResp.setPayType(orderPaymentDTO.getPayType());
        orderPaymentResp.setPointId(orderPaymentDTO.getPointId());
        orderPaymentResp.setRechargeCardId(orderPaymentDTO.getRechargeCardId());
        orderPaymentResp.setVoucherId(orderPaymentDTO.getVoucherId());
        orderPaymentResp.setPayMoney(orderPaymentDTO.getPayMoney());
        orderPaymentResp.setSerialNumber(orderPaymentDTO.getSerialNumber());
        orderPaymentResp.setOrderInfoResp(OrderInfoAdapter.DTO2Resp(orderPaymentDTO.getOrderInfoDTO()));
        return orderPaymentResp;
    }
}
