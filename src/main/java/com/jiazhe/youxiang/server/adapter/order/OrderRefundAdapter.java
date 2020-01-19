package com.jiazhe.youxiang.server.adapter.order;

import com.jiazhe.youxiang.server.domain.po.OrderRefundPO;
import com.jiazhe.youxiang.server.dto.order.orderrefund.OrderRefundDTO;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class OrderRefundAdapter {
    public static OrderRefundPO DTO2PO(OrderRefundDTO orderRefundDTO) {
        if (orderRefundDTO == null) {
            return null;
        }
        OrderRefundPO orderRefundPO = new OrderRefundPO();
        orderRefundPO.setId(orderRefundDTO.getId());
        orderRefundPO.setOrderCode(orderRefundDTO.getOrderCode());
        orderRefundPO.setOrderId(orderRefundDTO.getOrderId());
        orderRefundPO.setRefundType(orderRefundDTO.getRefundType());
        orderRefundPO.setRechargeCardId(orderRefundDTO.getRechargeCardId());
        orderRefundPO.setPointId(orderRefundDTO.getPointId());
        orderRefundPO.setVoucherId(orderRefundDTO.getVoucherId());
        orderRefundPO.setRefundMoney(orderRefundDTO.getRefundMoney());
        orderRefundPO.setSerialNumber(orderRefundDTO.getSerialNumber());
        return orderRefundPO;
    }

    public static OrderRefundDTO PO2DTO(OrderRefundPO orderRefundPO) {
        if (orderRefundPO == null) {
            return null;
        }
        OrderRefundDTO orderRefundDTO = new OrderRefundDTO();
        orderRefundDTO.setId(orderRefundPO.getId());
        orderRefundDTO.setOrderCode(orderRefundPO.getOrderCode());
        orderRefundDTO.setOrderId(orderRefundPO.getOrderId());
        orderRefundDTO.setRefundType(orderRefundPO.getRefundType());
        orderRefundDTO.setPointId(orderRefundPO.getPointId());
        orderRefundDTO.setRechargeCardId(orderRefundPO.getRechargeCardId());
        orderRefundDTO.setVoucherId(orderRefundPO.getVoucherId());
        orderRefundDTO.setRefundMoney(orderRefundPO.getRefundMoney());
        orderRefundDTO.setSerialNumber(orderRefundPO.getSerialNumber());
        return orderRefundDTO;
    }
}
