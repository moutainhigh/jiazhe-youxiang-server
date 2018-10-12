package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.domain.po.SysRolePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolePOMapper {
    int countByExample(SysRolePOExample example);

    int deleteByExample(SysRolePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePO record);

    int insertSelective(SysRolePO record);

    List<SysRolePO> selectByExample(SysRolePOExample example);

    SysRolePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRolePO record, @Param("example") SysRolePOExample example);

    int updateByExample(@Param("record") SysRolePO record, @Param("example") SysRolePOExample example);

    int updateByPrimaryKeySelective(SysRolePO record);

    int updateByPrimaryKey(SysRolePO record);
}