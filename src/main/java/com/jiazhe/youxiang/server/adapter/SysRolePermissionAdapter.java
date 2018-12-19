package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;

/**
 * @author TU
 * Created by tujia on 2018/10/18.
 */
public class SysRolePermissionAdapter {

    public static SysRolePermissionDTO PO2DTO(SysRolePermissionPO po) {
        if (po == null) {
            return null;
        }
        SysRolePermissionDTO sysRolePermissionDTO = new SysRolePermissionDTO();
        sysRolePermissionDTO.setId(po.getId());
        sysRolePermissionDTO.setRoleId(po.getRoleId());
        sysRolePermissionDTO.setPermUrl(po.getPermUrl());
        return sysRolePermissionDTO;
    }

    public static SysRolePermissionPO DTO2PO(SysRolePermissionDTO dto) {
        if (dto == null) {
            return null;
        }
        SysRolePermissionPO sysRolePermissionPO = new SysRolePermissionPO();
        sysRolePermissionPO.setId(dto.getId());
        sysRolePermissionPO.setRoleId(dto.getRoleId());
        sysRolePermissionPO.setPermUrl(dto.getPermUrl());
        return sysRolePermissionPO;
    }
}
