package com.jiazhe.youxiang.server.service.point;

import com.jiazhe.youxiang.server.domain.po.PointPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public interface PointService {

    void batchUpdate(List<Integer> cardIds, PointExchangeCodeBatchSaveDTO batchSaveDTO);

    void batchChangeStatus(List<Integer> cardIds, Byte status);

    void insert(PointPO pointPO);

    void update(PointPO pointPO);

    List<PointDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging);

    void changeStatus(Integer id, Byte status);

    void directCharge(Integer id, Integer batchId, BigDecimal faceValue);

    PointDTO getById(Integer id);

    void editSave(PointEditDTO dto);

    BigDecimal totalValidBalance(Integer customerId);

    void batchUpdate(List<PointDTO> pointDTOList);

    List<PointDTO> findByIds(List<Integer> pointIds);

    List<PointDTO> findByIdsInOrder(List<Integer> pointIds);
}
