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
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeRecordService;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
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
 * @date 2018/11/3
 */
@Service("voucherExchangeCodeBatchService")
public class VoucherExchangeCodeBatchServiceImpl implements VoucherExchangeCodeBatchService {

    @Autowired
    private VoucherExchangeCodeBatchPOMapper voucherExchangeCodeBatchPOMapper;
    @Autowired
    private VoucherExchangeCodeBatchPOManualMapper voucherExchangeCodeBatchPOManualMapper;
    @Autowired
    private VoucherExchangeCodeService voucherExchangeCodeService;
    @Autowired
    private VoucherExchangeRecordService voucherExchangeRecordService;
    @Autowired
    private VoucherService voucherService;

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
        po.setIsMade(CommonConstant.CODE_NOT_MADE);
        po.setStatus(CommonConstant.CODE_STOP_USING);
        voucherExchangeCodeBatchPOMapper.insertSelective(po);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void editSave(VoucherExchangeCodeBatchSaveDTO batchSaveDTO) {
        VoucherExchangeCodeBatchPO batchPO = voucherExchangeCodeBatchPOMapper.selectByPrimaryKey(batchSaveDTO.getId());
        if(null == batchPO){
            throw new VoucherException(VoucherCodeEnum.BATCH_NOT_EXISTED);
        }
        //批次肯定要修改的信息
        batchPO.setName(batchSaveDTO.getName());
        batchPO.setVoucherName(batchSaveDTO.getVoucherName());
        batchPO.setProjectId(batchSaveDTO.getProjectId());
        batchPO.setCityCodes(batchSaveDTO.getCityCodes());
        batchPO.setProductIds(batchSaveDTO.getProductIds());
        batchPO.setExpiryTime(batchSaveDTO.getExpiryTime());
        batchPO.setExpiryType(batchSaveDTO.getExpiryType());
        batchPO.setVoucherEffectiveTime(batchSaveDTO.getVoucherEffectiveTime());
        batchPO.setVoucherExpiryTime(batchSaveDTO.getVoucherExpiryTime());
        batchPO.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        batchPO.setDescription(batchSaveDTO.getDescription());
        //批次下面是否有码，有则为true
        List<VoucherExchangeCodeDTO> codeDTOList = voucherExchangeCodeService.getByBatchId(batchSaveDTO.getId());
        boolean batchEmpty = codeDTOList.isEmpty();
        //没有码则可以修改面额和数量
        if (batchEmpty) {
            batchPO.setAmount(batchSaveDTO.getAmount());
            batchPO.setCount(batchSaveDTO.getCount());
        }else {
            voucherExchangeCodeService.updateWithBatch(batchSaveDTO);
        }
        voucherExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
    }

