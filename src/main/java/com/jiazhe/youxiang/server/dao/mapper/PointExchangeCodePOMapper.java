package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointExchangeCodePOMapper {
    int countByExample(PointExchangeCodePOExample example);

    int deleteByExample(PointExchangeCodePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointExchangeCodePO record);

    int insertSelective(PointExchangeCodePO record);

    List<PointExchangeCodePO> selectByExample(PointExchangeCodePOExample example);

    PointExchangeCodePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointExchangeCodePO record, @Param("example") PointExchangeCodePOExample example);

    int updateByExample(@Param("record") PointExchangeCodePO record, @Param("example") PointExchangeCodePOExample example);

    int updateByPrimaryKeySelective(PointExchangeCodePO record);

    int updateByPrimaryKey(PointExchangeCodePO record);
}