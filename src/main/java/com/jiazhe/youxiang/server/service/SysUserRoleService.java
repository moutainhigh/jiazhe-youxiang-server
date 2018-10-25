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

    List<SysUserRoleDTO> findByUserId(Integer userId);

    int batchDelete(List<Integer> ids);

    int batchInsert(List<SysUserRolePO> newPermsPO);
}