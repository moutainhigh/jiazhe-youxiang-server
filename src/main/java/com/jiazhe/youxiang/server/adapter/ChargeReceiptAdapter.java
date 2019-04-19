package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.ChargeReceiptPO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptSaveDTO;
import com.jiazhe.youxiang.server.vo.req.chargereceipt.ChargeReceiptSaveReq;
import com.jiazhe.youxiang.server.vo.resp.chargereceipt.ChargeReceiptResp;

import java.util.Date;

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
        chargeReceiptDTO.setImgUrl(chargeReceiptPO.getImgUrl());
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
        chargeReceiptResp.setImgUrl(chargeReceiptDTO.getImgUrl());
        chargeReceiptResp.setExtInfo(chargeReceiptDTO.getExtInfo());
        chargeReceiptResp.setIsDeleted(chargeReceiptDTO.getIsDeleted());
        chargeReceiptResp.setAddTime(chargeReceiptDTO.getAddTime().getTime());
        chargeReceiptResp.setModTime(chargeReceiptDTO.getModTime().getTime());
        chargeReceiptResp.setChargeReceiptStatus(chargeReceiptDTO.getChargeReceiptStatus());
        return chargeReceiptResp;
    }

    public static ChargeReceiptSaveDTO saveReq2SaveDto(ChargeReceiptSaveReq req) {
        if (req == null) {
            return null;
        }
        ChargeReceiptSaveDTO chargeReceiptSaveDTO = new ChargeReceiptSaveDTO();
        chargeReceiptSaveDTO.setId(req.getId());
        chargeReceiptSaveDTO.setAuditRecordId(req.getAuditRecordId());
        chargeReceiptSaveDTO.setExchangePoint(req.getExchangePoint());
        chargeReceiptSaveDTO.setCustomerName(req.getCustomerName());
        chargeReceiptSaveDTO.setPosCode(req.getPosCode());
        chargeReceiptSaveDTO.setCardNo(req.getCardNo());
        chargeReceiptSaveDTO.setTradeTime(new Date(req.getTradeTime()));
        chargeReceiptSaveDTO.setImgUrl(req.getImgUrl());
        return chargeReceiptSaveDTO;
    }
}
