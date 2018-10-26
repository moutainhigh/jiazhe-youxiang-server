package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.adapter.SysUserAdapter;
import com.jiazhe.youxiang.server.adapter.SysUserRoleAdapter;
import com.jiazhe.youxiang.server.dao.mapper.SysUserPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysUserPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;
import com.jiazhe.youxiang.server.dto.sysuser.UserWithRoleDTO;
import com.jiazhe.youxiang.server.service.SysUserRoleService;
import com.jiazhe.youxiang.server.service.SysUserService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
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
    @Autowired
    private SysUserRoleService sysUserRoleService;


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
    public List<SysUserDTO> getList(String name, Paging paging) {
        Integer count = sysUserPOManualMapper.count(name);
        List<SysUserPO> sysUserPOList = sysUserPOManualMapper.query(name,paging.getOffset(),paging.getLimit());
        paging.setTotal(count);
        if (paging.getLimit() + paging.getOffset() >= count) {
            paging.setHasMore(false);
        }
        return sysUserPOList.stream().map(SysUserAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public int deleteUserWithRole(Integer userId) {
        List<SysUserRoleDTO> sysUserRoleDTOList = sysUserRoleService.findByUserId(userId);
        List<Integer> ids = sysUserRoleDTOList.stream().map(SysUserRoleDTO::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(sysUserRoleDTOList)) {
            sysUserRoleService.batchDelete(ids);
        }
        return delete(userId);
    }

    @Override
    public int delete(Integer id) {
        return sysUserPOManualMapper.delete(id);
    }

    @Override
    public UserWithRoleDTO findUserWithRoleById(Integer id) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(id);
        List<SysUserRoleDTO> sysUserRoleDTOList = sysUserRoleService.findByUserId(id);
        StringBuilder roleIds = new StringBuilder();
        for (SysUserRoleDTO dto : sysUserRoleDTOList) {
            roleIds.append(dto.getRoleId() + ",");
        }
        if (sysUserRoleDTOList.size() > 0) {
            roleIds.deleteCharAt(roleIds.length() - 1);
        }
        UserWithRoleDTO userWithRoleDTO = new UserWithRoleDTO();
        userWithRoleDTO.setId(sysUserPO.getId());
        userWithRoleDTO.setMobile(sysUserPO.getMobile());
        /*userWithRoleDTO.setName(sysUserPO.getName());*/
        userWithRoleDTO.setRoleIds(roleIds.toString());
        return userWithRoleDTO;
    }

    @Override
    public List<SysUserDTO> findByName(String name) {
        SysUserPOExample sysUserPOExample = new SysUserPOExample();
        SysUserPOExample.Criteria criteria = sysUserPOExample.createCriteria();
        if (!Strings.isEmpty(name)) {
            /*criteria.andNameEqualTo(name);*/
        }
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysUserPO> sysUserPOList = sysUserPOMapper.selectByExample(sysUserPOExample);
        return sysUserPOList.stream().map(SysUserAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public SysUserDTO findById(Integer id) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(id);
        return SysUserAdapter.PO2DTO(sysUserPO);
    }

    @Override
    public int saveUserWithRole(boolean isAdd, SysUserDTO sysUserDTO, List<SysUserRoleDTO> newRolesDto, List<SysUserRoleDTO> oldRolesDto) {
        SysUserPO sysUserPO = SysUserAdapter.DTO2PO(sysUserDTO);
        sysUserPO.setModTime(new Date());
        List<SysUserRolePO> newPermsPO = newRolesDto.stream().map(SysUserRoleAdapter::DTO2PO).collect(Collectors.toList());
        if (isAdd) {
            sysUserPO.setRemark("");
            sysUserPO.setExtInfo("");
            sysUserPO.setAddTime(new Date());
            sysUserPO.setIsDeleted(Byte.valueOf("0"));
            sysUserPO.setLastLoginTime(new Date());
            sysUserPOManualMapper.insert(sysUserPO);
        } else {
            sysUserPOMapper.updateByPrimaryKeySelective(sysUserPO);
        }
        if (!CollectionUtils.isEmpty(newPermsPO)) {
            for (SysUserRolePO newPO : newPermsPO) {
                newPO.setUserId(sysUserPO.getId());
                newPO.setIsDeleted(Byte.valueOf("0"));
                newPO.setExtInfo("");
                newPO.setAddTime(new Date());
                newPO.setModTime(new Date());
            }
            sysUserRoleService.batchInsert(newPermsPO);
        }
        if (!CollectionUtils.isEmpty(oldRolesDto)) {
            List<Integer> oldIds = oldRolesDto.stream().map(SysUserRoleDTO::getId).collect(Collectors.toList());
            sysUserRoleService.batchDelete(oldIds);
        }
        return 1;
    }

    @Override
    public int updateLaseLoginTime(Integer id) {
        SysUserPO sysUserPO = sysUserPOMapper.selectByPrimaryKey(id);
        sysUserPO.setLastLoginTime(new Date());
        return sysUserPOMapper.updateByPrimaryKeySelective(sysUserPO);
    }
}
