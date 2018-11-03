package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherExchangeCodeService")
@Transactional(rollbackFor=Exception.class)
public class VoucherExchangeCodeServiceImpl implements VoucherExchangeCodeService {
}
