package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptSaveDTO;
import com.jiazhe.youxiang.server.service.ChargeReceiptService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
@Service("chargeReceiptBiz")
public class ChargeReceiptBiz {

    @Autowired
    private ChargeReceiptService chargeReceiptService;

    public List<ChargeReceiptDTO> getList(Integer auditRecordId, Paging paging) {
        return chargeReceiptService.getList(auditRecordId, paging);
    }

    public void delete(Integer id) {
        chargeReceiptService.delete(id);
    }

    public void save(ChargeReceiptSaveDTO dto) {
        chargeReceiptService.save(dto);
    }

    public ChargeReceiptDTO getById(Integer id) {
        return chargeReceiptService.getById(id);
    }
}
