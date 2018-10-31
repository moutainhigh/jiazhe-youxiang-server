package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("rcExchangeRecordService")
public class RCExchangeRecordServiceImpl implements RCExchangeRecordService {

    @Autowired
    private RCExchangeRecordPOManualMapper rcExchangeRecordPOManualMapper;

    @Override
    public int insert(RechargeCardExchangeRecordPO rechargeCardRecordPO) {
        rcExchangeRecordPOManualMapper.insert(rechargeCardRecordPO);
        return rechargeCardRecordPO.getId();
    }

    @Override
    public List<RechargeCardExchangeRecordPO> findByCodeIds(List<Integer> codeIds) {
        return rcExchangeRecordPOManualMapper.findByCodeIds(codeIds);
    }
}
