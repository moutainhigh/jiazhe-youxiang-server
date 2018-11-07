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
     * @param orderStartTime
     * @param orderEndTime
     * @param workerMobile
     * @return
     */
    Integer count(@Param("status") Byte status, @Param("orderCode")String orderCode, @Param("mobile")String mobile, @Param("orderStartTime")Date orderStartTime, @Param("orderEndTime")Date orderEndTime,@Param("workerMobile")String workerMobile);

    /**
     * 后台分页查询订单
     * @param status
     * @param orderCode
     * @param mobile
     * @param orderStartTime
     * @param orderEndTime
     * @param workerMobile
     * @param offset
     * @param limit
     * @return
     */
    List<OrderInfoPO> query(@Param("status") Byte status, @Param("orderCode")String orderCode, @Param("mobile")String mobile, @Param("orderStartTime")Date orderStartTime, @Param("orderEndTime")Date orderEndTime,@Param("workerMobile")String workerMobile, @Param("offset")Integer offset,  @Param("limit")Integer limit);

}
