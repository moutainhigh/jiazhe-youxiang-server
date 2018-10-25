package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("voucherExchangeRecordBiz")
public class VoucherExchangeRecordBiz {
    public List<VoucherExchangeRecordDTO> getList(Date beginDate, Date endDate, String code, String keyt, Paging paging) {
        return null;
    }
}
