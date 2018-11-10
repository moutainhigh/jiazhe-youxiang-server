package com.jiazhe.youxiang.server.service.impl.order;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dao.mapper.OrderInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.order.OrderInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.dto.order.orderrefund.OrderRefundDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.service.order.OrderPaymentService;
import com.jiazhe.youxiang.server.service.order.OrderRefundService;
import com.jiazhe.youxiang.server.service.product.ProductService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private RCService rcService;
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private OrderRefundService orderRefundService;
    @Autowired
    private ProductService productService;

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
        }else {
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
        }else {
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
        }else {
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

    public void orderRefund(Integer orderId){
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
                if (bean.getPayType().equals(CommonConstant.PAY_CASH)) {

                }
            });
        }
        rcService.batchUpdate(rcDTOList);
        voucherService.batchChangeUsed(voucherIds, Byte.valueOf("0"));
        orderRefundService.batchInsert(orderRefundDTOList);
    }
}
