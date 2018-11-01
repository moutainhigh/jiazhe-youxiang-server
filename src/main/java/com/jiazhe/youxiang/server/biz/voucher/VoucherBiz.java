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
    public void startUsing(Integer id) {

    }

    public void stopUsing(Integer id) {

    }

    public void changeExpiryTime(Integer id, Date expiryTime) {

    }

    public List<VoucherDTO> getList(Integer id, Paging paging) {
        return null;
    }

    public List<VoucherDTO> findUnexpiredByCustomerId(Integer id) {
        return null;
    }
}
