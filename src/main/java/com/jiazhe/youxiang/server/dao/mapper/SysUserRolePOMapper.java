package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.SysUserRolePO;
import com.jiazhe.youxiang.server.domain.po.SysUserRolePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRolePOMapper {
    int countByExample(SysUserRolePOExample example);

    int deleteByExample(SysUserRolePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRolePO record);

    int insertSelective(SysUserRolePO record);

    List<SysUserRolePO> selectByExample(SysUserRolePOExample example);

    SysUserRolePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserRolePO record, @Param("example") SysUserRolePOExample example);

    int updateByExample(@Param("record") SysUserRolePO record, @Param("example") SysUserRolePOExample example);

    int updateByPrimaryKeySelective(SysUserRolePO record);

    int updateByPrimaryKey(SysUserRolePO record);
}