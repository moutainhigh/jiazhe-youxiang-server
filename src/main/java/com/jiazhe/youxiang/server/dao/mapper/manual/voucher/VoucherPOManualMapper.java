package com.jiazhe.youxiang.server.dao.mapper.manual.voucher;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.domain.po.VoucherPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author tu
 * @description：
 * @date 2018/11/03
 */
public interface VoucherPOManualMapper {

    /**
     * 根据ids查找代金券
     * @param ids
     * @return
     */
    List<VoucherPO> findByIds(List<Integer> ids);

    /**
     * 批量更新代金券信息
     * @param rcPOList
     */
    void batchUpdate(List<VoucherPO> rcPOList);

    /**
     * 根据代金券ids 批量启用、停用充值卡
     * @param map
     */
    void batchChangeStatus(Map<String, Object> map);

    /**
     * 根据客户id,代金券停用、启用状态，是否过期，分页参数查询代金券列表
     * @param mobile
     * @param exchangeType
     * @param status
     * @param expiry
     * @param offset
     * @param limit
     * @return
     */
    List<VoucherPO> query(@Param("mobile") String mobile, @Param("exchangeType") Integer exchangeType, @Param("status") Byte status, @Param("expiry") Byte expiry, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 根据客户id,代金券停用、启用状态，是否过期，兑换方式查询记录条数
     * @param mobile
     * @param exchangeType
     * @param status
     * @param expiry
     * @return
     */
    Integer count(@Param("mobile") String mobile, @Param("exchangeType") Integer exchangeType, @Param("status") Byte status, @Param("expiry") Byte expiry);

    /**
     * 插入代金券记录，返回记录id
     * @param voucherPO
     */
    void insert(VoucherPO voucherPO);

    /**
     * 根据代金券ids 批量将代金券置为不同已使用，未使用状态
     * @param map
     */
    void batchChangeUsed(Map<String, Object> map);

    /**
     * 计算客户有效代金券的数量
     * @param customerId
     * @return
     */
    Integer totalValidVoucher(@Param("customerId")Integer customerId);

    /**
     * 根据ids查找代金券，并按ids顺序排序
     * @param ids
     * @return
     */
    List<VoucherPO> findByIdsInOrder(List<Integer> ids);
}
