package com.jiazhe.youxiang.server.service.impl.partnerorder;

import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.adapter.partnerorder.PartnerOrderInfoAdapter;
import com.jiazhe.youxiang.server.dao.mapper.PartnerOrderInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.partnerorder.PartnerOrderInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.advancepay.AdvancePayDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;
import com.jiazhe.youxiang.server.service.PartnerService;
import com.jiazhe.youxiang.server.service.ServiceItemService;
import com.jiazhe.youxiang.server.service.advancepay.AdvancePayService;
import com.jiazhe.youxiang.server.service.partnerorder.PartnerOrderInfoService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.ThreeMoneyResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
public class PartnerOrderInfoServiceImpl implements PartnerOrderInfoService {

    @Autowired
    private PartnerOrderInfoPOMapper partnerOrderInfoPOMapper;
    @Autowired
    private PartnerOrderInfoPOManualMapper partnerOrderInfoPOManualMapper;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private ServiceItemService serviceItemService;
    @Autowired
    private AdvancePayService advancePayService;


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

    @Override
    public ThreeMoneyResp calThreeMoney(Date timeStart, Date timeEnd) {
        ThreeMoneyResp threeMoneyResp = new ThreeMoneyResp();
        PartnerOrderInfoPOExample pExample = new PartnerOrderInfoPOExample();
        PartnerOrderInfoPOExample.Criteria pCriteria = pExample.createCriteria();
        if (timeStart != null) {
            pCriteria.andOrderTimeGreaterThan(timeStart);
        }
        if (timeEnd != null) {
            pCriteria.andOrderTimeLessThanOrEqualTo(timeEnd);
        }
        pCriteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<PartnerOrderInfoPO> pPOList = partnerOrderInfoPOMapper.selectByExample(pExample);
        if (!pPOList.isEmpty()) {
            BigDecimal prePay = pPOList.stream().map(PartnerOrderInfoPO::getPrePay).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal appendPay = pPOList.stream().map(PartnerOrderInfoPO::getAppendPay).reduce(BigDecimal.ZERO, BigDecimal::add);
            threeMoneyResp.setSpend(prePay.add(appendPay));
        }
        List<AdvancePayDTO> aPOList = advancePayService.getList(timeStart, timeEnd);
        if (!aPOList.isEmpty()) {
            threeMoneyResp.setTotal(aPOList.stream().map(AdvancePayDTO::getAdvancePay).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        threeMoneyResp.setLeft(threeMoneyResp.getTotal().subtract(threeMoneyResp.getSpend()));
        return threeMoneyResp;
    }

    @Override
    public PartnerOrderInfoDTO getById(Integer id) {
        PartnerOrderInfoPO po = partnerOrderInfoPOMapper.selectByPrimaryKey(id);
        return PartnerOrderInfoAdapter.PO2DTO(po);
    }

    @Override
    public void save(PartnerOrderInfoDTO dto) {
        PartnerOrderInfoPO poIn = PartnerOrderInfoAdapter.DTO2PO(dto);
        if(poIn.getId() == 0){
            partnerOrderInfoPOMapper.insert(poIn);
        }else{
            PartnerOrderInfoPO po = partnerOrderInfoPOMapper.selectByPrimaryKey(dto.getId());
            poIn.setAddTime(po.getAddTime());
            poIn.setExtInfo(po.getExtInfo());
            poIn.setIsDeleted(po.getIsDeleted());
            partnerOrderInfoPOMapper.updateByPrimaryKey(poIn);
        }
    }
}
