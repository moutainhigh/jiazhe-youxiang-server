package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.MaterialInfoPO;
import com.jiazhe.youxiang.server.domain.po.MaterialInfoPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialInfoPOMapper {
    int countByExample(MaterialInfoPOExample example);

    int deleteByExample(MaterialInfoPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MaterialInfoPO record);

    int insertSelective(MaterialInfoPO record);

    List<MaterialInfoPO> selectByExample(MaterialInfoPOExample example);

    MaterialInfoPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MaterialInfoPO record, @Param("example") MaterialInfoPOExample example);

    int updateByExample(@Param("record") MaterialInfoPO record, @Param("example") MaterialInfoPOExample example);

    int updateByPrimaryKeySelective(MaterialInfoPO record);

    int updateByPrimaryKey(MaterialInfoPO record);
}