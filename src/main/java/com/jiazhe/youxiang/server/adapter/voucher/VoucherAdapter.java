package com.jiazhe.youxiang.server.adapter.voucher;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.domain.po.VoucherPO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherEditDTO;
import com.jiazhe.youxiang.server.vo.req.voucher.voucher.VoucherEditReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.voucher.VoucherResp;

import java.util.Date;

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
        voucherResp.setCityCodes(voucherDTO.getCityCodes());
        voucherResp.setProductIds(voucherDTO.getProductIds());
        voucherResp.setCount(voucherDTO.getCount());
        voucherResp.setEffectiveTime(voucherDTO.getEffectiveTime().getTime());
        voucherResp.setExpiryTime(voucherDTO.getExpiryTime().getTime());
        voucherResp.setStatus(voucherDTO.getStatus());
        voucherResp.setUsed(voucherDTO.getUsed());
        voucherResp.setAddTime(voucherDTO.getAddTime().getTime());
        voucherResp.setCustomerResp(CustomerAdapter.customerDTO2VO(voucherDTO.getCustomerDTO()));
        voucherResp.setVoucherExchangeRecordResp(VoucherExchangeRecordAdapter.DTO2Resp(voucherDTO.getVoucherExchangeRecordDTO()));
        return voucherResp;
    }

    public static VoucherDTO PO2DTO(VoucherPO voucherPO) {
        if (voucherPO == null) {
            return null;
        }
        VoucherDTO voucherDTO = new VoucherDTO();
        voucherDTO.setId(voucherPO.getId());
        voucherDTO.setName(voucherPO.getName());
        voucherDTO.setDescription(voucherPO.getDescription());
        voucherDTO.setCustomerId(voucherPO.getCustomerId());
        voucherDTO.setProjectId(voucherPO.getProjectId());
        voucherDTO.setCityCodes(voucherPO.getCityCodes());
        voucherDTO.setProductIds(voucherPO.getProductIds());
        voucherDTO.setCount(voucherPO.getCount());
        voucherDTO.setEffectiveTime(voucherPO.getEffectiveTime());
        voucherDTO.setExpiryTime(voucherPO.getExpiryTime());
        voucherDTO.setUsed(voucherPO.getUsed());
        voucherDTO.setStatus(voucherPO.getStatus());
        voucherDTO.setAddTime(voucherPO.getAddTime());
        return voucherDTO;
    }

    public static VoucherEditDTO EditReq2EditDTO(VoucherEditReq req) {
        if (req == null) {
            return null;
        }
        VoucherEditDTO voucherEditDTO = new VoucherEditDTO();
        voucherEditDTO.setId(req.getId());
        voucherEditDTO.setCityCodes(req.getCityCodes());
        voucherEditDTO.setProductIds(req.getProductIds());
        voucherEditDTO.setName(req.getName());
        voucherEditDTO.setDescription(req.getDescription());
        voucherEditDTO.setEffectiveTime(new Date(req.getEffectiveTime()));
        voucherEditDTO.setExpiryTime(new Date(req.getExpiryTime()));
        return voucherEditDTO;
    }
}
