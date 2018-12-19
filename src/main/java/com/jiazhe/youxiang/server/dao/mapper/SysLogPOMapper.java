package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.SysLogPO;
import com.jiazhe.youxiang.server.domain.po.SysLogPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysLogPOMapper {
    int countByExample(SysLogPOExample example);

    int deleteByExample(SysLogPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysLogPO record);

    int insertSelective(SysLogPO record);

    List<SysLogPO> selectByExample(SysLogPOExample example);

    SysLogPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysLogPO record, @Param("example") SysLogPOExample example);

    int updateByExample(@Param("record") SysLogPO record, @Param("example") SysLogPOExample example);

    int updateByPrimaryKeySelective(SysLogPO record);

    int updateByPrimaryKey(SysLogPO record);
}