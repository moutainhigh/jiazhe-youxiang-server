package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeRecordAdapter;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dao.mapper.VoucherExchangeRecordPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.voucher.VoucherExchangeRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPOExample;
import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherExchangeRecordService")
@Transactional(rollbackFor=Exception.class)
public class VoucherExchangeRecordServiceImpl implements VoucherExchangeRecordService {

    @Autowired
    private VoucherExchangeRecordPOManualMapper voucherExchangeRecordPOManualMapper;
    @Autowired
    private VoucherExchangeRecordPOMapper voucherExchangeRecordPOMapper;


    @Override
    public List<VoucherExchangeRecordPO> findByCodeIds(List<Integer> codeIds) {
        return voucherExchangeRecordPOManualMapper.findByCodeIds(codeIds);
    }

    @Override
    public VoucherExchangeRecordDTO findByVoucherId(Integer voucherId) {
        VoucherExchangeRecordPOExample example = new VoucherExchangeRecordPOExample();
        VoucherExchangeRecordPOExample.Criteria criteria = example.createCriteria();
        criteria.andVoucherIdEqualTo(voucherId);
        List<VoucherExchangeRecordPO> poList = voucherExchangeRecordPOMapper.selectByExample(example);
        if(poList.isEmpty()||poList.size()>1){
            throw new VoucherException(VoucherCodeEnum.CARD_2_RECORD_EXCEPTION);
        }
        VoucherExchangeRecordDTO dto = VoucherExchangeRecordAdapter.PO2DTO(poList.get(0));
        return dto;
    }

    @Override
    public void insert(VoucherExchangeRecordPO voucherExchangeRecordPO) {
        voucherExchangeRecordPOManualMapper.insert(voucherExchangeRecordPO);
    }

    @Override
    public VoucherExchangeRecordDTO findByCodeId(Integer id) {
        VoucherExchangeRecordPOExample example = new VoucherExchangeRecordPOExample();
        VoucherExchangeRecordPOExample.Criteria criteria = example.createCriteria();
        criteria.andExchangeCodeIdEqualTo(id);
        List<VoucherExchangeRecordPO> poList = voucherExchangeRecordPOMapper.selectByExample(example);
        if(poList.isEmpty()){
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_HAS_NOT_USED);
        }
        if(poList.size()>1){
            throw new VoucherException(VoucherCodeEnum.CODE_2_RECORD_EXCEPTION);
        }
        VoucherExchangeRecordDTO dto = VoucherExchangeRecordAdapter.PO2DTO(poList.get(0));
        return dto;
    }
}
