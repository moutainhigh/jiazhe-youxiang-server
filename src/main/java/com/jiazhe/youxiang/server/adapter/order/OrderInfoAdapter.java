package com.jiazhe.youxiang.server.adapter.order;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.AppendOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.PlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.UserReservationOrderDTO;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.*;
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
        orderInfoResp.setCustomerCityCode(dto.getCustomerCityCode());
        orderInfoResp.setCustomerCityName(dto.getCustomerCityName());
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
        orderInfoResp.setTotalAmount(dto.getTotalAmount());
        orderInfoResp.setCost(dto.getCost());
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
        orderInfoDTO.setCustomerCityCode(orderInfoPO.getCustomerCityCode());
        orderInfoDTO.setCustomerCityName(orderInfoPO.getCustomerCityName());
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
        orderInfoDTO.setTotalAmount(orderInfoPO.getTotalAmount());
        orderInfoDTO.setCost(orderInfoPO.getCost());
        orderInfoDTO.setComments(orderInfoPO.getComments());
        orderInfoDTO.setType(orderInfoPO.getType());
        orderInfoDTO.setStatus(orderInfoPO.getStatus());
        orderInfoDTO.setAuditReason(orderInfoPO.getAuditReason());
        return orderInfoDTO;
    }

    public static PlaceOrderDTO ReqUserPlaceOrder2DTOPlaceOrder(UserPlaceOrderReq req) {
        if (req == null) {
            return null;
        }
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setCustomerId(req.getCustomerId());
        placeOrderDTO.setProductId(req.getProductId());
        placeOrderDTO.setCustomerCityCode(req.getCustomerCityCode());
        placeOrderDTO.setCount(req.getCount());
        placeOrderDTO.setCustomerAddress(req.getCustomerAddress());
        placeOrderDTO.setCustomerMobile(req.getCustomerMobile());
        placeOrderDTO.setCustomerName(req.getCustomerName());
        placeOrderDTO.setCustomerRemark(req.getCustomerRemark());
        placeOrderDTO.setWorkerName(req.getWorkerName());
        placeOrderDTO.setWorkerMobile(req.getWorkerMobile());
        placeOrderDTO.setRealServiceTime(req.getRealServiceTime());
        placeOrderDTO.setVoucherIds(req.getVoucherIds());
        placeOrderDTO.setRechargeCardIds(req.getRechargeCardIds());
        placeOrderDTO.setCardMoneys(req.getCardMoneys());
        return placeOrderDTO;
    }

    public static PlaceOrderDTO ReqCustomerPlaceOrder2DTOPlaceOrder(CustomerPlaceOrderReq req) {
        if (req == null) {
            return null;
        }
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setCustomerId(req.getCustomerId());
        placeOrderDTO.setProductId(req.getProductId());
        placeOrderDTO.setCustomerCityCode(req.getCustomerCityCode());
        placeOrderDTO.setCount(req.getCount());
        placeOrderDTO.setCustomerAddress(req.getCustomerAddress());
        placeOrderDTO.setCustomerMobile(req.getCustomerMobile());
        placeOrderDTO.setCustomerName(req.getCustomerName());
        placeOrderDTO.setCustomerRemark(req.getCustomerRemark());
        placeOrderDTO.setServiceTime(req.getServiceTime());
        placeOrderDTO.setVoucherIds(req.getVoucherIds());
        placeOrderDTO.setRechargeCardIds(req.getRechargeCardIds());
        placeOrderDTO.setCardMoneys(req.getCardMoneys());
        return placeOrderDTO;
    }

    public static UserReservationOrderDTO ReqUserReservationOrder2DTOUserReservationOrder(UserReservationOrderReq req) {
        if (req == null) {
            return null;
        }
        UserReservationOrderDTO userReservationOrderDTO = new UserReservationOrderDTO();
        userReservationOrderDTO.setOrderId(req.getOrderId());
        userReservationOrderDTO.setWorkerName(req.getWorkerName());
        userReservationOrderDTO.setWorkerMobile(req.getWorkerMobile());
        userReservationOrderDTO.setRealServiceTime(req.getRealServiceTime());
        userReservationOrderDTO.setCost(req.getCost());
        return userReservationOrderDTO;
    }

    public static AppendOrderDTO ReqAppendOrder2DTOAppendOrder(AppendOrderReq req) {
        if (req == null) {
            return null;
        }
        AppendOrderDTO appendOrderDTO = new AppendOrderDTO();
        appendOrderDTO.setOrderId(req.getOrderId());
        appendOrderDTO.setCount(req.getCount());
        appendOrderDTO.setCost(req.getCost());
        appendOrderDTO.setVoucherIds(req.getVoucherIds());
        appendOrderDTO.setRechargeCardIds(req.getRechargeCardIds());
        appendOrderDTO.setCardMoneys(req.getCardMoneys());
        return appendOrderDTO;
    }
}
