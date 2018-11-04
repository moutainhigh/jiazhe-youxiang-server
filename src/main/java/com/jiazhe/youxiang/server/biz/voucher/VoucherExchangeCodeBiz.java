package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
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
@Service("voucherExchangeCodeBiz")
public class VoucherExchangeCodeBiz {

    @Autowired
    private VoucherExchangeCodeService voucherExchangeCodeService;

    public void startUsing(Integer id) {

    }

    public void stopUsing(Integer id) {

    }

    public void changeExpiryTime(Integer customerId, Date expiryTime) {

    }

    public void customerSelfCharge(Integer customerId, String keyt) {

    }

    public void backstageCodeCharge(Integer customerId, String keyt) {

    }

    public List<VoucherExchangeCodeDTO> getByBatchId(Integer id) {
        return voucherExchangeCodeService.getByBatchId(id);
    }

    public List<VoucherExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        return voucherExchangeCodeService.getList(batchId,code,keyt,status,used,paging);
    }
}
