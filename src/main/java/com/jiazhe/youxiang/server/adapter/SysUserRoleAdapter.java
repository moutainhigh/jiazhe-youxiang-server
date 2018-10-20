package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.SysUserRolePO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class SysUserRoleAdapter {

    public static SysUserRoleDTO PO2DTO(SysUserRolePO sysUserRolePO) {
        if (sysUserRolePO == null) {
            return null;
        }
        SysUserRoleDTO sysUserRoleDTO = new SysUserRoleDTO();
        sysUserRoleDTO.setId(sysUserRolePO.getId());
        sysUserRoleDTO.setUserId(sysUserRolePO.getUserId());
        sysUserRoleDTO.setRoleId(sysUserRolePO.getRoleId());
        return sysUserRoleDTO;
    }

    public static SysUserRolePO DTO2PO(SysUserRoleDTO sysUserRoleDTO) {
        if (sysUserRoleDTO == null) {
            return null;
        }
        SysUserRolePO sysUserRolePO = new SysUserRolePO();
        sysUserRolePO.setId(sysUserRoleDTO.getId());
        sysUserRolePO.setUserId(sysUserRoleDTO.getUserId());
        sysUserRolePO.setRoleId(sysUserRoleDTO.getRoleId());
        return sysUserRolePO;
    }
}
