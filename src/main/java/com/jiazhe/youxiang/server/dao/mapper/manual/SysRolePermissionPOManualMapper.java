package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;

import java.util.List;


/**
 * @author TU
 * Created by tujia on 2018/10/14.
 */
public interface SysRolePermissionPOManualMapper {

    /**
     * 批量插入
     * @param sysRolePermissionPOList
     * @return
     */
    int batchInsert(List<SysRolePermissionPO> sysRolePermissionPOList);

    /**
     * 批量更新
     * @param sysRolePermissionPOList
     * @return
     */
    int batchUpdate(List<SysRolePermissionPO> sysRolePermissionPOList);

}
