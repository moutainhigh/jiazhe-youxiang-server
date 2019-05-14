package com.jiazhe.youxiang.server.service.material;

import com.jiazhe.youxiang.server.dto.material.MaterialDto;
import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.vo.Paging;

import java.math.BigDecimal;
import java.util.Date;
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

    /**
     * 计算汇总
     * @param payerIds
     * @param payeeIds
     * @return
     */
    MaterialSummaryDto calculateSummary(String payerIds, String payeeIds);

    /**
     * 保存转账信息
     * @param id
     * @param payeeId
     * @param transferAmount
     * @param materialValue
     * @param transferTime
     * @param remark
     */
    void save(Integer id,Integer payeeId, BigDecimal transferAmount, BigDecimal materialValue, Date transferTime, String remark);

    /**
     * 根据条件查询转账明细信息
     * @param payerIds
     * @param payeeIds
     * @param transferTimeBegin
     * @param transferTimeEnd
     * @param paging
     * @return
     */
    List<MaterialDto> getList(String payerIds, String payeeIds, Date transferTimeBegin, Date transferTimeEnd, Paging paging);

    /**
     * 删除转账记录
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取详情
     * @param id
     * @return
     */
    MaterialDto getById(Integer id);
}
