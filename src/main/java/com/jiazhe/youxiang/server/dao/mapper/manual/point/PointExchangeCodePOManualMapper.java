package com.jiazhe.youxiang.server.dao.mapper.manual.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public interface PointExchangeCodePOManualMapper {

    void batchUpdate(List<PointExchangeCodePO> poList);

    void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList);

    void batchChangeStatus(@Param("batchId") Integer batchId, @Param("status") Byte status);
}
