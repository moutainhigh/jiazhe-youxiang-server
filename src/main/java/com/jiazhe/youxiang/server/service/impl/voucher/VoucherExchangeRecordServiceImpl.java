package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.dao.mapper.manual.voucher.VoucherExchangeRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherExchangeRecordService")
@Transactional(rollbackFor=Exception.class)
public class VoucherExchangeRecordServiceImpl implements VoucherExchangeRecordService {

    @Autowired
    private VoucherExchangeRecordPOManualMapper voucherExchangeRecordPOManualMapper;

    @Override
    public List<VoucherExchangeRecordPO> findByCodeIds(List<Integer> codeIds) {
        return voucherExchangeRecordPOManualMapper.findByCodeIds(codeIds);
    }
}
