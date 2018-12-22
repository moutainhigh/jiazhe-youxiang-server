package com.jiazhe.youxiang.server.service.impl.order;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.ConstantFetchUtil;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dao.mapper.OrderInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.order.OrderInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPOExample;
import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductCodeDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.AppendOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.PlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.UserReservationOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.dto.order.orderrefund.OrderRefundDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.EleProductCodeService;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.service.order.OrderPaymentService;
import com.jiazhe.youxiang.server.service.order.OrderRefundService;
import com.jiazhe.youxiang.server.service.point.PointService;
import com.jiazhe.youxiang.server.service.product.ProductPriceService;
import com.jiazhe.youxiang.server.service.product.ProductService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/11/7.
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

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
    @Autowired
    private PointService pointService;
    @Autowired
    private EleProductCodeService eleProductCodeService;

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

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BigDecimal customerPay(Integer orderId, BigDecimal payCash, String serialNumber) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(orderId);
        List<EleProductCodeDTO> eleProductCodeDTOList = Lists.newArrayList();
        Integer needPayCount = orderInfoPO.getCount();
        BigDecimal needPayMoney = orderInfoPO.getProductPrice().multiply(new BigDecimal(needPayCount)).subtract(orderInfoPO.getPayVoucher());
        BigDecimal left = needPayMoney.subtract(orderInfoPO.getPayRechargeCard().add(orderInfoPO.getPayCash().add(payCash)));
        orderInfoPO.setPayCash(orderInfoPO.getPayCash().add(payCash));
        if (payCash.compareTo(BigDecimal.ZERO) == 1) {
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
        if (left.compareTo(BigDecimal.ZERO) == 0 || left.compareTo(BigDecimal.ZERO) == -1) {
            ProductDTO productDTO = productService.getById(orderInfoPO.getProductId());
            if (productDTO.getProductType().equals(CommonConstant.SERVICE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
            }
            if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
                eleProductCodeDTOList = sendEleProductCode(orderInfoPO.getProductId(), orderInfoPO.getCount());
                eleProductCodeService.batchSendOut(eleProductCodeDTOList.stream().map(EleProductCodeDTO::getId).collect(Collectors.toList()), orderInfoPO.getId(), orderInfoPO.getOrderCode());
                StringBuilder comments = new StringBuilder();
                eleProductCodeDTOList.stream().forEach(bean -> {
                    if (!Strings.isBlank(bean.getCode())) {
                        comments.append("兑换码为：" + bean.getCode());
                    }
                    comments.append("，兑换密钥为：" + bean.getKeyt() + "；");
                });
                orderInfoPO.setComments(comments.toString());
            }
        }
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
        return left;
    }

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void userCompleteOrder(Integer id) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(id);
        orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
        orderInfoPOMapper.updateByPrimaryKey(orderInfoPO);
    }

    @Override
    public void userReservationOrder(UserReservationOrderDTO dto) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(dto.getOrderId());
        if (null == orderInfoPO || orderInfoPO.getIsDeleted().equals(Byte.valueOf("1"))) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        if (!orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSENT)) {
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
    public void userChangeReservationInfo(UserReservationOrderDTO dto) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(dto.getOrderId());
        if (null == orderInfoPO || orderInfoPO.getIsDeleted().equals(Byte.valueOf("1"))) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        if (!orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSERVICE)) {
            throw new OrderException(OrderCodeEnum.ORDER_STATUS_NOT_UNSERVICE);
        }
        orderInfoPO.setWorkerMobile(dto.getWorkerMobile());
        orderInfoPO.setWorkerName(dto.getWorkerName());
        orderInfoPO.setRealServiceTime(dto.getRealServiceTime());
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void appendOrder(AppendOrderDTO appendOrderDTO) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(appendOrderDTO.getOrderId());
        if (null == orderInfoPO || orderInfoPO.getIsDeleted().equals(Byte.valueOf("1"))) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        if (!orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSERVICE)) {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_APPEND_ANOTHER);
        }
        List<OrderPaymentPO> orderPaymentPOList = Lists.newArrayList();
        //代金券支付数量
        Integer[] voucherPayCount = {0};
        BigDecimal[] rechargeCardPayMoney = {BigDecimal.ZERO};
        if (!Strings.isBlank(appendOrderDTO.getVoucherIds())) {
            List<Integer> voucherIds = Arrays.asList(appendOrderDTO.getVoucherIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<VoucherDTO> voucherDTOList = voucherService.findByIds(voucherIds);
            voucherDTOList.stream().forEach(bean -> {
                if (!bean.getCustomerId().equals(orderInfoPO.getCustomerId())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_NOT_YOURS);
                }
                if (bean.getStatus().equals(Byte.valueOf("0")) || bean.getUsed().equals(Byte.valueOf("1"))) {
                    throw new OrderException(OrderCodeEnum.ORDER_VOUCHER_PAY_ERROR);
                }
                if (!bean.getCityCodes().contains(orderInfoPO.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_CITY);
                }
                List<Integer> productIds = Arrays.asList(bean.getProductIds().split(","))
                        .stream().map(s -> Integer.parseInt(s.trim()))
                        .collect(Collectors.toList());
                if (!productIds.contains(orderInfoPO.getProductId())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_PRODUCT);
                }
                bean.setUsed(Byte.valueOf("1"));
                voucherPayCount[0] = voucherPayCount[0] + bean.getCount();
                OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                orderPaymentPO.setOrderId(appendOrderDTO.getOrderId());
                orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
                orderPaymentPO.setPayType(CommonConstant.PAY_VOUCHER);
                orderPaymentPO.setVoucherId(bean.getId());
                orderPaymentPO.setPayMoney(BigDecimal.ZERO);
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
                int index = rechargeCardIds.lastIndexOf(bean.getId());
                if (!bean.getCustomerId().equals(orderInfoPO.getCustomerId())) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_NOT_YOURS);
                }
                if (!bean.getCityCodes().contains(orderInfoPO.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_CITY);
                }
                List<Integer> productIds = Arrays.asList(bean.getProductIds().split(","))
                        .stream().map(s -> Integer.parseInt(s.trim()))
                        .collect(Collectors.toList());
                if (!productIds.contains(orderInfoPO.getProductId())) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_PRODUCT);
                }
                if (bean.getBalance().compareTo(cardMoneys.get(index)) == -1) {
                    throw new OrderException(OrderCodeEnum.ORDER_RECHARGE_CARD_PAY_ERROR);
                }
                bean.setBalance(bean.getBalance().subtract(cardMoneys.get(index)));
                rechargeCardPayMoney[0] = rechargeCardPayMoney[0].add(cardMoneys.get(index));
                OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                orderPaymentPO.setOrderId(appendOrderDTO.getOrderId());
                orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
                orderPaymentPO.setPayType(CommonConstant.PAY_RECHARGE_CARD);
                orderPaymentPO.setRechargeCardId(bean.getId());
                orderPaymentPO.setPayMoney(cardMoneys.get(index));
                orderPaymentPO.setSerialNumber("");
                orderPaymentPOList.add(orderPaymentPO);
            });
            rcService.batchUpdate(rcdtoList);
        }
        orderPaymentService.batchInsert(orderPaymentPOList);
        orderInfoPO.setCount(orderInfoPO.getCount() + appendOrderDTO.getCount());
        orderInfoPO.setPayRechargeCard(orderInfoPO.getPayRechargeCard().add(rechargeCardPayMoney[0]));
        orderInfoPO.setPayVoucher(orderInfoPO.getPayVoucher().add(orderInfoPO.getProductPrice().multiply(new BigDecimal(voucherPayCount[0]))));
        orderInfoPO.setCost(appendOrderDTO.getCost());
        orderInfoPO.setTotalAmount(orderInfoPO.getProductPrice().multiply(new BigDecimal(orderInfoPO.getCount())));
        orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
    }

    @Override
    public Integer getCountByStatus(Byte status) {
        return orderInfoPOManualMapper.getCountByStatus(status);
    }

    @Override
    public void prePaymentCheck(Integer id) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(id);
        ProductDTO productDTO = productService.getById(orderInfoPO.getProductId());
        //电子商品需要检查一下电子吗是否足够，服务类商品不需要检查
        if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
            List<EleProductCodeDTO> eleProductCodeDTOList = eleProductCodeService.selectTopN(orderInfoPO.getProductId(), orderInfoPO.getCount());
            if (eleProductCodeDTOList.size() < orderInfoPO.getCount()) {
                throw new OrderException(OrderCodeEnum.ELE_PRODUCT_CODE_NOT_ENOUGH);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public NeedPayResp placeOrder(PlaceOrderDTO dto) throws ParseException {
        List<OrderPaymentPO> orderPaymentPOList = Lists.newArrayList();
        List<EleProductCodeDTO> eleProductCodeDTOList = Lists.newArrayList();
        CustomerDTO customerDTO = customerService.getById(dto.getCustomerId());
        if (null == customerDTO) {
            throw new OrderException(OrderCodeEnum.CUSTOMER_NOT_EXIST);
        }
        ProductDTO productDTO = productService.getById(dto.getProductId());
        if (null == productDTO || productDTO.getStatus().equals(Byte.valueOf("0"))) {
            throw new OrderException(OrderCodeEnum.PRODUCT_NOT_AVAILABLE);
        }
        ProductPriceDTO productPriceDTO = productPriceService.getPriceByCity(dto.getProductId(), dto.getCustomerCityCode());
        if (null == productPriceDTO || productPriceDTO.getStatus().equals(Byte.valueOf("0"))) {
            throw new OrderException(OrderCodeEnum.PRODUCT_NOT_AVAILABLE);
        }
        if (productDTO.getLastNum() > dto.getCount()) {
            throw new OrderException(OrderCodeEnum.ORDER_COUNT_LESS_THAN_LAST_NUM);
        }
        long delayDays = dto.getServiceTime().getTime() / ConstantFetchUtil.day_1 - System.currentTimeMillis() / ConstantFetchUtil.day_1;
        if (productDTO.getDelayDays() > delayDays) {
            throw new OrderException(OrderCodeEnum.SERVICE_TIME_ERROR);
        }
        String orderCode = generateOrderCode();
        //待支付金额
        BigDecimal[] needPay = {productPriceDTO.getPrice().multiply(new BigDecimal(dto.getCount()))};
        //代金券支付金额
        BigDecimal[] voucherPay = {BigDecimal.ZERO};
        //充值卡支付金额
        BigDecimal[] rechargeCardPayMoney = {BigDecimal.ZERO};
        //积分卡支付金额
        BigDecimal[] pointPayMoney = {BigDecimal.ZERO};
        //*********************代金券支付部分*************************/
        if (!Strings.isBlank(dto.getVoucherIds())) {
            List<Integer> voucherIds = Arrays.asList(dto.getVoucherIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<VoucherDTO> voucherDTOList = voucherService.findByIdsInOrder(voucherIds);
            voucherDTOList.stream().forEach(bean -> {
                if (!bean.getCustomerId().equals(customerDTO.getId())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_NOT_YOURS);
                }
                if (bean.getStatus().equals(Byte.valueOf("0")) || bean.getUsed().equals(Byte.valueOf("1"))) {
                    throw new OrderException(OrderCodeEnum.ORDER_VOUCHER_PAY_ERROR);
                }
                if (!bean.getCityCodes().contains(dto.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_CITY);
                }
                if (!idsContainsId(bean.getProductIds(), dto.getProductId())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_PRODUCT);
                }
                if (needPay[0].compareTo(BigDecimal.ZERO) == 1) {
                    bean.setUsed(Byte.valueOf("1"));
                    voucherPay[0] = voucherPay[0].add(productPriceDTO.getPrice().multiply(new BigDecimal(bean.getCount())));
                    OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                    orderPaymentPO.setOrderCode(orderCode);
                    orderPaymentPO.setPayType(CommonConstant.PAY_VOUCHER);
                    orderPaymentPO.setVoucherId(bean.getId());
                    orderPaymentPO.setPayMoney(BigDecimal.ZERO);
                    orderPaymentPO.setSerialNumber("");
                    orderPaymentPOList.add(orderPaymentPO);
                    needPay[0] = needPay[0].subtract(productPriceDTO.getPrice().multiply(new BigDecimal(bean.getCount())));
                }
            });
            voucherService.batchChangeUsed(voucherIds, Byte.valueOf("1"));
        }
        //*********************积分卡支付部分*************************/
        if (!Strings.isBlank(dto.getPointIds())) {
            List<Integer> pointIds = Arrays.asList(dto.getPointIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<PointDTO> pointDtoList = pointService.findByIdsInOrder(pointIds);
            pointDtoList.stream().forEach(bean -> {
                if (!bean.getCustomerId().equals(customerDTO.getId())) {
                    throw new OrderException(OrderCodeEnum.POINT_IS_NOT_YOURS);
                }
                if (bean.getStatus().equals(Byte.valueOf("0"))) {
                    throw new OrderException(OrderCodeEnum.POINT_CARD_PAY_ERROR);
                }
                if (!bean.getCityCodes().contains(dto.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.POINT_NOT_SUPPORT_CITY);
                }
                if (!idsContainsId(bean.getProductIds(), dto.getProductId())) {
                    throw new OrderException(OrderCodeEnum.POINT_NOT_SUPPORT_PRODUCT);
                }

                if (needPay[0].compareTo(BigDecimal.ZERO) == 1) {
                    //支付积分数量
                    BigDecimal thisPointPay;
                    BigDecimal conversionRate = new BigDecimal(bean.getProjectDTO().getPointConversionRate());
                    if (needPay[0].compareTo(bean.getBalance().multiply(conversionRate)) == 1) {
                        thisPointPay = bean.getBalance();
                    } else {
                        //当【需支付的金额/兑换比例】不为整数时，则抛异常
                        if (needPay[0].divide(conversionRate).setScale(0, RoundingMode.HALF_UP).compareTo(needPay[0].subtract(conversionRate)) != 0) {
                            throw new OrderException(OrderCodeEnum.POINT_PAY_DECIMAL_APPEAR);
                        }
                        thisPointPay = needPay[0].subtract(conversionRate);
                    }
                    bean.setBalance(bean.getBalance().subtract(thisPointPay));
                    pointPayMoney[0] = pointPayMoney[0].add(thisPointPay.multiply(conversionRate));
                    OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                    orderPaymentPO.setOrderCode(orderCode);
                    orderPaymentPO.setPayType(CommonConstant.PAY_POINT);
                    orderPaymentPO.setRechargeCardId(bean.getId());
                    orderPaymentPO.setPayMoney(thisPointPay);
                    orderPaymentPO.setSerialNumber("");
                    orderPaymentPOList.add(orderPaymentPO);
                    needPay[0] = needPay[0].subtract(thisPointPay.multiply(conversionRate));
                }
            });
            pointService.batchUpdate(pointDtoList);
        }
        //*********************充值卡支付部分*************************/
        if (!Strings.isBlank(dto.getRechargeCardIds())) {
            List<Integer> rechargeCardIds = Arrays.asList(dto.getRechargeCardIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<RCDTO> rcdtoList = rcService.findByIdsInOrder(rechargeCardIds);
            rcdtoList.stream().forEach(bean -> {
                if (!bean.getCustomerId().equals(customerDTO.getId())) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_NOT_YOURS);
                }
                if (bean.getStatus().equals(Byte.valueOf("0"))) {
                    throw new OrderException(OrderCodeEnum.ORDER_RECHARGE_CARD_PAY_ERROR);
                }
                if (!bean.getCityCodes().contains(dto.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_CITY);
                }
                if (!idsContainsId(bean.getProductIds(), dto.getProductId())) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_PRODUCT);
                }
                if (needPay[0].compareTo(BigDecimal.ZERO) == 1) {
                    BigDecimal thisCardPay;
                    if (needPay[0].compareTo(bean.getBalance()) == 1) {
                        thisCardPay = bean.getBalance();
                    } else {
                        thisCardPay = needPay[0];
                    }
                    bean.setBalance(bean.getBalance().subtract(thisCardPay));
                    rechargeCardPayMoney[0] = rechargeCardPayMoney[0].add(thisCardPay);
                    OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                    orderPaymentPO.setOrderCode(orderCode);
                    orderPaymentPO.setPayType(CommonConstant.PAY_RECHARGE_CARD);
                    orderPaymentPO.setRechargeCardId(bean.getId());
                    orderPaymentPO.setPayMoney(thisCardPay);
                    orderPaymentPO.setSerialNumber("");
                    orderPaymentPOList.add(orderPaymentPO);
                    needPay[0] = needPay[0].subtract(thisCardPay);
                }
            });
            rcService.batchUpdate(rcdtoList);
        }
        if (needPay[0].compareTo(BigDecimal.ZERO) == -1) {
            throw new OrderException(OrderCodeEnum.ORDER_OVER_PAYMENT);
        }
        //计算订单下单完成后应该置为什么状态
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
        orderInfoPO.setPayPoint(pointPayMoney[0]);
        orderInfoPO.setPayRechargeCard(rechargeCardPayMoney[0]);
        orderInfoPO.setPayVoucher(voucherPay[0]);
        orderInfoPO.setPayCash(BigDecimal.ZERO);
        orderInfoPO.setTotalAmount(productPriceDTO.getPrice().multiply(new BigDecimal(dto.getCount())));
        orderInfoPO.setCost(dto.getCost());
        orderInfoPO.setComments(dto.getComments());
        orderInfoPO.setType(dto.getType());
        if (needPay[0].compareTo(BigDecimal.ZERO) == 0) {//刚好可以支付
            if (productDTO.getProductType().equals(CommonConstant.SERVICE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
            }
            if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
                eleProductCodeDTOList = sendEleProductCode(dto.getProductId(), dto.getCount());
                eleProductCodeService.batchSendOut(eleProductCodeDTOList.stream().map(EleProductCodeDTO::getId).collect(Collectors.toList()), orderInfoPO.getId(), orderCode);
                StringBuilder comments = new StringBuilder();
                eleProductCodeDTOList.stream().forEach(bean -> {
                    if (!Strings.isBlank(bean.getCode())) {
                        comments.append("兑换码为：" + bean.getCode());
                    }
                    comments.append("，兑换密钥为：" + bean.getKeyt() + "；");
                });
                orderInfoPO.setComments(comments.toString());
            }
        } else { //需要在线支付
            orderInfoPO.setStatus(CommonConstant.ORDER_UNPAID);
        }
        orderInfoPOManualMapper.insert(orderInfoPO);
        orderPaymentPOList.stream().forEach(bean -> {
            bean.setOrderId(orderInfoPO.getId());
        });
        orderPaymentService.batchInsert(orderPaymentPOList);
        NeedPayResp needPayResp = new NeedPayResp();
        needPayResp.setOrderId(orderInfoPO.getId());
        needPayResp.setPayCash(needPay[0]);
        return needPayResp;
    }

    @Override
    public OrderInfoDTO getByOrderNo(String orderNo) {
        OrderInfoPOExample example = new OrderInfoPOExample();
        OrderInfoPOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andOrderCodeEqualTo(orderNo);
        List<OrderInfoPO> poList = orderInfoPOMapper.selectByExample(example);
        if (poList.isEmpty()) {
            return null;
        }
        if (poList.size() > 1) {
            throw new OrderException(OrderCodeEnum.ORDER_CODE_REPEAT);
        }
        return OrderInfoAdapter.PO2DTO(poList.get(0));
    }

    private OrderRefundDTO paymentDto2RefundDto(OrderPaymentDTO paymentDTO) {
        OrderRefundDTO refundDTO = new OrderRefundDTO();
        refundDTO.setOrderCode(paymentDTO.getOrderCode());
        refundDTO.setOrderId(paymentDTO.getOrderId());
        refundDTO.setRefundType(paymentDTO.getPayType());
        refundDTO.setPointId(paymentDTO.getPointId());
        refundDTO.setRechargeCardId(paymentDTO.getRechargeCardId());
        refundDTO.setVoucherId(paymentDTO.getVoucherId());
        refundDTO.setRefundMoney(paymentDTO.getPayMoney());
        refundDTO.setSerialNumber(paymentDTO.getSerialNumber());
        return refundDTO;
    }

    public void orderRefund(Integer orderId) {
        List<OrderPaymentDTO> orderPaymentDTOList = orderPaymentService.getByOrderId(orderId);
        List<OrderRefundDTO> orderRefundDTOList = Lists.newArrayList();
        List<PointDTO> pointDTOList = Lists.newArrayList();
        List<RCDTO> rcDTOList = Lists.newArrayList();
        List<Integer> voucherIds = Lists.newArrayList();
        if (!orderPaymentDTOList.isEmpty()) {
            orderPaymentDTOList.stream().forEach(bean -> {
                orderRefundDTOList.add(paymentDto2RefundDto(bean));
                if (bean.getPayType().equals(CommonConstant.PAY_POINT)) {
                    PointDTO pointDTO = pointService.getById(bean.getPointId());
                    pointDTO.setBalance(pointDTO.getBalance().add(bean.getPayMoney()));
                    pointDTOList.add(pointDTO);
                }
                if (bean.getPayType().equals(CommonConstant.PAY_RECHARGE_CARD)) {
                    RCDTO rcdto = rcService.getById(bean.getRechargeCardId());
                    rcdto.setBalance(rcdto.getBalance().add(bean.getPayMoney()));
                    rcDTOList.add(rcdto);
                }
                if (bean.getPayType().equals(CommonConstant.PAY_VOUCHER)) {
                    voucherIds.add(bean.getVoucherId());
                }
                if (bean.getPayType().equals(CommonConstant.PAY_CASH)) {
                    //微信退款
                }
            });
        }
        if (!pointDTOList.isEmpty()) {
            pointService.batchUpdate(pointDTOList);
        }
        if (!rcDTOList.isEmpty()) {
            rcService.batchUpdate(rcDTOList);
        }
        if (!voucherIds.isEmpty()) {
            voucherService.batchChangeUsed(voucherIds, Byte.valueOf("0"));
        }
        if (!orderRefundDTOList.isEmpty()) {
            orderRefundService.batchInsert(orderRefundDTOList);
        }
    }

    /**
     * 根据订单信息，设置定单comment字段内容，并且返回要兑换的电子码list
     *
     * @param productId
     * @param count
     * @return
     */
    private List<EleProductCodeDTO> sendEleProductCode(Integer productId, Integer count) {
        List<EleProductCodeDTO> eleProductCodeDTOList = eleProductCodeService.selectTopN(productId, count);
        if (eleProductCodeDTOList.size() != count) {
            throw new OrderException(OrderCodeEnum.ELE_PRODUCT_CODE_NOT_ENOUGH);
        }
        return eleProductCodeDTOList;
    }

    /**
     * 生成订单号 yyyyMMddHH+3位序列号
     */
    private String generateOrderCode() {
        Long beginHour = (System.currentTimeMillis() / ConstantFetchUtil.hour_1) * ConstantFetchUtil.hour_1;
        Long endHour = beginHour + ConstantFetchUtil.hour_1;
        Integer count = orderInfoPOManualMapper.getCountWithinThisHour(beginHour, endHour);
        if (count >= CommonConstant.ORDER_CEILING_PER_HOUR) {
            logger.error(OrderCodeEnum.ORDER_NO_GENERATE_ERROR.getMessage());
            throw new OrderException(OrderCodeEnum.ORDER_NO_GENERATE_ERROR);
        }
        String index = String.format("%03d", count + 1);
        String prefix = DateUtil.yyyyMMDDhh();
        return prefix + index;
    }

    /**
     * 判断String类型的ids（英文逗号分隔）是否包含某个Integer类型的id
     */
    private boolean idsContainsId(String ids, Integer id) {
        if (Strings.isEmpty(ids)) {
            return false;
        }
        List<Integer> intIds = Arrays.asList(ids.split(","))
                .stream().map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList());
        if (intIds.isEmpty()) {
            return false;
        }
        return intIds.contains(id);
    }
}
