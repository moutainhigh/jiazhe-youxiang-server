package com.jiazhe.youxiang.server.dao.mapper.manual.order;

import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/10
 */
public interface OrderPaymentPOManualMapper {

    /**
     * 批量插入付款信息
     * @param orderPaymentPOList
     */
    void batchInsert(List<OrderPaymentPO> orderPaymentPOList);
}
