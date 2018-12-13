package com.jiazhe.youxiang.server.service.impl.point;

import com.jiazhe.youxiang.server.adapter.point.PointExchangeRecordAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeRecordAdapter;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeRecordPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.point.PointExchangeRecordPOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPOExample;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("pointExchangeRecordService")
public class PointExchangeRecordServiceImpl implements PointExchangeRecordService {

    @Autowired
    private PointExchangeRecordPOManualMapper pointExchangeRecordPOManualMapper;
    @Override
    public List<PointExchangeRecordDTO> findByCodeIds(List<Integer> codeIds) {
        List<PointExchangeRecordPO> poList = pointExchangeRecordPOManualMapper.findByCodeIds(codeIds);
        return poList.stream().map(PointExchangeRecordAdapter::po2Dto).collect(Collectors.toList());
    }
}
