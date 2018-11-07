package com.jiazhe.youxiang.server.adapter.order;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
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
        orderInfoResp.setAuditReason(dto.getAuditReason());
        orderInfoResp.setProductResp(ProductAdapter.productDTO2VO(dto.getProductDTO()));
        orderInfoResp.setCustomerResp(CustomerAdapter.customerDTO2VO(dto.getCustomerDTO()));
        return orderInfoResp;
    }

    public static OrderInfoDTO PO2DTO(OrderInfoPO orderInfoPO) {
        if (orderInfoPO == null) {
            return null;
        }
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setId(orderInfoPO.getId());
        orderInfoDTO.setOrderCode(orderInfoPO.getOrderCode());
        orderInfoDTO.setCustomerId(orderInfoPO.getCustomerId());
        orderInfoDTO.setProductId(orderInfoPO.getProductId());
        orderInfoDTO.setProductPrice(orderInfoPO.getProductPrice());
        orderInfoDTO.setCount(orderInfoPO.getCount());
        orderInfoDTO.setCustomerAddress(orderInfoPO.getCustomerAddress());
        orderInfoDTO.setCustomerMobile(orderInfoPO.getCustomerMobile());
        orderInfoDTO.setCustomerName(orderInfoPO.getCustomerName());
        orderInfoDTO.setCustomerRemark(orderInfoPO.getCustomerRemark());
        orderInfoDTO.setWorkerName(orderInfoPO.getWorkerName());
        orderInfoDTO.setWorkerMobile(orderInfoPO.getWorkerMobile());
        orderInfoDTO.setOrderTime(orderInfoPO.getOrderTime());
        orderInfoDTO.setServiceTime(orderInfoPO.getServiceTime());
        orderInfoDTO.setRealServiceTime(orderInfoPO.getRealServiceTime());
        orderInfoDTO.setPayRechargeCard(orderInfoPO.getPayRechargeCard());
        orderInfoDTO.setPayVoucher(orderInfoPO.getPayVoucher());
        orderInfoDTO.setPayCash(orderInfoPO.getPayCash());
        orderInfoDTO.setTotalCost(orderInfoPO.getTotalCost());
        orderInfoDTO.setComments(orderInfoPO.getComments());
        orderInfoDTO.setType(orderInfoPO.getType());
        orderInfoDTO.setStatus(orderInfoPO.getStatus());
        orderInfoDTO.setAuditReason(orderInfoPO.getAuditReason());
        return orderInfoDTO;
    }
}
