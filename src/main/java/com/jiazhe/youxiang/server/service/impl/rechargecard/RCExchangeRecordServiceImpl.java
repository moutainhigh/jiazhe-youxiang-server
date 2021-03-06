package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeRecordAdapter;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeRecordPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPOExample;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("rcExchangeRecordService")
public class RCExchangeRecordServiceImpl implements RCExchangeRecordService {

    @Autowired
    private RCExchangeRecordPOManualMapper rcExchangeRecordPOManualMapper;
    @Autowired
    private RechargeCardExchangeRecordPOMapper rechargeCardExchangeRecordPOMapper;

    @Override
    public void insert(RechargeCardExchangeRecordPO rechargeCardRecordPO) {
        rcExchangeRecordPOManualMapper.insert(rechargeCardRecordPO);
    }

    @Override
    public List<RCExchangeRecordDTO> findByCodeIds(List<Integer> codeIds) {
        List<RechargeCardExchangeRecordPO> poList = rcExchangeRecordPOManualMapper.findByCodeIds(codeIds);
        return poList.stream().map(RCExchangeRecordAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public RCExchangeRecordDTO findByCardId(Integer cardId) {
        RechargeCardExchangeRecordPOExample example = new RechargeCardExchangeRecordPOExample();
        RechargeCardExchangeRecordPOExample.Criteria criteria = example.createCriteria();
        criteria.andRechargeCardIdEqualTo(cardId);
        List<RechargeCardExchangeRecordPO> poList = rechargeCardExchangeRecordPOMapper.selectByExample(example);
        if(poList.isEmpty()||poList.size()>1){
            throw new RechargeCardException(RechargeCardCodeEnum.CARD_2_RECORD_EXCEPTION);
        }
        RCExchangeRecordDTO dto = RCExchangeRecordAdapter.PO2DTO(poList.get(0));
        return dto;
    }

    @Override
    public RCExchangeRecordDTO findByCodeId(Integer id) {
        RechargeCardExchangeRecordPOExample example = new RechargeCardExchangeRecordPOExample();
        RechargeCardExchangeRecordPOExample.Criteria criteria = example.createCriteria();
        criteria.andExchangeCodeIdEqualTo(id);
        List<RechargeCardExchangeRecordPO> poList = rechargeCardExchangeRecordPOMapper.selectByExample(example);
        if(poList.isEmpty()){
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_HAS_NOT_USED);
        }
        if(poList.size()>1){
            throw new RechargeCardException(RechargeCardCodeEnum.CODE_2_RECORD_EXCEPTION);
        }
        RCExchangeRecordDTO dto = RCExchangeRecordAdapter.PO2DTO(poList.get(0));
        return dto;
    }
}
