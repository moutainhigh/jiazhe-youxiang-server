package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodeBatchPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointExchangeCodeBatchPOMapper {
    int countByExample(PointExchangeCodeBatchPOExample example);

    int deleteByExample(PointExchangeCodeBatchPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointExchangeCodeBatchPO record);

    int insertSelective(PointExchangeCodeBatchPO record);

    List<PointExchangeCodeBatchPO> selectByExample(PointExchangeCodeBatchPOExample example);

    PointExchangeCodeBatchPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointExchangeCodeBatchPO record, @Param("example") PointExchangeCodeBatchPOExample example);

    int updateByExample(@Param("record") PointExchangeCodeBatchPO record, @Param("example") PointExchangeCodeBatchPOExample example);

    int updateByPrimaryKeySelective(PointExchangeCodeBatchPO record);

    int updateByPrimaryKey(PointExchangeCodeBatchPO record);
}