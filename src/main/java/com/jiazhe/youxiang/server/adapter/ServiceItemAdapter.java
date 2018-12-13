package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.ServiceItemPO;
import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;
import com.jiazhe.youxiang.server.vo.resp.serviceitem.ServiceItemResp;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public class ServiceItemAdapter {
    public static ServiceItemDTO PO2DTO(ServiceItemPO po) {
        if (po == null) {
            return null;
        }
        ServiceItemDTO serviceItemDTO = new ServiceItemDTO();
        serviceItemDTO.setId(po.getId());
        serviceItemDTO.setName(po.getName());
        serviceItemDTO.setStatus(po.getStatus());
        serviceItemDTO.setExtInfo(po.getExtInfo());
        serviceItemDTO.setIsDeleted(po.getIsDeleted());
        serviceItemDTO.setAddTime(po.getAddTime());
        serviceItemDTO.setModTime(po.getModTime());
        return serviceItemDTO;
    }

    public static ServiceItemResp DTO2Resp(ServiceItemDTO serviceItemDTO) {
        if (serviceItemDTO == null) {
            return null;
        }
        ServiceItemResp serviceItemResp = new ServiceItemResp();
        serviceItemResp.setId(serviceItemDTO.getId());
        serviceItemResp.setName(serviceItemDTO.getName());
        serviceItemResp.setStatus(serviceItemDTO.getStatus());
        serviceItemResp.setExtInfo(serviceItemDTO.getExtInfo());
        serviceItemResp.setIsDeleted(serviceItemDTO.getIsDeleted());
        serviceItemResp.setAddTime(serviceItemDTO.getAddTime().getTime());
        serviceItemResp.setModTime(serviceItemDTO.getModTime().getTime());
        return serviceItemResp;
    }
}
