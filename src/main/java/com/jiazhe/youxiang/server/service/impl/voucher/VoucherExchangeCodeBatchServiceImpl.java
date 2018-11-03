package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeBatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherExchangeCodeBatchService")
@Transactional(rollbackFor=Exception.class)
public class VoucherExchangeCodeBatchServiceImpl implements VoucherExchangeCodeBatchService {
}
