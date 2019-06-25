package com.jiazhe.youxiang.server.biz.order;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.AppendOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.PlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.UserReservationOrderDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.vo.Paging;
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

    public List<OrderInfoDTO> getList(String status, String orderCode, String mobile, String customerMobile, Date orderStartTime, Date orderEndTime, String workerMobile, Integer productId, Date realServiceStartTime, Date realServiceEndTime, Paging paging) {
        List<OrderInfoDTO> orderInfoDTOList = orderInfoService.getList(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile, productId, realServiceStartTime, realServiceEndTime, paging);
        orderInfoDTOList.stream().forEach(bean -> {
            //计算待支付金额放入订单信息中
            bean.setPayment(calculateOrderNeedPay(bean));
        });
        return orderInfoDTOList;
    }

    public void customerCancelOrder(Integer id) {
        orderInfoService.customerCancelOrder(id);
    }

    public void orderCancelPass(Integer id) {
        orderInfoService.orderCancelPass(id);
    }

    public void orderCancelUnpass(Integer id, String auditReason) {
        orderInfoService.orderCancelUnpass(id, auditReason);
    }

    public void userCancelOrder(Integer id) {
        orderInfoService.userCancelOrder(id);

    }

    public BigDecimal customerNeedPayCash(Integer id) {
        OrderInfoDTO dto = orderInfoService.getById(id);
        if (!dto.getStatus().equals(CommonConstant.ORDER_UNPAID)) {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_CALCULATE_NEED_PAY);
        }
        return calculateOrderNeedPay(dto);
    }

    /**
     * 支付订单，并返回订单待支付金额
     *
     * @param orderId
     * @param payCash
     * @param serialNumber
     * @return
     */
    public BigDecimal customerPay(Integer orderId, BigDecimal payCash, String serialNumber) {
        return orderInfoService.customerPay(orderId, payCash, serialNumber);
    }

    public void userCompleteOrder(Integer id) {
        orderInfoService.userCompleteOrder(id);
    }

    public void userReservationOrder(UserReservationOrderDTO dto) {
        orderInfoService.userReservationOrder(dto);
    }

    public OrderInfoDTO getById(Integer id) {
        return orderInfoService.getById(id);
    }

    public Integer getUnsentOrderCount() {
        return orderInfoService.getCountByStatus(CommonConstant.ORDER_UNSENT);
    }

    public Integer getUnauditOrderCount() {
        return orderInfoService.getCountByStatus(CommonConstant.ORDER_CANCELWATINGCHECK);
    }

    public List<OrderInfoDTO> getOrderByRCId(Integer id) {
        return null;
    }

    public List<OrderInfoDTO> customerGetList(Integer customerId, String status, Paging paging) {
        CustomerDTO customerDTO = customerService.getById(customerId);
        if (null == customerDTO) {
            throw new OrderException(OrderCodeEnum.CUSTOMER_NOT_EXIST);
        }
        return getList(status, null, customerDTO.getMobile(), null, null, null, null,null,null,null, paging);
    }

    /**
     * 计算订单待支付金额
     *
     * @param dto
     * @return
     */
    private BigDecimal calculateOrderNeedPay(OrderInfoDTO dto) {
        Integer needPayCount = dto.getCount();
        BigDecimal needPay = dto.getProductPrice().multiply(new BigDecimal(needPayCount));
        BigDecimal hasPay = dto.getPayPoint().add(dto.getPayRechargeCard()).add(dto.getPayVoucher().add(dto.getPayCash()));
        BigDecimal left = needPay.subtract(hasPay);
        return left;
    }

    public NeedPayResp userPlaceOrder(PlaceOrderDTO placeOrderDTO) {
        return orderInfoService.placeOrder(placeOrderDTO);
    }

    public void appendOrder(AppendOrderDTO appendOrderDTO) {
        orderInfoService.appendOrder(appendOrderDTO);
    }

    public void prePaymentCheck(Integer id) {
        orderInfoService.prePaymentCheck(id);
    }

    public void userChangeReservationInfo(UserReservationOrderDTO userReservationOrderDTO) {
        orderInfoService.userChangeReservationInfo(userReservationOrderDTO);
    }

    public NeedPayResp customerPlaceOrder(PlaceOrderDTO placeOrderDTO) {
        return orderInfoService.placeOrder(placeOrderDTO);
    }

    public OrderInfoDTO getByOrderNo(String orderNo) {
        return orderInfoService.getByOrderNo(orderNo);
    }

    public List<OrderInfoDTO> getOrderByPointId(Integer id) {
        return null;
    }

    /**
     * 微信付款成功通知
     * @param transactionId 微信支付订单号
     * @param orderNo 商户订单号
     * @param wxPay
     */
    public void wxNotify(String transactionId,String orderNo, Integer wxPay) {
        orderInfoService.wxNotify(transactionId,orderNo, wxPay);
    }

    public List<OrderInfoDTO> getList(String status, String orderCode, String mobile, String customerMobile, Date orderStartTime, Date orderEndTime, String workerMobile, Integer productId, Date realServiceStartTime, Date realServiceEndTime) {
        return orderInfoService.getList(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile, productId, realServiceStartTime, realServiceEndTime);
    }
}
