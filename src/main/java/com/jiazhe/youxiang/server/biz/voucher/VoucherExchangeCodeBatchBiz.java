package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeBatchService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("voucherExchangeCodeBatchBiz")
public class VoucherExchangeCodeBatchBiz {

    @Autowired
    private VoucherExchangeCodeBatchService voucherExchangeCodeBatchService;

    public List<VoucherExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        return voucherExchangeCodeBatchService.getList(projectId,name,paging) ;
    }

    public void startUsing(Integer id) {
        voucherExchangeCodeBatchService.changeBatchStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
        voucherExchangeCodeBatchService.changeBatchStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public VoucherExchangeCodeBatchEditDTO getById(Integer id) {
        return voucherExchangeCodeBatchService.getById(id);
    }

    public void generateCode(Integer id) {
        voucherExchangeCodeBatchService.generateCode(id);
    }

    public void addSave(VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO) {
        if(voucherExchangeCodeBatchSaveDTO.getExpiryType().equals(CommonConstant.VOUCHER_EXPIRY_TIME)){
            voucherExchangeCodeBatchSaveDTO.setValidityPeriod(0);
        }else{
            voucherExchangeCodeBatchSaveDTO.setVoucherExpiryTime(new Date());
        }
        voucherExchangeCodeBatchService.addSave(voucherExchangeCodeBatchSaveDTO);
    }

    public void editSave(VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO) {
        voucherExchangeCodeBatchService.editSave(voucherExchangeCodeBatchSaveDTO);
    }

}
