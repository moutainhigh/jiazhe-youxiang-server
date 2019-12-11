package com.jiazhe.youxiang.server.service.order;

import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderTrackDTO;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/23 13:00
 */
public interface OrderTrackService {

    List<OrderTrackDTO> getList(Integer orderId);

    void create(OrderTrackDTO orderTrackDTO);

}
