package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
@Service("sysRolePermissionBiz")
public class SysRolePermissionBiz {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 查询角色下的权限
     * @param id
     * @return
     */
    public List<SysRolePermissionDTO> findByRoleId(Integer id) {
        return sysRolePermissionService.findByRoleId(id);
    }
}
