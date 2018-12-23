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
}
