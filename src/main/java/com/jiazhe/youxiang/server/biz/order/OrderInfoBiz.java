package com.jiazhe.youxiang.server.biz.order;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.CustomerPlaceOrderReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.UserPlaceOrderReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.UserReservationOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("orderInfoBiz")
public class OrderInfoBiz {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private CustomerService customerService;

    public List<OrderInfoDTO> getList(Byte status, String orderCode, String mobile,String customerMobile, Date orderStartTime, Date orderEndTime,String worekerMobile, Paging paging) {
        return orderInfoService.getList(status,orderCode,mobile,customerMobile,orderStartTime,orderEndTime,worekerMobile,paging);
    }

    public void customerCancelOrder(Integer id) {
        orderInfoService.customerCancelOrder(id);
    }

    public void orderCancelPass(Integer id) {

    }

    public void orderCancelUnpass(Integer id) {

    }

    public void userCancelOrder(Integer id) {

    }

    public void userChargeAdditional(Integer orderId, BigDecimal additionalPay) {

    }

    public BigDecimal customerNeedPayCash(Integer id) {
        OrderInfoDTO dto = orderInfoService.getById(id);
        if(!dto.getStatus().equals(CommonConstant.ORDER_UNPAID)){
            throw new OrderException(OrderCodeEnum.ORDER_CAN_CALCULATE_NEED_PAY);
        }
        return calculateOrderNeedPay(dto);
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
        return orderInfoService.getById(id);
    }

    public Integer getUnsentOrderCount() {
        return 0;
    }

    public Integer getUnauditOrderCount() {
        return 0;
    }

    public List<OrderInfoDTO> getOrderByRCId(Integer id) {
        return null;
    }

    public List<OrderInfoDTO> custoemrGetList(Integer customerId, Byte status, Paging paging) {
        CustomerDTO customerDTO = customerService.getById(customerId);
        return getList(status,null,customerDTO.getMobile(),null,null,null,null,paging);
    }

    private BigDecimal calculateOrderNeedPay(OrderInfoDTO dto){
        Integer needPayCount = dto.getCount() - dto.getPayVoucher();
        BigDecimal needPayMoney = dto.getProductPrice().multiply(new BigDecimal(needPayCount));
        return needPayMoney.subtract(dto.getPayRechargeCard().add(dto.getPayCash()));
    }
}
