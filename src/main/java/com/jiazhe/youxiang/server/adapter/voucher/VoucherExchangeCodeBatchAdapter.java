package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecodebatch.VoucherExchangeCodeBatchResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodeBatchAdapter {
    public static VoucherExchangeCodeBatchResp DTO2Resp(VoucherExchangeCodeBatchDTO dto) {
        if (dto == null) {
            return null;
        }
        VoucherExchangeCodeBatchResp voucherExchangeCodeBatchResp = new VoucherExchangeCodeBatchResp();
        voucherExchangeCodeBatchResp.setId(dto.getId());
        voucherExchangeCodeBatchResp.setName(dto.getName());
        voucherExchangeCodeBatchResp.setAmount(dto.getAmount());
        voucherExchangeCodeBatchResp.setCount(dto.getCount());
        voucherExchangeCodeBatchResp.setExpiryTime(dto.getExpiryTime());
        voucherExchangeCodeBatchResp.setStatus(dto.getStatus());
        return voucherExchangeCodeBatchResp;
    }

    public static VoucherExchangeCodeBatchSaveDTO ReqSave2DTOSave(VoucherExchangeCodeBatchSaveReq req) {
        if (req == null) {
            return null;
        }
        VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO = new VoucherExchangeCodeBatchSaveDTO();
        voucherExchangeCodeBatchSaveDTO.setId(req.getId());
        voucherExchangeCodeBatchSaveDTO.setName(req.getName());
        voucherExchangeCodeBatchSaveDTO.setVoucherName(req.getVoucherName());
        voucherExchangeCodeBatchSaveDTO.setAmount(req.getAmount());
        voucherExchangeCodeBatchSaveDTO.setCount(req.getCount());
        voucherExchangeCodeBatchSaveDTO.setProjectId(req.getProjectId());
        voucherExchangeCodeBatchSaveDTO.setCityCodes(req.getCityCodes());
        voucherExchangeCodeBatchSaveDTO.setProductIds(req.getProductIds());
        voucherExchangeCodeBatchSaveDTO.setExpiryTime(req.getExpiryTime());
        voucherExchangeCodeBatchSaveDTO.setRechargeCardExpiryTime(req.getRechargeCardExpiryTime());
        voucherExchangeCodeBatchSaveDTO.setValidityPeriod(req.getValidityPeriod());
        voucherExchangeCodeBatchSaveDTO.setExpiryType(req.getExpiryType());
        voucherExchangeCodeBatchSaveDTO.setDescription(req.getDescription());
        return voucherExchangeCodeBatchSaveDTO;
    }

    public static VoucherExchangeCodeBatchEditResp DTOEdit2RespEdit(VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO) {
        if (voucherExchangeCodeBatchEditDTO == null) {
            return null;
        }
        VoucherExchangeCodeBatchEditResp voucherExchangeCodeBatchEditResp = new VoucherExchangeCodeBatchEditResp();
        voucherExchangeCodeBatchEditResp.setId(voucherExchangeCodeBatchEditDTO.getId());
        voucherExchangeCodeBatchEditResp.setName(voucherExchangeCodeBatchEditDTO.getName());
        voucherExchangeCodeBatchEditResp.setVoucherName(voucherExchangeCodeBatchEditDTO.getVoucherName());
        voucherExchangeCodeBatchEditResp.setIsMade(voucherExchangeCodeBatchEditDTO.getIsMade());
        voucherExchangeCodeBatchEditResp.setDescription(voucherExchangeCodeBatchEditDTO.getDescription());
        voucherExchangeCodeBatchEditResp.setProjectId(voucherExchangeCodeBatchEditDTO.getProjectId());
        voucherExchangeCodeBatchEditResp.setCityCodes(voucherExchangeCodeBatchEditDTO.getCityCodes());
        voucherExchangeCodeBatchEditResp.setProductIds(voucherExchangeCodeBatchEditDTO.getProductIds());
        voucherExchangeCodeBatchEditResp.setAmount(voucherExchangeCodeBatchEditDTO.getAmount());
        voucherExchangeCodeBatchEditResp.setCount(voucherExchangeCodeBatchEditDTO.getCount());
        voucherExchangeCodeBatchEditResp.setExpiryTime(voucherExchangeCodeBatchEditDTO.getExpiryTime());
        voucherExchangeCodeBatchEditResp.setRechargeCardExpiryTime(voucherExchangeCodeBatchEditDTO.getRechargeCardExpiryTime());
        voucherExchangeCodeBatchEditResp.setValidityPeriod(voucherExchangeCodeBatchEditDTO.getValidityPeriod());
        voucherExchangeCodeBatchEditResp.setExpiryType(voucherExchangeCodeBatchEditDTO.getExpiryType());
        return voucherExchangeCodeBatchEditResp;
    }
}
