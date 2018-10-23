package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;
import com.jiazhe.youxiang.server.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/20
 */
@Service("sysUserRoleBiz")
public class SysUserRoleBiz {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    public List<SysUserRoleDTO> findByUserId(Integer id) {
        return sysUserRoleService.findByUserId(id);
    }
}
