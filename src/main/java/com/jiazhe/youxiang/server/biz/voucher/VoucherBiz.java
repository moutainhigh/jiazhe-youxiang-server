package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("voucherBiz")
public class VoucherBiz {
    public int startUsing(Integer id) {
        return 0;
    }

    public int stopUsing(Integer id) {
        return 0;
    }

    public int changeExpiryTime(Integer id, Date expiryTime) {
        return 0;
    }

    public List<VoucherDTO> getList(Integer id, Paging paging) {
        return null;
    }

    public List<VoucherDTO> findUnexpiredByCustomerId(Integer id) {
        return null;
    }
}
