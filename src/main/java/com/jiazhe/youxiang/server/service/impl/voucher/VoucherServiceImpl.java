package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.manual.voucher.VoucherPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.VoucherPO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeRecordService;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherService")
@Transactional(rollbackFor=Exception.class)
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherPOManualMapper voucherPOManualMapper;
    @Autowired
    private VoucherExchangeRecordService voucherExchangeRecordService;
    @Override
    public void batchUpdate(List<Integer> usedIds, VoucherExchangeCodeBatchSaveDTO batchSaveDTO) {
        List<VoucherExchangeRecordPO> recordPOList = voucherExchangeRecordService.findByCodeIds(usedIds);
        List<Integer> cardIds = recordPOList.stream().map(VoucherExchangeRecordPO::getVoucherId).collect(Collectors.toList());
        List<VoucherPO> rcPOList = voucherPOManualMapper.findByIds(cardIds);
        rcPOList.stream().forEach(bean -> {
            bean.setName(batchSaveDTO.getVoucherName());
            bean.setDescription(batchSaveDTO.getDescription());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            //直接指定过期时间
            if(batchSaveDTO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)){
                bean.setExpiryTime(batchSaveDTO.getVoucherExpiryTime());
            }else{
                bean.setExpiryTime(new Date(bean.getAddTime().getTime()+batchSaveDTO.getValidityPeriod()* CommonConstant.ONE_DAY));
            }
        });
        voucherPOManualMapper.batchUpdate(rcPOList);
    }

    @Override
    public void batchChangeStatus(List<Integer> usedIds, Byte status) {
        List<VoucherExchangeRecordPO> recordPOList = voucherExchangeRecordService.findByCodeIds(usedIds);
        List<Integer> cardIds = recordPOList.stream().map(VoucherExchangeRecordPO::getVoucherId).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("status",status);
        map.put("ids",cardIds);
        voucherPOManualMapper.batchChangeStatus(map);
    }
}
