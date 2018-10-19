package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;

import java.util.List;
import java.util.Map;

/**
 * Created by TU on 2018/10/15.
 */
public interface SysRoleService {

    //根据id查询角色信息（包括权限字符串）
    RoleWithPermDTO findRoleWithPermById(Integer roleId);

    //根据id查询角色信息（不包含权限字符串）
    SysRoleDTO findById(int id);

    //通过名称查找角色信息（不包含权限字符串）
    List<SysRoleDTO> findByName(String name);

    //保存角色信息（包含权限字符串）
    int saveRoleWithPerm(boolean isAdd, SysRoleDTO sysRoleDTO, List<SysRolePermissionDTO> newPerms, List<SysRolePermissionDTO> oldPerms);

    //根据条件查询角色总数量
    int count(SysRoleReq req);

    int softDeleteById(SysRoleDTO sysRoleDTO, List<SysRolePermissionDTO> perms);
}
