package com.jiazhe.youxiang.server.service.order;

import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/7
 */
public interface OrderPaymentService {
    /**
     * 通过充值卡查看付款记录
     *
     * @param id
     * @return
     */
    List<OrderPaymentDTO> getByRechargeCardId(Integer id);

    /**
     * 通过充值卡查看付款记录
     *
     * @param id
     * @return
     */
    List<OrderPaymentDTO> getByVoucherId(Integer id);

    /**
     * 通过订单id，查找消费记录
     *
     * @param id
     * @return
     */
    List<OrderPaymentDTO> getByOrderId(Integer id);

    /**
     * 插入付款记录，不用返回id
     *
     * @param orderPaymentPO
     */
    void insert(OrderPaymentPO orderPaymentPO);

    /**
     * 批量插入付款信息
     * @param orderPaymentPOList
     */
    void batchInsert(List<OrderPaymentPO> orderPaymentPOList);

    List<OrderPaymentDTO> getByPointId(Integer id);
}
