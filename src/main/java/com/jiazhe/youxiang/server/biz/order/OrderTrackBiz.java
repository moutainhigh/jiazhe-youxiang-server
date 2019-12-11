package com.jiazhe.youxiang.server.biz.order;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderTrackDTO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderTrackDTO;
import com.jiazhe.youxiang.server.service.order.OrderTrackService;
import com.jiazhe.youxiang.server.service.partnerorder.PartnerOrderTrackService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/12/6 21:30
 */
@Service("orderTrackBiz")
@Transactional
public class OrderTrackBiz {
    @Autowired
    private OrderTrackService orderTrackService;
    @Autowired
    private PartnerOrderTrackService partnerOrderTrackService;

    public void createOrderTrack(OrderTrackDTO orderTrackDTO) {
        orderTrackService.create(orderTrackDTO);
    }

    public void createPartnerOrderTrack(PartnerOrderTrackDTO orderTrackDTO) {
        partnerOrderTrackService.create(orderTrackDTO);
    }

    public String getOrderTrackInfo(Integer id) {
        List<OrderTrackDTO> list = orderTrackService.getList(id);
        if (list == null || list.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (sb.length() > 0) sb.append("<br>");
            sb.append(parseOrderTrackInfo(i + 1, list.get(i)));
        }
        return sb.toString();
    }

    public String getPartnerOrderTrackInfo(Integer id) {
        List<PartnerOrderTrackDTO> list = partnerOrderTrackService.getList(id);
        if (list == null || list.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (sb.length() > 0) sb.append("<br>");
            sb.append(parseOrderTrackInfo(i + 1, list.get(i)));
        }
        return sb.toString();
    }

    private String parseOrderTrackInfo(Integer index, OrderTrackDTO info) {
        if (info == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("【").append(index).append("】 ");
        sb.append(info.getUsername()).append(" ");
        sb.append("在 ").append(DateUtil.secondToStr(info.getAddTime())).append(" ");
        sb.append(info.getOpreation().getMessage());
        if (StringUtils.isNotBlank(info.getMsg())) sb.append("==>").append(info.getMsg());
        return sb.toString();
    }

    public static String parseOrderTrackInfo(String fieldName, Object oldValue, Object newValue) {
        if (Objects.equals(oldValue, newValue)) return "";
        if (oldValue instanceof BigDecimal && newValue instanceof BigDecimal) {
            if (((BigDecimal) oldValue).compareTo((BigDecimal) newValue) == 0) return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" ").append(fieldName).append("[由 \"").append(fieldToStr(oldValue)).append("\" 改为 \"").append(fieldToStr(newValue)).append("\" ]; ");
        return sb.toString();
    }

    public static String fieldToStr(Object field) {
        if (field == null) return "null";
        if (field instanceof Date) return DateUtil.secondToStr((Date) field);
        if (field instanceof BigDecimal) {
            DecimalFormat df = new DecimalFormat("#.0000");
            if (BigDecimal.ZERO.compareTo((BigDecimal) field) == 0) {
                return "0.0000";
            } else if (BigDecimal.ZERO.compareTo((BigDecimal) field) > 0 && new BigDecimal(1).compareTo((BigDecimal) field) < 0) {
                return "0" + df.format(field);
            } else {
                return df.format(field);
            }
        }
        return field.toString();
    }
}
