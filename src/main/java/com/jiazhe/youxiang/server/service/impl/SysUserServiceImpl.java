package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.adapter.SysUserAdapter;
import com.jiazhe.youxiang.server.dao.mapper.SysUserPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysUserPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.domain.po.SysUserPOExample;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.SysUserService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @date 2018/10/15.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserPOMapper sysUserPOMapper;
    @Autowired
    private SysUserPOManualMapper sysUserPOManualMapper;


    @Override
    public List<SysUserDTO> findAll() {
        SysUserPOExample sysUserPOExample = new SysUserPOExample();
        SysUserPOExample.Criteria criteria = sysUserPOExample.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysUserPO> sysUserPOList = sysUserPOMapper.selectByExample(sysUserPOExample);
        List<SysUserDTO> sysUserDTOList = sysUserPOList.stream().map(SysUserAdapter::PO2DTO).collect(Collectors.toList());
        List<SysUserDTO> listSorted = sysUserDTOList.stream().sorted(Comparator.comparing(SysUserDTO::getLastLoginTime)).collect(Collectors.toList());
        return listSorted;
    }

    @Override
    public List<SysUserDTO> findByName(String name, Paging paging) {
        Integer count = sysUserPOManualMapper.count(name);
        List<SysUserPO> sysUserPOList = sysUserPOManualMapper.query(name,paging.getOffset(),paging.getLimit());
        paging.setTotal(count);
        if (paging.getLimit() + paging.getOffset() >= count) {
            paging.setHasMore(false);
        }
        return sysUserPOList.stream().map(SysUserAdapter::PO2DTO).collect(Collectors.toList());
    }
}
