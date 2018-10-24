package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchAddDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchListDTO;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch.VoucherExchangeCodeBatchAddReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecodebatch.VoucherExchangeCodeBatchListResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodeBatchAdapter {
    public static VoucherExchangeCodeBatchListResp DTOList2RespList(VoucherExchangeCodeBatchListDTO voucherExchangeCodeBatchListDTO) {
        if (voucherExchangeCodeBatchListDTO == null) {
            return null;
        }
        VoucherExchangeCodeBatchListResp voucherExchangeCodeBatchListResp = new VoucherExchangeCodeBatchListResp();
        voucherExchangeCodeBatchListResp.setId(voucherExchangeCodeBatchListDTO.getId());
        voucherExchangeCodeBatchListResp.setName(voucherExchangeCodeBatchListDTO.getName());
        voucherExchangeCodeBatchListResp.setAmount(voucherExchangeCodeBatchListDTO.getAmount());
        voucherExchangeCodeBatchListResp.setCount(voucherExchangeCodeBatchListDTO.getCount());
        voucherExchangeCodeBatchListResp.setExpiryTime(voucherExchangeCodeBatchListDTO.getExpiryTime());
        voucherExchangeCodeBatchListResp.setStatus(voucherExchangeCodeBatchListDTO.getStatus());
        return voucherExchangeCodeBatchListResp;
    }

    public static VoucherExchangeCodeBatchAddDTO ReqAdd2DTOAdd(VoucherExchangeCodeBatchAddReq req) {
        if (req == null) {
            return null;
        }
        VoucherExchangeCodeBatchAddDTO voucherExchangeCodeBatchAddDTO = new VoucherExchangeCodeBatchAddDTO();
        voucherExchangeCodeBatchAddDTO.setName(req.getName());
        voucherExchangeCodeBatchAddDTO.setDescription(req.getDescription());
        voucherExchangeCodeBatchAddDTO.setAmount(req.getAmount());
        voucherExchangeCodeBatchAddDTO.setCityIds(req.getCityIds());
        voucherExchangeCodeBatchAddDTO.setProductIds(req.getProductIds());
        voucherExchangeCodeBatchAddDTO.setCount(req.getCount());
        voucherExchangeCodeBatchAddDTO.setExpiryTime(req.getExpiryTime());
        voucherExchangeCodeBatchAddDTO.setVoucherExpiryTime(req.getVoucherExpiryTime());
        voucherExchangeCodeBatchAddDTO.setValidityPeriod(req.getValidityPeriod());
        voucherExchangeCodeBatchAddDTO.setExpiryType(req.getExpiryType());
        return voucherExchangeCodeBatchAddDTO;
    }

    public static VoucherExchangeCodeBatchEditDTO ReqEdit2DTOEdit(VoucherExchangeCodeBatchEditReq req) {
        if (req == null) {
            return null;
        }
        VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO = new VoucherExchangeCodeBatchEditDTO();
        voucherExchangeCodeBatchEditDTO.setId(req.getId());
        voucherExchangeCodeBatchEditDTO.setName(req.getName());
        voucherExchangeCodeBatchEditDTO.setDescription(req.getDescription());
        voucherExchangeCodeBatchEditDTO.setCityIds(req.getCityIds());
        voucherExchangeCodeBatchEditDTO.setProductIds(req.getProductIds());
        voucherExchangeCodeBatchEditDTO.setExpiryTime(req.getExpiryTime());
        voucherExchangeCodeBatchEditDTO.setVoucherExpiryTime(req.getVoucherExpiryTime());
        voucherExchangeCodeBatchEditDTO.setValidityPeriod(req.getValidityPeriod());
        voucherExchangeCodeBatchEditDTO.setExpiryType(req.getExpiryType());
        return voucherExchangeCodeBatchEditDTO;
    }

    public static VoucherExchangeCodeBatchEditResp DTOEdit2RespEdit(VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO) {
        if (voucherExchangeCodeBatchEditDTO == null) {
            return null;
        }
        VoucherExchangeCodeBatchEditResp voucherExchangeCodeBatchEditResp = new VoucherExchangeCodeBatchEditResp();
        voucherExchangeCodeBatchEditResp.setId(voucherExchangeCodeBatchEditDTO.getId());
        voucherExchangeCodeBatchEditResp.setName(voucherExchangeCodeBatchEditDTO.getName());
        voucherExchangeCodeBatchEditResp.setDescription(voucherExchangeCodeBatchEditDTO.getDescription());
        voucherExchangeCodeBatchEditResp.setCityIds(voucherExchangeCodeBatchEditDTO.getCityIds());
        voucherExchangeCodeBatchEditResp.setProductIds(voucherExchangeCodeBatchEditDTO.getProductIds());
        voucherExchangeCodeBatchEditResp.setExpiryTime(voucherExchangeCodeBatchEditDTO.getExpiryTime());
        voucherExchangeCodeBatchEditResp.setVoucherExpiryTime(voucherExchangeCodeBatchEditDTO.getVoucherExpiryTime());
        voucherExchangeCodeBatchEditResp.setValidityPeriod(voucherExchangeCodeBatchEditDTO.getValidityPeriod());
        voucherExchangeCodeBatchEditResp.setExpiryType(voucherExchangeCodeBatchEditDTO.getExpiryType());
        return voucherExchangeCodeBatchEditResp;
    }
}
