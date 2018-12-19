package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.adapter.SysRolePermissionAdapter;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysRolePermissionPOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePermissionPOMapper;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPOExample;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TU on 2018/10/15.
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRolePermissionServiceImpl.class);


    @Autowired
    private SysRolePermissionPOMapper sysRolePermissionPOMapper;
    @Autowired
    private SysRolePermissionPOManualMapper sysRolePermissionPOManualMapper;

    @Override
    public List<SysRolePermissionDTO> findByRoleId(Integer roleId) {
        SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
        SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePermissionPO> sysRolePermissionPOList = sysRolePermissionPOMapper.selectByExample(sysRolePermissionPOExample);
        return sysRolePermissionPOList.stream().map(SysRolePermissionAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        sysRolePermissionPOManualMapper.batchDelete(ids);
    }

    @Override
    public void batchInsert(List<SysRolePermissionPO> newPermsPO) {
        sysRolePermissionPOManualMapper.batchInsert(newPermsPO);
    }

}
