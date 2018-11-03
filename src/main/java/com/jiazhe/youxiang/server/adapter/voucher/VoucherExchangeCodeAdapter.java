package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
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
        voucherExchangeCodeResp.setCode(dto.getCode());
        voucherExchangeCodeResp.setKeyt(dto.getKeyt());
        voucherExchangeCodeResp.setExpiryTime(dto.getExpiryTime());
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
}
