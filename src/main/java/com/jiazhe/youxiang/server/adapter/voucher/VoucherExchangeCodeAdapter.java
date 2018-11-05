package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecode.VoucherExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecode.VoucherExchangeCodeResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodeAdapter {
    public static VoucherExchangeCodeResp DTO2Resp(VoucherExchangeCodeDTO dto) {
        if (dto == null) {
            return null;
        }
        VoucherExchangeCodeResp voucherExchangeCodeResp = new VoucherExchangeCodeResp();
        voucherExchangeCodeResp.setId(dto.getId());
        voucherExchangeCodeResp.setBatchName(dto.getBatchName());
        voucherExchangeCodeResp.setVoucherName(dto.getVoucherName());
        voucherExchangeCodeResp.setBatchDescription(dto.getBatchDescription());
        voucherExchangeCodeResp.setCityCodes(dto.getCityCodes());
        voucherExchangeCodeResp.setProductIds(dto.getProductIds());
        voucherExchangeCodeResp.setCount(dto.getCount());
        voucherExchangeCodeResp.setCode(dto.getCode());
        voucherExchangeCodeResp.setKeyt(dto.getKeyt());
        voucherExchangeCodeResp.setExpiryTime(dto.getExpiryTime());
        voucherExchangeCodeResp.setVoucherExpiryTime(dto.getVoucherExpiryTime());
        voucherExchangeCodeResp.setValidityPeriod(dto.getValidityPeriod());
        voucherExchangeCodeResp.setExpiryType(dto.getExpiryType());
        voucherExchangeCodeResp.setStatus(dto.getStatus());
        voucherExchangeCodeResp.setUsed(dto.getUsed());
        return voucherExchangeCodeResp;
    }

    public static VoucherExchangeCodeBatchEditDTO PO2DTOEdit(VoucherExchangeCodeBatchPO voucherExchangeCodeBatchPO) {
        if (voucherExchangeCodeBatchPO == null) {
            return null;
        }
        VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO = new VoucherExchangeCodeBatchEditDTO();
        voucherExchangeCodeBatchEditDTO.setId(voucherExchangeCodeBatchPO.getId());
        voucherExchangeCodeBatchEditDTO.setName(voucherExchangeCodeBatchPO.getName());
        voucherExchangeCodeBatchEditDTO.setVoucherName(voucherExchangeCodeBatchPO.getVoucherName());
        voucherExchangeCodeBatchEditDTO.setProjectId(voucherExchangeCodeBatchPO.getProjectId());
        voucherExchangeCodeBatchEditDTO.setIsMade(voucherExchangeCodeBatchPO.getIsMade());
        voucherExchangeCodeBatchEditDTO.setDescription(voucherExchangeCodeBatchPO.getDescription());
        voucherExchangeCodeBatchEditDTO.setAmount(voucherExchangeCodeBatchPO.getAmount());
        voucherExchangeCodeBatchEditDTO.setCityCodes(voucherExchangeCodeBatchPO.getCityCodes());
        voucherExchangeCodeBatchEditDTO.setProductIds(voucherExchangeCodeBatchPO.getProductIds());
        voucherExchangeCodeBatchEditDTO.setCount(voucherExchangeCodeBatchPO.getCount());
        voucherExchangeCodeBatchEditDTO.setExpiryTime(voucherExchangeCodeBatchPO.getExpiryTime());
        voucherExchangeCodeBatchEditDTO.setVoucherExpiryTime(voucherExchangeCodeBatchPO.getVoucherExpiryTime());
        voucherExchangeCodeBatchEditDTO.setValidityPeriod(voucherExchangeCodeBatchPO.getValidityPeriod());
        voucherExchangeCodeBatchEditDTO.setExpiryType(voucherExchangeCodeBatchPO.getExpiryType());
        return voucherExchangeCodeBatchEditDTO;
    }

    public static VoucherExchangeCodePO DTOSave2PO(VoucherExchangeCodeSaveDTO voucherExchangeCodeSaveDTO) {
        if (voucherExchangeCodeSaveDTO == null) {
            return null;
        }
        VoucherExchangeCodePO voucherExchangeCodePO = new VoucherExchangeCodePO();
        voucherExchangeCodePO.setBatchId(voucherExchangeCodeSaveDTO.getBatchId());
        voucherExchangeCodePO.setBatchName(voucherExchangeCodeSaveDTO.getBatchName());
        voucherExchangeCodePO.setVoucherName(voucherExchangeCodeSaveDTO.getVoucherName());
        voucherExchangeCodePO.setBatchDescription(voucherExchangeCodeSaveDTO.getBatchDescription());
        voucherExchangeCodePO.setProjectId(voucherExchangeCodeSaveDTO.getProjectId());
        voucherExchangeCodePO.setCityCodes(voucherExchangeCodeSaveDTO.getCityCodes());
        voucherExchangeCodePO.setProductIds(voucherExchangeCodeSaveDTO.getProductIds());
        voucherExchangeCodePO.setCount(voucherExchangeCodeSaveDTO.getCount());
        voucherExchangeCodePO.setCode(voucherExchangeCodeSaveDTO.getCode());
        voucherExchangeCodePO.setKeyt(voucherExchangeCodeSaveDTO.getKeyt());
        voucherExchangeCodePO.setExpiryTime(voucherExchangeCodeSaveDTO.getExpiryTime());
        voucherExchangeCodePO.setVoucherExpiryTime(voucherExchangeCodeSaveDTO.getVoucherExpiryTime());
        voucherExchangeCodePO.setValidityPeriod(voucherExchangeCodeSaveDTO.getValidityPeriod());
        voucherExchangeCodePO.setExpiryType(voucherExchangeCodeSaveDTO.getExpiryType());
        voucherExchangeCodePO.setStatus(voucherExchangeCodeSaveDTO.getStatus());
        voucherExchangeCodePO.setUsed(voucherExchangeCodeSaveDTO.getUsed());
        voucherExchangeCodePO.setCustomerId(voucherExchangeCodeSaveDTO.getCustomerId());
        return voucherExchangeCodePO;
    }

    public static VoucherExchangeCodeDTO PO2DTO(VoucherExchangeCodePO voucherExchangeCodePO) {
        if (voucherExchangeCodePO == null) {
            return null;
        }
        VoucherExchangeCodeDTO voucherExchangeCodeDTO = new VoucherExchangeCodeDTO();
        voucherExchangeCodeDTO.setId(voucherExchangeCodePO.getId());
        voucherExchangeCodeDTO.setBatchId(voucherExchangeCodePO.getBatchId());
        voucherExchangeCodeDTO.setBatchName(voucherExchangeCodePO.getBatchName());
        voucherExchangeCodeDTO.setVoucherName(voucherExchangeCodePO.getVoucherName());
        voucherExchangeCodeDTO.setBatchDescription(voucherExchangeCodePO.getBatchDescription());
        voucherExchangeCodeDTO.setProjectId(voucherExchangeCodePO.getProjectId());
        voucherExchangeCodeDTO.setCityCodes(voucherExchangeCodePO.getCityCodes());
        voucherExchangeCodeDTO.setProductIds(voucherExchangeCodePO.getProductIds());
        voucherExchangeCodeDTO.setCount(voucherExchangeCodePO.getCount());
        voucherExchangeCodeDTO.setCode(voucherExchangeCodePO.getCode());
        voucherExchangeCodeDTO.setKeyt(voucherExchangeCodePO.getKeyt());
        voucherExchangeCodeDTO.setExpiryTime(voucherExchangeCodePO.getExpiryTime());
        voucherExchangeCodeDTO.setVoucherExpiryTime(voucherExchangeCodePO.getVoucherExpiryTime());
        voucherExchangeCodeDTO.setValidityPeriod(voucherExchangeCodePO.getValidityPeriod());
        voucherExchangeCodeDTO.setExpiryType(voucherExchangeCodePO.getExpiryType());
        voucherExchangeCodeDTO.setStatus(voucherExchangeCodePO.getStatus());
        voucherExchangeCodeDTO.setUsed(voucherExchangeCodePO.getUsed());
        voucherExchangeCodeDTO.setCustomerId(voucherExchangeCodePO.getCustomerId());
        return voucherExchangeCodeDTO;
    }

    public static VoucherExchangeCodeEditDTO EditReq2EditDTO(VoucherExchangeCodeEditReq req) {
        if (req == null) {
            return null;
        }
        VoucherExchangeCodeEditDTO voucherExchangeCodeEditDTO = new VoucherExchangeCodeEditDTO();
        voucherExchangeCodeEditDTO.setId(req.getId());
        voucherExchangeCodeEditDTO.setVoucherName(req.getVoucherName());
        voucherExchangeCodeEditDTO.setBatchDescription(req.getBatchDescription());
        voucherExchangeCodeEditDTO.setCityCodes(req.getCityCodes());
        voucherExchangeCodeEditDTO.setProductIds(req.getProductIds());
        voucherExchangeCodeEditDTO.setExpiryTime(req.getExpiryTime());
        voucherExchangeCodeEditDTO.setVoucherExpiryTime(req.getVoucherExpiryTime());
        voucherExchangeCodeEditDTO.setValidityPeriod(req.getValidityPeriod());
        voucherExchangeCodeEditDTO.setExpiryType(req.getExpiryType());
        return voucherExchangeCodeEditDTO;
    }
}
