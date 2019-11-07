package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptSaveDTO;
import com.jiazhe.youxiang.server.service.ChargeReceiptService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<ChargeReceiptDTO> getList(Integer auditRecordId,String customerName,String cardNo,String posCode,Date tradeStartTime,Date tradeEndTime, Paging paging) {
        return chargeReceiptService.getList(auditRecordId,customerName,cardNo,posCode,tradeStartTime,tradeEndTime, paging);
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

    public List<ChargeReceiptDTO> getList(String customerInfo, String submitterName, Byte status, Byte chargeReceiptStatus, String pointCodes, String exchangePoint,Date submitStartTime, Date submitEndTime,String exchangeType,String cityCode) {
        return chargeReceiptService.getList(customerInfo, submitterName, status, chargeReceiptStatus, pointCodes, exchangePoint,submitStartTime, submitEndTime,exchangeType,cityCode);
    }

    public boolean hasExisted(ChargeReceiptSaveDTO dto) {
        return chargeReceiptService.hasExisted(dto);
    }

}
