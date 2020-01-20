package com.jiazhe.youxiang.server.service.point;


import com.jiazhe.youxiang.server.domain.po.PointExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 添加批次信息
     * @param pointExchangeCodeBatchSaveDTO
     */
    void addSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO);

    /**
     * 修改批次信息
     * @param pointExchangeCodeBatchSaveDTO
     */
    void editSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO);

    PointExchangeCodeBatchEditDTO getById(Integer id);

    /**
     * 生成积分卡兑换码
     * @param id
     */
    void generateCode(Integer id);

    /**
     * 批量插入兑换码
     * @param batchPO
     * @param pointExchangeCodePOList
     */
    @Transactional(rollbackFor = Exception.class)
    void insertCodeAndKeyt(PointExchangeCodeBatchPO batchPO, List<PointExchangeCodePO> pointExchangeCodePOList);

    /**
     * 改变批次状态，业务重点：非虚拟批次需要改变批次下兑换码兑换的积分卡状态
     * @param id
     * @param status 【0停用 1启用】
     */
    void changeBatchStatus(Integer id, Byte status);

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

    /**
     * 判断绑定该商户号的批次是否存在
     * @param merchantNo
     * @param batchId
     * @return
     */
    boolean merchantNoIsRepeat(Integer batchId,String merchantNo);
}
