package com.jiazhe.youxiang.server.service.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public interface RCService {

    /**
     * 修改充值卡启用、停用状态
     * @param id
     * @param status
     * @return
     */
    int changeStatus(Integer id, Byte status);

    /**
     * 修改充值卡过期时间
     * @param id
     * @param expiryTime
     * @return
     */
    int changeExpiryTime(Integer id, Date expiryTime);

    /**
     * 插入充值卡记录
     * @param rechargeCardPO
     * @return
     */
    int insert(RechargeCardPO rechargeCardPO);

    /**
     * 修改充值卡信息
     * @param rechargeCardPO
     * @return
     */
    int update(RechargeCardPO rechargeCardPO);

    /**
     * 通过客户id查找所有未过期的充值卡
     * @param customerId
     * @return
     */
    List<RCDTO> findUnexpiredByCustomerId(Integer customerId);

    /**
     * 后台直接充值任意面额
     * @param mobile  客户手机
     * @param batchId  批次信息
     * @param faceValue  面额
     * @return
     */
    int directCharge(String mobile ,Integer batchId, BigDecimal faceValue);
}