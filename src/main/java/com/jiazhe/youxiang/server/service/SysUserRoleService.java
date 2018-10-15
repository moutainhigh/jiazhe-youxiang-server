package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysUserRolePO;
import com.jiazhe.youxiang.server.domain.po.SysUserRolePOExample;

import java.util.List;

/**
 * Created by TU on 2018/10/15.
 */
public interface SysUserRoleService {

    //根据SysUserRolePOExample查询SysUserRolePO List
    List<SysUserRolePO> selectByExample(SysUserRolePOExample sysUserRolePOExample);
}
