package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchAddDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("voucherExchangeCodeBatchBiz")
public class VoucherExchangeCodeBatchBiz {
    public List<VoucherExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        return null ;
    }

    public int startUsing(Integer id) {
        return 0;
    }

    public int stopUsing(Integer id) {
        return 0;
    }

    public int addSave(VoucherExchangeCodeBatchAddDTO voucherExchangeCodeBatchAddDTO) {
        return 0;
    }

    public int editSave(VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO) {
        return 0;
    }

    public VoucherExchangeCodeBatchEditDTO getById(Integer id) {
        return null;
    }

    public int generaterCode(Integer id) {
        return 0;
    }
}
