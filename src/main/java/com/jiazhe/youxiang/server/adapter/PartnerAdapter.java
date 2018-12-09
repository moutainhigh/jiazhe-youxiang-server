package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.PartnerPO;
import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;
import com.jiazhe.youxiang.server.vo.resp.partner.PartnerResp;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public class PartnerAdapter {
    public static PartnerDTO PO2DTO(PartnerPO po) {
        if (po == null) {
            return null;
        }
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setId(po.getId());
        partnerDTO.setName(po.getName());
        partnerDTO.setStatus(po.getStatus());
        partnerDTO.setExtInfo(po.getExtInfo());
        partnerDTO.setIsDeleted(po.getIsDeleted());
        partnerDTO.setAddTime(po.getAddTime());
        partnerDTO.setModTime(po.getModTime());
        return partnerDTO;
    }

    public static PartnerResp DTO2Resp(PartnerDTO partnerDTO) {
        if (partnerDTO == null) {
            return null;
        }
        PartnerResp partnerResp = new PartnerResp();
        partnerResp.setId(partnerDTO.getId());
        partnerResp.setName(partnerDTO.getName());
        partnerResp.setStatus(partnerDTO.getStatus());
        partnerResp.setExtInfo(partnerDTO.getExtInfo());
        partnerResp.setIsDeleted(partnerDTO.getIsDeleted());
        partnerResp.setAddTime(partnerDTO.getAddTime().getTime());
        partnerResp.setModTime(partnerDTO.getModTime().getTime());
        return partnerResp;
    }
}
