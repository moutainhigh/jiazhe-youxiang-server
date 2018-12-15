package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysUserRolePO;
import com.jiazhe.youxiang.server.domain.po.SysUserRolePOExample;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;

import java.util.List;

/**
 * @author tu
 * @date 2018/10/15.
 */
public interface SysUserRoleService {

    /**
     * 通过用户id，查找用户对应的权限
     * @param userId
     * @return
     */
    List<SysUserRoleDTO> findByUserId(Integer userId);

    void batchDelete(List<Integer> ids);

    void batchInsert(List<SysUserRolePO> newPermsPO);
}
