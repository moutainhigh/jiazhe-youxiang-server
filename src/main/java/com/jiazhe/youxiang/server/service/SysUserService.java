package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.domain.po.SysUserPOExample;

import java.util.List;

/**
 * Created by TU on 2018/10/15.
 */
public interface SysUserService {

    //根据SysUserPOExample查询SysUserPO List
    List<SysUserPO> selectByExample(SysUserPOExample sysUserPOExample);
}
