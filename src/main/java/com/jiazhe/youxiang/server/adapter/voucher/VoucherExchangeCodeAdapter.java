package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeListDTO;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecode.VoucherExchangeCodeListResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodeAdapter {
    public static VoucherExchangeCodeListResp DTOList2RespList(VoucherExchangeCodeListDTO voucherExchangeCodeListDTO) {
        if (voucherExchangeCodeListDTO == null) {
            return null;
        }
        VoucherExchangeCodeListResp voucherExchangeCodeListResp = new VoucherExchangeCodeListResp();
        voucherExchangeCodeListResp.setId(voucherExchangeCodeListDTO.getId());
        voucherExchangeCodeListResp.setBatchName(voucherExchangeCodeListDTO.getBatchName());
        voucherExchangeCodeListResp.setCode(voucherExchangeCodeListDTO.getCode());
        voucherExchangeCodeListResp.setKeyt(voucherExchangeCodeListDTO.getKeyt());
        voucherExchangeCodeListResp.setExpiryTime(voucherExchangeCodeListDTO.getExpiryTime());
        voucherExchangeCodeListResp.setStatus(voucherExchangeCodeListDTO.getStatus());
        voucherExchangeCodeListResp.setUsed(voucherExchangeCodeListDTO.getUsed());
        return voucherExchangeCodeListResp;
    }
}
