package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodeBatchPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RCExchangeCodeBatchServiceImpl implements RCExchangeCodeBatchService {

    @Autowired
    private RCExchangeCodeBatchPOManualMapper rcExchangeCodeBatchPOManualMapper;
    @Autowired
    private RechargeCardExchangeCodeBatchPOMapper rechargeCardExchangeCodeBatchPOMapper;
    @Autowired
    private RCExchangeCodeService rcExchangeCodeService;

    @Override
    public List<RCExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        Integer count = rcExchangeCodeBatchPOManualMapper.count(projectId, name);
        List<RechargeCardExchangeCodeBatchPO> rechargeCardExchangeCodeBatchPOList = rcExchangeCodeBatchPOManualMapper.query(projectId, name, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        if (paging.getLimit() + paging.getOffset() >= count) {
            paging.setHasMore(false);
        }
        return rechargeCardExchangeCodeBatchPOList.stream().map(RCExchangeCodeBatchAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public int addSave(RCExchangeCodeBatchAddDTO rcExchangeCodeBatchAddDTO) {
        RechargeCardExchangeCodeBatchPO rcExchangeCodeBatchPO = RCExchangeCodeBatchAdapter.DTOSave2PO(rcExchangeCodeBatchAddDTO);
        rcExchangeCodeBatchPO.setStatus(Byte.valueOf("1"));
        rcExchangeCodeBatchPO.setIsDeleted(Byte.valueOf("0"));
        rcExchangeCodeBatchPO.setExtInfo("");
        rcExchangeCodeBatchPO.setAddTime(new Date());
        rcExchangeCodeBatchPO.setModTime(new Date());
        return rechargeCardExchangeCodeBatchPOMapper.insert(rcExchangeCodeBatchPO);
    }

    @Override
    public RCExchangeCodeBatchEditDTO getById(Integer id) {
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = RCExchangeCodeAdapter.PO2DTOEdit(rechargeCardExchangeCodeBatchPO);
        return rcExchangeCodeBatchEditDTO;
    }

    @Override
    public int editSave(RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO) {
        //是否修改该批次下的兑换码相关信息？？？
        return 1;
    }

    @Override
    public int changeBatchStatus(Integer id, Byte status) {
        //修改改批次下的兑换码启用停用状态
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        rechargeCardExchangeCodeBatchPO.setStatus(status);
        rechargeCardExchangeCodeBatchPO.setModTime(new Date());
        rechargeCardExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodeBatchPO);
        return 1;
    }

    @Override
    public int generateCode(Integer id) {
        RechargeCardExchangeCodeBatchPO batchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        List<RCExchangeCodeSaveDTO> rcExchangeCodeSaveDTOS = new ArrayList<RCExchangeCodeSaveDTO>();
        Integer amount = batchPO.getAmount();
        String[][] codeAndKeyts = GenerateCode.generateCode(CommonConstant.RC_EXCHANGE_CODE_PREFIX,amount);

        for(int i=0;i<amount;i++){
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
            rcExchangeCodeSaveDTO.setStatus(Byte.valueOf("1"));
            rcExchangeCodeSaveDTO.setUsed(Byte.valueOf("0"));
            rcExchangeCodeSaveDTOS.add(rcExchangeCodeSaveDTO);
        }
        List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList = rcExchangeCodeSaveDTOS.stream().map(RCExchangeCodeAdapter::DTOSave2PO).collect(Collectors.toList());
        rcExchangeCodeService.batchInsert(rechargeCardExchangeCodePOList);
        return 0;
    }
}
