package com.jiazhe.youxiang.server.service.order;

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
}
