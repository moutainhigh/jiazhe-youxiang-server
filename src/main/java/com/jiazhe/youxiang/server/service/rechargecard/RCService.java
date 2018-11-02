package com.jiazhe.youxiang.server.service.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

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
    void changeStatus(Integer id, Byte status);

    /**
     * 修改充值卡过期时间
     * @param id
     * @param expiryTime
     * @return
     */
    void changeExpiryTime(Integer id, Date expiryTime);

    /**
     * 插入充值卡记录
     * @param rechargeCardPO
     * @return
     */
    void insert(RechargeCardPO rechargeCardPO);

    /**
     * 修改充值卡信息
     * @param rechargeCardPO
     * @return
     */
    void update(RechargeCardPO rechargeCardPO);

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
    void directCharge(String mobile ,Integer batchId, BigDecimal faceValue);

    /**
     * 根据已经使用的兑换码的ids 查找对应的充值卡，根据相关信息进行修改
     * @param usedIds
     * @param batchSaveDTO
     * @return
     */
    void batchUpdate(List<Integer> usedIds, RCExchangeCodeBatchSaveDTO batchSaveDTO);

    /**
     * 已经使用的兑换码的ids 查找对应的充值卡，并启用停用
     * @param usedIds
     * @param status
     * @return
     */
    void batchChangeStatus(List<Integer> usedIds,Byte status);

    /**
     * 根据客户电话，充值卡状态，是否过期和分页参数查询充值卡信息
     * @param mobile
     * @param status
     * @param expiry
     * @param paging
     * @return
     */
    List<RCDTO> getList(String mobile, Byte status, Byte expiry, Paging paging);
}
