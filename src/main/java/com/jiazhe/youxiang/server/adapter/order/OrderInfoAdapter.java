package com.jiazhe.youxiang.server.adapter.order;

import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class OrderInfoAdapter {
    public static OrderInfoResp DTO2Resp(OrderInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        OrderInfoResp orderInfoResp = new OrderInfoResp();
        orderInfoResp.setId(dto.getId());
        orderInfoResp.setOrderCode(dto.getOrderCode());
        orderInfoResp.setCustomerId(dto.getCustomerId());
        orderInfoResp.setProductId(dto.getProductId());
        orderInfoResp.setProductPrice(dto.getProductPrice());
        orderInfoResp.setCount(dto.getCount());
        orderInfoResp.setCustomerAddress(dto.getCustomerAddress());
        orderInfoResp.setCustomerMobile(dto.getCustomerMobile());
        orderInfoResp.setCustomerName(dto.getCustomerName());
        orderInfoResp.setCustomerRemark(dto.getCustomerRemark());
        orderInfoResp.setWorkerName(dto.getWorkerName());
        orderInfoResp.setWorkerMobile(dto.getWorkerMobile());
        orderInfoResp.setOrderTime(dto.getOrderTime());
        orderInfoResp.setServiceTime(dto.getServiceTime());
        orderInfoResp.setRealServiceTime(dto.getRealServiceTime());
        orderInfoResp.setPayRechargeCard(dto.getPayRechargeCard());
        orderInfoResp.setPayVoucher(dto.getPayVoucher());
        orderInfoResp.setPayCash(dto.getPayCash());
        orderInfoResp.setTotalCost(dto.getTotalCost());
        orderInfoResp.setComments(dto.getComments());
        orderInfoResp.setType(dto.getType());
        orderInfoResp.setStatus(dto.getStatus());
        return orderInfoResp;
    }
}
