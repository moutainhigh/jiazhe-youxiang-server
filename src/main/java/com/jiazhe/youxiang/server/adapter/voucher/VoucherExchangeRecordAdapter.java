package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;
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

    public static VoucherExchangeRecordDTO PO2DTO(VoucherExchangeRecordPO voucherExchangeRecordPO) {
        if (voucherExchangeRecordPO == null) {
            return null;
        }
        VoucherExchangeRecordDTO voucherExchangeRecordDTO = new VoucherExchangeRecordDTO();
        voucherExchangeRecordDTO.setId(voucherExchangeRecordPO.getId());
        voucherExchangeRecordDTO.setVoucherId(voucherExchangeRecordPO.getVoucherId());
        voucherExchangeRecordDTO.setExchangeCodeId(voucherExchangeRecordPO.getExchangeCodeId());
        voucherExchangeRecordDTO.setExchangeType(voucherExchangeRecordPO.getExchangeType());
        voucherExchangeRecordDTO.setOperatorName(voucherExchangeRecordPO.getOperatorName());
        return voucherExchangeRecordDTO;
    }
}
