package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.ChargeReceiptPO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.vo.resp.chargereceipt.ChargeReceiptResp;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public class ChargeReceiptAdapter {
    public static ChargeReceiptDTO po2Dto(ChargeReceiptPO chargeReceiptPO) {
        if (chargeReceiptPO == null) {
            return null;
        }
        ChargeReceiptDTO chargeReceiptDTO = new ChargeReceiptDTO();
        chargeReceiptDTO.setId(chargeReceiptPO.getId());
        chargeReceiptDTO.setAuditRecordId(chargeReceiptPO.getAuditRecordId());
        chargeReceiptDTO.setExchangePoint(chargeReceiptPO.getExchangePoint());
        chargeReceiptDTO.setPosCode(chargeReceiptPO.getPosCode());
        chargeReceiptDTO.setTradeTime(chargeReceiptPO.getTradeTime());
        chargeReceiptDTO.setCardNo(chargeReceiptPO.getCardNo());
        chargeReceiptDTO.setCustomerName(chargeReceiptPO.getCustomerName());
        chargeReceiptDTO.setInputerId(chargeReceiptPO.getInputerId());
        chargeReceiptDTO.setInputerName(chargeReceiptPO.getInputerName());
        chargeReceiptDTO.setExtInfo(chargeReceiptPO.getExtInfo());
        chargeReceiptDTO.setIsDeleted(chargeReceiptPO.getIsDeleted());
        chargeReceiptDTO.setAddTime(chargeReceiptPO.getAddTime());
        chargeReceiptDTO.setModTime(chargeReceiptPO.getModTime());
        return chargeReceiptDTO;
    }

    public static ChargeReceiptResp dto2Resp(ChargeReceiptDTO chargeReceiptDTO) {
        if (chargeReceiptDTO == null) {
            return null;
        }
        ChargeReceiptResp chargeReceiptResp = new ChargeReceiptResp();
        chargeReceiptResp.setId(chargeReceiptDTO.getId());
        chargeReceiptResp.setAuditRecordId(chargeReceiptDTO.getAuditRecordId());
        chargeReceiptResp.setExchangePoint(chargeReceiptDTO.getExchangePoint());
        chargeReceiptResp.setPosCode(chargeReceiptDTO.getPosCode());
        chargeReceiptResp.setTradeTime(chargeReceiptDTO.getTradeTime().getTime());
        chargeReceiptResp.setCardNo(chargeReceiptDTO.getCardNo());
        chargeReceiptResp.setCustomerName(chargeReceiptDTO.getCustomerName());
        chargeReceiptResp.setInputerId(chargeReceiptDTO.getInputerId());
        chargeReceiptResp.setInputerName(chargeReceiptDTO.getInputerName());
        chargeReceiptResp.setExtInfo(chargeReceiptDTO.getExtInfo());
        chargeReceiptResp.setIsDeleted(chargeReceiptDTO.getIsDeleted());
        chargeReceiptResp.setAddTime(chargeReceiptDTO.getAddTime().getTime());
        chargeReceiptResp.setModTime(chargeReceiptDTO.getModTime().getTime());
        return chargeReceiptResp;
    }
}
