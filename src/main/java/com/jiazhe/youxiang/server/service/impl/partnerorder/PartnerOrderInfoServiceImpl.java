package com.jiazhe.youxiang.server.service.impl.partnerorder;

import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.adapter.partnerorder.PartnerOrderInfoAdapter;
import com.jiazhe.youxiang.server.dao.mapper.PartnerOrderInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.partnerorder.PartnerOrderInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import com.jiazhe.youxiang.server.domain.po.PartnerOrderInfoPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;
import com.jiazhe.youxiang.server.service.PartnerService;
import com.jiazhe.youxiang.server.service.ServiceItemService;
import com.jiazhe.youxiang.server.service.partnerorder.PartnerOrderInfoService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
@Service("partnerOrderInfoService")
@Transactional(rollbackFor = Exception.class)
public class PartnerOrderInfoServiceImpl implements PartnerOrderInfoService{

    @Autowired
    private PartnerOrderInfoPOMapper partnerOrderInfoPOMapper;
    @Autowired
    private PartnerOrderInfoPOManualMapper partnerOrderInfoPOManualMapper;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private ServiceItemService serviceItemService;


    @Override
    public List<PartnerOrderInfoDTO> getList(Byte status, String customerCityCode, Integer partnerId, Integer serviceItemId, Date serviceTimeStart, Date serviceTimeEnd, String customerMobile, Paging paging) {
        Integer count = partnerOrderInfoPOManualMapper.count(status, customerCityCode, partnerId, serviceItemId, serviceTimeStart, serviceTimeEnd, customerMobile);
        List<PartnerOrderInfoPO> poList = partnerOrderInfoPOManualMapper.query(status, customerCityCode, partnerId, serviceItemId, serviceTimeStart, serviceTimeEnd, customerMobile, paging.getOffset(), paging.getLimit());
        List<PartnerOrderInfoDTO> dtoList = poList.stream().map(PartnerOrderInfoAdapter::PO2DTO).collect(Collectors.toList());
        dtoList.forEach(bean -> {
            PartnerDTO partnerDTO = partnerService.getById(bean.getPartnerId());
            ServiceItemDTO serviceItemDTO = serviceItemService.getById(bean.getServiceItemId());
            bean.setPartnerDTO(partnerDTO);
            bean.setServiceItemDTO(serviceItemDTO);
        });
        paging.setTotal(count);
        return dtoList;
    }
}
