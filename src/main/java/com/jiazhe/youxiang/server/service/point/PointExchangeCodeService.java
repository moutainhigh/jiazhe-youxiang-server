package com.jiazhe.youxiang.server.service.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.Date;
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
     *
     * @param batchSaveDTO
     */
    void updateWithBatch(PointExchangeCodeBatchSaveDTO batchSaveDTO);

    /**
     * 批量插入兑换码记录，此时卡号和密码为空
     *
     * @param pointExchangeCodePOList
     */
    void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList);

    /**
     * 修改该批次下【未使用】的积分兑换码状态
     *
     * @param batchId 批次id
     * @param status  启停用状态
     */
    void batchChangeStatus(Integer batchId, Byte status);

    /**
     * 根据条件查询积分卡兑换码
     *
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @param paging
     * @return
     */
    List<PointExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging);

    /**
     * 根据id，单个修改兑换码启停用状态
     *
     * @param id
     * @param status
     */
    void changeCodeStatus(Integer id, Byte status);

    /**
     * 获取单个兑换码信息
     *
     * @param id
     * @return
     */
    PointExchangeCodeDTO getById(Integer id);

    /**
     * 修改兑换码信息
     *
     * @param dto
     */
    void editSave(PointExchangeCodeEditDTO dto);

    /**
     * 充值（由码到卡的过程）
     *
     * @param type
     * @param customerId
     * @param keyt
     */
    void codeCharge(Integer type, Integer customerId, String keyt);

    /**
     * 根据密码查询兑换码
     *
     * @param keyt
     * @return
     */
    PointExchangeCodePO findByKeyt(String keyt);

    /**
     * 根据卡号查询兑换码
     *
     * @param code
     * @return
     */
    PointExchangeCodePO findByCode(String code);

    /**
     * 获取已有记录的最大id值
     *
     * @return
     */
    Integer getMaxId();

    /**
     * 生成兑换码的时候，先生成卡号和密码为空的兑换码，回头来根据兑换码的id，生成卡号和密码，更新兑换码
     *
     * @param pointExchangeCodeDTOS
     */
    void batchUpdateCodeAndKeyt(List<PointExchangeCodeDTO> pointExchangeCodeDTOS);

    /**
     * 根据卡号list查找兑换码
     *
     * @param pointCodes
     * @return
     */
    List<PointExchangeCodeDTO> findByCodes(List<String> pointCodes);

    /**
     * 批量激活兑换码
     *
     * @param pointExchangeCodeDtoList
     */
    void batchActive(List<PointExchangeCodeDTO> pointExchangeCodeDtoList);

    /**
     * 通过code，检查兑换码是否存在，兑换码是否已经启用
     *
     * @param code
     */
    void checkByCode(String code);

    /**
     * 中行储蓄卡查询库存，不成功返回null
     *
     * @param orderNo
     * @param giftNo
     * @param expiryDate
     */
    PointExchangeCodeDTO queryStock(String orderNo, String giftNo, Date expiryDate);


    /**
     * 通过订单号（中行）查找兑换码
     *
     * @param orderNo
     * @return
     */
    PointExchangeCodeDTO queryByOrderNo(String orderNo);

    /**
     * 修改兑换码使用状态（中行）
     *
     * @param id
     * @param usedStaus
     */
    void changeCodeUsedStatus(Integer id, Byte usedStaus);
}
