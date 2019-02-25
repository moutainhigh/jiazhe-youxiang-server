package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecodebatch.VoucherExchangeCodeBatchResp;

import java.util.Date;

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
        voucherExchangeCodeBatchResp.setVoucherName(dto.getVoucherName());
        voucherExchangeCodeBatchResp.setAmount(dto.getAmount());
        voucherExchangeCodeBatchResp.setCount(dto.getCount());
        voucherExchangeCodeBatchResp.setVoucherEffectiveTime(dto.getVoucherEffectiveTime().getTime());
        voucherExchangeCodeBatchResp.setExpiryTime(dto.getExpiryTime().getTime());
        voucherExchangeCodeBatchResp.setStatus(dto.getStatus());
        voucherExchangeCodeBatchResp.setIsMade(dto.getIsMade());
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
        voucherExchangeCodeBatchSaveDTO.setExpiryTime(new Date(req.getExpiryTime()));
        voucherExchangeCodeBatchSaveDTO.setVoucherEffectiveTime(new Date(req.getVoucherEffectiveTime()));
        voucherExchangeCodeBatchSaveDTO.setVoucherExpiryTime(new Date(req.getVoucherExpiryTime()));
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
        voucherExchangeCodeBatchEditResp.setExpiryTime(voucherExchangeCodeBatchEditDTO.getExpiryTime().getTime());
        voucherExchangeCodeBatchEditResp.setVoucherEffectiveTime(voucherExchangeCodeBatchEditDTO.getVoucherEffectiveTime().getTime());
        voucherExchangeCodeBatchEditResp.setVoucherExpiryTime(voucherExchangeCodeBatchEditDTO.getVoucherExpiryTime().getTime());
        voucherExchangeCodeBatchEditResp.setValidityPeriod(voucherExchangeCodeBatchEditDTO.getValidityPeriod());
        voucherExchangeCodeBatchEditResp.setExpiryType(voucherExchangeCodeBatchEditDTO.getExpiryType());
        return voucherExchangeCodeBatchEditResp;
    }

    public static VoucherExchangeCodeBatchDTO PO2DTO(VoucherExchangeCodeBatchPO voucherExchangeCodeBatchPO) {
        if (voucherExchangeCodeBatchPO == null) {
            return null;
        }
        VoucherExchangeCodeBatchDTO voucherExchangeCodeBatchDTO = new VoucherExchangeCodeBatchDTO();
        voucherExchangeCodeBatchDTO.setId(voucherExchangeCodeBatchPO.getId());
        voucherExchangeCodeBatchDTO.setName(voucherExchangeCodeBatchPO.getName());
        voucherExchangeCodeBatchDTO.setVoucherName(voucherExchangeCodeBatchPO.getVoucherName());
        voucherExchangeCodeBatchDTO.setIsMade(voucherExchangeCodeBatchPO.getIsMade());
        voucherExchangeCodeBatchDTO.setAmount(voucherExchangeCodeBatchPO.getAmount());
        voucherExchangeCodeBatchDTO.setCount(voucherExchangeCodeBatchPO.getCount());
        voucherExchangeCodeBatchDTO.setVoucherEffectiveTime(voucherExchangeCodeBatchPO.getVoucherEffectiveTime());
        voucherExchangeCodeBatchDTO.setExpiryTime(voucherExchangeCodeBatchPO.getExpiryTime());
        voucherExchangeCodeBatchDTO.setStatus(voucherExchangeCodeBatchPO.getStatus());
        return voucherExchangeCodeBatchDTO;
    }

    public static VoucherExchangeCodeBatchPO DTOSave2PO(VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO) {
        if (voucherExchangeCodeBatchSaveDTO == null) {
            return null;
        }
        VoucherExchangeCodeBatchPO voucherExchangeCodeBatchPO = new VoucherExchangeCodeBatchPO();
        voucherExchangeCodeBatchPO.setId(voucherExchangeCodeBatchSaveDTO.getId());
        voucherExchangeCodeBatchPO.setName(voucherExchangeCodeBatchSaveDTO.getName());
        voucherExchangeCodeBatchPO.setVoucherName(voucherExchangeCodeBatchSaveDTO.getVoucherName());
        voucherExchangeCodeBatchPO.setAmount(voucherExchangeCodeBatchSaveDTO.getAmount());
        voucherExchangeCodeBatchPO.setCount(voucherExchangeCodeBatchSaveDTO.getCount());
        voucherExchangeCodeBatchPO.setProjectId(voucherExchangeCodeBatchSaveDTO.getProjectId());
        voucherExchangeCodeBatchPO.setCityCodes(voucherExchangeCodeBatchSaveDTO.getCityCodes());
        voucherExchangeCodeBatchPO.setProductIds(voucherExchangeCodeBatchSaveDTO.getProductIds());
        voucherExchangeCodeBatchPO.setExpiryTime(voucherExchangeCodeBatchSaveDTO.getExpiryTime());
        voucherExchangeCodeBatchPO.setVoucherEffectiveTime(voucherExchangeCodeBatchSaveDTO.getVoucherEffectiveTime());
        voucherExchangeCodeBatchPO.setVoucherExpiryTime(voucherExchangeCodeBatchSaveDTO.getVoucherExpiryTime());
        voucherExchangeCodeBatchPO.setValidityPeriod(voucherExchangeCodeBatchSaveDTO.getValidityPeriod());
        voucherExchangeCodeBatchPO.setExpiryType(voucherExchangeCodeBatchSaveDTO.getExpiryType());
        voucherExchangeCodeBatchPO.setDescription(voucherExchangeCodeBatchSaveDTO.getDescription());
        return voucherExchangeCodeBatchPO;
    }

    public static VoucherExchangeCodeBatchEditDTO po2DtoEdit(VoucherExchangeCodeBatchPO voucherExchangeCodeBatchPO) {
        if (voucherExchangeCodeBatchPO == null) {
            return null;
        }
        VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO = new VoucherExchangeCodeBatchEditDTO();
        voucherExchangeCodeBatchEditDTO.setId(voucherExchangeCodeBatchPO.getId());
        voucherExchangeCodeBatchEditDTO.setName(voucherExchangeCodeBatchPO.getName());
        voucherExchangeCodeBatchEditDTO.setVoucherName(voucherExchangeCodeBatchPO.getVoucherName());
        voucherExchangeCodeBatchEditDTO.setProjectId(voucherExchangeCodeBatchPO.getProjectId());
        voucherExchangeCodeBatchEditDTO.setIsMade(voucherExchangeCodeBatchPO.getIsMade());
        voucherExchangeCodeBatchEditDTO.setStatus(voucherExchangeCodeBatchPO.getStatus());
        voucherExchangeCodeBatchEditDTO.setDescription(voucherExchangeCodeBatchPO.getDescription());
        voucherExchangeCodeBatchEditDTO.setAmount(voucherExchangeCodeBatchPO.getAmount());
        voucherExchangeCodeBatchEditDTO.setCityCodes(voucherExchangeCodeBatchPO.getCityCodes());
        voucherExchangeCodeBatchEditDTO.setProductIds(voucherExchangeCodeBatchPO.getProductIds());
        voucherExchangeCodeBatchEditDTO.setCount(voucherExchangeCodeBatchPO.getCount());
        voucherExchangeCodeBatchEditDTO.setExpiryTime(voucherExchangeCodeBatchPO.getExpiryTime());
        voucherExchangeCodeBatchEditDTO.setVoucherEffectiveTime(voucherExchangeCodeBatchPO.getVoucherEffectiveTime());
        voucherExchangeCodeBatchEditDTO.setVoucherExpiryTime(voucherExchangeCodeBatchPO.getVoucherExpiryTime());
        voucherExchangeCodeBatchEditDTO.setValidityPeriod(voucherExchangeCodeBatchPO.getValidityPeriod());
        voucherExchangeCodeBatchEditDTO.setExpiryType(voucherExchangeCodeBatchPO.getExpiryType());
        return voucherExchangeCodeBatchEditDTO;
    }
}
