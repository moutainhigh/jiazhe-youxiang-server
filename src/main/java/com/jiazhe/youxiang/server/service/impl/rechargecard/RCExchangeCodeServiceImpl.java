package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dao.mapper.CustomerPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
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
    @Autowired
    private RCService rcService;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;

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

    @Override
    public int codeCharge(Integer type,String mobile, String keyt) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = findByKeyt(keyt);
        CustomerPO customerPO = null;
        RechargeCardPO rechargeCardPO = new RechargeCardPO();
        //直接指定过期时间
        if(rechargeCardExchangeCodePO.getExpiryType().equals(Byte.valueOf("0"))){
            rechargeCardPO.setExpiryTime(rechargeCardExchangeCodePO.getRechargeCardExpiryTime());
        }else{
            rechargeCardPO.setExpiryTime(new Date(System.currentTimeMillis()+rechargeCardExchangeCodePO.getValidityPeriod()* CommonConstant.ONE_DAY));
        }
        rechargeCardPO.setFaceValue(rechargeCardExchangeCodePO.getFaceValue());
        rechargeCardPO.setBalance(rechargeCardExchangeCodePO.getFaceValue());
        //暂时置为0，等生成了兑换记录再修改
        rechargeCardPO.setExchangeRecordId(0);
        rechargeCardPO.setStatus(CodeStatusEnum.START_USING.getId().byteValue());
        rechargeCardPO.setProjectId(rechargeCardExchangeCodePO.getProjectId());
        rechargeCardPO.setName(rechargeCardExchangeCodePO.getBatchName());
        rechargeCardPO.setCustomerId(customerPO.getId());
        rechargeCardPO.setCityCodes(rechargeCardExchangeCodePO.getCityCodes());
        rechargeCardPO.setProductIds(rechargeCardExchangeCodePO.getProductIds());
        rcService.insert(rechargeCardPO);
        //插入兑换记录信息
        RechargeCardExchangeRecordPO rechargeCardRecordPO = new RechargeCardExchangeRecordPO();
        rechargeCardRecordPO.setExchangeCodeId(rechargeCardExchangeCodePO.getId());
        rechargeCardRecordPO.setExchangeType(type);
        rechargeCardRecordPO.setRechargeCardId(rechargeCardPO.getId());
        rcExchangeRecordService.insert(rechargeCardRecordPO);
        //修改充值卡对应的兑换记录id
        rechargeCardPO.setExchangeRecordId(rechargeCardRecordPO.getId());
        rcService.update(rechargeCardPO);
        //修改充值卡兑换码的使用状态
        rechargeCardExchangeCodePO.setUsed(Byte.valueOf("1"));
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodePO);
        return 1;
    }

    @Override
    public RechargeCardExchangeCodePO findByKeyt(String keyt) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rcExchangeCodePOManualMapper.findByKeyt(keyt);
        return rechargeCardExchangeCodePO;
    }
}
