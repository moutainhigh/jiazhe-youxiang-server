package com.jiazhe.youxiang.server.adapter.advancepay;

import com.jiazhe.youxiang.server.domain.po.AdvancePayPO;
import com.jiazhe.youxiang.server.dto.advancepay.AdvancePayDTO;
import com.jiazhe.youxiang.server.vo.resp.advancepay.AdvancePayResp;

/**
 * @author TU
 * @description
 * @date 2018/12/10.
 */
public class AdvancePayAdapter {
    public static AdvancePayDTO PO2DTO(AdvancePayPO advancePayPO) {
        if (advancePayPO == null) {
            return null;
        }
        AdvancePayDTO advancePayDTO = new AdvancePayDTO();
        advancePayDTO.setId(advancePayPO.getId());
        advancePayDTO.setAdvancePay(advancePayPO.getAdvancePay());
        advancePayDTO.setAdvanceTime(advancePayPO.getAdvanceTime());
        advancePayDTO.setRemark(advancePayPO.getRemark());
        advancePayDTO.setExtInfo(advancePayPO.getExtInfo());
        advancePayDTO.setIsDeleted(advancePayPO.getIsDeleted());
        advancePayDTO.setAddTime(advancePayPO.getAddTime());
        advancePayDTO.setModTime(advancePayPO.getModTime());
        return advancePayDTO;
    }

    public static AdvancePayResp DTO2Resp(AdvancePayDTO advancePayDTO) {
        if (advancePayDTO == null) {
            return null;
        }
        AdvancePayResp advancePayResp = new AdvancePayResp();
        advancePayResp.setId(advancePayDTO.getId());
        advancePayResp.setAdvancePay(advancePayDTO.getAdvancePay());
        advancePayResp.setAdvanceTime(advancePayDTO.getAdvanceTime().getTime());
        advancePayResp.setRemark(advancePayDTO.getRemark());
        advancePayResp.setExtInfo(advancePayDTO.getExtInfo());
        advancePayResp.setIsDeleted(advancePayDTO.getIsDeleted());
        advancePayResp.setAddTime(advancePayDTO.getAddTime().getTime());
        advancePayResp.setModTime(advancePayDTO.getModTime().getTime());
        return advancePayResp;
    }
}
