package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.dao.mapper.SysUserPOMapper;
import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.domain.po.SysUserPOExample;
import com.jiazhe.youxiang.server.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TU on 2018/10/15.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserPOMapper sysUserPOMapper;

    @Override
    public List<SysUserPO> selectByExample(SysUserPOExample sysUserPOExample) {
        return sysUserPOMapper.selectByExample(sysUserPOExample);
    }
}
