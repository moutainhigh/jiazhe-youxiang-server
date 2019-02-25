package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecode.VoucherExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecode.VoucherExchangeCodeResp;

import java.util.Date;

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
        voucherExchangeCodeResp.setExpiryTime(dto.getExpiryTime().getTime());
        voucherExchangeCodeResp.setVoucherEffectiveTime(dto.getVoucherEffectiveTime().getTime());
        voucherExchangeCodeResp.setVoucherExpiryTime(dto.getVoucherExpiryTime().getTime());
        voucherExchangeCodeResp.setValidityPeriod(dto.getValidityPeriod());
        voucherExchangeCodeResp.setExpiryType(dto.getExpiryType());
        voucherExchangeCodeResp.setStatus(dto.getStatus());
        voucherExchangeCodeResp.setUsed(dto.getUsed());
        voucherExchangeCodeResp.setCustomerResp(CustomerAdapter.customerDTO2VO(dto.getCustomerDTO()));
        return voucherExchangeCodeResp;
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
        voucherExchangeCodePO.setVoucherEffectiveTime(voucherExchangeCodeSaveDTO.getVoucherEffectiveTime());
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
        voucherExchangeCodeDTO.setVoucherEffectiveTime(voucherExchangeCodePO.getVoucherEffectiveTime());
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
        voucherExchangeCodeEditDTO.setExpiryTime(new Date(req.getExpiryTime()));
        voucherExchangeCodeEditDTO.setVoucherEffectiveTime(new Date(req.getVoucherEffectiveTime()));
        voucherExchangeCodeEditDTO.setVoucherExpiryTime(new Date(req.getVoucherExpiryTime()));
        voucherExchangeCodeEditDTO.setValidityPeriod(req.getValidityPeriod());
        voucherExchangeCodeEditDTO.setExpiryType(req.getExpiryType());
        return voucherExchangeCodeEditDTO;
    }

    public static VoucherExchangeCodePO dto2Po(VoucherExchangeCodeDTO voucherExchangeCodeDTO) {
        if (voucherExchangeCodeDTO == null) {
            return null;
        }
        VoucherExchangeCodePO voucherExchangeCodePO = new VoucherExchangeCodePO();
        voucherExchangeCodePO.setId(voucherExchangeCodeDTO.getId());
        voucherExchangeCodePO.setBatchId(voucherExchangeCodeDTO.getBatchId());
        voucherExchangeCodePO.setBatchName(voucherExchangeCodeDTO.getBatchName());
        voucherExchangeCodePO.setVoucherName(voucherExchangeCodeDTO.getVoucherName());
        voucherExchangeCodePO.setBatchDescription(voucherExchangeCodeDTO.getBatchDescription());
        voucherExchangeCodePO.setProjectId(voucherExchangeCodeDTO.getProjectId());
        voucherExchangeCodePO.setCityCodes(voucherExchangeCodeDTO.getCityCodes());
        voucherExchangeCodePO.setProductIds(voucherExchangeCodeDTO.getProductIds());
        voucherExchangeCodePO.setCount(voucherExchangeCodeDTO.getCount());
        voucherExchangeCodePO.setCode(voucherExchangeCodeDTO.getCode());
        voucherExchangeCodePO.setKeyt(voucherExchangeCodeDTO.getKeyt());
        voucherExchangeCodePO.setExpiryTime(voucherExchangeCodeDTO.getExpiryTime());
        voucherExchangeCodePO.setVoucherEffectiveTime(voucherExchangeCodeDTO.getVoucherEffectiveTime());
        voucherExchangeCodePO.setVoucherExpiryTime(voucherExchangeCodeDTO.getVoucherExpiryTime());
        voucherExchangeCodePO.setValidityPeriod(voucherExchangeCodeDTO.getValidityPeriod());
        voucherExchangeCodePO.setExpiryType(voucherExchangeCodeDTO.getExpiryType());
        voucherExchangeCodePO.setStatus(voucherExchangeCodeDTO.getStatus());
        voucherExchangeCodePO.setUsed(voucherExchangeCodeDTO.getUsed());
        voucherExchangeCodePO.setCustomerId(voucherExchangeCodeDTO.getCustomerId());
        return voucherExchangeCodePO;
    }
}
