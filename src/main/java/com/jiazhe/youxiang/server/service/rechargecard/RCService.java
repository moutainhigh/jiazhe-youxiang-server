package com.jiazhe.youxiang.server.service.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
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
     * @param id  客户id
     * @param batchId  批次信息
     * @param faceValue  面额
     * @return
     */
    void directCharge(Integer id ,Integer batchId, BigDecimal faceValue) ;

    /**
     * 根据充值卡ids，将充值卡信息修改为批次相关的信息
     * @param ids
     * @param batchSaveDTO
     * @return
     */
    void batchUpdate(List<Integer> ids, RCExchangeCodeBatchSaveDTO batchSaveDTO);

    /**
     * 根据充值卡ids，批量停用启用
     * @param ids
     * @param status
     * @return
     */
    void batchChangeStatus(List<Integer> ids ,Byte status);

    /**
     * 根据客户电话，充值卡状态，是否过期和分页参数查询充值卡信息
     * @param mobile
     * @param exchangeType
     * @param status
     * @param expiry
     * @param paging
     * @return
     */
    List<RCDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging);

    /**
     * 通过id查询充值卡信息
     * @param id
     * @return
     */
    RCDTO getById(Integer id);

    /**
     *编辑保存充值卡信息
     * @param dto
     */
    void editSave(RCEditDTO dto);

    /**
     * 根据充值卡相关信息，进行修改
     * @param rcDTOList
     */
    void batchUpdate(List<RCDTO> rcDTOList);

    /**
     * 根据充值卡ids，查找充值卡list
     * @param ids
     * @return
     */
    List<RCDTO> findByIds(List<Integer> ids);

    /**
     * 计算客户有效充值卡的余额
     * @param customerId
     */
    BigDecimal totalValidBalance(Integer customerId);

    /**
     * 根据充值卡ids，查找充值卡list，按ids的原始顺序排列
     * @param ids
     * @return
     */
    List<RCDTO> findByIdsInOrder(List<Integer> ids);
}
