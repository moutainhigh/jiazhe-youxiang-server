package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangerecord.VoucherExchangeRecordResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeRecordAdapter {
    public static VoucherExchangeRecordResp DTO2Resp(VoucherExchangeRecordDTO dto) {
        if (dto == null) {
            return null;
        }
        VoucherExchangeRecordResp voucherExchangeRecordResp = new VoucherExchangeRecordResp();
        voucherExchangeRecordResp.setId(dto.getId());
        voucherExchangeRecordResp.setVoucherId(dto.getVoucherId());
        voucherExchangeRecordResp.setExchangeCodeId(dto.getExchangeCodeId());
        voucherExchangeRecordResp.setExchangeType(dto.getExchangeType());
        voucherExchangeRecordResp.setOperatorName(dto.getOperatorName());
        return voucherExchangeRecordResp;
    }
}
