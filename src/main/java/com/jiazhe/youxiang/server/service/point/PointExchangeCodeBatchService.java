package com.jiazhe.youxiang.server.service.point;


import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public interface PointExchangeCodeBatchService {

    List<PointExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging);

    List<PointExchangeCodeBatchDTO> getVirtualByProjectId(Integer id);

    void addSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO);

    void editSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO);

    PointExchangeCodeBatchEditDTO getById(Integer id);

    void generateCode(Integer id);

    void changeBatchStatus(Integer id, Byte b);

    /**
     * 根据商户号码找到虚拟批次id
     *
     * @param merchantNo
     * @return
     */
    Integer getBatchIdByMerchantNo(String merchantNo);

    /**
     * 根据批次Id获得扣减银行积分所获得的面值
     *
     * @param batchId
     * @param bonus
     * @return
     */
    BigDecimal getFaceValue(Integer batchId, String bonus);
}
