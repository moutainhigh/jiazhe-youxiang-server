package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPOExample;

import java.util.List;

/**
 * Created by TU on 2018/10/15.
 */
public interface SysRolePermissionService {

    //根据条件查询rolePermission
    List<SysRolePermissionPO> selectByExample(SysRolePermissionPOExample sysRolePermissionPOExample);

    //根据list批量修改perms
    int batchUpdate(List<SysRolePermissionPO> perms);

    //根据list批量插入perms
    int batchInsert(List<SysRolePermissionPO> newPerms);
}
