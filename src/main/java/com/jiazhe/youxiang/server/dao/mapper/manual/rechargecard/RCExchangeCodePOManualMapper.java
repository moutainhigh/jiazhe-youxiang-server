package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCExchangeCodePOManualMapper {

    int batchInsert(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList);

    RechargeCardExchangeCodePO findByKeyt(String keyt);

    int batchUpdate(List<RechargeCardExchangeCodePO> poList);

    /**
     * 根据批次id，批量修改兑换码的启用停用状态
     * @param batchId
     * @param status
     * @return
     */
    int batchChangeStatus(@Param("batchId") Integer batchId, @Param("status")Byte status);

    /**
     * 根据批次id 和查询条件查询统计兑换码个数
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @return
     */
    Integer count(@Param("batchId")Integer batchId, @Param("code")String code, @Param("keyt")String keyt, @Param("status")Byte status, @Param("used")Byte used);

    /**
     * 根据批次id 和查询条件分页查询兑换码
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @param offset
     * @param limit
     * @return
     */
    List<RechargeCardExchangeCodePO> query(@Param("batchId")Integer batchId, @Param("code")String code, @Param("keyt")String keyt, @Param("status")Byte status, @Param("used")Byte used, @Param("offset")Integer offset, @Param("limit")Integer limit);
}
