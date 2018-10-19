package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.vo.req.sysrole.RoleSaveReq;
import com.jiazhe.youxiang.server.vo.resp.sysrole.RoleWithPermResp;

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
        RoleWithPermResp roleWithPermResp = new RoleWithPermResp();
        roleWithPermResp.setId(dto.getId());
        roleWithPermResp.setName(dto.getName());
        roleWithPermResp.setIsSuper(dto.getIsSuper());
        roleWithPermResp.setPriority(dto.getPriority());
        roleWithPermResp.setPermsStr(dto.getPermsStr());
        return roleWithPermResp;
    }

    public static RoleWithPermDTO roleSaveReq2RoleWithPremDTO(RoleSaveReq req) {
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

    public static SysRolePO DTO2PO(SysRoleDTO sysRoleDTO) {
        if (sysRoleDTO == null) {
            return null;
        }
        SysRolePO sysRolePO = new SysRolePO();
        sysRolePO.setId(sysRoleDTO.getId());
        sysRolePO.setName(sysRoleDTO.getName());
        sysRolePO.setIsSuper(sysRoleDTO.getIsSuper());
        sysRolePO.setPriority(sysRoleDTO.getPriority());
        return sysRolePO;
    }

    public static SysRoleDTO PO2DTO(SysRolePO sysRolePO) {
        if (sysRolePO == null) {
            return null;
        }
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        sysRoleDTO.setId(sysRolePO.getId());
        sysRoleDTO.setName(sysRolePO.getName());
        sysRoleDTO.setIsSuper(sysRolePO.getIsSuper());
        sysRoleDTO.setPriority(sysRolePO.getPriority());
        return sysRoleDTO;
    }
}
