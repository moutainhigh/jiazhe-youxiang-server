package com.jiazhe.youxiang.server.service.order;

import com.jiazhe.youxiang.server.domain.po.OrderRefundPO;
import com.jiazhe.youxiang.server.dto.order.orderrefund.OrderRefundDTO;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/9.
 */
public interface OrderRefundService {

    /**
     * 批量插入退款信息
     * @param orderRefundDTOList
     */
    void batchInsert(List<OrderRefundDTO> orderRefundDTOList);

    /**
     * 插入退款记录，不用返回id
     *
     * @param orderRefundPO
     */
    void insert(OrderRefundPO orderRefundPO);

    /**
     * 根据微信退款单号查询
     * @param refundId
     * @return
     */
    List<OrderRefundDTO> getBySerialNumber(String refundId);
}
