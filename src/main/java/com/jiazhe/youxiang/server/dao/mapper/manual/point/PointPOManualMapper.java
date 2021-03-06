package com.jiazhe.youxiang.server.dao.mapper.manual.point;

import com.jiazhe.youxiang.server.domain.po.PointPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface PointPOManualMapper {

    List<PointPO> findByIds(List<Integer> ids);

    void batchUpdate(List<PointPO> rcPOList);

    void batchChangeStatus(Map<String, Object> map);

    void insert(PointPO pointPO);

    List<PointPO> query(@Param("mobile") String mobile, @Param("exchangeType") Integer exchangeType, @Param("status") Byte status, @Param("expiry") Byte expiry, @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer count(@Param("mobile") String mobile, @Param("exchangeType") Integer exchangeType, @Param("status") Byte status, @Param("expiry") Byte expiry);

    BigDecimal totalValidBalance(@Param("customerId")Integer customerId);

    List<PointPO> findByIdsInOrder(List<Integer> ids);
}
