package com.jiazhe.youxiang.server.biz.partnerorder;

import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.service.partnerorder.PartnerOrderInfoService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.ThreeMoneyResp;
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

    public ThreeMoneyResp calThreeMoney(Date timeStart, Date timeEnd) {
        return partnerOrderInfoService.calThreeMoney(timeStart,timeEnd);
    }
}
