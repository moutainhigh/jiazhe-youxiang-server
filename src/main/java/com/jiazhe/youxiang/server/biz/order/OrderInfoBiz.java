package com.jiazhe.youxiang.server.biz.order;

import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.base.util.WeChatPayUtils;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.WeChatPayConstant;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.enums.OrderOpreationTypeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.*;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("orderInfoBiz")
@Transactional
public class OrderInfoBiz {

    private static String SUCCESS = "SUCCESS";

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderInfoBiz.class);

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderTrackBiz orderTrackBiz;

    @Value("${wechat_public.appid}")
    private String APP_ID;
    @Value("${wechat_public.mchid}")
    private String MCH_ID;

    public List<OrderInfoDTO> getList(String status, String orderCode, String mobile, String customerMobile, Date orderStartTime, Date orderEndTime, String workerMobile, Integer productId, Integer serviceProductId, Date realServiceStartTime, Date realServiceEndTime, String customerCityCode, Paging paging) {
        List<OrderInfoDTO> orderInfoDTOList = orderInfoService.getList(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile, productId, serviceProductId, realServiceStartTime, realServiceEndTime, customerCityCode, paging);
        orderInfoDTOList.stream().forEach(bean -> {
            //计算待支付金额放入订单信息中
            bean.setPayment(calculateOrderNeedPay(bean));
        });
        return orderInfoDTOList;
    }

    public void customerCancelOrder(Integer id) {
        orderInfoService.customerCancelOrder(id);
    }

    public void orderCancelPass(OrderInfoDTO orderInfoDTO) {
        Integer orderId = orderInfoDTO.getId();
        OrderInfoDTO oldOrderInfo = orderInfoService.getById(orderId);
        StringBuilder sb = new StringBuilder();
        sb.append(OrderTrackBiz.parseOrderTrackInfo("订单成本", oldOrderInfo.getCost(), orderInfoDTO.getCost()));
        orderInfoService.orderCancelPass(orderInfoDTO);
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setOrderid(orderInfoDTO.getId());
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.PASS);
        orderTrackDTO.setMsg(sb.toString());
        orderTrackBiz.create(orderTrackDTO);
    }

    public void orderCancelUnpass(Integer id, String auditReason) {
        orderInfoService.orderCancelUnpass(id, auditReason);
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setOrderid(id);
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.UNPASS);
        orderTrackBiz.create(orderTrackDTO);
    }

    public void userCancelOrder(OrderInfoDTO orderInfoDTO) {
        Integer orderId = orderInfoDTO.getId();
        OrderInfoDTO oldOrderInfo = orderInfoService.getById(orderId);
        StringBuilder sb = new StringBuilder();
        sb.append(OrderTrackBiz.parseOrderTrackInfo("订单成本", oldOrderInfo.getCost(), orderInfoDTO.getCost()));
        orderInfoService.userCancelOrder(orderInfoDTO);
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setOrderid(orderInfoDTO.getId());
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.CANCEL);
        orderTrackDTO.setMsg(sb.toString());
        orderTrackBiz.create(orderTrackDTO);
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
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setOrderid(id);
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.COMPLETE);
        orderTrackBiz.create(orderTrackDTO);
    }

    public void userReservationOrder(UserReservationOrderDTO dto) {
        Integer orderId = dto.getOrderId();
        OrderInfoDTO oldOrderInfo = orderInfoService.getById(orderId);
        StringBuilder sb = new StringBuilder();
        sb.append(OrderTrackBiz.parseOrderTrackInfo("服务人员姓名", oldOrderInfo.getWorkerName(), dto.getWorkerName()));
        sb.append(OrderTrackBiz.parseOrderTrackInfo("服务人员电话", oldOrderInfo.getWorkerMobile(), dto.getWorkerMobile()));
        sb.append(OrderTrackBiz.parseOrderTrackInfo("服务时间", oldOrderInfo.getRealServiceTime(), dto.getRealServiceTime()));
        sb.append(OrderTrackBiz.parseOrderTrackInfo("订单成本", oldOrderInfo.getCost(), dto.getCost()));
        sb.append(OrderTrackBiz.parseOrderTrackInfo("订单备注", oldOrderInfo.getWorkerName(), dto.getWorkerName()));
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setOrderid(orderId);
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.RESERVATION);
        orderTrackDTO.setMsg(sb.toString());
        orderInfoService.userReservationOrder(dto);
        orderTrackBiz.create(orderTrackDTO);
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
        return getList(status, null, customerDTO.getMobile(), null, null, null, null, null, null, null, null, null, paging);
    }

    /**
     * 计算订单待支付金额
     *
     * @param dto
     * @return
     */
    private BigDecimal calculateOrderNeedPay(OrderInfoDTO dto) {
        return orderInfoService.calculateORderNeedPay(dto);
    }

    public NeedPayResp userPlaceOrder(PlaceOrderDTO placeOrderDTO) {
        NeedPayResp needPayResp = orderInfoService.placeOrder(placeOrderDTO);
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setOrderid(needPayResp.getOrderId());
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.CREATE);
        orderTrackBiz.create(orderTrackDTO);
        return needPayResp;
    }

    public void appendOrder(AppendOrderDTO appendOrderDTO) {
        orderInfoService.appendOrder(appendOrderDTO);
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setOrderid(appendOrderDTO.getOrderId());
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.APPEND);
        orderTrackBiz.create(orderTrackDTO);
    }

    public void prePaymentCheck(Integer id) {
        orderInfoService.prePaymentCheck(id);
    }

    public void userChangeReservationInfo(UserReservationOrderDTO userReservationOrderDTO) {
        Integer orderId = userReservationOrderDTO.getOrderId();
        OrderInfoDTO oldOrderInfo = orderInfoService.getById(orderId);
        StringBuilder sb = new StringBuilder();
        sb.append(OrderTrackBiz.parseOrderTrackInfo("服务人员姓名", oldOrderInfo.getWorkerName(), userReservationOrderDTO.getWorkerName()));
        sb.append(OrderTrackBiz.parseOrderTrackInfo("服务人员电话", oldOrderInfo.getWorkerMobile(), userReservationOrderDTO.getWorkerMobile()));
        sb.append(OrderTrackBiz.parseOrderTrackInfo("服务时间", oldOrderInfo.getRealServiceTime(), userReservationOrderDTO.getRealServiceTime()));
        sb.append(OrderTrackBiz.parseOrderTrackInfo("订单成本", oldOrderInfo.getCost(), userReservationOrderDTO.getCost()));
        sb.append(OrderTrackBiz.parseOrderTrackInfo("订单备注", oldOrderInfo.getWorkerName(), userReservationOrderDTO.getWorkerName()));
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setOrderid(orderId);
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.UPDATE);
        orderTrackDTO.setMsg(sb.toString());
        orderInfoService.userChangeReservationInfo(userReservationOrderDTO);
        orderTrackBiz.create(orderTrackDTO);
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
     *
     * @param transactionId 微信支付订单号
     * @param orderNo       商户订单号
     * @param wxPay
     */
    public void wxNotify(String transactionId, String orderNo, Integer wxPay) {
        orderInfoService.wxNotify(transactionId, orderNo, wxPay);
    }

    public List<OrderInfoDTO> getList(String status, String orderCode, String mobile, String customerMobile, Date orderStartTime, Date orderEndTime, String workerMobile, Integer productId, Integer serviceProductId, Date realServiceStartTime, Date realServiceEndTime, String customerCityCode) {
        return orderInfoService.getList(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile, productId, serviceProductId, realServiceStartTime, realServiceEndTime, customerCityCode);
    }

    public TenpayQureyDTO checkTenPay(String orderCode) {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", APP_ID);
        param.put("mch_id", MCH_ID);
        String nonceStr = RandomUtil.generateCode(WeChatPayConstant.NONCE_STR_LENGTH);
        param.put("nonce_str", nonceStr);
        param.put("out_trade_no", orderCode);
        String sign = WeChatPayUtils.createSign("UTF-8", param, WeChatPayConstant.API_KEY);
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.ORDER_QUERY_URL, "POST", requestXml);
        System.out.println(result);
        TenpayQureyDTO dto = new TenpayQureyDTO();
        dto.setTradeState("FAIL");
        try {
            Map<String, String> map = WeChatPayUtils.doXMLParse(result);
            String returnCode = map.get("return_code");
            if (!returnCode.equals(SUCCESS)) {
                dto.setReason(map.get("return_msg"));
                logger.info("微信支付订单查询失败，原因：" + map.get("return_msg"));
                return dto;
            }
            String resultCode = map.get("result_code");
            if (!resultCode.equals(SUCCESS)) {
                logger.info("微信支付订单查询失败，原因：" + map.get("err_code_des"));
                dto.setReason(map.get("err_code_des"));
                return dto;
            }
            String tradeState = map.get("trade_state");
            if (tradeState.equals(SUCCESS)) {
                dto.setTradeState("SUCCESS");
                dto.setTotalFee(new Integer(map.get("total_fee")));
                dto.setTransactionId(map.get("transaction_id"));
                wxNotify(dto.getTransactionId(), orderCode, dto.getTotalFee());
                return dto;
            } else {
                logger.info(map.get("trade_state_desc"));
                dto.setReason(map.get("trade_state_desc"));
                return dto;
            }
        } catch (Exception e) {
            logger.info("发起预支付失败，异常信息：" + e.getMessage());
            dto.setReason("查询失败，未知异常");
        }
        return null;
    }
}
