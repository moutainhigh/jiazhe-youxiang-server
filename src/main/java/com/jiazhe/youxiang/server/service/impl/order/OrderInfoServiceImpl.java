package com.jiazhe.youxiang.server.service.impl.order;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dao.mapper.OrderInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.order.OrderInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.AppendOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.PlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.UserReservationOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.dto.order.orderrefund.OrderRefundDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.service.order.OrderPaymentService;
import com.jiazhe.youxiang.server.service.order.OrderRefundService;
import com.jiazhe.youxiang.server.service.product.ProductPriceService;
import com.jiazhe.youxiang.server.service.product.ProductService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/11/7.
 */
@Service("orderInfoService")
@Transactional(rollbackFor = Exception.class)
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoPOManualMapper orderInfoPOManualMapper;
    @Autowired
    private OrderInfoPOMapper orderInfoPOMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderPaymentService orderPaymentService;
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private OrderRefundService orderRefundService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductPriceService productPriceService;
    @Autowired
    private RCService rcService;

    @Override
    public List<OrderInfoDTO> getList(Byte status, String orderCode, String mobile, String customerMobile, Date orderStartTime, Date orderEndTime, String workerMobile, Paging paging) {
        Integer count = orderInfoPOManualMapper.count(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile);
        List<OrderInfoPO> orderInfoPOList = orderInfoPOManualMapper.query(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile, paging.getOffset(), paging.getLimit());
        List<OrderInfoDTO> orderInfoDTOList = orderInfoPOList.stream().map(OrderInfoAdapter::PO2DTO).collect(Collectors.toList());
        orderInfoDTOList.forEach(bean -> {
            ProductDTO productDTO = productService.getById(bean.getProductId());
            CustomerDTO customerDTO = customerService.getById(bean.getCustomerId());
            bean.setProductDTO(productDTO);
            bean.setCustomerDTO(customerDTO);
        });
        paging.setTotal(count);
        return orderInfoDTOList;
    }

    @Override
    public OrderInfoDTO getById(Integer id) {
        OrderInfoPO po = orderInfoPOMapper.selectByPrimaryKey(id);
        OrderInfoDTO dto = OrderInfoAdapter.PO2DTO(po);
        CustomerDTO customerDTO = customerService.getById(dto.getCustomerId());
        ProductDTO productDTO = productService.getById(dto.getProductId());
        dto.setCustomerDTO(customerDTO);
        dto.setProductDTO(productDTO);
        return dto;
    }

    @Override
    public void customerCancelOrder(Integer id) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(id);
        //订单为待付款或待派单状态，直接走退款流程
        if (orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNPAID) || orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSENT)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_CANCEL);
            //退款功能共用，提出公共方法
            orderRefund(id);
        }
        //订单为待服务状态，直接将订单置为取消待审核状态，此时不退款
        else if (orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSERVICE)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_CANCELWATINGCHECK);
        } else {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_CANCEL);
        }
        orderInfoPOMapper.updateByPrimaryKey(orderInfoPO);
    }

    @Override
    public BigDecimal customerPay(Integer orderId, BigDecimal payCash, String serialNumber) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(orderId);
        Integer needPayCount = orderInfoPO.getCount() - orderInfoPO.getPayVoucher();
        BigDecimal needPayMoney = orderInfoPO.getProductPrice().multiply(new BigDecimal(needPayCount));
        BigDecimal left = needPayMoney.subtract(orderInfoPO.getPayRechargeCard().add(orderInfoPO.getPayCash().add(payCash)));
        orderInfoPO.setPayCash(orderInfoPO.getPayCash().add(payCash));
        if (payCash.compareTo(new BigDecimal(0)) == 1) {
            OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
            orderPaymentPO.setOrderId(orderInfoPO.getId());
            orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
            orderPaymentPO.setPayType(CommonConstant.PAY_CASH);
            orderPaymentPO.setPayMoney(payCash);
            orderPaymentPO.setSerialNumber(serialNumber);
            orderPaymentPO.setExtInfo("");
            orderPaymentPO.setIsDeleted(Byte.valueOf("0"));
            orderPaymentPO.setAddTime(new Date());
            orderPaymentPO.setModTime(new Date());
            orderPaymentService.insert(orderPaymentPO);
        }
        //说明订单支付完成，判断商品，如果是服务类商品就置为待派单状态，如果是虚拟商品，就置为已完成状态，并发相关电子商品吗
        if (left.compareTo(new BigDecimal(0)) == 0 || left.compareTo(new BigDecimal(0)) == -1) {
            ProductDTO productDTO = productService.getById(orderInfoPO.getProductId());
            if (productDTO.getProductType().equals(CommonConstant.SERVICE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
            }
            if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
                //此处应该有发放电子码逻辑！！！！！！！
            }
        }
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
        return left;
    }

    @Override
    public void orderCancelPass(Integer id) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(id);
        //订单为取消待审核状态，才能审核
        if (orderInfoPO.getStatus().equals(CommonConstant.ORDER_CANCELWATINGCHECK)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_CANCEL);
            //退款功能共用，提出公共方法
            orderRefund(id);
        } else {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_CHECK);
        }
        orderInfoPOMapper.updateByPrimaryKey(orderInfoPO);
    }

    @Override
    public void orderCancelUnpass(Integer id, String auditReason) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(id);
        //订单为取消待审核状态，才能审核
        if (orderInfoPO.getStatus().equals(CommonConstant.ORDER_CANCELWATINGCHECK)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_CANCELUNPASS);
            orderInfoPO.setAuditReason(auditReason);
        } else {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_CHECK);
        }
        orderInfoPOMapper.updateByPrimaryKey(orderInfoPO);
    }

    @Override
    public void userCancelOrder(Integer id) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(id);
        //订单不是已完成状态，才能取消
        if (orderInfoPO.getStatus().equals(CommonConstant.ORDER_COMPLETE)) {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_CANCEL);
        } else {
            orderInfoPO.setStatus(CommonConstant.ORDER_CANCEL);
            //退款功能共用，提出公共方法
            orderRefund(id);
        }
        orderInfoPOMapper.updateByPrimaryKey(orderInfoPO);
    }

    @Override
    public void userCompleteOrder(Integer id) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(id);
        orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
        orderInfoPOMapper.updateByPrimaryKey(orderInfoPO);
    }

    @Override
    public void placeOrder(PlaceOrderDTO dto) throws ParseException {
        List<OrderPaymentPO> orderPaymentPOList = Lists.newArrayList();
        CustomerDTO customerDTO = customerService.getById(dto.getCustomerId());
        if (null == customerDTO) {
            throw new OrderException(OrderCodeEnum.CUSTOMER_NOT_EXIST);
        }
        String orderCode = GenerateCode.generateOrderCode(customerDTO.getMobile());
        ProductDTO productDTO = productService.getById(dto.getProductId());
        if (null == productDTO || productDTO.getStatus().equals(Byte.valueOf("0"))) {
            throw new OrderException(OrderCodeEnum.PRODUCT_NOT_AVAILABLE);
        }
        //代金券支付数量
        Integer[] voucherPayCount = {0};
        Integer[] i = {0};
        BigDecimal[] rechargeCardPayMoney = {new BigDecimal(0)};
        ProductPriceDTO productPriceDTO = productPriceService.getPriceByCity(dto.getProductId(), dto.getCustomerCityCode());
        if (null == productPriceDTO || productPriceDTO.getStatus().equals(Byte.valueOf("0"))) {
            throw new OrderException(OrderCodeEnum.PRODUCT_NOT_AVAILABLE);
        }
        if (!Strings.isBlank(dto.getVoucherIds())) {
            List<Integer> voucherIds = Arrays.asList(dto.getVoucherIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<VoucherDTO> voucherDTOList = voucherService.findByIds(voucherIds);
            voucherDTOList.stream().forEach(bean -> {
                if(!bean.getCustomerId().equals(customerDTO.getId())){
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_NOT_YOURS);
                }
                if (bean.getStatus().equals(Byte.valueOf("0")) || bean.getUsed().equals(Byte.valueOf("1"))) {
                    throw new OrderException(OrderCodeEnum.ORDER_VOUCHER_PAY_ERROR);
                }
                if(!bean.getCityCodes().contains(dto.getCustomerCityCode())){
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_CITY);
                }
                List<Integer> productIds = Arrays.asList(bean.getProductIds().split(","))
                        .stream().map(s -> Integer.parseInt(s.trim()))
                        .collect(Collectors.toList());
                if(!productIds.contains(dto.getProductId())){
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_PRODUCT);
                }
                bean.setUsed(Byte.valueOf("1"));
                voucherPayCount[0] = voucherPayCount[0] + bean.getCount();
                OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                orderPaymentPO.setOrderCode(orderCode);
                orderPaymentPO.setPayType(CommonConstant.PAY_VOUCHER);
                orderPaymentPO.setVoucherId(bean.getId());
                orderPaymentPO.setPayMoney(new BigDecimal(0));
                orderPaymentPO.setSerialNumber("");
                orderPaymentPOList.add(orderPaymentPO);
            });
            voucherService.batchChangeUsed(voucherIds, Byte.valueOf("1"));
        }
        if (!Strings.isBlank(dto.getRechargeCardIds())) {
            List<Integer> rechargeCardIds = Arrays.asList(dto.getRechargeCardIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<BigDecimal> cardMoneys = Arrays.asList(dto.getCardMoneys().split(","))
                    .stream().map(s -> new BigDecimal(s.trim()))
                    .collect(Collectors.toList());
            List<RCDTO> rcdtoList = rcService.findByIds(rechargeCardIds);
            rcdtoList.stream().forEach(bean -> {
                if(!bean.getCustomerId().equals(customerDTO.getId())){
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_NOT_YOURS);
                }
                if(!bean.getCityCodes().contains(dto.getCustomerCityCode())){
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_CITY);
                }
                List<Integer> productIds = Arrays.asList(bean.getProductIds().split(","))
                        .stream().map(s -> Integer.parseInt(s.trim()))
                        .collect(Collectors.toList());
                if(!productIds.contains(dto.getProductId())){
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_PRODUCT);
                }
                if (bean.getBalance().compareTo(cardMoneys.get(i[0])) == -1) {
                    throw new OrderException(OrderCodeEnum.ORDER_RECHARGE_CARD_PAY_ERROR);
                }
                bean.setBalance(bean.getBalance().subtract(cardMoneys.get(i[0])));
                rechargeCardPayMoney[0] = rechargeCardPayMoney[0].add(cardMoneys.get(i[0]));
                OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                orderPaymentPO.setOrderCode(orderCode);
                orderPaymentPO.setPayType(CommonConstant.PAY_RECHARGE_CARD);
                orderPaymentPO.setRechargeCardId(bean.getId());
                orderPaymentPO.setPayMoney(cardMoneys.get(i[0]));
                orderPaymentPO.setSerialNumber("");
                orderPaymentPOList.add(orderPaymentPO);
                i[0] = i[0] + 1;
            });
            rcService.batchUpdate(rcdtoList);
        }
        //计算订单下单完成后应该置为什么状态
        Integer needPayCount = dto.getCount() - voucherPayCount[0];
        BigDecimal needPay = productPriceDTO.getPrice().multiply(new BigDecimal(needPayCount));
        boolean payOff = rechargeCardPayMoney[0].compareTo(needPay) == 0;
        OrderInfoPO orderInfoPO = new OrderInfoPO();
        orderInfoPO.setOrderCode(orderCode);
        orderInfoPO.setCustomerId(dto.getCustomerId());
        orderInfoPO.setProductId(dto.getProductId());
        orderInfoPO.setCustomerCityCode(dto.getCustomerCityCode());
        orderInfoPO.setCustomerCityName(productPriceDTO.getCityName());
        orderInfoPO.setProductPrice(productPriceDTO.getPrice());
        orderInfoPO.setCount(dto.getCount());
        orderInfoPO.setCustomerAddress(dto.getCustomerAddress());
        orderInfoPO.setCustomerMobile(dto.getCustomerMobile());
        orderInfoPO.setCustomerName(dto.getCustomerName());
        orderInfoPO.setCustomerRemark(dto.getCustomerRemark());
        orderInfoPO.setWorkerName(dto.getWorkerName());
        orderInfoPO.setWorkerMobile(dto.getWorkerMobile());
        orderInfoPO.setOrderTime(new Date());
        orderInfoPO.setServiceTime(dto.getServiceTime());
        orderInfoPO.setRealServiceTime(dto.getServiceTime());
        orderInfoPO.setPayRechargeCard(rechargeCardPayMoney[0]);
        orderInfoPO.setPayVoucher(voucherPayCount[0]);
        orderInfoPO.setPayCash(new BigDecimal(0));
        orderInfoPO.setTotalAmount(productPriceDTO.getPrice().multiply(new BigDecimal(dto.getCount())));
        orderInfoPO.setCost(dto.getCost());
        orderInfoPO.setComments(dto.getComments());
        orderInfoPO.setType(dto.getType());
        if (payOff) {
            if (productDTO.getProductType().equals(CommonConstant.SERVICE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
            }
            if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
            }
        } else {
            orderInfoPO.setStatus(CommonConstant.ORDER_UNPAID);
        }
        orderInfoPOManualMapper.insert(orderInfoPO);
        orderPaymentPOList.stream().forEach(bean -> {
            bean.setOrderId(orderInfoPO.getId());
        });
        orderPaymentService.batchInsert(orderPaymentPOList);
    }

    @Override
    public void userReservationOrder(UserReservationOrderDTO dto) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(dto.getOrderId());
        if(null == orderInfoPO||orderInfoPO.getIsDeleted().equals(Byte.valueOf("1"))){
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        if(!orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSENT)){
            throw new OrderException(OrderCodeEnum.ORDER_STATUS_NOT_UNSENT);
        }
        orderInfoPO.setStatus(CommonConstant.ORDER_UNSERVICE);
        orderInfoPO.setWorkerMobile(dto.getWorkerMobile());
        orderInfoPO.setWorkerName(dto.getWorkerName());
        orderInfoPO.setRealServiceTime(dto.getRealServiceTime());
        orderInfoPO.setCost(dto.getCost());
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
    }

    @Override
    public void appendOrder(AppendOrderDTO appendOrderDTO) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(appendOrderDTO.getOrderId());
        if(null == orderInfoPO||orderInfoPO.getIsDeleted().equals(Byte.valueOf("1"))){
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        if(!orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSERVICE)){
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_APPEND_ANOTHER);
        }
        List<OrderPaymentPO> orderPaymentPOList = Lists.newArrayList();
        //代金券支付数量
        Integer[] voucherPayCount = {0};
        Integer[] i = {0};
        BigDecimal[] rechargeCardPayMoney = {new BigDecimal(0)};
        if (!Strings.isBlank(appendOrderDTO.getVoucherIds())) {
            List<Integer> voucherIds = Arrays.asList(appendOrderDTO.getVoucherIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<VoucherDTO> voucherDTOList = voucherService.findByIds(voucherIds);
            voucherDTOList.stream().forEach(bean -> {
                if(!bean.getCustomerId().equals(orderInfoPO.getCustomerId())){
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_NOT_YOURS);
                }
                if (bean.getStatus().equals(Byte.valueOf("0")) || bean.getUsed().equals(Byte.valueOf("1"))) {
                    throw new OrderException(OrderCodeEnum.ORDER_VOUCHER_PAY_ERROR);
                }
                if(!bean.getCityCodes().contains(orderInfoPO.getCustomerCityCode())){
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_CITY);
                }
                List<Integer> productIds = Arrays.asList(bean.getProductIds().split(","))
                        .stream().map(s -> Integer.parseInt(s.trim()))
                        .collect(Collectors.toList());
                if(!productIds.contains(orderInfoPO.getProductId())){
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_PRODUCT);
                }
                bean.setUsed(Byte.valueOf("1"));
                voucherPayCount[0] = voucherPayCount[0] + bean.getCount();
                OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                orderPaymentPO.setOrderId(appendOrderDTO.getOrderId());
                orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
                orderPaymentPO.setPayType(CommonConstant.PAY_VOUCHER);
                orderPaymentPO.setVoucherId(bean.getId());
                orderPaymentPO.setPayMoney(new BigDecimal(0));
                orderPaymentPO.setSerialNumber("");
                orderPaymentPOList.add(orderPaymentPO);
            });
            voucherService.batchChangeUsed(voucherIds, Byte.valueOf("1"));
        }
        if (!Strings.isBlank(appendOrderDTO.getRechargeCardIds())) {
            List<Integer> rechargeCardIds = Arrays.asList(appendOrderDTO.getRechargeCardIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<BigDecimal> cardMoneys = Arrays.asList(appendOrderDTO.getCardMoneys().split(","))
                    .stream().map(s -> new BigDecimal(s.trim()))
                    .collect(Collectors.toList());
            List<RCDTO> rcdtoList = rcService.findByIds(rechargeCardIds);
            rcdtoList.stream().forEach(bean -> {
                if(!bean.getCustomerId().equals(orderInfoPO.getCustomerId())){
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_NOT_YOURS);
                }
                if(!bean.getCityCodes().contains(orderInfoPO.getCustomerCityCode())){
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_CITY);
                }
                List<Integer> productIds = Arrays.asList(bean.getProductIds().split(","))
                        .stream().map(s -> Integer.parseInt(s.trim()))
                        .collect(Collectors.toList());
                if(!productIds.contains(orderInfoPO.getProductId())){
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_PRODUCT);
                }
                if (bean.getBalance().compareTo(cardMoneys.get(i[0])) == -1) {
                    throw new OrderException(OrderCodeEnum.ORDER_RECHARGE_CARD_PAY_ERROR);
                }
                bean.setBalance(bean.getBalance().subtract(cardMoneys.get(i[0])));
                rechargeCardPayMoney[0] = rechargeCardPayMoney[0].add(cardMoneys.get(i[0]));
                OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                orderPaymentPO.setOrderId(appendOrderDTO.getOrderId());
                orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
                orderPaymentPO.setPayType(CommonConstant.PAY_RECHARGE_CARD);
                orderPaymentPO.setRechargeCardId(bean.getId());
                orderPaymentPO.setPayMoney(cardMoneys.get(i[0]));
                orderPaymentPO.setSerialNumber("");
                orderPaymentPOList.add(orderPaymentPO);
                i[0] = i[0] + 1;
            });
            rcService.batchUpdate(rcdtoList);
        }
        orderPaymentService.batchInsert(orderPaymentPOList);
        orderInfoPO.setCount(orderInfoPO.getCount()+appendOrderDTO.getCount());
        orderInfoPO.setPayRechargeCard(orderInfoPO.getPayRechargeCard().add(rechargeCardPayMoney[0]));
        orderInfoPO.setPayVoucher(orderInfoPO.getPayVoucher()+voucherPayCount[0]);
        orderInfoPO.setCost(appendOrderDTO.getCost());
        orderInfoPO.setTotalAmount(orderInfoPO.getProductPrice().multiply(new BigDecimal(orderInfoPO.getCount())));
        orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
    }

    @Override
    public Integer getCountByStatus(Byte status) {
        return orderInfoPOManualMapper.getCountByStatus(status);
    }

    private OrderRefundDTO paymentDto2RefundDto(OrderPaymentDTO paymentDTO) {
        OrderRefundDTO refundDTO = new OrderRefundDTO();
        refundDTO.setOrderCode(paymentDTO.getOrderCode());
        refundDTO.setOrderId(paymentDTO.getOrderId());
        refundDTO.setRefundType(paymentDTO.getPayType());
        refundDTO.setRechargeCardId(paymentDTO.getRechargeCardId());
        refundDTO.setVoucherId(paymentDTO.getVoucherId());
        refundDTO.setRefundMoney(paymentDTO.getPayMoney());
        refundDTO.setSerialNumber(paymentDTO.getSerialNumber());
        return refundDTO;
    }

    public void orderRefund(Integer orderId) {
        List<OrderPaymentDTO> orderPaymentDTOList = orderPaymentService.getByOrderId(orderId);
        List<OrderRefundDTO> orderRefundDTOList = Lists.newArrayList();
        List<RCDTO> rcDTOList = Lists.newArrayList();
        List<Integer> voucherIds = Lists.newArrayList();
        if (!orderPaymentDTOList.isEmpty()) {
            orderPaymentDTOList.stream().forEach(bean -> {
                orderRefundDTOList.add(paymentDto2RefundDto(bean));
                if (bean.getPayType().equals(CommonConstant.PAY_RECHARGE_CARD)) {
                    RCDTO rcdto = rcService.getById(bean.getRechargeCardId());
                    rcdto.setBalance(rcdto.getBalance().add(bean.getPayMoney()));
                    rcDTOList.add(rcdto);
                }
                if (bean.getPayType().equals(CommonConstant.PAY_VOUCHER)) {
                    voucherIds.add(bean.getVoucherId());
                }
                if (bean.getPayType().equals(CommonConstant.PAY_CASH)){

                }
            });
        }
        if(!rcDTOList.isEmpty()){
            rcService.batchUpdate(rcDTOList);
        }
        if(!voucherIds.isEmpty()){
            voucherService.batchChangeUsed(voucherIds, Byte.valueOf("0"));
        }
        if(!orderRefundDTOList.isEmpty()){
            orderRefundService.batchInsert(orderRefundDTOList);
        }
    }
}
