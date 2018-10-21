package com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCExchangeCodeBatchPOManualMapper {

    /**
     * 根据项目id，批次名称分页查询充值卡兑换码批次
     * @param projectId
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    List<RechargeCardExchangeCodeBatchPO> query(@Param("projectId")Integer projectId, @Param("name")String name, @Param("offset")Integer offset, @Param("limit")Integer limit);

    /**
     * 根据项目id，批次名称查询充值卡兑换码批次个数
     * @param name
     * @return
     */
    Integer count(@Param("projectId")Integer projectId,@Param("name") String name);
}
