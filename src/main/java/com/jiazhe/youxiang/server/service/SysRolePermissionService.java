package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPOExample;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;

import java.util.List;

/**
 * @author TU
 *         Created by TU on 2018/10/15.
 */
public interface SysRolePermissionService {
    /**
     * 根据roleid查询所有可用权限信息
     *
     * @param roleId
     * @return
     */
    List<SysRolePermissionDTO> findByRoleId(Integer roleId);

    /**
     * 根据实体列表删除角色权限信息
     *
     * @param sysRolePermissionPOList
     * @return
     */
    int batchDelete(List<SysRolePermissionPO> sysRolePermissionPOList);
}
