package com.jiazhe.youxiang.server.dao.mapper.manual.order;

import com.jiazhe.youxiang.server.domain.po.OrderRefundPO;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/9.
 */
public interface OrderRefundPOManualMapper {

    /**
     * 批量插入退款信息
     * @param collect
     */
    void batchInsert(List<OrderRefundPO> collect);
}
