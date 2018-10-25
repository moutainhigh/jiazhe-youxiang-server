package com.jiazhe.youxiang.server.biz.order;

import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.CustomerPlaceOrderReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.UserPlaceOrderReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.UserReservationOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("orderInfoBiz")
public class OrderInfoBiz {

    public List<OrderInfoDTO> getList(String mobile, Byte status, Paging paging) {
        return null ;
    }

    public int customerCancelOrder(Integer id) {
        return 0;
    }

    public int orderCancelPass(Integer id) {
        return 0;
    }

    public int orderCancelUnpass(Integer id) {
        return 0;
    }

    public int userCancelOrder(Integer id) {
        return 0;
    }

    public int userChargeAdditional(Integer orderId, BigDecimal additionalPay) {
        return 0;
    }

    public NeedPayResp customerLaunchPay(Integer id) {
        return null;
    }

    public int customerPay(Integer orderId,BigDecimal payCash,BigDecimal payRechargeCard) {
        return 1;
    }

    public int userCompleteOrder(Integer orderId) {
        return 0;
    }

    public int customerPlaceOrder(CustomerPlaceOrderReq req) {
        return 0;
    }

    public int userPlaceOrder(UserPlaceOrderReq req) {
        return 0;
    }

    public int userReservationOrder(UserReservationOrderReq req) {
        return 0;
    }

    public OrderInfoDTO getById(Integer id) {
        return null;
    }

    public Integer getUnsentOrderCount() {
        return 0;
    }

    public Integer getUnauditOrderCount() {
        return 0;
    }
}
