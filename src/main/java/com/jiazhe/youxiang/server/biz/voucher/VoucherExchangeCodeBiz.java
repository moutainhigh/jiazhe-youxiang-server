package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("voucherExchangeCodeBiz")
public class VoucherExchangeCodeBiz {

    @Autowired
    private VoucherExchangeCodeService voucherExchangeCodeService;

    public void startUsing(Integer id) {
        voucherExchangeCodeService.changeCodeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
        voucherExchangeCodeService.changeCodeStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public void customerSelfCharge(Integer id , String keyt)  {
        voucherExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_CUSTOMER_CODE_EXCHANGE,id,keyt);
    }

    public void backstageCodeCharge(Integer id , String keyt)  {
        voucherExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_USER_CODE_EXCHANGE,id,keyt);
    }

    public List<VoucherExchangeCodeDTO> getByBatchId(Integer id) {
        return voucherExchangeCodeService.getByBatchId(id);
    }

    public List<VoucherExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        return voucherExchangeCodeService.getList(batchId,code,keyt,status,used,paging);
    }

    public VoucherExchangeCodeDTO getById(Integer id) {
        return voucherExchangeCodeService.getById(id);
    }

    public void editSave(VoucherExchangeCodeEditDTO dto) {
        voucherExchangeCodeService.editSave(dto);
    }
}
