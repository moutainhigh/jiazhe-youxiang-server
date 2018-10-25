package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.sysuser.UserWithRoleDTO;
import com.jiazhe.youxiang.server.vo.req.sysuser.UserSaveReq;
import com.jiazhe.youxiang.server.vo.resp.sysuser.SysUserResp;
import com.jiazhe.youxiang.server.vo.resp.sysuser.UserWithRoleResp;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class SysUserAdapter {

    public static SysUserDTO PO2DTO(SysUserPO sysUserPO) {
        if (sysUserPO == null) {
            return null;
        }
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setId(sysUserPO.getId());
        sysUserDTO.setMobile(sysUserPO.getMobile());
        sysUserDTO.setName(sysUserPO.getName());
        sysUserDTO.setLastLoginTime(sysUserPO.getLastLoginTime());
        sysUserDTO.setSalt(sysUserPO.getSalt());
        sysUserDTO.setPassword(sysUserPO.getPassword());
        return sysUserDTO;
    }

    public static SysUserPO DTO2PO(SysUserDTO sysUserDTO) {
        if (sysUserDTO == null) {
            return null;
        }
        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.setId(sysUserDTO.getId());
        sysUserPO.setMobile(sysUserDTO.getMobile());
        sysUserPO.setName(sysUserDTO.getName());
        sysUserPO.setLastLoginTime(sysUserDTO.getLastLoginTime());
        sysUserPO.setSalt(sysUserDTO.getSalt());
        sysUserPO.setPassword(sysUserDTO.getPassword());
        return sysUserPO;
    }

    public static SysUserResp DTO2RespVO(SysUserDTO sysUserDTO) {
        if (sysUserDTO == null) {
            return null;
        }
        SysUserResp sysUserResp = new SysUserResp();
        sysUserResp.setId(sysUserDTO.getId());
        sysUserResp.setMobile(sysUserDTO.getMobile());
        sysUserResp.setName(sysUserDTO.getName());
        sysUserResp.setLastLoginTime(sysUserDTO.getLastLoginTime());
        return sysUserResp;
    }

    public static UserWithRoleResp userWithRoleDTO2UserWithRoleResp(UserWithRoleDTO dto) {
        if (dto == null) {
            return null;
        }
        UserWithRoleResp userWithRoleResp = new UserWithRoleResp();
        userWithRoleResp.setId(dto.getId());
        userWithRoleResp.setMobile(dto.getMobile());
        userWithRoleResp.setName(dto.getName());
        userWithRoleResp.setRoleIds(dto.getRoleIds());
        return userWithRoleResp;
    }

    public static UserWithRoleDTO userSaveReq2UserWithDTO(UserSaveReq req) {
        if (req == null) {
            return null;
        }
        UserWithRoleDTO userWithRoleDTO = new UserWithRoleDTO();
        userWithRoleDTO.setId(req.getId());
        userWithRoleDTO.setMobile(req.getMobile());
        userWithRoleDTO.setName(req.getName());
        userWithRoleDTO.setPassword(req.getPassword());
        userWithRoleDTO.setRoleIds(req.getRoleIds());
        return userWithRoleDTO;
    }
}