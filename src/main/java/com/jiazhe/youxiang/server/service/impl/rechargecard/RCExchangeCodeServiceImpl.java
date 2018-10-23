package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/22.
 */
@Service("rcExchangeCodeService")
public class RCExchangeCodeServiceImpl implements RCExchangeCodeService {

    @Autowired
    private RCExchangeCodePOManualMapper rcExchangeCodePOManualMapper;
    @Autowired
    private RechargeCardExchangeCodePOMapper rechargeCardExchangeCodePOMapper;

    @Override
    public int batchSave(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList) {
        return rcExchangeCodePOManualMapper.batchSave(rechargeCardExchangeCodePOList);
    }

    @Override
    public int changeCodeStatus(Integer id, Byte status) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(id);
        rechargeCardExchangeCodePO.setStatus(status);
        rechargeCardExchangeCodePO.setModTime(new Date());
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodePO);
        return 1;
    }

    @Override
    public int changeExpiryTime(Integer id, Date expiryTime) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(id);
        rechargeCardExchangeCodePO.setExpiryTime(expiryTime);
        rechargeCardExchangeCodePO.setModTime(new Date());
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodePO);
        return 1;
    }
}
