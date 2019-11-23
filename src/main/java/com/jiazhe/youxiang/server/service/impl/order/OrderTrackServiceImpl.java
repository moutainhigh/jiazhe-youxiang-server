package com.jiazhe.youxiang.server.service.impl.order;

import com.jiazhe.youxiang.server.adapter.order.OrderTrackAdapter;
import com.jiazhe.youxiang.server.dao.mapper.OrderTrackPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.order.OrderTrackPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderTrackPO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderTrackDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.order.OrderTrackService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/23 13:27
 */
@Service("orderTrackService")
public class OrderTrackServiceImpl implements OrderTrackService {

    @Autowired
    private OrderTrackPOMapper orderTrackPOMapper;
    @Autowired
    private OrderTrackPOManualMapper orderTrackPOManualMapper;

    @Override
    public List<OrderTrackDTO> getList(Integer orderId) {
        List<OrderTrackPO> list = orderTrackPOManualMapper.getList(orderId);
        return list.stream().map(OrderTrackAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void create(OrderTrackDTO orderTrackDTO) {
        OrderTrackPO orderTrackPO = OrderTrackAdapter.DTO2PO(orderTrackDTO);
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        orderTrackPO.setUsername(sysUserDTO.getLoginName());
        orderTrackPOMapper.insertSelective(orderTrackPO);
    }
}
