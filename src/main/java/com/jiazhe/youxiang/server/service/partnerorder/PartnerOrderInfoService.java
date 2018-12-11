package com.jiazhe.youxiang.server.service.partnerorder;

import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.ThreeMoneyResp;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public interface PartnerOrderInfoService {

    List<PartnerOrderInfoDTO> getList(Byte status, String customerCityCode, Integer partnerId, Integer serviceItemId, Date serviceTimeStart, Date serviceTimeEnd, String customerMobile, Paging paging);

    ThreeMoneyResp calThreeMoney(Date timeStart, Date timeEnd);

    PartnerOrderInfoDTO getById(Integer id);

    void save(PartnerOrderInfoDTO dto);
}
