package com.jiazhe.youxiang.server.dao.mapper.manual.material;

import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
public interface MaterialInfoPOManualMapper {

    /**
     * 物料信息汇总
     * @param payerIds
     * @param payeeIds
     * @param offset
     * @param limit
     * @return
     */
    List<MaterialSummaryDto> getSummaryList(
            @Param("payerIds")String payerIds,
            @Param("payeeIds")String payeeIds,
            @Param("offset")Integer offset,
            @Param("limit")Integer limit);
}