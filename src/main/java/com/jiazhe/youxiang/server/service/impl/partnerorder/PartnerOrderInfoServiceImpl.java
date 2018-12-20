package com.jiazhe.youxiang.server.service.impl.partnerorder;

import com.jiazhe.youxiang.server.adapter.partnerorder.PartnerOrderInfoAdapter;
import com.jiazhe.youxiang.server.dao.mapper.PartnerOrderInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.partnerorder.PartnerOrderInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.advancepay.AdvancePayDTO;
import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.dto.serviceitem.ServiceItemDTO;
import com.jiazhe.youxiang.server.service.PartnerService;
import com.jiazhe.youxiang.server.service.ServiceItemService;
import com.jiazhe.youxiang.server.service.advancepay.AdvancePayService;
import com.jiazhe.youxiang.server.service.partnerorder.PartnerOrderInfoService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.OverviewMoneyResp;
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
    public OverviewMoneyResp calOverviewMoney(Date timeStart, Date timeEnd) {
        BigDecimal[] spend = {BigDecimal.ZERO};
        BigDecimal[] intervalSpend = {BigDecimal.ZERO};
        BigDecimal[] total = {BigDecimal.ZERO};
        BigDecimal[] intervalTotal = {BigDecimal.ZERO};
        OverviewMoneyResp overviewMoneyResp = new OverviewMoneyResp();
        PartnerOrderInfoPOExample pExample = new PartnerOrderInfoPOExample();
        PartnerOrderInfoPOExample.Criteria pCriteria = pExample.createCriteria();
        pCriteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        //所有的商家订单
        List<PartnerOrderInfoPO> pPOList = partnerOrderInfoPOMapper.selectByExample(pExample);
        pPOList.stream().forEach(bean -> {
            spend[0] = spend[0].add(bean.getPrePay().add(bean.getAppendPay()));
            if (isInInterval(bean.getServiceTime(), timeStart, timeEnd)) {
                intervalSpend[0] = intervalSpend[0].add(bean.getPrePay().add(bean.getAppendPay()));
            }
        });
        overviewMoneyResp.setSpend(spend[0]);
        overviewMoneyResp.setIntervalSpend(intervalSpend[0]);
        //所有的付款记录
        List<AdvancePayDTO> aPOList = advancePayService.getList(null, null);
        aPOList.stream().forEach(bean -> {
            total[0] = total[0].add(bean.getAdvancePay());
            if (isInInterval(bean.getAddTime(), timeStart, timeEnd)) {
                intervalTotal[0] = intervalTotal[0].add(bean.getAdvancePay());
            }
        });
        overviewMoneyResp.setTotal(total[0]);
        overviewMoneyResp.setIntervalTotal(intervalTotal[0]);
        overviewMoneyResp.setLeft(overviewMoneyResp.getTotal().subtract(overviewMoneyResp.getSpend()));
        overviewMoneyResp.setIntervalLeft(overviewMoneyResp.getIntervalTotal().subtract(overviewMoneyResp.getIntervalSpend()));
        return overviewMoneyResp;
    }

    @Override
    public PartnerOrderInfoDTO getById(Integer id) {
        PartnerOrderInfoPO po = partnerOrderInfoPOMapper.selectByPrimaryKey(id);
        return PartnerOrderInfoAdapter.PO2DTO(po);
    }

    @Override
    public void save(PartnerOrderInfoDTO dto) {
        PartnerOrderInfoPO poIn = PartnerOrderInfoAdapter.DTO2PO(dto);
        if (poIn.getId() == 0) {
            partnerOrderInfoPOMapper.insertSelective(poIn);
        } else {
            PartnerOrderInfoPO po = partnerOrderInfoPOMapper.selectByPrimaryKey(dto.getId());
            poIn.setAddTime(po.getAddTime());
            poIn.setExtInfo(po.getExtInfo());
            poIn.setIsDeleted(po.getIsDeleted());
            partnerOrderInfoPOMapper.updateByPrimaryKey(poIn);
        }
    }

    private boolean isInInterval(Date date, Date beginDate, Date endDate) {
        Long begin = 0L;
        Long end = Long.MAX_VALUE;
        if (beginDate != null) {
            begin = beginDate.getTime();
        }
        if (endDate != null) {
            end = endDate.getTime();
        }
        return date.getTime() >= begin && date.getTime() <= end;
    }
}
