package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.dao.mapper.SysUserRolePOMapper;
import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.domain.po.SysUserPOExample;
import com.jiazhe.youxiang.server.domain.po.SysUserRolePO;
import com.jiazhe.youxiang.server.domain.po.SysUserRolePOExample;
import com.jiazhe.youxiang.server.service.SysUserRoleService;
import com.jiazhe.youxiang.server.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TU on 2018/10/15.
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    @Autowired
    private SysUserRolePOMapper sysUserRolePOMapper;

    @Override
    public List<SysUserRolePO> selectByExample(SysUserRolePOExample sysUserRolePOExample) {
        return sysUserRolePOMapper.selectByExample(sysUserRolePOExample);
    }
}
