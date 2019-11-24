package com.jiazhe.youxiang.server.adapter.order;

import com.jiazhe.youxiang.server.common.enums.OrderOpreationTypeEnum;
import com.jiazhe.youxiang.server.domain.po.OrderTrackPO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderTrackDTO;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/23 12:53
 */
public class OrderTrackAdapter {
    public static OrderTrackDTO PO2DTO(OrderTrackPO orderTrackPO) {
        if (orderTrackPO == null) {
            return null;
        }
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        orderTrackDTO.setId(orderTrackPO.getId());
        orderTrackDTO.setOrderid(orderTrackPO.getOrderid());
        orderTrackDTO.setOpreation(OrderOpreationTypeEnum.getByCode(orderTrackPO.getOpreation()));
        orderTrackDTO.setUsername(orderTrackPO.getUsername());
        orderTrackDTO.setExtInfo(orderTrackPO.getExtInfo());
        orderTrackDTO.setIsDeleted(orderTrackPO.getIsDeleted());
        orderTrackDTO.setAddTime(orderTrackPO.getAddTime());
        orderTrackDTO.setModTime(orderTrackPO.getModTime());
        orderTrackDTO.setMsg(orderTrackPO.getMsg());
        return orderTrackDTO;
    }

    public static OrderTrackPO DTO2PO(OrderTrackDTO orderTrackDTO) {
        if (orderTrackDTO == null) {
            return null;
        }
        OrderTrackPO orderTrackPO = new OrderTrackPO();
        orderTrackPO.setId(orderTrackDTO.getId());
        orderTrackPO.setOrderid(orderTrackDTO.getOrderid());
        OrderOpreationTypeEnum opreationTypeEnum = orderTrackDTO.getOpreation();
        if (opreationTypeEnum != null)
            orderTrackPO.setOpreation(opreationTypeEnum.getCode());
        orderTrackPO.setUsername(orderTrackDTO.getUsername());
        orderTrackPO.setExtInfo(orderTrackDTO.getExtInfo());
        orderTrackPO.setIsDeleted(orderTrackDTO.getIsDeleted());
        orderTrackPO.setAddTime(orderTrackDTO.getAddTime());
        orderTrackPO.setModTime(orderTrackDTO.getModTime());
        orderTrackPO.setMsg(orderTrackDTO.getMsg());
        return orderTrackPO;
    }
}
