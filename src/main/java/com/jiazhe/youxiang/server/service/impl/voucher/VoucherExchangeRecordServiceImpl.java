package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherExchangeRecordService")
@Transactional(rollbackFor=Exception.class)
public class VoucherExchangeRecordServiceImpl implements VoucherExchangeRecordService {
}
