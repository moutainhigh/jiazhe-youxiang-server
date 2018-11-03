package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("voucherExchangeCodeBatchBiz")
public class VoucherExchangeCodeBatchBiz {

    @Autowired


    public List<VoucherExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        return null ;
    }

    public void startUsing(Integer id) {

    }

    public void stopUsing(Integer id) {

    }

    public VoucherExchangeCodeBatchEditDTO getById(Integer id) {
        return null;
    }

    public int generaterCode(Integer id) {
        return 0;
    }

    public void save(VoucherExchangeCodeBatchSaveDTO saveDTO) {
    }
}
