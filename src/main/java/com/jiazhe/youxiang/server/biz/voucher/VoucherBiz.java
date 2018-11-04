package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherEditDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("voucherBiz")
public class VoucherBiz {

    @Autowired
    private VoucherService voucherService;

    public void startUsing(Integer id) {
        voucherService.changeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
        voucherService.changeStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public List<VoucherDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging) {
        return voucherService.getList(mobile,exchangeType,status,expiry,paging);
    }

    public VoucherDTO getById(Integer id) {
        return voucherService.getById(id);
    }

    public void editSave(VoucherEditDTO dto) {
        voucherService.editSave(dto);
    }
}
