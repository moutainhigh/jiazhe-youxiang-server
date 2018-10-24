package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordListDTO;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangerecord.VoucherExchangeRecordListResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeRecordAdapter {
    public static VoucherExchangeRecordListResp DTOList2RespList(VoucherExchangeRecordListDTO voucherExchangeRecordListDTO) {
        if (voucherExchangeRecordListDTO == null) {
            return null;
        }
        VoucherExchangeRecordListResp voucherExchangeRecordListResp = new VoucherExchangeRecordListResp();
        voucherExchangeRecordListResp.setId(voucherExchangeRecordListDTO.getId());
        voucherExchangeRecordListResp.setVoucherId(voucherExchangeRecordListDTO.getVoucherId());
        voucherExchangeRecordListResp.setExchangeCodeId(voucherExchangeRecordListDTO.getExchangeCodeId());
        voucherExchangeRecordListResp.setExchangeType(voucherExchangeRecordListDTO.getExchangeType());
        voucherExchangeRecordListResp.setOperatorName(voucherExchangeRecordListDTO.getOperatorName());
        return voucherExchangeRecordListResp;
    }
}
