package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.vo.Paging;
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
    public List<VoucherExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Paging paging) {
        return null;
    }

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
}
