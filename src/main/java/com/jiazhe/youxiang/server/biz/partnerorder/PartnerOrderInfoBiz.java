package com.jiazhe.youxiang.server.biz.partnerorder;

import com.jiazhe.youxiang.server.biz.order.OrderTrackBiz;
import com.jiazhe.youxiang.server.common.enums.OrderOpreationTypeEnum;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderTrackDTO;
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
    private PartnerOrderInfoService partnerOrderInfoService;
    @Autowired
    private OrderTrackBiz orderTrackBiz;

    public List<PartnerOrderInfoDTO> getList(Byte status, String customerCityCode, Integer partnerId, Integer serviceItemId, Date orderTimeStart, Date orderTimeEnd, Date serviceTimeStart, Date serviceTimeEnd, String customerMobile, Paging paging) {
        return partnerOrderInfoService.getList(status, customerCityCode, partnerId, serviceItemId, orderTimeStart, orderTimeEnd, serviceTimeStart, serviceTimeEnd, customerMobile, paging);
    }

    public OverviewMoneyResp calOverviewMoney(Date timeStart, Date timeEnd) {
        return partnerOrderInfoService.calOverviewMoney(timeStart, timeEnd);
    }

    public PartnerOrderInfoDTO getById(Integer id) {
        return partnerOrderInfoService.getById(id);
    }

    public void save(PartnerOrderInfoDTO dto) {
        PartnerOrderInfoDTO partnerOrderInfoDTO = partnerOrderInfoService.getById(dto.getId());
        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();
        if (partnerOrderInfoDTO == null) {
            orderTrackDTO.setOpreation(OrderOpreationTypeEnum.PARTNER_CREATE);
        } else {
            orderTrackDTO.setOpreation(OrderOpreationTypeEnum.PARTNER_UPDATE);
            StringBuilder sb = new StringBuilder();
            sb.append(OrderTrackBiz.parseOrderTrackInfo("客户姓名", partnerOrderInfoDTO.getCustomerName(), dto.getCustomerName()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("客户电话", partnerOrderInfoDTO.getCustomerMobile(), dto.getCustomerMobile()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("城市", partnerOrderInfoDTO.getCustomerCityName(), dto.getCustomerCityName()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("兑换密钥", partnerOrderInfoDTO.getKeyt(), dto.getKeyt()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("兑换时间", partnerOrderInfoDTO.getOrderTime(), dto.getOrderTime()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("预约时间", partnerOrderInfoDTO.getServiceTime(), dto.getServiceTime()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("订单来源", partnerOrderInfoDTO.getOrderSource(), dto.getOrderSource()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("保洁师姓名", partnerOrderInfoDTO.getWorkerName(), dto.getWorkerName()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("保洁师电话", partnerOrderInfoDTO.getWorkerMobile(), dto.getWorkerMobile()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("服务商家", partnerOrderInfoDTO.getPartnerId(), dto.getPartnerId()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("服务项目", partnerOrderInfoDTO.getServiceItemId(), dto.getServiceItemId()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("备注", partnerOrderInfoDTO.getRemark(), dto.getRemark()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("预付", partnerOrderInfoDTO.getPrePay(), dto.getPrePay()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("再次支付", partnerOrderInfoDTO.getAppendPay(), dto.getAppendPay()));
            sb.append(OrderTrackBiz.parseOrderTrackInfo("订单状态", partnerOrderInfoDTO.getStatus(), dto.getStatus()));
            orderTrackDTO.setMsg(sb.toString());
        }
        partnerOrderInfoService.save(dto);
        orderTrackDTO.setOrderid(dto.getId());
        orderTrackBiz.create(orderTrackDTO);
    }

    public List<PartnerOrderInfoDTO> getList(Byte status, String customerCityCode, Integer partnerId, Integer serviceItemId, Date orderTimeStart, Date orderTimeEnd, Date serviceTimeStart, Date serviceTimeEnd, String customerMobile) {
        return partnerOrderInfoService.getList(status, customerCityCode, partnerId, serviceItemId, orderTimeStart, orderTimeEnd, serviceTimeStart, serviceTimeEnd, customerMobile);
    }
}
