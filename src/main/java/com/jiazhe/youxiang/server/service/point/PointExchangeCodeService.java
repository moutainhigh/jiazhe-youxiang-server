package com.jiazhe.youxiang.server.service.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public interface PointExchangeCodeService {

    List<PointExchangeCodeDTO> getByBatchId(Integer id);

    void updateWithBatch(PointExchangeCodeBatchSaveDTO batchSaveDTO);

    void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList);

    /**
     * 修改该批次下所有积分兑换码状态，并同时修改已经兑换为的积分卡
     * @param id 批次id
     * @param status 启停用状态
     */
    void batchChangeStatus(Integer id, Byte status);

    List<PointExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging);

    void changeCodeStatus(Integer id, Byte status);

    PointExchangeCodeDTO getById(Integer id);

    void editSave(PointExchangeCodeEditDTO dto);

    void codeCharge(Integer type, Integer id, String keyt) ;

    PointExchangeCodePO findByKeyt(String keyt);

    Integer getMaxId();

    void batchUpdateCodeAndKeyt(List<PointExchangeCodeDTO> pointExchangeCodeDTOS);
}