    @Override
    public VoucherExchangeCodeBatchEditDTO getById(Integer id) {
        VoucherExchangeCodeBatchPO voucherExchangeCodeBatchPO = voucherExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if(null == voucherExchangeCodeBatchPO){
            throw new VoucherException(VoucherCodeEnum.BATCH_NOT_EXISTED);
        }
        VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO = VoucherExchangeCodeBatchAdapter.po2DtoEdit(voucherExchangeCodeBatchPO);
        return voucherExchangeCodeBatchEditDTO;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void generateCode(Integer id) {
        VoucherExchangeCodeBatchPO batchPO = voucherExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if(null == batchPO){
            throw new VoucherException(VoucherCodeEnum.BATCH_NOT_EXISTED);
        }
        if (batchPO.getIsMade().equals(CommonConstant.EXCHANGE_CODE_HAS_MADE)) {
            throw new VoucherException(VoucherCodeEnum.CODE_GENERATED);
        }
        //实际去查一下，批次下是否有兑换码
        List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOList = voucherExchangeCodeService.getByBatchId(id);

        if (!voucherExchangeCodeDTOList.isEmpty()) {
            throw new VoucherException(VoucherCodeEnum.CODE_GENERATED);
        }
        batchPO.setIsMade(CommonConstant.EXCHANGE_CODE_HAS_MADE);
        voucherExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        List<VoucherExchangeCodeSaveDTO> voucherExchangeCodeSaveDTOList = Lists.newArrayList();
        Integer amount = batchPO.getAmount();
//        String[][] codeAndKeyts = GenerateCode.generateCode(CommonConstant.VOUCHER_EXCHANGE_CODE_PREFIX, amount);
        for (int i = 0; i < amount; i++) {
            VoucherExchangeCodeSaveDTO voucherExchangeCodeSaveDTO = new VoucherExchangeCodeSaveDTO();
            voucherExchangeCodeSaveDTO.setBatchId(batchPO.getId());
            voucherExchangeCodeSaveDTO.setBatchName(batchPO.getName());
            voucherExchangeCodeSaveDTO.setVoucherName(batchPO.getVoucherName());
            voucherExchangeCodeSaveDTO.setBatchDescription(batchPO.getDescription());
            voucherExchangeCodeSaveDTO.setProjectId(batchPO.getProjectId());
            voucherExchangeCodeSaveDTO.setCityCodes(batchPO.getCityCodes());
            voucherExchangeCodeSaveDTO.setProductIds(batchPO.getProductIds());
            voucherExchangeCodeSaveDTO.setCode("");
            voucherExchangeCodeSaveDTO.setKeyt("");
            voucherExchangeCodeSaveDTO.setCount(batchPO.getCount());
            voucherExchangeCodeSaveDTO.setExpiryTime(batchPO.getExpiryTime());
            voucherExchangeCodeSaveDTO.setVoucherEffectiveTime(batchPO.getVoucherEffectiveTime());
            voucherExchangeCodeSaveDTO.setVoucherExpiryTime(batchPO.getVoucherExpiryTime());
            voucherExchangeCodeSaveDTO.setValidityPeriod(batchPO.getValidityPeriod());
            voucherExchangeCodeSaveDTO.setExpiryType(batchPO.getExpiryType());
            voucherExchangeCodeSaveDTO.setStatus(CommonConstant.CODE_STOP_USING);
            voucherExchangeCodeSaveDTO.setUsed(CommonConstant.CODE_NOT_USED);
            voucherExchangeCodeSaveDTOList.add(voucherExchangeCodeSaveDTO);
        }
        List<VoucherExchangeCodePO> voucherExchangeCodePOList = voucherExchangeCodeSaveDTOList.stream().map(VoucherExchangeCodeAdapter::DTOSave2PO).collect(Collectors.toList());
        voucherExchangeCodeService.batchInsert(voucherExchangeCodePOList);
        List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOS = voucherExchangeCodeService.getByBatchId(batchPO.getId());
        voucherExchangeCodeDTOS.stream().forEach(bean -> {
            Map map = GenerateCode.generateOneCode(CommonConstant.VOUCHER_EXCHANGE_CODE_PREFIX, bean.getId());
            bean.setCode(map.get("code").toString());
            bean.setKeyt(map.get("keyt").toString());
        });
        //此处更新code和keyt
        voucherExchangeCodeService.batchUpdateCodeAndKeyt(voucherExchangeCodeDTOS);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void changeBatchStatus(Integer id, Byte status) {
        VoucherExchangeCodeBatchPO batchPO = voucherExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if(null == batchPO){
            throw new VoucherException(VoucherCodeEnum.BATCH_NOT_EXISTED);
        }
        batchPO.setStatus(status);
        batchPO.setModTime(new Date());
        voucherExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        List<VoucherExchangeCodeDTO> codeDTOList = voucherExchangeCodeService.getByBatchId(id);
        List<Integer> usedIds = codeDTOList.stream().filter(bean -> bean.getUsed().equals(CommonConstant.CODE_HAS_USED)).map(VoucherExchangeCodeDTO::getId).collect(Collectors.toList());
        if (!usedIds.isEmpty()) {
            List<VoucherExchangeRecordDTO> recordDTOList = voucherExchangeRecordService.findByCodeIds(usedIds);
            List<Integer> voucherIds = recordDTOList.stream().map(VoucherExchangeRecordDTO::getVoucherId).collect(Collectors.toList());
            voucherService.batchChangeStatus(voucherIds, status);
        }
    }
}
