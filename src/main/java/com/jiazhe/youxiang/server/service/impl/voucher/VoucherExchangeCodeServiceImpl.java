package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeAdapter;
import com.jiazhe.youxiang.server.dao.mapper.VoucherExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.voucher.VoucherExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePOExample;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherExchangeCodeService")
@Transactional(rollbackFor=Exception.class)
public class VoucherExchangeCodeServiceImpl implements VoucherExchangeCodeService {

    @Autowired
    private VoucherExchangeCodePOMapper voucherExchangeCodePOMapper;
    @Autowired
    private VoucherExchangeCodePOManualMapper voucherExchangeCodePOManualMapper;
    @Autowired
    private VoucherService voucherService;

    @Override
    public List<VoucherExchangeCodeDTO> getByBatchId(Integer id) {
        VoucherExchangeCodePOExample example = new VoucherExchangeCodePOExample();
        VoucherExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andBatchIdEqualTo(id);
        List<VoucherExchangeCodePO> poList = voucherExchangeCodePOMapper.selectByExample(example);
        return poList.stream().map(VoucherExchangeCodeAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void batchInsert(List<VoucherExchangeCodePO> voucherExchangeCodePOList) {
        voucherExchangeCodePOManualMapper.batchInsert(voucherExchangeCodePOList);
    }

    @Override
    public List<VoucherExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        Integer count = voucherExchangeCodePOManualMapper.count(batchId, code,keyt,status,used);
        List<VoucherExchangeCodePO> voucherExchangeCodePOList = voucherExchangeCodePOManualMapper.query(batchId, code,keyt,status,used, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return voucherExchangeCodePOList.stream().map(VoucherExchangeCodeAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void updateWithBatch(VoucherExchangeCodeBatchSaveDTO batchSaveDTO) {
        VoucherExchangeCodePOExample example = new VoucherExchangeCodePOExample();
        VoucherExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchSaveDTO.getId());
        List<VoucherExchangeCodePO> poList = voucherExchangeCodePOMapper.selectByExample(example);
        poList.stream().forEach(bean -> {
            bean.setBatchName(batchSaveDTO.getName());
            bean.setVoucherName(batchSaveDTO.getVoucherName());
            bean.setBatchDescription(batchSaveDTO.getDescription());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            bean.setExpiryTime(batchSaveDTO.getExpiryTime());
            bean.setExpiryType(batchSaveDTO.getExpiryType());
            bean.setVoucherExpiryTime(batchSaveDTO.getVoucherExpiryTime());
            bean.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        });
        voucherExchangeCodePOManualMapper.batchUpdate(poList);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(VoucherExchangeCodePO::getId).collect(Collectors.toList());
        if(!usedIds.isEmpty()){
            voucherService.batchUpdate(usedIds,batchSaveDTO);
        }
    }

    @Override
    public void batchChangeStatus(Integer batchId, Byte status) {
        voucherExchangeCodePOManualMapper.batchChangeStatus(batchId,status);
        VoucherExchangeCodePOExample example = new VoucherExchangeCodePOExample();
        VoucherExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchId);
        List<VoucherExchangeCodePO> poList = voucherExchangeCodePOMapper.selectByExample(example);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(VoucherExchangeCodePO::getId).collect(Collectors.toList());
        if(!usedIds.isEmpty()){
            voucherService.batchChangeStatus(usedIds,status);
        }
    }
}
