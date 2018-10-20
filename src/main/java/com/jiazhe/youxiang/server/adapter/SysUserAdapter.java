package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.vo.resp.sysuser.SysUserResp;

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
}
