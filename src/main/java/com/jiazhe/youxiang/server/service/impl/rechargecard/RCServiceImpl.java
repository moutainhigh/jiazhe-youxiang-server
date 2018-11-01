package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("rcService")
public class RCServiceImpl implements RCService {

    @Autowired
    private RechargeCardPOMapper rechargeCardPOMapper;
    @Autowired
    private RCPOManualMapper rcPOManualMapper;
    @Autowired
    private RCExchangeCodeBatchService rcExchangeCodeBatchService;
    @Autowired
    private RCService rcService;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;

    @Override
    public void changeStatus(Integer id, Byte status) {
        RechargeCardPO rechargeCardPO = rechargeCardPOMapper.selectByPrimaryKey(id);
        rechargeCardPO.setStatus(status);
        rechargeCardPO.setModTime(new Date());
        rechargeCardPOMapper.updateByPrimaryKeySelective(rechargeCardPO);
    }

    @Override
    public void changeExpiryTime(Integer id, Date expiryTime) {
        RechargeCardPO rechargeCardPO = rechargeCardPOMapper.selectByPrimaryKey(id);
        rechargeCardPO.setExpiryTime(expiryTime);
        rechargeCardPO.setModTime(new Date());
        rechargeCardPOMapper.updateByPrimaryKeySelective(rechargeCardPO);
    }

    @Override
    public void insert(RechargeCardPO rechargeCardPO) {
        rcPOManualMapper.insert(rechargeCardPO);
    }

    @Override
    public void update(RechargeCardPO rechargeCardPO) {
        rechargeCardPOMapper.updateByPrimaryKeySelective(rechargeCardPO);
    }

    @Override
    public List<RCDTO> findUnexpiredByCustomerId(Integer customerId) {
        RechargeCardPOExample rechargeCardPOExample = new RechargeCardPOExample();
        RechargeCardPOExample.Criteria criteria = rechargeCardPOExample.createCriteria();
        criteria.andCustomerIdEqualTo(customerId);
        criteria.andExpiryTimeGreaterThanOrEqualTo(new Date());
        List<RechargeCardPO> rechargeCardPOList = rechargeCardPOMapper.selectByExample(rechargeCardPOExample);
        List<RCDTO> rcdtoList = rechargeCardPOList.stream().map(RCAdapter::PO2DTO).collect(Collectors.toList());
        return rcdtoList;
    }

    @Override
    public void directCharge(String mobile, Integer batchId, BigDecimal faceValue) {
        CustomerPO customerPO = null;
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchService.getById(batchId);
        RechargeCardPO rechargeCardPO = new RechargeCardPO();
        //直接指定过期时间
        if(rcExchangeCodeBatchEditDTO.getExpiryType().equals(Byte.valueOf("0"))){
            rechargeCardPO.setExpiryTime(rcExchangeCodeBatchEditDTO.getRechargeCardExpiryTime());
        }else{
            rechargeCardPO.setExpiryTime(new Date(System.currentTimeMillis()+rcExchangeCodeBatchEditDTO.getValidityPeriod()* CommonConstant.ONE_DAY));
        }
        /*rechargeCardPO.setFaceValue(rcExchangeCodeBatchEditDTO.getFaceValue());
        rechargeCardPO.setBalance(rcExchangeCodeBatchEditDTO.getFaceValue());*/
        //暂时置为0，等生成了兑换记录再修改
        rechargeCardPO.setExchangeRecordId(0);
        rechargeCardPO.setStatus(CodeStatusEnum.START_USING.getId().byteValue());
        rechargeCardPO.setProjectId(rcExchangeCodeBatchEditDTO.getProjectId());
        rechargeCardPO.setName(rcExchangeCodeBatchEditDTO.getName());
        rechargeCardPO.setCustomerId(customerPO.getId());
        rechargeCardPO.setCityCodes(rcExchangeCodeBatchEditDTO.getCityCodes());
        rechargeCardPO.setProductIds(rcExchangeCodeBatchEditDTO.getProductIds());
        rcService.insert(rechargeCardPO);
        //插入兑换记录信息
        RechargeCardExchangeRecordPO rechargeCardRecordPO = new RechargeCardExchangeRecordPO();
        rechargeCardRecordPO.setExchangeType(2);
        rechargeCardRecordPO.setRechargeCardId(rechargeCardPO.getId());
        rcExchangeRecordService.insert(rechargeCardRecordPO);
        //修改充值卡对应的兑换记录id
        rechargeCardPO.setExchangeRecordId(rechargeCardRecordPO.getId());
        rcService.update(rechargeCardPO);
    }

    @Override
    public void batchUpdate(List<Integer> usedIds, RCExchangeCodeBatchSaveDTO batchSaveDTO) {
        List<RechargeCardExchangeRecordPO> recordPOList = rcExchangeRecordService.findByCodeIds(usedIds);
        List<Integer> cardIds = recordPOList.stream().map(RechargeCardExchangeRecordPO::getRechargeCardId).collect(Collectors.toList());
        List<RechargeCardPO> rcPOList = rcPOManualMapper.findByIds(cardIds);
        rcPOList.stream().forEach(bean -> {
            bean.setName(batchSaveDTO.getRechargeCardName());
            bean.setDescription(batchSaveDTO.getDescription());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            //直接指定过期时间
            if(batchSaveDTO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)){
                bean.setExpiryTime(batchSaveDTO.getRechargeCardExpiryTime());
            }else{
                bean.setExpiryTime(new Date(bean.getAddTime().getTime()+batchSaveDTO.getValidityPeriod()* CommonConstant.ONE_DAY));
            }
        });
        rcPOManualMapper.batchUpdate(rcPOList);
    }

    @Override
    public void batchChangeStatus(List<Integer> usedIds,Byte status) {
        List<RechargeCardExchangeRecordPO> recordPOList = rcExchangeRecordService.findByCodeIds(usedIds);
        List<Integer> cardIds = recordPOList.stream().map(RechargeCardExchangeRecordPO::getRechargeCardId).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status",status);
        map.put("ids",cardIds);
        rcPOManualMapper.batchChangeStatus(map);
    }
}
