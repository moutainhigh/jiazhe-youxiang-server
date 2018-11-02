package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.CustomerPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class RCExchangeCodeServiceImpl implements RCExchangeCodeService {

    @Autowired
    private RCExchangeCodePOManualMapper rcExchangeCodePOManualMapper;
    @Autowired
    private RechargeCardExchangeCodePOMapper rechargeCardExchangeCodePOMapper;
    @Autowired
    private RCService rcService;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;
    @Autowired
    private CustomerService customerService;

    @Override
    public void batchInsert(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList) {
        rcExchangeCodePOManualMapper.batchInsert(rechargeCardExchangeCodePOList);
    }

    @Override
    public void changeCodeStatus(Integer id, Byte status) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(id);
        rechargeCardExchangeCodePO.setStatus(status);
        rechargeCardExchangeCodePO.setModTime(new Date());
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodePO);
    }

    @Override
    public void changeExpiryTime(Integer id, Date expiryTime) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(id);
        rechargeCardExchangeCodePO.setExpiryTime(expiryTime);
        rechargeCardExchangeCodePO.setModTime(new Date());
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodePO);
    }

    @Override
    public void codeCharge(Integer type,String mobile, String keyt) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = findByKeyt(keyt);
        CustomerDTO customerDTO = customerService.getById(1);;
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
        rechargeCardPO.setCustomerId(customerDTO.getId());
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
    public void updateWithBatch(RCExchangeCodeBatchSaveDTO batchSaveDTO) {
        RechargeCardExchangeCodePOExample example = new RechargeCardExchangeCodePOExample();
        RechargeCardExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchSaveDTO.getId());
        List<RechargeCardExchangeCodePO> poList = rechargeCardExchangeCodePOMapper.selectByExample(example);
        poList.stream().forEach(bean -> {
            bean.setBatchName(batchSaveDTO.getName());
            bean.setBatchName(batchSaveDTO.getName());
            bean.setRechargeCardName(batchSaveDTO.getRechargeCardName());
            bean.setBatchDescription(batchSaveDTO.getDescription());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            bean.setExpiryTime(batchSaveDTO.getExpiryTime());
            bean.setExpiryType(batchSaveDTO.getExpiryType());
            bean.setRechargeCardExpiryTime(batchSaveDTO.getRechargeCardExpiryTime());
            bean.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        });
        rcExchangeCodePOManualMapper.batchUpdate(poList);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(RechargeCardExchangeCodePO::getId).collect(Collectors.toList());
        if(!usedIds.isEmpty()){
            rcService.batchUpdate(usedIds,batchSaveDTO);
        }
    }

    @Override
    public void batchChangeStatus(Integer batchId, Byte status) {
        rcExchangeCodePOManualMapper.batchChangeStatus(batchId,status);
        RechargeCardExchangeCodePOExample example = new RechargeCardExchangeCodePOExample();
        RechargeCardExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchId);
        List<RechargeCardExchangeCodePO> poList = rechargeCardExchangeCodePOMapper.selectByExample(example);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(RechargeCardExchangeCodePO::getId).collect(Collectors.toList());
        if(!usedIds.isEmpty()){
            rcService.batchChangeStatus(usedIds,status);
        }
    }

    @Override
    public List<RCExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used,Paging paging) {
        Integer count = rcExchangeCodePOManualMapper.count(batchId, code,keyt,status,used);
        List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList = rcExchangeCodePOManualMapper.query(batchId, code,keyt,status,used, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return rechargeCardExchangeCodePOList.stream().map(RCExchangeCodeAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public RCExchangeCodeDTO getById(Integer id) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(id);
        if(null == rechargeCardExchangeCodePO){
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        RCExchangeCodeDTO rcExchangeCodeDTO = RCExchangeCodeAdapter.PO2DTO(rechargeCardExchangeCodePO);
        return rcExchangeCodeDTO;
    }

    @Override
    public void editSave(RCExchangeCodeEditDTO dto) {
        RechargeCardExchangeCodePO po = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(dto.getId());
        if(null == po){
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        po.setRechargeCardName(dto.getRechargeCardName());
        po.setExpiryTime(dto.getExpiryTime());
        po.setExpiryType(dto.getExpiryType());
        if(dto.getExpiryType().equals(Byte.valueOf("0"))){
            po.setRechargeCardExpiryTime(dto.getRechargeCardExpiryTime());
        }else{
            po.setValidityPeriod(dto.getValidityPeriod());
        }
        po.setBatchDescription(dto.getBatchDescription());
        po.setCityCodes(dto.getCityCodes());
        po.setProductIds(dto.getProductIds());
        po.setAddTime(new Date());
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public RechargeCardExchangeCodePO findByKeyt(String keyt) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rcExchangeCodePOManualMapper.findByKeyt(keyt);
        return rechargeCardExchangeCodePO;
    }
}
