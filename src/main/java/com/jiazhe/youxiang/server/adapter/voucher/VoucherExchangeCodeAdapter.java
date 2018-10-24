package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
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
}
