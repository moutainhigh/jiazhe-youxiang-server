package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.dao.mapper.SysRolePermissionPOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePermissionPOMapper;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPOExample;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TU on 2018/10/15.
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl implements SysRolePermissionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRolePermissionServiceImpl.class);

    @Autowired
    private SysRolePermissionPOMapper sysRolePermissionPOMapper;
    @Autowired
    private SysRolePermissionPOManualMapper sysRolePermissionPOManualMapper;

    @Override
    public List<SysRolePermissionPO> selectByExample(SysRolePermissionPOExample sysRolePermissionPOExample) {
        return sysRolePermissionPOMapper.selectByExample(sysRolePermissionPOExample);
    }

    @Override
    public int batchUpdate(List<SysRolePermissionPO> perms) {
        return sysRolePermissionPOManualMapper.batchUpdate(perms);
    }

    @Override
    public int batchInsert(List<SysRolePermissionPO> newPerms) {
        return sysRolePermissionPOManualMapper.batchInsert(newPerms);
    }


}
