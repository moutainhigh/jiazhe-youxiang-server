package com.jiazhe.youxiang.server.dao.mapper.manual.order;

import com.jiazhe.youxiang.server.domain.po.OrderInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/7.
 */
public interface OrderInfoPOManualMapper {

    /**
     * 后台订单计数
     * @param status
     * @param orderCode
     * @param mobile
     * @param customerMobile 收货人手机号
     * @param orderStartTime
     * @param orderEndTime
     * @param workerMobile
     * @param productId
     * @param realServiceStartTime
     * @param realServiceEndTime
     * @return
     */
    Integer count(
            @Param("status") String status,
            @Param("orderCode")String orderCode,
            @Param("mobile")String mobile,
            @Param("customerMobile")String customerMobile,
            @Param("orderStartTime")Date orderStartTime,
            @Param("orderEndTime")Date orderEndTime,
            @Param("workerMobile")String workerMobile,
            @Param("productId")Integer productId,
            @Param("serviceProductId")Integer serviceProductId,
            @Param("realServiceStartTime")Date realServiceStartTime,
            @Param("realServiceEndTime")Date realServiceEndTime,
            @Param("customerCityCode")String customerCityCode
    );

    /**
     * 后台分页查询订单
     * @param status
     * @param orderCode
     * @param mobile
     * @param customerMobile 收货人手机号
     * @param orderStartTime
     * @param orderEndTime
     * @param workerMobile
     * @param productId
     * @param realServiceStartTime
     * @param realServiceEndTime
     * @param offset
     * @param limit
     * @return
     */
    List<OrderInfoPO> query(
            @Param("status") String status,
            @Param("orderCode")String orderCode,
            @Param("mobile")String mobile,
            @Param("customerMobile")String customerMobile,
            @Param("orderStartTime")Date orderStartTime,
            @Param("orderEndTime")Date orderEndTime,
            @Param("workerMobile")String workerMobile,
            @Param("productId")Integer productId,
            @Param("serviceProductId")Integer serviceProductId,
            @Param("realServiceStartTime")Date realServiceStartTime,
            @Param("realServiceEndTime")Date realServiceEndTime,
            @Param("customerCityCode")String customerCityCode,
            @Param("offset")Integer offset,
            @Param("limit")Integer limit);

    /**
     * 插入订单信息，并将id返回至实体中
     * @param orderInfoPO
     */
    void insert(OrderInfoPO orderInfoPO);

    /**
     * 根据订单状态，获取订单的个数
     * @param status
     * @return
     */
    Integer getCountByStatus(@Param("status")Byte status);

    /**
     * 计算当前小时内的订单数量
     * @param beginHour
     * @param endHour
     * @return
     */
    Integer getCountWithinThisHour(@Param("beginHour")Long beginHour,@Param("endHour")Long endHour);
}
