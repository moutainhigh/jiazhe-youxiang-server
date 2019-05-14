package com.jiazhe.youxiang.server.biz.material;

import com.jiazhe.youxiang.server.dto.material.MaterialDto;
import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.service.material.MaterialService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
@Service("materialBiz")
public class MaterialBiz {

    @Autowired
    private MaterialService materialService;

    public List<MaterialSummaryDto> getSummaryList(String payerIds, String payeeIds, Paging paging) {
        return materialService.getSummaryList(payerIds,payeeIds,paging);
    }

    public MaterialSummaryDto calculateSummary(String payerIds, String payeeIds) {
        return materialService.calculateSummary(payerIds,payeeIds);
    }

    public void save(Integer payeeId, BigDecimal transferAmount, BigDecimal materialValue, Date transferTime, String remark) {
        materialService.save(payeeId,transferAmount,materialValue,transferTime,remark);
    }

    public List<MaterialDto> getList(String payerIds, String payeeIds, Date transferTimeBegin, Date transferTimeEnd, Paging paging) {
        return materialService.getList(payerIds,payeeIds,transferTimeBegin,transferTimeEnd,paging);
    }

    public void delete(Integer id) {
        materialService.delete(id);
    }
}
