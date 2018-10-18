package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.vo.req.sysrole.RoleSaveReq;
import com.jiazhe.youxiang.server.vo.resp.sysrole.RoleWithPermResp;
import com.jiazhe.youxiang.server.vo.resp.sysrole.SysRoleResp;

/**
 * @author tu
 * @description 角色管理Adapter
 * @date 2018/10/17.
 */
public class SysRoleAdapter {

    public static RoleWithPermResp roleWithPermDTO2RoleWithPermResp(RoleWithPermDTO dto) {
        if (dto == null) {
            return null;
        }
        RoleWithPermResp roleAndPermissionResp = new RoleWithPermResp();
        roleAndPermissionResp.setId(dto.getId());
        roleAndPermissionResp.setName(dto.getName());
        roleAndPermissionResp.setIsSuper(dto.getIsSuper());
        roleAndPermissionResp.setPriority(dto.getPriority());
        roleAndPermissionResp.setPermsStr(dto.getPermsStr());
        return roleAndPermissionResp;
    }

    public static SysRoleDTO sysRolePO2SysRoleDTO(SysRolePO po) {
        if (po == null) {
            return null;
        }
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        sysRoleDTO.setId(po.getId());
        sysRoleDTO.setName(po.getName());
        sysRoleDTO.setIsSuper(po.getIsSuper());
        sysRoleDTO.setPriority(po.getPriority());
        return sysRoleDTO;
    }

    public static RoleWithPermDTO roleSaveVO2RoleWithPermDTO(RoleSaveReq req) {
        if (req == null) {
            return null;
        }
        RoleWithPermDTO roleWithPermDTO = new RoleWithPermDTO();
        roleWithPermDTO.setId(req.getId());
        roleWithPermDTO.setName(req.getName());
        roleWithPermDTO.setIsSuper(req.getIsSuper());
        roleWithPermDTO.setPriority(req.getPriority());
        roleWithPermDTO.setPermsStr(req.getPermsStr());
        return roleWithPermDTO;
    }

    public static SysRolePO sysRoleDTO2SysRolePO(SysRoleDTO dto) {
        if (dto == null) {
            return null;
        }
        SysRolePO sysRolePO = new SysRolePO();
        sysRolePO.setId(dto.getId());
        sysRolePO.setName(dto.getName());
        sysRolePO.setIsSuper(dto.getIsSuper());
        sysRolePO.setPriority(dto.getPriority());
        return sysRolePO;
    }
}
