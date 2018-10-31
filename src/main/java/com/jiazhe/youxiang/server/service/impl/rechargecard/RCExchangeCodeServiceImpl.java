package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dao.mapper.CustomerPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public int batchInsert(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList) {
        return rcExchangeCodePOManualMapper.batchInsert(rechargeCardExchangeCodePOList);
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
    public List<RCExchangeCodeDTO> getByBatchId(Integer id) {
        RechargeCardExchangeCodePOExample example = new RechargeCardExchangeCodePOExample();
        RechargeCardExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andBatchIdEqualTo(id);
        List<RechargeCardExchangeCodePO> poList = rechargeCardExchangeCodePOMapper.selectByExample(example);
        return poList.stream().map(RCExchangeCodeAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public int updateWithBatch(RCExchangeCodeBatchSaveDTO batchSaveDTO) {
        RechargeCardExchangeCodePOExample example = new RechargeCardExchangeCodePOExample();
        RechargeCardExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchSaveDTO.getId());
        List<RechargeCardExchangeCodePO> poList = rechargeCardExchangeCodePOMapper.selectByExample(example);
        List<Integer> usedIds = new ArrayList<Integer>();
        for(RechargeCardExchangeCodePO po :poList){
            po.setBatchName(batchSaveDTO.getName());
            po.setRechargeCardName(batchSaveDTO.getRechargeCardName());
            po.setBatchDescription(batchSaveDTO.getDescription());
            po.setProjectId(batchSaveDTO.getProjectId());
            po.setCityCodes(batchSaveDTO.getCityCodes());
            po.setProductIds(batchSaveDTO.getProductIds());
            po.setExpiryTime(batchSaveDTO.getExpiryTime());
            po.setExpiryType(batchSaveDTO.getExpiryType());
            po.setRechargeCardExpiryTime(batchSaveDTO.getRechargeCardExpiryTime());
            po.setValidityPeriod(batchSaveDTO.getValidityPeriod());
            if(po.getUsed().equals(Byte.valueOf("1"))){
                //记录已经使用过码的id
                usedIds.add(po.getId());
            }
        }
        rcExchangeCodePOManualMapper.batchUpdate(poList);
        if(!usedIds.isEmpty()){
            rcService.batchUpdate(usedIds,batchSaveDTO);
        }
        return 0;
    }

    @Override
    public int batchChangeStatus(Integer batchId, Byte status) {
        rcExchangeCodePOManualMapper.batchChangeStatus(batchId,status);
        RechargeCardExchangeCodePOExample example = new RechargeCardExchangeCodePOExample();
        RechargeCardExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchId);
        List<RechargeCardExchangeCodePO> poList = rechargeCardExchangeCodePOMapper.selectByExample(example);
        List<Integer> usedIds = new ArrayList<Integer>();
        for(RechargeCardExchangeCodePO po :poList){
            if(po.getUsed().equals(Byte.valueOf("1"))){
                //记录已经使用过码的id
                usedIds.add(po.getId());
            }
        }
        if(!usedIds.isEmpty()){
            rcService.batchChangeStatus(usedIds,status);
        }
        return 0;
    }

    @Override
    public RechargeCardExchangeCodePO findByKeyt(String keyt) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rcExchangeCodePOManualMapper.findByKeyt(keyt);
        return rechargeCardExchangeCodePO;
    }
}
