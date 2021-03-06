package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysUserRoleAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.SysUserRolePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysUserRolePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.SysUserRolePO;
import com.jiazhe.youxiang.server.domain.po.SysUserRolePOExample;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;
import com.jiazhe.youxiang.server.service.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TU on 2018/10/15.
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    @Autowired
    private SysUserRolePOMapper sysUserRolePOMapper;
    @Autowired
    private SysUserRolePOManualMapper sysUserRolePOManualMapper;

    @Override
    public List<SysUserRoleDTO> findByUserId(Integer userId) {
        SysUserRolePOExample sysUserRolePOExample = new SysUserRolePOExample();
        SysUserRolePOExample.Criteria criteria = sysUserRolePOExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<SysUserRolePO> sysUserRolePOList = sysUserRolePOMapper.selectByExample(sysUserRolePOExample);
        return sysUserRolePOList.stream().map(SysUserRoleAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void batchDelete(List<Integer> ids) {
         sysUserRolePOManualMapper.batchDelete(ids);
    }

    @Override
    public void batchInsert(List<SysUserRolePO> newRolesPO) {
         sysUserRolePOManualMapper.batchInsert(newRolesPO);
    }
}
