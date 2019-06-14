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

import java.util.Date;

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
        orderInfoResp.setOrderTime(dto.getOrderTime().getTime());
        orderInfoResp.setServiceTime(dto.getServiceTime().getTime());
        orderInfoResp.setRealServiceTime(dto.getRealServiceTime().getTime());
        orderInfoResp.setPayRechargeCard(dto.getPayRechargeCard());
        orderInfoResp.setPayPoint(dto.getPayPoint());
        orderInfoResp.setPayVoucher(dto.getPayVoucher());
        orderInfoResp.setPayCash(dto.getPayCash());
        orderInfoResp.setPayment(dto.getPayment());
        orderInfoResp.setTotalAmount(dto.getTotalAmount());
        orderInfoResp.setCost(dto.getCost());
        orderInfoResp.setComments(dto.getComments());
        orderInfoResp.setType(dto.getType());
        orderInfoResp.setStatus(dto.getStatus());
        orderInfoResp.setAuditReason(dto.getAuditReason());
        orderInfoResp.setExtInfo(dto.getExtInfo());
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
        orderInfoDTO.setPayPoint(orderInfoPO.getPayPoint());
        orderInfoDTO.setPayRechargeCard(orderInfoPO.getPayRechargeCard());
        orderInfoDTO.setPayVoucher(orderInfoPO.getPayVoucher());
        orderInfoDTO.setPayCash(orderInfoPO.getPayCash());
        orderInfoDTO.setTotalAmount(orderInfoPO.getTotalAmount());
        orderInfoDTO.setCost(orderInfoPO.getCost());
        orderInfoDTO.setComments(orderInfoPO.getComments());
        orderInfoDTO.setType(orderInfoPO.getType());
        orderInfoDTO.setStatus(orderInfoPO.getStatus());
        orderInfoDTO.setAuditReason(orderInfoPO.getAuditReason());
        orderInfoDTO.setExtInfo(orderInfoPO.getExtInfo());
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
        placeOrderDTO.setRealServiceTime(new Date(req.getRealServiceTime()));
        placeOrderDTO.setPointIds(req.getPointIds());
        placeOrderDTO.setVoucherIds(req.getVoucherIds());
        placeOrderDTO.setRechargeCardIds(req.getRechargeCardIds());
        placeOrderDTO.setCost(req.getCost());
        placeOrderDTO.setComments(req.getComments());
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
        placeOrderDTO.setServiceTime(new Date(req.getServiceTime()));
        placeOrderDTO.setPointIds(req.getPointIds());
        placeOrderDTO.setVoucherIds(req.getVoucherIds());
        placeOrderDTO.setRechargeCardIds(req.getRechargeCardIds());
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
        userReservationOrderDTO.setRealServiceTime(new Date(req.getRealServiceTime()));
        userReservationOrderDTO.setCost(req.getCost());
        userReservationOrderDTO.setComments(req.getComments());
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
        appendOrderDTO.setPointIds(req.getPointIds());
        appendOrderDTO.setVoucherIds(req.getVoucherIds());
        appendOrderDTO.setRechargeCardIds(req.getRechargeCardIds());
        appendOrderDTO.setComments(req.getComments());
        return appendOrderDTO;
    }

    public static OrderInfoPO dto2Po(OrderInfoDTO orderInfoDTO) {
        if (orderInfoDTO == null) {
            return null;
        }
        OrderInfoPO orderInfoPO = new OrderInfoPO();
        orderInfoPO.setId(orderInfoDTO.getId());
        orderInfoPO.setOrderCode(orderInfoDTO.getOrderCode());
        orderInfoPO.setCustomerId(orderInfoDTO.getCustomerId());
        orderInfoPO.setCustomerCityCode(orderInfoDTO.getCustomerCityCode());
        orderInfoPO.setCustomerCityName(orderInfoDTO.getCustomerCityName());
        orderInfoPO.setProductId(orderInfoDTO.getProductId());
        orderInfoPO.setProductPrice(orderInfoDTO.getProductPrice());
        orderInfoPO.setCount(orderInfoDTO.getCount());
        orderInfoPO.setCustomerAddress(orderInfoDTO.getCustomerAddress());
        orderInfoPO.setCustomerMobile(orderInfoDTO.getCustomerMobile());
        orderInfoPO.setCustomerName(orderInfoDTO.getCustomerName());
        orderInfoPO.setCustomerRemark(orderInfoDTO.getCustomerRemark());
        orderInfoPO.setWorkerName(orderInfoDTO.getWorkerName());
        orderInfoPO.setWorkerMobile(orderInfoDTO.getWorkerMobile());
        orderInfoPO.setOrderTime(orderInfoDTO.getOrderTime());
        orderInfoPO.setServiceTime(orderInfoDTO.getServiceTime());
        orderInfoPO.setRealServiceTime(orderInfoDTO.getRealServiceTime());
        orderInfoPO.setPayPoint(orderInfoDTO.getPayPoint());
        orderInfoPO.setPayRechargeCard(orderInfoDTO.getPayRechargeCard());
        orderInfoPO.setPayVoucher(orderInfoDTO.getPayVoucher());
        orderInfoPO.setPayCash(orderInfoDTO.getPayCash());
        orderInfoPO.setCost(orderInfoDTO.getCost());
        orderInfoPO.setTotalAmount(orderInfoDTO.getTotalAmount());
        orderInfoPO.setComments(orderInfoDTO.getComments());
        orderInfoPO.setType(orderInfoDTO.getType());
        orderInfoPO.setStatus(orderInfoDTO.getStatus());
        orderInfoPO.setAuditReason(orderInfoDTO.getAuditReason());
        orderInfoPO.setExtInfo(orderInfoDTO.getExtInfo());
        return orderInfoPO;
    }
}
