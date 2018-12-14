package com.jiazhe.youxiang.server.biz.partnerorder;

import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.service.partnerorder.PartnerOrderInfoService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.OverviewMoneyResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
@Service("partnerOrderInfoBiz")
public class PartnerOrderInfoBiz {

    @Autowired
    private PartnerOrderInfoService partnerOrderInfoService ;

    public List<PartnerOrderInfoDTO> getList(Byte status, String customerCityCode, Integer partnerId, Integer serviceItemId, Date serviceTimeStart, Date serviceTimeEnd, String customerMobile, Paging paging) {
        return partnerOrderInfoService.getList(status, customerCityCode, partnerId, serviceItemId, serviceTimeStart, serviceTimeEnd, customerMobile, paging);
    }

    public OverviewMoneyResp calOverviewMoney(Date timeStart, Date timeEnd) {
        return partnerOrderInfoService.calOverviewMoney(timeStart,timeEnd);
    }

    public PartnerOrderInfoDTO getById(Integer id) {
        return partnerOrderInfoService.getById(id);
    }

    public void save(PartnerOrderInfoDTO dto) {
        partnerOrderInfoService.save(dto);
    }
}