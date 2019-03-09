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

    /**
     * 根据批次信息，修改兑换码信息
     * @param batchSaveDTO
     */
    void updateWithBatch(PointExchangeCodeBatchSaveDTO batchSaveDTO);

    void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList);

    /**
     * 修改该批次下【未使用】的积分兑换码状态
     * @param id 批次id
     * @param status 启停用状态
     */
    void batchChangeStatus(Integer id, Byte status);

    List<PointExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging);

    /**
     * 根据id，单个修改兑换码状态
     * @param id
     * @param status
     */
    void changeCodeStatus(Integer id, Byte status);

    PointExchangeCodeDTO getById(Integer id);

    /**
     * 修改兑换码信息
     * @param dto
     */
    void editSave(PointExchangeCodeEditDTO dto);

    /**
     * 由码到卡的过程
     * @param type
     * @param id
     * @param keyt
     */
    void codeCharge(Integer type, Integer id, String keyt) ;

    PointExchangeCodePO findByKeyt(String keyt);

    Integer getMaxId();

    void batchUpdateCodeAndKeyt(List<PointExchangeCodeDTO> pointExchangeCodeDTOS);

    List<PointExchangeCodeDTO> findByCodes(List<String> pointCodes);

    void batchUpdate(List<PointExchangeCodeDTO> pointExchangeCodeDtoList);
}
