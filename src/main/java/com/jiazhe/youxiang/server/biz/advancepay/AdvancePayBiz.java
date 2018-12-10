package com.jiazhe.youxiang.server.biz.advancepay;

import com.jiazhe.youxiang.server.dto.advancepay.AdvancePayDTO;
import com.jiazhe.youxiang.server.service.advancepay.AdvancePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/10
 */
@Service("advancePayBiz")
public class AdvancePayBiz {

    @Autowired
    private AdvancePayService advancePayService;

    public List<AdvancePayDTO> getList(Date timeStart, Date timeEnd) {
        return advancePayService.getList(timeStart,timeEnd);
    }

    public void save(BigDecimal advancePay, Long advanceTime, String remark) {
        AdvancePayDTO dto = new AdvancePayDTO();
        dto.setAdvancePay(advancePay);
        dto.setAdvanceTime(new Date(advanceTime));
        dto.setRemark(remark);
        dto.setAddTime(new Date());
        dto.setModTime(new Date());
        dto.setIsDeleted(Byte.valueOf("0"));
        dto.setExtInfo("");
        advancePayService.save(dto);
    }
}
