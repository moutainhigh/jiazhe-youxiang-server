package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import com.jiazhe.youxiang.server.domain.po.SysCityPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCityPOMapper {
    int countByExample(SysCityPOExample example);

    int deleteByExample(SysCityPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysCityPO record);

    int insertSelective(SysCityPO record);

    List<SysCityPO> selectByExample(SysCityPOExample example);

    SysCityPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysCityPO record, @Param("example") SysCityPOExample example);

    int updateByExample(@Param("record") SysCityPO record, @Param("example") SysCityPOExample example);

    int updateByPrimaryKeySelective(SysCityPO record);

    int updateByPrimaryKey(SysCityPO record);
}