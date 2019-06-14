package com.jiazhe.youxiang.server.service.impl.order;

import com.jiazhe.youxiang.server.adapter.order.OrderPaymentAdapter;
import com.jiazhe.youxiang.server.dao.mapper.OrderPaymentPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.order.OrderPaymentPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderPaymentPO;
import com.jiazhe.youxiang.server.domain.po.OrderPaymentPOExample;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.service.order.OrderPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/11/7
 */
@Service("orderPaymentService")
public class OrderPaymentServiceImpl implements OrderPaymentService {

    @Autowired
    private OrderPaymentPOMapper orderPaymentPOMapper;
    @Autowired
    private OrderPaymentPOManualMapper orderPaymentPOManualMapper;
    @Autowired
    private OrderInfoService orderInfoService;

    @Override
    public List<OrderPaymentDTO> getByRechargeCardId(Integer id) {
        OrderPaymentPOExample example = new OrderPaymentPOExample();
        OrderPaymentPOExample.Criteria criteria = example.createCriteria();
        criteria.andRechargeCardIdEqualTo(id);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<OrderPaymentPO> poList = orderPaymentPOMapper.selectByExample(example);
        List<OrderPaymentDTO> orderPaymentDTOList = poList.stream().map(OrderPaymentAdapter::PO2DTO).collect(Collectors.toList());
        orderPaymentDTOList.forEach(bean -> {
            bean.setOrderInfoDTO(orderInfoService.getById(bean.getOrderId()));
        });
        return orderPaymentDTOList;
    }

    @Override
    public List<OrderPaymentDTO> getByVoucherId(Integer id) {
        OrderPaymentPOExample example = new OrderPaymentPOExample();
        OrderPaymentPOExample.Criteria criteria = example.createCriteria();
        criteria.andVoucherIdEqualTo(id);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<OrderPaymentPO> poList = orderPaymentPOMapper.selectByExample(example);
        List<OrderPaymentDTO> orderPaymentDTOList = poList.stream().map(OrderPaymentAdapter::PO2DTO).collect(Collectors.toList());
        orderPaymentDTOList.forEach(bean -> {
            bean.setOrderInfoDTO(orderInfoService.getById(bean.getOrderId()));
        });
        return orderPaymentDTOList;
    }

    @Override
    public List<OrderPaymentDTO> getByOrderId(Integer id) {
        OrderPaymentPOExample example = new OrderPaymentPOExample();
        OrderPaymentPOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(id);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<OrderPaymentPO> poList = orderPaymentPOMapper.selectByExample(example);
        List<OrderPaymentDTO> orderPaymentDTOList = poList.stream().map(OrderPaymentAdapter::PO2DTO).collect(Collectors.toList());
        return orderPaymentDTOList;
    }

    @Override
    public void insert(OrderPaymentPO orderPaymentPO) {
        orderPaymentPOMapper.insertSelective(orderPaymentPO);
    }

    @Override
    public void batchInsert(List<OrderPaymentPO> orderPaymentPOList) {
        if(!orderPaymentPOList.isEmpty()){
            orderPaymentPOManualMapper.batchInsert(orderPaymentPOList);
        }
    }

    @Override
    public List<OrderPaymentDTO> getByPointId(Integer id) {
        OrderPaymentPOExample example = new OrderPaymentPOExample();
        OrderPaymentPOExample.Criteria criteria = example.createCriteria();
        criteria.andPointIdEqualTo(id);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<OrderPaymentPO> poList = orderPaymentPOMapper.selectByExample(example);
        List<OrderPaymentDTO> orderPaymentDTOList = poList.stream().map(OrderPaymentAdapter::PO2DTO).collect(Collectors.toList());
        orderPaymentDTOList.forEach(bean -> {
            bean.setOrderInfoDTO(orderInfoService.getById(bean.getOrderId()));
        });
        return orderPaymentDTOList;
    }
}
