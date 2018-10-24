package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.vo.resp.voucher.voucher.VoucherResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherAdapter {
    public static VoucherResp DTO2Resp(VoucherDTO voucherDTO) {
        if (voucherDTO == null) {
            return null;
        }
        VoucherResp voucherResp = new VoucherResp();
        voucherResp.setId(voucherDTO.getId());
        voucherResp.setName(voucherDTO.getName());
        voucherResp.setDescription(voucherDTO.getDescription());
        voucherResp.setCustomerId(voucherDTO.getCustomerId());
        voucherResp.setProjectId(voucherDTO.getProjectId());
        voucherResp.setCityIds(voucherDTO.getCityIds());
        voucherResp.setProductIds(voucherDTO.getProductIds());
        voucherResp.setCount(voucherDTO.getCount());
        voucherResp.setExpiryTime(voucherDTO.getExpiryTime());
        voucherResp.setUsed(voucherDTO.getUsed());
        voucherResp.setStatus(voucherDTO.getStatus());
        return voucherResp;
    }
}
