package com.jiazhe.youxiang.server.adapter.partnerorder;

import com.jiazhe.youxiang.server.common.enums.OrderOpreationTypeEnum;
import com.jiazhe.youxiang.server.domain.po.PartnerOrderTrackPO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderTrackDTO;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/23 12:53
 */
public class PartnerOrderTrackAdapter {
    public static PartnerOrderTrackDTO PO2DTO( PartnerOrderTrackPO orderTrackPO) {
        if (orderTrackPO == null) {
            return null;
        }
        PartnerOrderTrackDTO orderTrackDTO = new PartnerOrderTrackDTO();
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

    public static PartnerOrderTrackPO DTO2PO(PartnerOrderTrackDTO orderTrackDTO) {
        if (orderTrackDTO == null) {
            return null;
        }
        PartnerOrderTrackPO orderTrackPO = new PartnerOrderTrackPO();
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
