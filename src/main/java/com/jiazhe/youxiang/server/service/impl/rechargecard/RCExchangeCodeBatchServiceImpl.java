package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodeBatchPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPOExample;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.ProjectService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
@Service("rcExchangeCodeBatchService")
public class RCExchangeCodeBatchServiceImpl implements RCExchangeCodeBatchService {

    @Autowired
    private RCExchangeCodeBatchPOManualMapper rcExchangeCodeBatchPOManualMapper;
    @Autowired
    private RechargeCardExchangeCodeBatchPOMapper rechargeCardExchangeCodeBatchPOMapper;
    @Autowired
    private RCExchangeCodeService rcExchangeCodeService;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;
    @Autowired
    private RCService rcService;
    @Autowired
    private ProjectService projectService;

    @Override
    public List<RCExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        Integer count = rcExchangeCodeBatchPOManualMapper.count(projectId, name);
        List<RechargeCardExchangeCodeBatchPO> rechargeCardExchangeCodeBatchPOList = rcExchangeCodeBatchPOManualMapper.query(projectId, name, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return rechargeCardExchangeCodeBatchPOList.stream().map(RCExchangeCodeBatchAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void addSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO) {
        ProjectDTO projectDTO = projectService.getById(rcExchangeCodeBatchSaveDTO.getProjectId());
        if(null == projectDTO){
            throw new PointException(PointCodeEnum.PROJECT_IS_NOT_EXIST);
        }
        RechargeCardExchangeCodeBatchPO rcExchangeCodeBatchPO = RCExchangeCodeBatchAdapter.DTOSave2PO(rcExchangeCodeBatchSaveDTO);
        rcExchangeCodeBatchPO.setIsMade(CommonConstant.CODE_NOT_MADE);
        rcExchangeCodeBatchPO.setStatus(CommonConstant.CODE_STOP_USING);
        rechargeCardExchangeCodeBatchPOMapper.insertSelective(rcExchangeCodeBatchPO);
    }

    @Override
    public RCExchangeCodeBatchEditDTO getById(Integer id) {
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if (null == rechargeCardExchangeCodeBatchPO) {
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = RCExchangeCodeBatchAdapter.po2DtoEdit(rechargeCardExchangeCodeBatchPO);
        return rcExchangeCodeBatchEditDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editSave(RCExchangeCodeBatchSaveDTO batchSaveDTO) {
        ProjectDTO projectDTO = projectService.getById(batchSaveDTO.getProjectId());
        if(null == projectDTO){
            throw new PointException(PointCodeEnum.PROJECT_IS_NOT_EXIST);
        }
        RechargeCardExchangeCodeBatchPO batchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(batchSaveDTO.getId());
        if (null == batchPO) {
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
        batchPO.setRechargeCardEffectiveTime(batchSaveDTO.getRechargeCardEffectiveTime());
        batchPO.setRechargeCardExpiryTime(batchSaveDTO.getRechargeCardExpiryTime());
        batchPO.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        batchPO.setDescription(batchSaveDTO.getDescription());
        //不是虚拟批次，要修改批次、码、充值卡的信息
        if (!batchPO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            //批次下面是否有码，有则为true
            List<RCExchangeCodeDTO> codeDTOList = rcExchangeCodeService.getByBatchId(batchSaveDTO.getId());
            //没有码则可以修改面额和数量
            if (codeDTOList.isEmpty()) {
                batchPO.setAmount(batchSaveDTO.getAmount());
                batchPO.setFaceValue(batchSaveDTO.getFaceValue());
            } else {
                rcExchangeCodeService.updateWithBatch(batchSaveDTO);
            }
        }
        rechargeCardExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeBatchStatus(Integer id, Byte status) {
        RechargeCardExchangeCodeBatchPO batchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if (null == batchPO) {
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        batchPO.setStatus(status);
        batchPO.setModTime(new Date());
        rechargeCardExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        //非虚拟批次，需要修改批次下的积分卡启、停用状态
        if (!batchPO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            List<RCExchangeCodeDTO> codeDTOList = rcExchangeCodeService.getByBatchId(id);
            List<Integer> usedIds = codeDTOList.stream().filter(bean -> bean.getUsed().equals(CommonConstant.CODE_HAS_USED)).map(RCExchangeCodeDTO::getId).collect(Collectors.toList());
            if (!usedIds.isEmpty()) {
                List<RCExchangeRecordDTO> recordDTOList = rcExchangeRecordService.findByCodeIds(usedIds);
                List<Integer> cardIds = recordDTOList.stream().map(RCExchangeRecordDTO::getRechargeCardId).collect(Collectors.toList());
                rcService.batchChangeStatus(cardIds, status);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void generateCode(Integer id) {
        RechargeCardExchangeCodeBatchPO batchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if (null == batchPO) {
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        if (batchPO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            throw new RechargeCardException(RechargeCardCodeEnum.VIRTUAL_BATCH_CANNOT_GENERATE);
        }
        if (batchPO.getIsMade().equals(CommonConstant.EXCHANGE_CODE_HAS_MADE)) {
            throw new RechargeCardException(RechargeCardCodeEnum.CODE_GENERATED);
        }
        //实际去查一下，批次下是否有兑换码
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList = rcExchangeCodeService.getByBatchId(id);
        if (!rcExchangeCodeDTOList.isEmpty()) {
            throw new RechargeCardException(RechargeCardCodeEnum.CODE_GENERATED);
        }
        batchPO.setIsMade(CommonConstant.EXCHANGE_CODE_HAS_MADE);
        rechargeCardExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        List<RCExchangeCodeSaveDTO> rcExchangeCodeSaveDTOS = Lists.newArrayList();
        Integer amount = batchPO.getAmount();
        for (int i = 0; i < amount; i++) {
            RCExchangeCodeSaveDTO rcExchangeCodeSaveDTO = new RCExchangeCodeSaveDTO();
            rcExchangeCodeSaveDTO.setBatchId(batchPO.getId());
            rcExchangeCodeSaveDTO.setBatchName(batchPO.getName());
            rcExchangeCodeSaveDTO.setRechargeCardName(batchPO.getRechargeCardName());
            rcExchangeCodeSaveDTO.setProjectId(batchPO.getProjectId());
            rcExchangeCodeSaveDTO.setCityCodes(batchPO.getCityCodes());
            rcExchangeCodeSaveDTO.setProductIds(batchPO.getProductIds());
            rcExchangeCodeSaveDTO.setCode("");
            rcExchangeCodeSaveDTO.setKeyt("");
            rcExchangeCodeSaveDTO.setFaceValue(batchPO.getFaceValue());
            rcExchangeCodeSaveDTO.setExpiryTime(batchPO.getExpiryTime());
            rcExchangeCodeSaveDTO.setExpiryType(batchPO.getExpiryType());
            rcExchangeCodeSaveDTO.setRechargeCardEffectiveTime(batchPO.getRechargeCardEffectiveTime());
            rcExchangeCodeSaveDTO.setRechargeCardExpiryTime(batchPO.getRechargeCardExpiryTime());
            rcExchangeCodeSaveDTO.setValidityPeriod(batchPO.getValidityPeriod());
            rcExchangeCodeSaveDTO.setBatchDescription(batchPO.getDescription());
            rcExchangeCodeSaveDTO.setStatus(CommonConstant.CODE_STOP_USING);
            rcExchangeCodeSaveDTO.setUsed(CommonConstant.CODE_NOT_USED);
            rcExchangeCodeSaveDTOS.add(rcExchangeCodeSaveDTO);
        }
        List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList = rcExchangeCodeSaveDTOS.stream().map(RCExchangeCodeAdapter::DTOSave2PO).collect(Collectors.toList());
        rcExchangeCodeService.batchInsert(rechargeCardExchangeCodePOList);
        List<RCExchangeCodeDTO> rcExchangeCodeDTOS = rcExchangeCodeService.getByBatchId(batchPO.getId());
        rcExchangeCodeDTOS.stream().forEach(bean -> {
            Map map = GenerateCode.generateOneCode(CommonConstant.RC_EXCHANGE_CODE_PREFIX, bean.getId());
            bean.setCode(map.get("code").toString());
            bean.setKeyt(map.get("keyt").toString());
        });
        rcExchangeCodeService.batchUpdateCodeAndKeyt(rcExchangeCodeDTOS);
    }

    @Override
    public List<RCExchangeCodeBatchDTO> getVirtualByProjectId(Integer projectId) {
        RechargeCardExchangeCodeBatchPOExample example = new RechargeCardExchangeCodeBatchPOExample();
        RechargeCardExchangeCodeBatchPOExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andIsVirtualEqualTo(CommonConstant.BATCH_IS_VIRTUAL);
        List<RechargeCardExchangeCodeBatchPO> poList = rechargeCardExchangeCodeBatchPOMapper.selectByExample(example);
        List<RechargeCardExchangeCodeBatchPO> validBatchList = Lists.newArrayList();
        //筛选出转为充值卡不过期的批次
        poList.stream().forEach(bean -> {
            if (bean.getExpiryTime().getTime() > System.currentTimeMillis()) {
                if (bean.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME) && bean.getRechargeCardExpiryTime().getTime() > System.currentTimeMillis()) {
                    validBatchList.add(bean);
                }
                if (bean.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXCHANGE_PERIOD) && bean.getValidityPeriod() > 0) {
                    validBatchList.add(bean);
                }
            }
        });
        return validBatchList.stream().map(RCExchangeCodeBatchAdapter::PO2DTO).collect(Collectors.toList());
    }
}
