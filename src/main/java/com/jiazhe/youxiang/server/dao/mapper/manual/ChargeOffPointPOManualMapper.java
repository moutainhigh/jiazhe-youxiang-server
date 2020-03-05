package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.ChargeOffPointPO;

import java.util.List;

public interface ChargeOffPointPOManualMapper {

    void batchInsert(List<ChargeOffPointPO> chargeOffPointPOList);

}
