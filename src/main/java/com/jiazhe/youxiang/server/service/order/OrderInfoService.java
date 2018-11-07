package com.jiazhe.youxiang.server.service.order;

import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/7.
 */
public interface OrderInfoService {
    /**
     * 后台通过参数分页查询订单信息
     * @param status
     * @param orderCode
     * @param mobile  下单人的电话或收货人的电话
     * @param orderStartTime
     * @param orderEndTime
     * @param workerMobile
     * @param paging
     * @return
     */
    List<OrderInfoDTO> getList(Byte status, String orderCode, String mobile, Date orderStartTime, Date orderEndTime,String workerMobile, Paging paging);

    /**
     * 获取订单信息
     * @param id
     * @return
     */
    OrderInfoDTO getById(Integer id);

}
