package com.jiazhe.youxiang.server.service.impl.order;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.djbx.DJBXBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.DJBXConstant;
import com.jiazhe.youxiang.server.common.enums.DJBXCodeEnum;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.DJBXException;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dao.mapper.OrderInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.order.OrderInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPOExample;
import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.djbx.DJBXPlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductCodeDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.AppendOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.PlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.UserReservationOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.dto.order.orderrefund.OrderRefundDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
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
import com.jiazhe.youxiang.server.vo.req.djbx.PointsConsumeParam;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
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
    @Lazy
    @Autowired
    private DJBXBiz djbxBiz;

    //商品id作为key，电子码集合作为value
    private static HashMap<Integer, Set<EleProductCodeDTO>> eleProductMap;

    @Override
    public List<OrderInfoDTO> getList(String status, String orderCode, String mobile, String customerMobile, Date orderStartTime, Date orderEndTime, String workerMobile, Integer productId, Integer serviceProductId, Date realServiceStartTime, Date realServiceEndTime, String customerCityCode, Paging paging) {
        Integer count = orderInfoPOManualMapper.count(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile, productId, serviceProductId, realServiceStartTime, realServiceEndTime, customerCityCode);
        List<OrderInfoPO> orderInfoPOList = orderInfoPOManualMapper.query(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile, productId, serviceProductId, realServiceStartTime, realServiceEndTime, customerCityCode, paging.getOffset(), paging.getLimit());
        List<OrderInfoDTO> orderInfoDTOList = orderInfoPOList.stream().map(OrderInfoAdapter::PO2DTO).collect(Collectors.toList());
        orderInfoDTOList.forEach(bean -> {
            ProductDTO productDTO = productService.getById(bean.getProductId());
            ProductDTO serviceProductDTO = productService.getById(bean.getServiceProductId());
            CustomerDTO customerDTO = customerService.getById(bean.getCustomerId());
            bean.setProductDTO(productDTO);
            bean.setServiceProductDTO(serviceProductDTO);
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
        ProductDTO serviceProductDTO = productService.getById(dto.getServiceProductId());
        dto.setCustomerDTO(customerDTO);
        dto.setProductDTO(productDTO);
        dto.setServiceProductDTO(serviceProductDTO);
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
            orderPaymentPO.setIsDeleted(CommonConstant.CODE_NOT_DELETED);
            orderPaymentPO.setAddTime(new Date());
            orderPaymentPO.setModTime(new Date());
            orderPaymentService.insert(orderPaymentPO);
        }
        //说明订单支付完成，判断商品，如果是服务类商品就置为待派单状态，如果是虚拟商品，就置为已完成状态，并发相关电子商品吗
        if (left.compareTo(BigDecimal.ZERO) != 1) {
            ProductDTO productDTO = productService.getById(orderInfoPO.getProductId());
            if (productDTO.getProductType().equals(CommonConstant.SERVICE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
            }
            if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
                String extInfo = sendEleProductCode(orderInfoPO.getProductId(), orderInfoPO.getCount(), orderInfoPO.getId(), orderInfoPO.getOrderCode());
                orderInfoPO.setExtInfo(extInfo);
            }
        }
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
        return left;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void orderCancelPass(OrderInfoDTO orderInfoDTO) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(orderInfoDTO.getId());
        //订单为取消待审核状态，才能审核
        if (orderInfoPO.getStatus().equals(CommonConstant.ORDER_CANCELWATINGCHECK)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_CANCEL);
            orderInfoPO.setCost(orderInfoDTO.getCost());
            orderRefund(orderInfoDTO.getId());
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
    public void userCancelOrder(OrderInfoDTO orderInfoDTO) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(orderInfoDTO.getId());
        if (CommonConstant.DJBX_PLACE_ORDER.equals(orderInfoPO.getType())) {
            throw new OrderException(OrderCodeEnum.DJBX_ORDER_USER_CANCEL_ERROR);
        }
        //订单不是已完成状态，才能取消
        if (orderInfoPO.getStatus().equals(CommonConstant.ORDER_COMPLETE)) {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_CANCEL);
        } else {
            orderInfoPO.setCost(orderInfoDTO.getCost());
            orderInfoPO.setStatus(CommonConstant.ORDER_CANCEL);
            orderRefund(orderInfoDTO.getId());
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
        if (null == orderInfoPO || orderInfoPO.getIsDeleted().equals(CommonConstant.CODE_DELETED)) {
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
        orderInfoPO.setComments(dto.getComments());
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
    }

    @Override
    public void userChangeReservationInfo(UserReservationOrderDTO dto) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(dto.getOrderId());
        if (null == orderInfoPO || orderInfoPO.getIsDeleted().equals(CommonConstant.CODE_DELETED)) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        if (!orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSERVICE)) {
            throw new OrderException(OrderCodeEnum.ORDER_STATUS_NOT_UNSERVICE);
        }
        //订单改为了待派单状态
        if (CommonConstant.ORDER_UNSENT.equals(dto.getStatus()) || CommonConstant.ORDER_UNSERVICE.equals(dto.getStatus())) {
            orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
            orderInfoPO.setServiceTime(dto.getServiceTime());
        } else {
            throw new OrderException(OrderCodeEnum.ORDER_STATUS_ERROR);
        }
        orderInfoPO.setWorkerMobile(dto.getWorkerMobile());
        orderInfoPO.setWorkerName(dto.getWorkerName());
        orderInfoPO.setRealServiceTime(dto.getRealServiceTime());
        orderInfoPO.setCost(dto.getCost());
        orderInfoPO.setComments(dto.getComments());
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void appendOrder(AppendOrderDTO appendOrderDTO) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(appendOrderDTO.getOrderId());
        if (null == orderInfoPO || orderInfoPO.getIsDeleted().equals(CommonConstant.CODE_DELETED)) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        //不是服务型商品，不能追加订单
        if (!orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSERVICE)) {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_APPEND_ANOTHER);
        }
        List<OrderPaymentPO> orderPaymentPOList = Lists.newArrayList();
        //待支付金额
        BigDecimal[] needPay = {orderInfoPO.getProductPrice().multiply(new BigDecimal(appendOrderDTO.getCount()))};
        //代金券支付金额
        BigDecimal[] voucherPay = {BigDecimal.ZERO};
        //积分卡支付金额
        BigDecimal[] pointPayMoney = {BigDecimal.ZERO};
        //充值卡支付金额
        BigDecimal[] rechargeCardPayMoney = {BigDecimal.ZERO};
        /***************************代金券支付部分*****************************/
        if (!Strings.isBlank(appendOrderDTO.getVoucherIds())) {
            List<Integer> voucherIds = Arrays.asList(appendOrderDTO.getVoucherIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<VoucherDTO> voucherDTOList = voucherService.findByIdsInOrder(voucherIds);
            voucherDTOList.stream().forEach(bean -> {
                if (!bean.getCustomerId().equals(orderInfoPO.getCustomerId())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_NOT_YOURS);
                }
                if (bean.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_USED);
                }
                if (bean.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_STOP_USING);
                }
                if (bean.getExpiryTime().getTime() < System.currentTimeMillis()) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_EXPIRY);
                }
                if (bean.getEffectiveTime().getTime() > System.currentTimeMillis()) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_NOT_EFFECTIVE);
                }
                if (!bean.getCityCodes().contains(orderInfoPO.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_CITY);
                }
                if (!idsContainsId(bean.getProductIds(), orderInfoPO.getProductId())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_PRODUCT);
                }
                if (needPay[0].compareTo(BigDecimal.ZERO) == 1) {
                    bean.setUsed(CommonConstant.CODE_HAS_USED);
                    OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                    orderPaymentPO.setOrderId(orderInfoPO.getId());
                    orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
                    orderPaymentPO.setPayType(CommonConstant.PAY_VOUCHER);
                    orderPaymentPO.setVoucherId(bean.getId());
                    orderPaymentPO.setPayMoney(BigDecimal.ZERO);
                    orderPaymentPO.setSerialNumber("");
                    if (needPay[0].subtract(orderInfoPO.getProductPrice().multiply(new BigDecimal(bean.getCount()))).compareTo(BigDecimal.ZERO) >= 0) {
                        needPay[0] = needPay[0].subtract(orderInfoPO.getProductPrice().multiply(new BigDecimal(bean.getCount())));
                        voucherPay[0] = voucherPay[0].add(orderInfoPO.getProductPrice().multiply(new BigDecimal(bean.getCount())));
                    } else {
                        voucherPay[0] = voucherPay[0].add(needPay[0]);
                        needPay[0] = BigDecimal.ZERO;
                    }
                    orderPaymentPOList.add(orderPaymentPO);
                }
            });
            voucherService.batchChangeUsed(voucherIds, CommonConstant.CODE_HAS_USED);
        }
        //*********************积分卡支付部分*************************/
        if (!Strings.isBlank(appendOrderDTO.getPointIds())) {
            List<Integer> pointIds = Arrays.asList(appendOrderDTO.getPointIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<PointDTO> pointDtoList = pointService.findByIdsInOrder(pointIds);
            pointDtoList.stream().forEach(bean -> {
                if (!bean.getCustomerId().equals(orderInfoPO.getCustomerId())) {
                    throw new OrderException(OrderCodeEnum.POINT_IS_NOT_YOURS);
                }
                if (bean.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                    throw new OrderException(OrderCodeEnum.POINT_STOP_USING);
                }
                if (!bean.getCityCodes().contains(orderInfoPO.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.POINT_NOT_SUPPORT_CITY);
                }
                if (!idsContainsId(bean.getProductIds(), orderInfoPO.getProductId())) {
                    throw new OrderException(OrderCodeEnum.POINT_NOT_SUPPORT_PRODUCT);
                }
                if (needPay[0].compareTo(BigDecimal.ZERO) == 1) {
                    //支付积分数量
                    BigDecimal thisPointPay;
                    BigDecimal conversionRate = bean.getProjectDTO().getPointConversionRate();
                    if (needPay[0].compareTo(bean.getBalance().multiply(conversionRate)) == 1) {
                        thisPointPay = bean.getBalance();
                    } else {
                        //当【需支付的金额/兑换比例】不为整数时，则抛异常
                        if (needPay[0].divide(conversionRate).setScale(0, RoundingMode.HALF_UP).compareTo(needPay[0].divide(conversionRate)) != 0) {
                            throw new OrderException(OrderCodeEnum.POINT_PAY_DECIMAL_APPEAR);
                        }
                        thisPointPay = needPay[0].divide(conversionRate);
                    }
                    bean.setBalance(bean.getBalance().subtract(thisPointPay));
                    pointPayMoney[0] = pointPayMoney[0].add(thisPointPay.multiply(conversionRate));
                    OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                    orderPaymentPO.setOrderId(orderInfoPO.getId());
                    orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
                    orderPaymentPO.setPayType(CommonConstant.PAY_POINT);
                    orderPaymentPO.setPointId(bean.getId());
                    orderPaymentPO.setPayMoney(thisPointPay);
                    orderPaymentPO.setSerialNumber("");
                    orderPaymentPOList.add(orderPaymentPO);
                    needPay[0] = needPay[0].subtract(thisPointPay.multiply(conversionRate));
                }
            });
            pointService.batchUpdate(pointDtoList);
        }
        //*********************充值卡支付部分*************************/
        if (!Strings.isBlank(appendOrderDTO.getRechargeCardIds())) {
            List<Integer> rechargeCardIds = Arrays.asList(appendOrderDTO.getRechargeCardIds().split(","))
                    .stream().map(s -> Integer.parseInt(s.trim()))
                    .collect(Collectors.toList());
            List<RCDTO> rcdtoList = rcService.findByIdsInOrder(rechargeCardIds);
            rcdtoList.stream().forEach(bean -> {
                if (!bean.getCustomerId().equals(orderInfoPO.getCustomerId())) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_NOT_YOURS);
                }
                if (bean.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_STOP_USING);
                }
                if (bean.getExpiryTime().getTime() < System.currentTimeMillis()) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_EXPIRY);
                }
                if (bean.getEffectiveTime().getTime() > System.currentTimeMillis()) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_NOT_EFFECTIVE);
                }
                if (!bean.getCityCodes().contains(orderInfoPO.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_NOT_SUPPORT_CITY);
                }
                if (!idsContainsId(bean.getProductIds(), orderInfoPO.getProductId())) {
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
                    orderPaymentPO.setOrderId(orderInfoPO.getId());
                    orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
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
        if (needPay[0].compareTo(BigDecimal.ZERO) == 1) {
            throw new OrderException(OrderCodeEnum.ORDER_PAYMENT_NOT_ENOUGH);
        }
        if (needPay[0].compareTo(BigDecimal.ZERO) == -1) {
            throw new OrderException(OrderCodeEnum.ORDER_OVER_PAYMENT);
        }
        orderPaymentService.batchInsert(orderPaymentPOList);
        orderInfoPO.setCount(orderInfoPO.getCount() + appendOrderDTO.getCount());
        orderInfoPO.setPayPoint(orderInfoPO.getPayPoint().add(pointPayMoney[0]));
        orderInfoPO.setPayRechargeCard(orderInfoPO.getPayRechargeCard().add(rechargeCardPayMoney[0]));
        orderInfoPO.setPayVoucher(orderInfoPO.getPayVoucher().add(voucherPay[0]));
        orderInfoPO.setCost(appendOrderDTO.getCost());
        orderInfoPO.setComments(appendOrderDTO.getComments());
        orderInfoPO.setTotalAmount(orderInfoPO.getProductPrice().multiply(new BigDecimal(orderInfoPO.getCount())));
        //追加订单，订单状态不改变
        //orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
        orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
    }

    @Override
    public Integer getCountByStatus(Byte status) {
        return orderInfoPOManualMapper.getCountByStatus(status);
    }

    //TODO
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
    public NeedPayResp placeOrder(PlaceOrderDTO dto) {
        List<OrderPaymentPO> orderPaymentPOList = Lists.newArrayList();
        CustomerDTO customerDTO = customerService.getById(dto.getCustomerId());
        if (null == customerDTO) {
            throw new OrderException(OrderCodeEnum.CUSTOMER_NOT_EXIST);
        }
        ProductDTO productDTO = productService.getById(dto.getProductId());
        if (null == productDTO || productDTO.getStatus().equals(Byte.valueOf("0"))) {
            throw new OrderException(OrderCodeEnum.PRODUCT_NOT_AVAILABLE);
        }
        CommonValidator.validateNull(dto.getCustomerCityCode(), new OrderException(OrderCodeEnum.CITY_INFO_ERROR));
        ProductPriceDTO productPriceDTO = productPriceService.getPriceByCity(dto.getProductId(), dto.getCustomerCityCode());
        if (null == productPriceDTO || productPriceDTO.getStatus().equals(Byte.valueOf("0"))) {
            throw new OrderException(OrderCodeEnum.PRODUCT_NOT_AVAILABLE);
        }
        if (productDTO.getLastNum() > dto.getCount()) {
            throw new OrderException(OrderCodeEnum.ORDER_COUNT_LESS_THAN_LAST_NUM);
        }
        //服务类商品，手机端下单才检查预约时间、服务地址，服务联系电话等信息
        if (dto.getType().equals(CommonConstant.CUSTOMER_PLACE_ORDER)) {
            if (CommonConstant.SERVICE_PRODUCT.equals(productDTO.getProductType())) {
                Long bookStartTime = DateUtil.getFirstSecond(System.currentTimeMillis() + productDTO.getDelayDays() * CommonConstant.ONE_DAY);
                Long bookEndTime = DateUtil.getLastSecond(System.currentTimeMillis() + (productDTO.getBookDays() + productDTO.getDelayDays()) * CommonConstant.ONE_DAY);
                if (dto.getServiceTime().getTime() > bookEndTime || dto.getServiceTime().getTime() < bookStartTime) {
                    throw new OrderException(OrderCodeEnum.SERVICE_TIME_ERROR);
                }
                CommonValidator.validateNull(dto.getCustomerAddress(), new OrderException(OrderCodeEnum.SERVICE_ADDRESS_IS_NULL));
                CommonValidator.validateNull(dto.getCustomerMobile(), new OrderException(OrderCodeEnum.SERVICE_MOBILE_IS_VALID));
            }
            if (CommonConstant.ELE_PRODUCT.equals(productDTO.getProductType())) {
                dto.setCustomerName("");
                dto.setCustomerAddress("");
                dto.setCustomerMobile("");
                dto.setServiceTime(new Date());
                dto.setRealServiceTime(new Date());
            }
        }
        String orderCode = generateOrderCode();
        //待支付金额
        BigDecimal[] needPay = {productPriceDTO.getPrice().multiply(new BigDecimal(dto.getCount()))};
        //代金券支付金额
        BigDecimal[] voucherPay = {BigDecimal.ZERO};
        //积分卡支付金额
        BigDecimal[] pointPayMoney = {BigDecimal.ZERO};
        //充值卡支付金额
        BigDecimal[] rechargeCardPayMoney = {BigDecimal.ZERO};
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
                if (bean.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_USED);
                }
                if (bean.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_STOP_USING);
                }
                if (bean.getExpiryTime().getTime() < System.currentTimeMillis()) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_EXPIRY);
                }
                if (bean.getEffectiveTime().getTime() > System.currentTimeMillis()) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_IS_NOT_EFFECTIVE);
                }
                if (!bean.getCityCodes().contains(dto.getCustomerCityCode())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_CITY);
                }
                if (!idsContainsId(bean.getProductIds(), dto.getProductId())) {
                    throw new OrderException(OrderCodeEnum.VOUCHER_NOT_SUPPORT_PRODUCT);
                }
                if (needPay[0].compareTo(BigDecimal.ZERO) == 1) {
                    bean.setUsed(CommonConstant.CODE_HAS_USED);
                    OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                    orderPaymentPO.setOrderCode(orderCode);
                    orderPaymentPO.setPayType(CommonConstant.PAY_VOUCHER);
                    orderPaymentPO.setVoucherId(bean.getId());
                    orderPaymentPO.setPayMoney(BigDecimal.ZERO);
                    orderPaymentPO.setSerialNumber("");
                    if (needPay[0].subtract(productPriceDTO.getPrice().multiply(new BigDecimal(bean.getCount()))).compareTo(BigDecimal.ZERO) >= 0) {
                        needPay[0] = needPay[0].subtract(productPriceDTO.getPrice().multiply(new BigDecimal(bean.getCount())));
                        voucherPay[0] = voucherPay[0].add(productPriceDTO.getPrice().multiply(new BigDecimal(bean.getCount())));
                    } else {
                        voucherPay[0] = voucherPay[0].add(needPay[0]);
                        needPay[0] = BigDecimal.ZERO;
                    }
                    orderPaymentPOList.add(orderPaymentPO);
                }
            });
            voucherService.batchChangeUsed(voucherIds, CommonConstant.CODE_HAS_USED);
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
                if (bean.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                    throw new OrderException(OrderCodeEnum.POINT_STOP_USING);
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
                    BigDecimal conversionRate = bean.getProjectDTO().getPointConversionRate();
                    if (needPay[0].compareTo(bean.getBalance().multiply(conversionRate)) == 1) {
                        thisPointPay = bean.getBalance();
                    } else {
                        //当【需支付的金额/兑换比例】不为整数时，则抛异常
                        if (needPay[0].divide(conversionRate).setScale(0, RoundingMode.HALF_UP).compareTo(needPay[0].divide(conversionRate)) != 0) {
                            throw new OrderException(OrderCodeEnum.POINT_PAY_DECIMAL_APPEAR);
                        }
                        thisPointPay = needPay[0].divide(conversionRate);
                    }
                    bean.setBalance(bean.getBalance().subtract(thisPointPay));
                    pointPayMoney[0] = pointPayMoney[0].add(thisPointPay.multiply(conversionRate));
                    OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
                    orderPaymentPO.setOrderCode(orderCode);
                    orderPaymentPO.setPayType(CommonConstant.PAY_POINT);
                    orderPaymentPO.setPointId(bean.getId());
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
                if (bean.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_STOP_USING);
                }
                if (bean.getExpiryTime().getTime() < System.currentTimeMillis()) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_EXPIRY);
                }
                if (bean.getEffectiveTime().getTime() > System.currentTimeMillis()) {
                    throw new OrderException(OrderCodeEnum.RECHARGE_CARD_IS_NOT_EFFECTIVE);
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
        if ("false".equals(dto.getCashSupport())) {
            if (needPay[0].compareTo(BigDecimal.ZERO) == 1) {
                throw new OrderException(OrderCodeEnum.ORDER_PAYMENT_NOT_ENOUGH);
            }
        }
        if (needPay[0].compareTo(BigDecimal.ZERO) == -1) {
            throw new OrderException(OrderCodeEnum.ORDER_OVER_PAYMENT);
        }
        //计算订单下单完成后应该置为什么状态
        OrderInfoPO orderInfoPO = new OrderInfoPO();
        orderInfoPO.setOrderCode(orderCode);
        orderInfoPO.setCustomerId(dto.getCustomerId());
        orderInfoPO.setProductId(dto.getProductId());
        orderInfoPO.setServiceProductId(dto.getServiceProductId());
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
        orderInfoPO.setExtInfo("");
        //使用代金券-->积分卡-->充值卡可以完成支付
        if (needPay[0].compareTo(BigDecimal.ZERO) == 0) {
            if (productDTO.getProductType().equals(CommonConstant.SERVICE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
            }
            if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
                orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
            }
        } else { //使用代金券-->积分卡-->充值卡不足以完成支付
            orderInfoPO.setStatus(CommonConstant.ORDER_UNPAID);
        }
        orderInfoPOManualMapper.insert(orderInfoPO);
        //支付完成并且是电子商品的话，将所选的电子码置为已发放状态，并记录相关的订单id和订单号
        if (needPay[0].compareTo(BigDecimal.ZERO) == 0 && productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
            String extInfo = sendEleProductCode(orderInfoPO.getProductId(), orderInfoPO.getCount(), orderInfoPO.getId(), orderInfoPO.getOrderCode());
            orderInfoPO.setExtInfo(extInfo);
            orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
        }
        orderPaymentPOList.stream().forEach(bean -> {
            bean.setOrderId(orderInfoPO.getId());
        });
        orderPaymentService.batchInsert(orderPaymentPOList);
        NeedPayResp needPayResp = new NeedPayResp();
        needPayResp.setOrderId(orderInfoPO.getId());
        needPayResp.setPayCash(needPay[0].multiply(new BigDecimal(100)).intValue());
        needPayResp.setOrderCode(orderInfoPO.getOrderCode());
        return needPayResp;
    }

    @Override
    public OrderInfoDTO getByOrderNo(String orderNo) {
        OrderInfoPOExample example = new OrderInfoPOExample();
        OrderInfoPOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        criteria.andOrderCodeEqualTo(orderNo);
        List<OrderInfoPO> poList = orderInfoPOMapper.selectByExample(example);
        if (poList.isEmpty()) {
            return null;
        }
        if (poList.size() > 1) {
            throw new OrderException(OrderCodeEnum.ORDER_CODE_REPEAT);
        }
        OrderInfoDTO orderInfoDTO = OrderInfoAdapter.PO2DTO(poList.get(0));
        orderInfoDTO.setPayment(calculateOrderNeedPay(orderInfoDTO));
        orderInfoDTO.setProductDTO(productService.getById(orderInfoDTO.getProductId()));
        return orderInfoDTO;
    }

    @Override
    public void wxNotify(String transactionId, String orderNo, Integer wxPay) {
        OrderInfoDTO orderInfoDTO = getByOrderNo(orderNo);
        OrderInfoPO orderInfoPO = OrderInfoAdapter.dto2Po(orderInfoDTO);
        orderInfoPO.setPayCash(new BigDecimal(wxPay).divide(new BigDecimal(100)));
        if (orderInfoDTO == null) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        if (!orderInfoDTO.getStatus().equals(CommonConstant.ORDER_UNPAID)) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_UNPAID);
        }
        if (orderInfoDTO.getPayment().multiply(new BigDecimal(100)).compareTo(new BigDecimal(wxPay)) != 0) {
            throw new OrderException(OrderCodeEnum.WECHAT_PAY_FEE_ERROR);
        }
        //服务型商品
        if (orderInfoDTO.getProductDTO().getProductType().equals(CommonConstant.SERVICE_PRODUCT)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
        }
        //电子码商品
        if (orderInfoDTO.getProductDTO().getProductType().equals(CommonConstant.ELE_PRODUCT)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
            //发放电子码
            String extInfo = sendEleProductCode(orderInfoDTO.getProductDTO().getId(), orderInfoPO.getCount(), orderInfoPO.getId(), orderInfoPO.getOrderCode());
            orderInfoPO.setExtInfo(extInfo);
            orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
        }
        OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
        orderPaymentPO.setPayMoney(new BigDecimal(wxPay).divide(new BigDecimal(100)));
        orderPaymentPO.setOrderId(orderInfoPO.getId());
        orderPaymentPO.setOrderCode(orderInfoPO.getOrderCode());
        orderPaymentPO.setSerialNumber(transactionId);
        orderPaymentService.insert(orderPaymentPO);
    }

    @Override
    public List<OrderInfoDTO> getList(String status, String orderCode, String mobile, String customerMobile, Date orderStartTime, Date orderEndTime, String workerMobile, Integer productId, Integer serviceProductId, Date realServiceStartTime, Date realServiceEndTime, String customerCityCode) {
        List<OrderInfoPO> orderInfoPOList = orderInfoPOManualMapper.query(status, orderCode, mobile, customerMobile, orderStartTime, orderEndTime, workerMobile, productId, serviceProductId, realServiceStartTime, realServiceEndTime, customerCityCode, null, null);
        List<OrderInfoDTO> orderInfoDTOList = orderInfoPOList.stream().map(OrderInfoAdapter::PO2DTO).collect(Collectors.toList());
        orderInfoDTOList.forEach(bean -> {
            ProductDTO productDTO = productService.getById(bean.getProductId());
            ProductDTO serviceProductDTO = productService.getById(bean.getServiceProductId());
            CustomerDTO customerDTO = customerService.getById(bean.getCustomerId());
            bean.setProductDTO(productDTO);
            bean.setServiceProductDTO(serviceProductDTO);
            bean.setCustomerDTO(customerDTO);
        });
        return orderInfoDTOList;
    }

    @Override
    public BigDecimal calculateOrderNeedPay(OrderInfoDTO dto) {
        Integer needPayCount = dto.getCount();
        BigDecimal needPay = dto.getProductPrice().multiply(new BigDecimal(needPayCount));
        BigDecimal hasPay = dto.getPayPoint().add(dto.getPayRechargeCard()).add(dto.getPayVoucher().add(dto.getPayCash()));
        BigDecimal left = needPay.subtract(hasPay);
        return left;
    }

    //大家保险下单
    @Transactional(rollbackFor = Exception.class)
    @Override
    public NeedPayResp djbxPlaceOrder(DJBXPlaceOrderDTO dto) {
        CustomerDTO customerDTO = customerService.getById(dto.getCustomerId());
        if (null == customerDTO) {
            throw new OrderException(OrderCodeEnum.CUSTOMER_NOT_EXIST);
        }
        ProductDTO productDTO = productService.getById(dto.getProductId());
        if (null == productDTO || productDTO.getStatus().equals(Byte.valueOf("0"))) {
            throw new OrderException(OrderCodeEnum.PRODUCT_NOT_AVAILABLE);
        }
        CommonValidator.validateNull(dto.getCustomerCityCode(), new OrderException(OrderCodeEnum.CITY_INFO_ERROR));
        ProductPriceDTO productPriceDTO = productPriceService.getPriceByCity(dto.getProductId(), dto.getCustomerCityCode());
        if (null == productPriceDTO || productPriceDTO.getStatus().equals(Byte.valueOf("0"))) {
            throw new OrderException(OrderCodeEnum.PRODUCT_NOT_AVAILABLE);
        }
        if (productDTO.getLastNum() > dto.getCount()) {
            throw new OrderException(OrderCodeEnum.ORDER_COUNT_LESS_THAN_LAST_NUM);
        }
        //服务类商品，大家保险端下单，检查预约时间、服务地址，服务联系电话等信息
        if (CommonConstant.SERVICE_PRODUCT.equals(productDTO.getProductType())) {
            Long bookStartTime = DateUtil.getFirstSecond(System.currentTimeMillis() + productDTO.getDelayDays() * CommonConstant.ONE_DAY);
            Long bookEndTime = DateUtil.getLastSecond(System.currentTimeMillis() + (productDTO.getBookDays() + productDTO.getDelayDays()) * CommonConstant.ONE_DAY);
            if (dto.getServiceTime().getTime() > bookEndTime || dto.getServiceTime().getTime() < bookStartTime) {
                throw new OrderException(OrderCodeEnum.SERVICE_TIME_ERROR);
            }
            CommonValidator.validateNull(dto.getCustomerAddress(), new OrderException(OrderCodeEnum.SERVICE_ADDRESS_IS_NULL));
            CommonValidator.validateNull(dto.getCustomerMobile(), new OrderException(OrderCodeEnum.SERVICE_MOBILE_IS_VALID));
        }
        if (CommonConstant.ELE_PRODUCT.equals(productDTO.getProductType())) {
            dto.setCustomerAddress("");
            dto.setCustomerMobile("");
            dto.setCustomerName("");
            dto.setServiceTime(new Date());
            dto.setRealServiceTime(new Date());
        }
        //大家保险订单号
        String orderCode = generateDJBXOrderCode();
        //待支付金额
        BigDecimal needPay = productPriceDTO.getPrice().multiply(new BigDecimal(dto.getCount()));
        PointsConsumeParam pointsConsumeParam = new PointsConsumeParam(dto.getAgentCode(), orderCode, DJBXConstant.DJBX_TRANSACTIONTYPE_CONSUME, DJBXConstant.DJBX_SETTLEMENTTYPE_NEED, needPay, dto.getVerifiCode());
        OrderInfoPO orderInfoPO = new OrderInfoPO();
        OrderPaymentPO orderPaymentPO = new OrderPaymentPO();
        //添加订单
        orderInfoPO.setOrderCode(orderCode);
        orderInfoPO.setCustomerId(dto.getCustomerId());
        orderInfoPO.setProductId(dto.getProductId());
        orderInfoPO.setServiceProductId(dto.getServiceProductId());
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
        orderInfoPO.setPayPoint(BigDecimal.ZERO);
        orderInfoPO.setPayRechargeCard(BigDecimal.ZERO);
        orderInfoPO.setPayVoucher(BigDecimal.ZERO);
        orderInfoPO.setPayCash(needPay);
        orderInfoPO.setTotalAmount(productPriceDTO.getPrice().multiply(new BigDecimal(dto.getCount())));
        orderInfoPO.setCost(dto.getCost());
        orderInfoPO.setComments(dto.getComments());
        orderInfoPO.setType(dto.getType());
        orderInfoPO.setExtInfo("");
        //添加支付记录
        orderPaymentPO.setOrderCode(orderCode);
        orderPaymentPO.setPayType(CommonConstant.PAY_DJBX);
        orderPaymentPO.setPayMoney(needPay);
        orderPaymentPO.setSerialNumber("");
        //如果是服务类型商品
        if (productDTO.getProductType().equals(CommonConstant.SERVICE_PRODUCT)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_UNSENT);
        }
        //如果是电子码商品
        if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_COMPLETE);
        }
        orderInfoPOManualMapper.insert(orderInfoPO);
        //电子商品的话，发放电子码，并记录相关的电子码信息
        if (productDTO.getProductType().equals(CommonConstant.ELE_PRODUCT)) {
            String extInfo = sendEleProductCode(dto.getProductId(), dto.getCount(), orderInfoPO.getId(), orderInfoPO.getOrderCode());
            orderInfoPO.setExtInfo(extInfo);
            orderInfoPOMapper.updateByPrimaryKeySelective(orderInfoPO);
        }
        orderPaymentPO.setOrderId(orderInfoPO.getId());
        orderPaymentService.insert(orderPaymentPO);
        //扣分
        djbxBiz.consumePoints(pointsConsumeParam);
        NeedPayResp needPayResp = new NeedPayResp();
        needPayResp.setOrderId(orderInfoPO.getId());
        needPayResp.setPayCash(Integer.valueOf(0));
        needPayResp.setOrderCode(orderInfoPO.getOrderCode());
        return needPayResp;
    }

    //发放兑换码逻辑，支持并发操作，返回json格式的兑换码字符串
    public String sendEleProductCode(Integer productId, Integer count, Integer orderId, String orderCode) {
        synchronized (String.valueOf(productId).intern()) {
            List<Integer> ids = new ArrayList<>(count);
            if (eleProductMap == null) {
                eleProductMap = Maps.newHashMap();
            }
            if (MapUtils.isEmpty(eleProductMap) || !eleProductMap.containsKey(productId)) {
                eleProductMap.put(productId, Collections.newSetFromMap(Maps.newHashMap()));
            }
            if (CollectionUtils.isEmpty(eleProductMap.get(productId)) || eleProductMap.get(productId).size() < count) {
                //下单数量大于10，直接去获取count个，否则一次获取10个
                List<EleProductCodeDTO> eleProductCodeDTOS = getNEleProductCode(productId, count > 10 ? count : 10);
                if (CollectionUtils.isNotEmpty(eleProductCodeDTOS)) {
                    eleProductCodeDTOS.forEach(item -> {
                        eleProductMap.get(productId).add(item);
                    });
                }
            }
            //如果此时还是小于购买数量，抛出库存不足异常
            if (eleProductMap.get(productId).size() < count) {
                throw new OrderException(OrderCodeEnum.ELE_PRODUCT_CODE_NOT_ENOUGH);
            }
            JSONArray jsonArray = new JSONArray();
            while (count-- > 0) {
                Set<EleProductCodeDTO> codeSet = eleProductMap.get(productId);
                EleProductCodeDTO eleProductCodeDTO = codeSet.iterator().next();
                if (eleProductCodeDTO != null) {
                    ids.add(eleProductCodeDTO.getId());
                    JSONObject json = new JSONObject();
                    json.put("code", eleProductCodeDTO.getCode());
                    json.put("keyt", eleProductCodeDTO.getKeyt());
                    jsonArray.add(json);
                    codeSet.remove(eleProductCodeDTO);
                }
            }
            eleProductCodeService.batchSendOut(ids, orderId, orderCode);
            return jsonArray.toString();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void djbxCancelOrder(Integer id, String verifiCode) {
        OrderInfoPO orderInfoPO = orderInfoPOMapper.selectByPrimaryKey(id);
        CustomerDTO customerDTO = customerService.getById(orderInfoPO.getCustomerId());
        //大家保险订单为待付款或待派单状态，直接走退款流程
        if (orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNPAID) || orderInfoPO.getStatus().equals(CommonConstant.ORDER_UNSENT)) {
            orderInfoPO.setStatus(CommonConstant.ORDER_CANCEL);

            orderRefund(id);
        } else {
            throw new OrderException(OrderCodeEnum.ORDER_CAN_NOT_CANCEL);
        }
        orderInfoPOMapper.updateByPrimaryKey(orderInfoPO);
        PointsConsumeParam pointsConsumeParam = new PointsConsumeParam(customerDTO.getName(), orderInfoPO.getOrderCode(), DJBXConstant.DJBX_TRANSACTIONTYPE_BACK, DJBXConstant.DJBX_SETTLEMENTTYPE_NEED, orderInfoPO.getTotalAmount(), verifiCode);
        //先掉用大家保险将积分冲正
        djbxBiz.consumePoints(pointsConsumeParam);
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
            voucherService.batchChangeUsed(voucherIds, CommonConstant.CODE_NOT_USED);
        }
        if (!orderRefundDTOList.isEmpty()) {
            orderRefundService.batchInsert(orderRefundDTOList);
        }
    }

    /**
     * 根据电子商品id，定量获取电子码，不一定能获取到这么多
     *
     * @param productId
     * @param count
     * @return
     */
    private List<EleProductCodeDTO> getNEleProductCode(Integer productId, Integer count) {
        logger.info("数据库查询了商品id为{}的电子码{}个", productId, count);
        List<EleProductCodeDTO> eleProductCodeDTOList = eleProductCodeService.selectTopN(productId, count);
        return eleProductCodeDTOList;
    }

    /**
     * 生成订单号13位： yyyyMMddHH+3位序列号
     */
    private String generateOrderCode() {
        Long beginHour = (System.currentTimeMillis() / CommonConstant.ONE_HOUR) * CommonConstant.ONE_HOUR;
        Long endHour = beginHour + CommonConstant.ONE_HOUR;
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
     * 生成大家保险订单号18位 : yyyyMMddHH + 3位序列号 + 5位随机数
     */
    private String generateDJBXOrderCode() {
        return generateOrderCode() + RandomUtil.generateNumber(5);
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
