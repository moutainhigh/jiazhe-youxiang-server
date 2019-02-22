package com.jiazhe.youxiang.server.dao.mapper.manual.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public interface PointExchangeCodePOManualMapper {

    void batchUpdate(List<PointExchangeCodePO> poList);

    void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList);

    void batchChangeStatus(@Param("batchId") Integer batchId, @Param("status") Byte status);

    Integer count(@Param("batchId") Integer batchId, @Param("code") String code, @Param("keyt") String keyt, @Param("status") Byte status, @Param("used") Byte used);

    List<PointExchangeCodePO> query(@Param("batchId") Integer batchId, @Param("code") String code, @Param("keyt") String keyt, @Param("status") Byte status, @Param("used") Byte used, @Param("offset") Integer offset, @Param("limit") Integer limit);

    PointExchangeCodePO findByKeyt(@Param("keyt") String keyt);

    Integer getMaxId();
}
