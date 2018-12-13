package com.jiazhe.youxiang.server.dao.mapper.manual.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public interface PointExchangeRecordPOManualMapper {

    List<PointExchangeRecordPO> findByCodeIds(List<Integer> codeIds);
}
