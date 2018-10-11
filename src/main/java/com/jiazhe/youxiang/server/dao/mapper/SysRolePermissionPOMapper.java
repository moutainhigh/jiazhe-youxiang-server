package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolePermissionPOMapper {
    int countByExample(SysRolePermissionPOExample example);

    int deleteByExample(SysRolePermissionPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermissionPO record);

    int insertSelective(SysRolePermissionPO record);

    List<SysRolePermissionPO> selectByExample(SysRolePermissionPOExample example);

    SysRolePermissionPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRolePermissionPO record, @Param("example") SysRolePermissionPOExample example);

    int updateByExample(@Param("record") SysRolePermissionPO record, @Param("example") SysRolePermissionPOExample example);

    int updateByPrimaryKeySelective(SysRolePermissionPO record);

    int updateByPrimaryKey(SysRolePermissionPO record);
}