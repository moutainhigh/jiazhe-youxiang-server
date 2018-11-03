package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeAdapter;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dao.mapper.VoucherExchangeCodeBatchPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.voucher.VoucherExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeBatchService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherExchangeCodeBatchService")
@Transactional(rollbackFor=Exception.class)
public class VoucherExchangeCodeBatchServiceImpl implements VoucherExchangeCodeBatchService {

    @Autowired
    private VoucherExchangeCodeBatchPOMapper voucherExchangeCodeBatchPOMapper;
    @Autowired
    private VoucherExchangeCodeBatchPOManualMapper voucherExchangeCodeBatchPOManualMapper;

    @Override
    public List<VoucherExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        Integer count = voucherExchangeCodeBatchPOManualMapper.count(projectId, name);
        List<VoucherExchangeCodeBatchPO> voucherExchangeCodeBatchPOList = voucherExchangeCodeBatchPOManualMapper.query(projectId, name, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return voucherExchangeCodeBatchPOList.stream().map(VoucherExchangeCodeBatchAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void addSave(VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO) {
        VoucherExchangeCodeBatchPO po = VoucherExchangeCodeBatchAdapter.DTOSave2PO(voucherExchangeCodeBatchSaveDTO);
        po.setIsMade(Byte.valueOf("0"));
        po.setStatus(Byte.valueOf("1"));
        po.setIsDeleted(Byte.valueOf("0"));
        po.setExtInfo("");
        po.setAddTime(new Date());
        po.setModTime(new Date());
        voucherExchangeCodeBatchPOMapper.insert(po);
    }

    @Override
    public void editSave(VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO) {

    }

    @Override
    public VoucherExchangeCodeBatchEditDTO getById(Integer id) {
        VoucherExchangeCodeBatchPO voucherExchangeCodeBatchPO = voucherExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if(null == voucherExchangeCodeBatchPO){
            throw new VoucherException(VoucherCodeEnum.BATCH_NOT_EXISTED);
        }
        VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO = VoucherExchangeCodeAdapter.PO2DTOEdit(voucherExchangeCodeBatchPO);
        return voucherExchangeCodeBatchEditDTO;
    }
}
