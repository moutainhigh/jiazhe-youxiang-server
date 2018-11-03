package com.jiazhe.youxiang.server.service.impl.voucher;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeAdapter;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dao.mapper.VoucherExchangeCodeBatchPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.voucher.VoucherExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
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
    @Autowired
    private VoucherExchangeCodeService voucherExchangeCodeService;

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

    @Override
    public void generateCode(Integer id) {
        VoucherExchangeCodeBatchPO batchPO = voucherExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if(null == batchPO){
            throw new VoucherException(VoucherCodeEnum.BATCH_NOT_EXISTED);
        }
        List<VoucherExchangeCodeDTO> rcExchangeCodeDTOList = voucherExchangeCodeService.getByBatchId(id);
        //实际去查一下，批次下是否有兑换码
        if (!rcExchangeCodeDTOList.isEmpty()) {
            throw new VoucherException(VoucherCodeEnum.CODE_GENERATED);
        }
        batchPO.setIsMade(CommonConstant.EXCHANGE_CODE_HAS_MADE);
        voucherExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        List<VoucherExchangeCodeSaveDTO> voucherExchangeCodeSaveDTOList = Lists.newArrayList();
        Integer amount = batchPO.getAmount();
        String[][] codeAndKeyts = GenerateCode.generateCode(CommonConstant.VOUCHER_EXCHANGE_CODE_PREFIX, amount);
        for (int i = 0; i < amount; i++) {
            VoucherExchangeCodeSaveDTO voucherExchangeCodeSaveDTO = new VoucherExchangeCodeSaveDTO();
            voucherExchangeCodeSaveDTO.setBatchId(batchPO.getId());
            voucherExchangeCodeSaveDTO.setBatchName(batchPO.getName());
            voucherExchangeCodeSaveDTO.setVoucherName(batchPO.getVoucherName());
            voucherExchangeCodeSaveDTO.setBatchDescription(batchPO.getDescription());
            voucherExchangeCodeSaveDTO.setProjectId(batchPO.getProjectId());
            voucherExchangeCodeSaveDTO.setCityCodes(batchPO.getCityCodes());
            voucherExchangeCodeSaveDTO.setProductIds(batchPO.getProductIds());
            voucherExchangeCodeSaveDTO.setCode(codeAndKeyts[0][i]);
            voucherExchangeCodeSaveDTO.setKeyt(codeAndKeyts[1][i]);
            voucherExchangeCodeSaveDTO.setCount(batchPO.getCount());
            voucherExchangeCodeSaveDTO.setExpiryTime(batchPO.getExpiryTime());
            voucherExchangeCodeSaveDTO.setVoucherExpiryTime(batchPO.getVoucherExpiryTime());
            voucherExchangeCodeSaveDTO.setValidityPeriod(batchPO.getValidityPeriod());
            voucherExchangeCodeSaveDTO.setExpiryType(batchPO.getExpiryType());
            voucherExchangeCodeSaveDTO.setStatus(batchPO.getStatus());
            voucherExchangeCodeSaveDTO.setUsed(Byte.valueOf("0"));
            voucherExchangeCodeSaveDTOList.add(voucherExchangeCodeSaveDTO);
        }
        List<VoucherExchangeCodePO> voucherExchangeCodePOList = voucherExchangeCodeSaveDTOList.stream().map(VoucherExchangeCodeAdapter::DTOSave2PO).collect(Collectors.toList());
        voucherExchangeCodeService.batchInsert(voucherExchangeCodePOList);
    }
}
