package com.jiazhe.youxiang.server.dao.mapper.manual.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public interface PointExchangeCodeBatchPOManualMapper {

    /**
     * 根据项目id，批次名称分页查询积分兑换码批次
     * @param projectId
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    List<PointExchangeCodeBatchPO> query(@Param("projectId") Integer projectId, @Param("name") String name, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 根据项目id，批次名称查询积分兑换码批次个数
     * @param name
     * @return
     */
    Integer count(@Param("projectId") Integer projectId, @Param("name") String name);

}
