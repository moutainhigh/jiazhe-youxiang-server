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

    public int startUsing(Integer id) {
        return 0 ;
    }

    public int stopUsing(Integer id) {
        return 0;
    }

    public int changeExpiryTime(Integer customerId, Date expiryTime) {
        return 0;
    }

    public int customerSelfCharge(Integer customerId, String keyt) {
        return 0;
    }

    public int backstageCodeCharge(Integer customerId, String keyt) {
        return 0;
    }
}
