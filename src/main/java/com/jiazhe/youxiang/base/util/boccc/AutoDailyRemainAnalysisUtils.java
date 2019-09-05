/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author tu
 * @version 1.0
 * @description 每日剩余分析
 * @created 2019-09-05 18:52
 */
@Component
public class AutoDailyRemainAnalysisUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoDailyRemainAnalysisUtils.class);

    public static AutoDailyRemainAnalysisUtils dailyPurchaseAnalysisUtils;

    @Autowired
    private VoucherExchangeCodeService voucherExchangeCodeService;

    @PostConstruct
    public void init() {
        dailyPurchaseAnalysisUtils = this;
        dailyPurchaseAnalysisUtils.voucherExchangeCodeService = this.voucherExchangeCodeService;
    }

}
