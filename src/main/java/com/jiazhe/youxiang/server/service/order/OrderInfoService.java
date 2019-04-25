package com.jiazhe.youxiang.server.service.order;

import com.jiazhe.youxiang.server.dto.order.orderinfo.AppendOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.PlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.UserReservationOrderDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/7.
 */
public interface OrderInfoService {
    /**
     * 后台通过参数分页查询订单信息
     *
     * @param status
     * @param orderCode
     * @param mobile         下单人手机号
     * @param customerMobile 收货人手机号
     * @param orderStartTime
     * @param orderEndTime
     * @param workerMobile
     * @param paging
     * @return
     */
    List<OrderInfoDTO> getList(String status, String orderCode, String mobile, String customerMobile, Date orderStartTime, Date orderEndTime, String workerMobile, Integer productId, Date realServiceStartTime, Date realServiceEndTime,Paging paging);

    /**
     * 获取订单信息
     *
     * @param id
     * @return
     */
    OrderInfoDTO getById(Integer id);

    /**
     * 客户取消订单
     *
     * @param id
     */
    void customerCancelOrder(Integer id);

    /**
     * 支付订单，并返回订单待支付金额，同时更新订单的状态
     *
     * @param orderId
     * @param payCash
     * @return
     */
    BigDecimal customerPay(Integer orderId, BigDecimal payCash, String serialNumber);

    /**
     * 订单取消审核通过
     * @param id
     */
    void orderCancelPass(Integer id);

    /**
     * 订单取消审核不通过
     * @param id
     * @param auditReason 不通过理由
     */
    void orderCancelUnpass(Integer id,String auditReason);

    /**
     * 员工直接后台取消订单【除了已完成状态订单，其余都能取消】
     * @param id
     */
    void userCancelOrder(Integer id);

    /**
     * 员工完成订单，所有状态下的订单都能置为已完成状态
     * @param id
     */
    void userCompleteOrder(Integer id);

    /**
     * 下单
     * @param dto
     */
    NeedPayResp placeOrder(PlaceOrderDTO dto) ;

    /**
     * 预约订单
     * @param dto
     */
    void userReservationOrder(UserReservationOrderDTO dto);

    /**
     * 用户修改预约信息
     * @param dto
     */
    void userChangeReservationInfo(UserReservationOrderDTO dto);

    /**
     * 追加订单
     * @param appendOrderDTO
     */
    void appendOrder(AppendOrderDTO appendOrderDTO);

    /**
     *根据订单状态，获取订单个数
     * @param status
     * @return
     */
    Integer getCountByStatus(Byte status);

    /**
     * 付款前检查，如果是电子商品，检查该兑换码数量是否足够
     * @param id
     */
    void prePaymentCheck(Integer id);

    /**
     * 通过订单号查找订单
     * @param orderNo
     * @return
     */
    OrderInfoDTO getByOrderNo(String orderNo);

    /**
     * 微信支付成功后，通知
     * @param orderNo
     * @param wxPay
     */
    void wxNotify(String orderNo, BigDecimal wxPay);
}
