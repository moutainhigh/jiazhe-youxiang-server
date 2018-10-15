package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.dao.mapper.SysRolePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePOMapper;
import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.service.SysRoleService;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by TU on 2018/10/15.
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRolePOMapper sysRolePOMapper;
    @Autowired
    private SysRolePOManualMapper sysRolePOManualMapper;

    @Override
    public int count(SysRoleReq req) {
        return sysRolePOManualMapper.count(req);
    }

    @Override
    public List<Map> getPageContent(SysRoleReq req) {
        return sysRolePOManualMapper.getPageContent(req);
    }

    @Override
    public SysRolePO findById(int id) {
        return  sysRolePOMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysRolePO sysRolePO) {
        return sysRolePOMapper.updateByPrimaryKeySelective(sysRolePO);
    }

    @Override
    public int insert(SysRolePO sysRolePO) {
        return sysRolePOManualMapper.insert(sysRolePO);
    }
}
