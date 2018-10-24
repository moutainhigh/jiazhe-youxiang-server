package com.jiazhe.youxiang.server.adapter.order;

import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoListDTO;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoListResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class OrderInfoAdapter {
    public static OrderInfoListResp DTOList2RespList(OrderInfoListDTO orderInfoListDTO) {
        if (orderInfoListDTO == null) {
            return null;
        }
        OrderInfoListResp orderInfoListResp = new OrderInfoListResp();
        orderInfoListResp.setId(orderInfoListDTO.getId());
        orderInfoListResp.setOrderCode(orderInfoListDTO.getOrderCode());
        orderInfoListResp.setCustomerId(orderInfoListDTO.getCustomerId());
        orderInfoListResp.setProductId(orderInfoListDTO.getProductId());
        orderInfoListResp.setProductPrice(orderInfoListDTO.getProductPrice());
        orderInfoListResp.setCount(orderInfoListDTO.getCount());
        orderInfoListResp.setCustomerAddress(orderInfoListDTO.getCustomerAddress());
        orderInfoListResp.setCustomerMobile(orderInfoListDTO.getCustomerMobile());
        orderInfoListResp.setCustomerName(orderInfoListDTO.getCustomerName());
        orderInfoListResp.setCustomerRemark(orderInfoListDTO.getCustomerRemark());
        orderInfoListResp.setWorkerName(orderInfoListDTO.getWorkerName());
        orderInfoListResp.setWorkerMobile(orderInfoListDTO.getWorkerMobile());
        orderInfoListResp.setOrderTime(orderInfoListDTO.getOrderTime());
        orderInfoListResp.setServiceTime(orderInfoListDTO.getServiceTime());
        orderInfoListResp.setRealServiceTime(orderInfoListDTO.getRealServiceTime());
        orderInfoListResp.setPayRechargeCard(orderInfoListDTO.getPayRechargeCard());
        orderInfoListResp.setPayVoucher(orderInfoListDTO.getPayVoucher());
        orderInfoListResp.setPayCash(orderInfoListDTO.getPayCash());
        orderInfoListResp.setTotalCost(orderInfoListDTO.getTotalCost());
        orderInfoListResp.setComments(orderInfoListDTO.getComments());
        orderInfoListResp.setType(orderInfoListDTO.getType());
        orderInfoListResp.setStatus(orderInfoListDTO.getStatus());
        return orderInfoListResp;
    }

    public static OrderInfoResp DTO2Resp(OrderInfoDTO orderInfoDTO) {
        if (orderInfoDTO == null) {
            return null;
        }
        OrderInfoResp orderInfoResp = new OrderInfoResp();
        return orderInfoResp;
    }
}
