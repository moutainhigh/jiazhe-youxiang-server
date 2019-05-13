package com.jiazhe.youxiang.server.service.material;

import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
public interface MaterialService {
    /**
     * 根据条件汇总物料消耗情况 汇总
     * @param payerIds
     * @param payeeIds
     * @param paging
     * @return
     */
    List<MaterialSummaryDto> getSummaryList(String payerIds, String payeeIds, Paging paging);
}
