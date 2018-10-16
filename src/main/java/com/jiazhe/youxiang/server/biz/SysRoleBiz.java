package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import com.jiazhe.youxiang.server.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by TU on 2018/10/15.
 */
@Service("sysRoleBiz")
public class SysRoleBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleBiz.class);

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    //保存角色信息，新建或修改
    public int save(boolean isAdd, SysRolePO sysRolePO, List<SysRolePermissionPO> newPerms, List<SysRolePermissionPO> oldPerms) {
        int count = 0;
        try {
            if (isAdd) {//新建角色
                sysRoleService.insert(sysRolePO);
                for (SysRolePermissionPO temp : newPerms) {
                    temp.setRoleId(sysRolePO.getId());
                }
            } else {//修改角色
                sysRoleService.update(sysRolePO);
            }
            if (newPerms != null && newPerms.size() > 0) {
                sysRolePermissionService.batchInsert(newPerms);
            }
            if (newPerms != null && oldPerms.size() > 0) {//把剩下的old给删除了
                for (SysRolePermissionPO old : oldPerms) {
                    old.setIsDeleted(Byte.valueOf("1"));
                    old.setModTime(new Date());
                }
                sysRolePermissionService.batchUpdate(oldPerms);
            }
            count = 1;
        } catch (Exception e) {
            LOGGER.warn("保存角色出错，错误消息：" + e.getMessage());
        }
        return count;
    }
}
