package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodeBatchPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPOExample;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
@Service("rcExchangeCodeBatchService")
@Transactional(rollbackFor=Exception.class)
public class RCExchangeCodeBatchServiceImpl implements RCExchangeCodeBatchService {

    @Autowired
    private RCExchangeCodeBatchPOManualMapper rcExchangeCodeBatchPOManualMapper;
    @Autowired
    private RechargeCardExchangeCodeBatchPOMapper rechargeCardExchangeCodeBatchPOMapper;
    @Autowired
    private RCExchangeCodeService rcExchangeCodeService;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;

    @Override
    public List<RCExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        Integer count = rcExchangeCodeBatchPOManualMapper.count(projectId, name);
        List<RechargeCardExchangeCodeBatchPO> rechargeCardExchangeCodeBatchPOList = rcExchangeCodeBatchPOManualMapper.query(projectId, name, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return rechargeCardExchangeCodeBatchPOList.stream().map(RCExchangeCodeBatchAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void addSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO) {
        RechargeCardExchangeCodeBatchPO rcExchangeCodeBatchPO = RCExchangeCodeBatchAdapter.DTOSave2PO(rcExchangeCodeBatchSaveDTO);
        rcExchangeCodeBatchPO.setIsMade(Byte.valueOf("0"));
        rcExchangeCodeBatchPO.setStatus(Byte.valueOf("1"));
        rcExchangeCodeBatchPO.setIsDeleted(Byte.valueOf("0"));
        rcExchangeCodeBatchPO.setExtInfo("");
        rcExchangeCodeBatchPO.setAddTime(new Date());
        rcExchangeCodeBatchPO.setModTime(new Date());
        rechargeCardExchangeCodeBatchPOMapper.insert(rcExchangeCodeBatchPO);
    }

    @Override
    public RCExchangeCodeBatchEditDTO getById(Integer id) {
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if(null == rechargeCardExchangeCodeBatchPO){
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = RCExchangeCodeAdapter.PO2DTOEdit(rechargeCardExchangeCodeBatchPO);
        return rcExchangeCodeBatchEditDTO;
    }

    @Override
    public void editSave(RCExchangeCodeBatchSaveDTO batchSaveDTO) {
        RechargeCardExchangeCodeBatchPO batchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(batchSaveDTO.getId());
        if(null == batchPO){
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        //批次肯定要修改的信息
        batchPO.setName(batchSaveDTO.getName());
        batchPO.setRechargeCardName(batchSaveDTO.getRechargeCardName());
        batchPO.setProjectId(batchSaveDTO.getProjectId());
        batchPO.setCityCodes(batchSaveDTO.getCityCodes());
        batchPO.setProductIds(batchSaveDTO.getProductIds());
        batchPO.setExpiryTime(batchSaveDTO.getExpiryTime());
        batchPO.setExpiryType(batchSaveDTO.getExpiryType());
        batchPO.setRechargeCardExpiryTime(batchSaveDTO.getRechargeCardExpiryTime());
        batchPO.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        batchPO.setDescription(batchSaveDTO.getDescription());
        //不是虚拟批次，要修改批次、码、充值卡的信息
        if (!batchPO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            //批次下面是否有码，有则为true
            List<RCExchangeCodeDTO> codeDTOList = rcExchangeCodeService.getByBatchId(batchSaveDTO.getId());
            boolean batchEmpty = codeDTOList.isEmpty();
            //没有码则可以修改面额和数量
            if (batchEmpty) {
                batchPO.setAmount(batchSaveDTO.getAmount());
                batchPO.setFaceValue(batchSaveDTO.getFaceValue());
            }else {
                rcExchangeCodeService.updateWithBatch(batchSaveDTO);
            }
        }
        rechargeCardExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
    }

    @Override
    public void changeBatchStatus(Integer id, Byte status) {
        RechargeCardExchangeCodeBatchPO batchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if(null == batchPO){
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        batchPO.setStatus(status);
        batchPO.setModTime(new Date());
        rechargeCardExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        //修改改批次下的兑换码启用停用状态
        if (!batchPO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            List<RCExchangeCodeDTO> codeDTOList = rcExchangeCodeService.getByBatchId(id);
            boolean batchEmpty = codeDTOList.isEmpty();
            //有码则修改对应的码信息
            if (!batchEmpty) {
                rcExchangeCodeService.batchChangeStatus(id, status);
            }
        }
    }

    @Override
    public void generateCode(Integer id) {
        RechargeCardExchangeCodeBatchPO batchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if(null == batchPO){
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList = rcExchangeCodeService.getByBatchId(id);
        //实际去查一下，批次下是否有兑换码
        if (!rcExchangeCodeDTOList.isEmpty()) {
            throw new RechargeCardException(RechargeCardCodeEnum.CODE_GENERATED);
        }
        batchPO.setIsMade(CommonConstant.EXCHANGE_CODE_HAS_MADE);
        rechargeCardExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        List<RCExchangeCodeSaveDTO> rcExchangeCodeSaveDTOS = Lists.newArrayList();
        Integer amount = batchPO.getAmount();
        String[][] codeAndKeyts = GenerateCode.generateCode(CommonConstant.RC_EXCHANGE_CODE_PREFIX, amount);
        for (int i = 0; i < amount; i++) {
            RCExchangeCodeSaveDTO rcExchangeCodeSaveDTO = new RCExchangeCodeSaveDTO();
            rcExchangeCodeSaveDTO.setBatchId(batchPO.getId());
            rcExchangeCodeSaveDTO.setBatchName(batchPO.getName());
            rcExchangeCodeSaveDTO.setRechargeCardName(batchPO.getRechargeCardName());
            rcExchangeCodeSaveDTO.setBatchDescription(batchPO.getDescription());
            rcExchangeCodeSaveDTO.setProjectId(batchPO.getProjectId());
            rcExchangeCodeSaveDTO.setCityCodes(batchPO.getCityCodes());
            rcExchangeCodeSaveDTO.setProductIds(batchPO.getProductIds());
            rcExchangeCodeSaveDTO.setCode(codeAndKeyts[0][i]);
            rcExchangeCodeSaveDTO.setKeyt(codeAndKeyts[1][i]);
            rcExchangeCodeSaveDTO.setFaceValue(batchPO.getFaceValue());
            rcExchangeCodeSaveDTO.setExpiryTime(batchPO.getExpiryTime());
            rcExchangeCodeSaveDTO.setRechargeCardExpiryTime(batchPO.getRechargeCardExpiryTime());
            rcExchangeCodeSaveDTO.setValidityPeriod(batchPO.getValidityPeriod());
            rcExchangeCodeSaveDTO.setExpiryType(batchPO.getExpiryType());
            rcExchangeCodeSaveDTO.setStatus(batchPO.getStatus());
            rcExchangeCodeSaveDTO.setUsed(Byte.valueOf("0"));
            rcExchangeCodeSaveDTOS.add(rcExchangeCodeSaveDTO);
        }
        List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList = rcExchangeCodeSaveDTOS.stream().map(RCExchangeCodeAdapter::DTOSave2PO).collect(Collectors.toList());
        rcExchangeCodeService.batchInsert(rechargeCardExchangeCodePOList);
    }

    @Override
    public List<RCExchangeCodeBatchDTO> getByProjectId(Integer projectId) {
        RechargeCardExchangeCodeBatchPOExample example = new RechargeCardExchangeCodeBatchPOExample();
        RechargeCardExchangeCodeBatchPOExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andIsVirtualEqualTo(CommonConstant.BATCH_IS_VIRTUAL);
        List<RechargeCardExchangeCodeBatchPO> poList = rechargeCardExchangeCodeBatchPOMapper.selectByExample(example);
        return poList.stream().map(RCExchangeCodeBatchAdapter::PO2DTO).collect(Collectors.toList());
    }
}
