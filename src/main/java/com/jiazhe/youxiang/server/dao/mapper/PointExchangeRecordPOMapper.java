package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointExchangeRecordPOMapper {
    int countByExample(PointExchangeRecordPOExample example);

    int deleteByExample(PointExchangeRecordPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointExchangeRecordPO record);

    int insertSelective(PointExchangeRecordPO record);

    List<PointExchangeRecordPO> selectByExample(PointExchangeRecordPOExample example);

    PointExchangeRecordPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointExchangeRecordPO record, @Param("example") PointExchangeRecordPOExample example);

    int updateByExample(@Param("record") PointExchangeRecordPO record, @Param("example") PointExchangeRecordPOExample example);

    int updateByPrimaryKeySelective(PointExchangeRecordPO record);

    int updateByPrimaryKey(PointExchangeRecordPO record);
}