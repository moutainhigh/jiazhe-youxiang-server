package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.PointPO;
import com.jiazhe.youxiang.server.domain.po.PointPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointPOMapper {
    int countByExample(PointPOExample example);

    int deleteByExample(PointPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointPO record);

    int insertSelective(PointPO record);

    List<PointPO> selectByExample(PointPOExample example);

    PointPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointPO record, @Param("example") PointPOExample example);

    int updateByExample(@Param("record") PointPO record, @Param("example") PointPOExample example);

    int updateByPrimaryKeySelective(PointPO record);

    int updateByPrimaryKey(PointPO record);
}