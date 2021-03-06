package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCPOManualMapper {
    /**
     * 插入充值卡信息
     * @param rechargeCardPO
     * @return
     */
    void insert(RechargeCardPO rechargeCardPO);

    /**
     * 根据ids获取充值卡list
     * @param ids
     * @return
     */
    List<RechargeCardPO> findByIds(List<Integer> ids);

    /**
     * 批量更新充值卡信息
     * @param rcPOList
     * @return
     */
    void batchUpdate(List<RechargeCardPO> rcPOList);

    /**
     * 根据充值卡ids 批量启用、停用充值卡
     * @param map
     * @return
     */
    void batchChangeStatus(Map<String, Object> map);

    /**
     * 根据客户id,充值卡停用、启用状态，是否过期，分页参数查询充值卡列表
     * @param mobile
     * @param exchangeType
     * @param status
     * @param expiry
     * @param offset
     * @param limit
     * @return
     */
    List<RechargeCardPO> query(@Param("mobile") String mobile, @Param("exchangeType") Integer exchangeType, @Param("status") Byte status, @Param("expiry") Byte expiry, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 根据客户id,充值卡停用、启用状态，是否过期，兑换方式查询记录条数
     * @param mobile
     * @param exchangeType
     * @param status
     * @param expiry
     * @return
     */
    Integer count(@Param("mobile") String mobile, @Param("exchangeType") Integer exchangeType, @Param("status") Byte status, @Param("expiry") Byte expiry);

    /**
     * 计算客户有效充值卡的总金额
     * @param customerId
     * @return
     */
    BigDecimal totalValidBalance(@Param("customerId")Integer customerId);

    /**
     * 根据ids获取充值卡list，按ids的原始顺序排列
     * @param ids
     * @return
     */
    List<RechargeCardPO> findByIdsInOrder(List<Integer> ids);
}
