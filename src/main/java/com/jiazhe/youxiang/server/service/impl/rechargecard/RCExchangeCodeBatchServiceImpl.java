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
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchListDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
@Service("rcExchangeCodeBatchService")
public class RCExchangeCodeBatchServiceImpl implements RCExchangeCodeBatchService{

    @Autowired
    private RCExchangeCodeBatchPOManualMapper rcExchangeCodeBatchPOManualMapper;
    @Autowired
    private RechargeCardExchangeCodeBatchPOMapper rechargeCardExchangeCodeBatchPOMapper;
    @Autowired
    private RCExchangeCodeService rcExchangeCodeService;

    @Override
    public List<RCExchangeCodeBatchListDTO> getList(Integer projectId, String name, Paging paging) {
        Integer count = rcExchangeCodeBatchPOManualMapper.count(projectId,name);
        List<RechargeCardExchangeCodeBatchPO> rechargeCardExchangeCodeBatchPOList = rcExchangeCodeBatchPOManualMapper.query(projectId,name,paging.getOffset(),paging.getLimit());
        paging.setTotal(count);
        if (paging.getLimit() + paging.getOffset() >= count) {
            paging.setHasMore(false);
        }
        return rechargeCardExchangeCodeBatchPOList.stream().map(RCExchangeCodeBatchAdapter::PO2DTOList).collect(Collectors.toList());
    }

    @Override
    public int addSave(RCExchangeCodeBatchAddDTO rcExchangeCodeBatchAddDTO) {
        RechargeCardExchangeCodeBatchPO rcExchangeCodeBatchPO  = RCExchangeCodeBatchAdapter.DTOSave2PO(rcExchangeCodeBatchAddDTO);
        rcExchangeCodeBatchPOManualMapper.insert(rcExchangeCodeBatchPO);
        List<RCExchangeCodeSaveDTO> rcExchangeCodeSaveDTOS = new ArrayList<RCExchangeCodeSaveDTO>();
        Integer amount = rcExchangeCodeBatchAddDTO.getAmount();
        String[][] codeAndKeyts = GenerateCode.generateCode(CommonConstant.RC_EXCHANGE_CODE_PREFIX,amount);
        //保存批次信息，并保存批次下的兑换码
        for(int i=0;i<amount;i++){
            RCExchangeCodeSaveDTO rcExchangeCodeSaveDTO = new RCExchangeCodeSaveDTO();
            rcExchangeCodeSaveDTO.setBatchId(rcExchangeCodeBatchPO.getId());
            rcExchangeCodeSaveDTO.setBatchName(rcExchangeCodeBatchAddDTO.getName());
            rcExchangeCodeSaveDTO.setBatchDescription(rcExchangeCodeBatchAddDTO.getDescription());
            rcExchangeCodeSaveDTO.setProjectId(rcExchangeCodeBatchAddDTO.getProjectId());
            rcExchangeCodeSaveDTO.setCityIds(rcExchangeCodeBatchAddDTO.getCityIds());
            rcExchangeCodeSaveDTO.setProductIds(rcExchangeCodeBatchAddDTO.getProductIds());
            rcExchangeCodeSaveDTO.setCode(codeAndKeyts[0][i]);
            rcExchangeCodeSaveDTO.setKeyt(codeAndKeyts[1][i]);
            rcExchangeCodeSaveDTO.setFaceValue(rcExchangeCodeBatchAddDTO.getFaceValue());
            rcExchangeCodeSaveDTO.setExpiryTime(rcExchangeCodeBatchAddDTO.getExpiryTime());
            rcExchangeCodeSaveDTO.setRechargeCardExpiryTime(rcExchangeCodeBatchAddDTO.getRechargeCardExpiryTime());
            rcExchangeCodeSaveDTO.setValidityPeriod(rcExchangeCodeBatchAddDTO.getValidityPeriod());
            rcExchangeCodeSaveDTO.setExpiryType(rcExchangeCodeBatchAddDTO.getExpiryType());
            rcExchangeCodeSaveDTO.setStatus(Byte.valueOf("1"));
            rcExchangeCodeSaveDTO.setUsed(Byte.valueOf("0"));
            rcExchangeCodeSaveDTOS.add(rcExchangeCodeSaveDTO);
        }
        List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList = rcExchangeCodeSaveDTOS.stream().map(RCExchangeCodeAdapter::DTOSave2PO).collect(Collectors.toList());
        return rcExchangeCodeService.batchSave(rechargeCardExchangeCodePOList);
    }

    @Override
    public RCExchangeCodeBatchEditDTO getById(Integer id) {
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = RCExchangeCodeAdapter.PO2DTOEdit(rechargeCardExchangeCodeBatchPO);
        return rcExchangeCodeBatchEditDTO;
    }

    @Override
    public int editSave(RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO) {
        //是否修改该批次下的兑换码相关信息
        return 0;
    }

    @Override
    public int changeBatchStatus(Integer id, Byte status) {
        //修改改批次下的兑换码启用停用状态
        RechargeCardExchangeCodeBatchPO rechargeCardExchangeCodeBatchPO = rechargeCardExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        rechargeCardExchangeCodeBatchPO.setStatus(status);
        rechargeCardExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodeBatchPO);
        return 0;
    }
}
