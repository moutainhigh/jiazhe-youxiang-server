package com.jiazhe.youxiang.server.service.impl.order;

import com.jiazhe.youxiang.server.adapter.order.OrderRefundAdapter;
import com.jiazhe.youxiang.server.dao.mapper.OrderRefundPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.order.OrderRefundPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderRefundPO;
import com.jiazhe.youxiang.server.dto.order.orderrefund.OrderRefundDTO;
import com.jiazhe.youxiang.server.service.order.OrderRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/11/9.
 */
@Service("orderRefundService")
public class OrderRefundServiceImpl implements OrderRefundService {

    @Autowired
    private OrderRefundPOManualMapper orderRefundPOManualMapper;
    @Autowired
    private OrderRefundPOMapper orderRefundPOMapper;

    @Override
    public void batchInsert(List<OrderRefundDTO> orderRefundDTOList) {
        orderRefundPOManualMapper.batchInsert(orderRefundDTOList.stream().map(OrderRefundAdapter::DTO2PO).collect(Collectors.toList()));
    }

    @Override
    public void insert(OrderRefundPO orderRefundPO) {
        orderRefundPOMapper.insertSelective(orderRefundPO);
    }
}
