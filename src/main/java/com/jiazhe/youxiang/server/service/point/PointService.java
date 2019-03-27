package com.jiazhe.youxiang.server.service.point;

import com.jiazhe.youxiang.server.domain.po.PointPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public interface PointService {

    /**
     * 根据批次信息修改积分卡的信息
     * @param ids 待修改的积分卡id集合
     * @param batchSaveDTO  批次信息
     */
    void updateWithBatch(List<Integer> ids, PointExchangeCodeBatchSaveDTO batchSaveDTO);

    /**
     * 根据积分卡的ids，批量修改积分卡的启停用状态
     * @param ids
     * @param status
     */
    void batchChangeStatus(List<Integer> ids, Byte status);

    /**
     * 插入积分卡
     * @param pointPO
     */
    void insert(PointPO pointPO);

    void update(PointPO pointPO);

    List<PointDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging);

    /**
     * 修改积分卡状态
     * @param id
     * @param status
     */
    void changeStatus(Integer id, Byte status);

    /**
     * 选择批次直接充值
     * @param customerId
     * @param batchId
     * @param faceValue
     */
    void directCharge(Integer customerId, Integer batchId, BigDecimal faceValue) ;

    PointDTO getById(Integer id);

    /**
     * 修改积分卡信息
     * @param dto
     */
    void editSave(PointEditDTO dto);

    BigDecimal totalValidBalance(Integer customerId);

    void batchUpdate(List<PointDTO> pointDTOList);

    List<PointDTO> findByIds(List<Integer> pointIds);

    List<PointDTO> findByIdsInOrder(List<Integer> pointIds);

    void chargeByQRCode(String purchaseOrderStr, CustomerDTO customerDTO, Integer batchId, BigDecimal faceValue) ;
}
