package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
import java.util.List;

/**
 * @author TU
 * Created by TU on 2018/10/15.
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
     * 根据ids删除角色权限信息
     *
     * @param ids
     * @return
     */
    void batchDelete(List<Integer> ids);

    /**
     * 根据实体批量插入
     * @param newPermsPO
     * @return
     */
    void batchInsert(List<SysRolePermissionPO> newPermsPO);

}
