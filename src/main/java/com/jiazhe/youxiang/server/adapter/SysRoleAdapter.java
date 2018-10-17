package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.vo.resp.SysRoleResp;

/**
 * 角色管理Adapter
 * Created by TU on 2018/10/17.
 */
public class SysRoleAdapter {
    public static SysRoleResp sysRoleDTO2VO(SysRoleDTO dto) {
        if (dto == null) {
            return null;
        }
        SysRoleResp sysRoleResp = new SysRoleResp();

        return sysRoleResp;
    }
}
