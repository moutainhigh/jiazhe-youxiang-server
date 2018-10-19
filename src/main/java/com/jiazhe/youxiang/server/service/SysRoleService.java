package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;

import java.util.List;

/**
 * @author TU
 * Created by TU on 2018/10/15.
 */
public interface SysRoleService {

    /**
     * 根据roleID删除角色信息（包括权限）
     * @param roleId
     * @return
     */
    int deleteRoleWithPerms(Integer roleId);

    /**
     * 根据实体删除角色（不包括权限）
     * @param sysRolePO
     * @return
     */
    int delete(SysRolePO sysRolePO);

    /**
     * 根据角色id查找角色信息，包括权限
     * @param roleId
     * @return
     */
    RoleWithPermDTO findRoleWithPermById(Integer roleId);

    /**
     * 根据角色名查找所有的角色
     * @param name
     * @return
     */
    List<SysRoleDTO> findByName(String name);


    /**
     * 根据id查询角色信息（不包含权限字符串）
     * @param id
     * @return
     */
    SysRoleDTO findById(int id);


    /**
     * 根据参数保存角色信息
     * @param isAdd
     * @param sysRoleDTO
     * @param newPermsDto
     * @param oldPermsDto
     * @return
     */
    int saveRoleWithPerm(boolean isAdd, SysRoleDTO sysRoleDTO, List<SysRolePermissionDTO> newPermsDto, List<SysRolePermissionDTO> oldPermsDto);

}
