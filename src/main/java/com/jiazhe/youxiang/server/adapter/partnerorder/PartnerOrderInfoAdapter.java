package com.jiazhe.youxiang.server.adapter.partnerorder;

import com.jiazhe.youxiang.server.adapter.PartnerAdapter;
import com.jiazhe.youxiang.server.adapter.ServiceItemAdapter;
import com.jiazhe.youxiang.server.domain.po.PartnerOrderInfoPO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.vo.req.partnerorder.PartnerOrderSaveReq;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.PartnerOrderInfoResp;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public class PartnerOrderInfoAdapter {

    public static PartnerOrderInfoResp DTO2Resp(PartnerOrderInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        PartnerOrderInfoResp partnerOrderInfoResp = new PartnerOrderInfoResp();
        partnerOrderInfoResp.setId(dto.getId());
        partnerOrderInfoResp.setCustomerName(dto.getCustomerName());
        partnerOrderInfoResp.setCustomerMobile(dto.getCustomerMobile());
        partnerOrderInfoResp.setCustomerAddress(dto.getCustomerAddress());
        partnerOrderInfoResp.setCustomerCityCode(dto.getCustomerCityCode());
        partnerOrderInfoResp.setCustomerCityName(dto.getCustomerCityName());
        partnerOrderInfoResp.setKeyt(dto.getKeyt());
        partnerOrderInfoResp.setOrderTime(dto.getOrderTime().getTime());
        partnerOrderInfoResp.setServiceTime(dto.getServiceTime().getTime());
        partnerOrderInfoResp.setProductType(dto.getProductType());
        partnerOrderInfoResp.setWorkerName(dto.getWorkerName());
        partnerOrderInfoResp.setWorkerMobile(dto.getWorkerMobile());
        partnerOrderInfoResp.setServiceItemId(dto.getServiceItemId());
        partnerOrderInfoResp.setServiceItemResp(ServiceItemAdapter.DTO2Resp(dto.getServiceItemDTO()));
        partnerOrderInfoResp.setPrePay(dto.getPrePay());
        partnerOrderInfoResp.setAppendPay(dto.getAppendPay());
        partnerOrderInfoResp.setRemark(dto.getRemark());
        partnerOrderInfoResp.setPartnerId(dto.getPartnerId());
        partnerOrderInfoResp.setPartnerResp(PartnerAdapter.DTO2Resp(dto.getPartnerDTO()));
        partnerOrderInfoResp.setStatus(dto.getStatus());
        partnerOrderInfoResp.setExtInfo(dto.getExtInfo());
        partnerOrderInfoResp.setIsDeleted(dto.getIsDeleted());
        partnerOrderInfoResp.setAddTime(dto.getAddTime().getTime());
        partnerOrderInfoResp.setModTime(dto.getModTime().getTime());
        return partnerOrderInfoResp;
    }

    public static PartnerOrderInfoDTO PO2DTO(PartnerOrderInfoPO partnerOrderInfoPO) {
        if (partnerOrderInfoPO == null) {
            return null;
        }
        PartnerOrderInfoDTO partnerOrderInfoDTO = new PartnerOrderInfoDTO();
        partnerOrderInfoDTO.setId(partnerOrderInfoPO.getId());
        partnerOrderInfoDTO.setCustomerName(partnerOrderInfoPO.getCustomerName());
        partnerOrderInfoDTO.setCustomerMobile(partnerOrderInfoPO.getCustomerMobile());
        partnerOrderInfoDTO.setCustomerAddress(partnerOrderInfoPO.getCustomerAddress());
        partnerOrderInfoDTO.setCustomerCityCode(partnerOrderInfoPO.getCustomerCityCode());
        partnerOrderInfoDTO.setCustomerCityName(partnerOrderInfoPO.getCustomerCityName());
        partnerOrderInfoDTO.setKeyt(partnerOrderInfoPO.getKeyt());
        partnerOrderInfoDTO.setOrderTime(partnerOrderInfoPO.getOrderTime());
        partnerOrderInfoDTO.setServiceTime(partnerOrderInfoPO.getServiceTime());
        partnerOrderInfoDTO.setProductType(partnerOrderInfoPO.getProductType());
        partnerOrderInfoDTO.setWorkerName(partnerOrderInfoPO.getWorkerName());
        partnerOrderInfoDTO.setWorkerMobile(partnerOrderInfoPO.getWorkerMobile());
        partnerOrderInfoDTO.setServiceItemId(partnerOrderInfoPO.getServiceItemId());
        partnerOrderInfoDTO.setPrePay(partnerOrderInfoPO.getPrePay());
        partnerOrderInfoDTO.setAppendPay(partnerOrderInfoPO.getAppendPay());
        partnerOrderInfoDTO.setRemark(partnerOrderInfoPO.getRemark());
        partnerOrderInfoDTO.setPartnerId(partnerOrderInfoPO.getPartnerId());
        partnerOrderInfoDTO.setStatus(partnerOrderInfoPO.getStatus());
        partnerOrderInfoDTO.setExtInfo(partnerOrderInfoPO.getExtInfo());
        partnerOrderInfoDTO.setIsDeleted(partnerOrderInfoPO.getIsDeleted());
        partnerOrderInfoDTO.setAddTime(partnerOrderInfoPO.getAddTime());
        partnerOrderInfoDTO.setModTime(partnerOrderInfoPO.getModTime());
        return partnerOrderInfoDTO;
    }

    public static PartnerOrderInfoDTO saveReq2DTO(PartnerOrderSaveReq req) {
        if (req == null) {
            return null;
        }
        PartnerOrderInfoDTO partnerOrderInfoDTO = new PartnerOrderInfoDTO();
        partnerOrderInfoDTO.setId(req.getId());
        partnerOrderInfoDTO.setCustomerName(req.getCustomerName());
        partnerOrderInfoDTO.setCustomerMobile(req.getCustomerMobile());
        partnerOrderInfoDTO.setCustomerAddress(req.getCustomerAddress());
        partnerOrderInfoDTO.setCustomerCityCode(req.getCustomerCityCode());
        partnerOrderInfoDTO.setCustomerCityName(req.getCustomerCityName());
        partnerOrderInfoDTO.setKeyt(req.getKeyt());
        partnerOrderInfoDTO.setOrderTime(new Date(req.getOrderTime()));
        partnerOrderInfoDTO.setServiceTime(new Date(req.getServiceTime()));
        partnerOrderInfoDTO.setProductType(req.getProductType());
        partnerOrderInfoDTO.setWorkerName(req.getWorkerName());
        partnerOrderInfoDTO.setWorkerMobile(req.getWorkerMobile());
        partnerOrderInfoDTO.setServiceItemId(req.getServiceItemId());
        partnerOrderInfoDTO.setPrePay(req.getPrePay());
        partnerOrderInfoDTO.setAppendPay(req.getAppendPay());
        partnerOrderInfoDTO.setRemark(req.getRemark());
        partnerOrderInfoDTO.setPartnerId(req.getPartnerId());
        partnerOrderInfoDTO.setStatus(req.getStatus());
        return partnerOrderInfoDTO;
    }

    public static PartnerOrderInfoPO DTO2PO(PartnerOrderInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        PartnerOrderInfoPO partnerOrderInfoPO = new PartnerOrderInfoPO();
        partnerOrderInfoPO.setId(dto.getId());
        partnerOrderInfoPO.setCustomerName(dto.getCustomerName());
        partnerOrderInfoPO.setCustomerMobile(dto.getCustomerMobile());
        partnerOrderInfoPO.setCustomerAddress(dto.getCustomerAddress());
        partnerOrderInfoPO.setCustomerCityCode(dto.getCustomerCityCode());
        partnerOrderInfoPO.setCustomerCityName(dto.getCustomerCityName());
        partnerOrderInfoPO.setKeyt(dto.getKeyt());
        partnerOrderInfoPO.setOrderTime(dto.getOrderTime());
        partnerOrderInfoPO.setServiceTime(dto.getServiceTime());
        partnerOrderInfoPO.setProductType(dto.getProductType());
        partnerOrderInfoPO.setWorkerName(dto.getWorkerName());
        partnerOrderInfoPO.setWorkerMobile(dto.getWorkerMobile());
        partnerOrderInfoPO.setServiceItemId(dto.getServiceItemId());
        partnerOrderInfoPO.setPrePay(dto.getPrePay());
        partnerOrderInfoPO.setAppendPay(dto.getAppendPay());
        partnerOrderInfoPO.setRemark(dto.getRemark());
        partnerOrderInfoPO.setPartnerId(dto.getPartnerId());
        partnerOrderInfoPO.setStatus(dto.getStatus());
        partnerOrderInfoPO.setExtInfo(dto.getExtInfo());
        partnerOrderInfoPO.setIsDeleted(dto.getIsDeleted());
        partnerOrderInfoPO.setAddTime(dto.getAddTime());
        partnerOrderInfoPO.setModTime(dto.getModTime());
        return partnerOrderInfoPO;
    }
}
