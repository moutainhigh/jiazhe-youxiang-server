package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.adapter.SysRolePermissionAdapter;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysRolePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePOMapper;
import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.domain.po.SysRolePOExample;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import com.jiazhe.youxiang.server.service.SysRoleService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

/**
 * @author Tu
 *         Created by TU on 2018/10/15.
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRolePOMapper sysRolePOMapper;
    @Autowired
    private SysRolePOManualMapper sysRolePOManualMapper;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Override
    public int deleteRoleWithPerms(Integer roleId) {
        List<SysRolePermissionDTO> sysRolePermissionDTOList = sysRolePermissionService.findByRoleId(roleId);
        List<Integer> ids = sysRolePermissionDTOList.stream().map(SysRolePermissionDTO::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(sysRolePermissionDTOList)) {
            sysRolePermissionService.batchDelete(ids);
        }
        return delete(roleId);
    }

    @Override
    public int delete(Integer id) {
        return sysRolePOManualMapper.delete(id);
    }

    @Override
    public RoleWithPermDTO findRoleWithPermById(Integer roleId) {
        SysRolePO sysRolePO = sysRolePOMapper.selectByPrimaryKey(roleId);
        List<SysRolePermissionDTO> sysRolePermissionDTOList = sysRolePermissionService.findByRoleId(roleId);
        StringBuilder perms = new StringBuilder();
        for (SysRolePermissionDTO dto : sysRolePermissionDTOList) {
            perms.append(dto.getPermUrl() + ",");
        }
        if (sysRolePermissionDTOList.size() > 0) {
            perms.deleteCharAt(perms.length() - 1);
        }
        RoleWithPermDTO roleWithPermDTO = new RoleWithPermDTO();
        roleWithPermDTO.setId(sysRolePO.getId());
        roleWithPermDTO.setIsSuper(sysRolePO.getIsSuper());
        roleWithPermDTO.setName(sysRolePO.getName());
        roleWithPermDTO.setPriority(sysRolePO.getPriority());
        roleWithPermDTO.setPermsStr(perms.toString());
        return roleWithPermDTO;
    }

    @Override
    public List<SysRoleDTO> findByName(String name) {
        SysRolePOExample sysRolePOExample = new SysRolePOExample();
        SysRolePOExample.Criteria criteria = sysRolePOExample.createCriteria();
        if (!Strings.isEmpty(name)) {
            criteria.andNameEqualTo(name);
        }
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePO> sysRolePOList = sysRolePOMapper.selectByExample(sysRolePOExample);
        return sysRolePOList.stream().map(SysRoleAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public SysRoleDTO findById(int id) {
        SysRolePO sysRolePO = sysRolePOMapper.selectByPrimaryKey(id);
        return SysRoleAdapter.PO2DTO(sysRolePO);
    }

    @Override
    public int saveRoleWithPerm(boolean isAdd, SysRoleDTO sysRoleDTO, List<SysRolePermissionDTO> newPermsDto, List<SysRolePermissionDTO> oldPermsDto) {
        SysRolePO sysRolePO = SysRoleAdapter.DTO2PO(sysRoleDTO);
        sysRolePO.setModTime(new Date());
        List<SysRolePermissionPO> newPermsPO = newPermsDto.stream().map(SysRolePermissionAdapter::DTO2PO).collect(Collectors.toList());
        if (isAdd) {
            sysRolePO.setExtInfo("");
            sysRolePO.setAddTime(new Date());
            sysRolePO.setIsDeleted(Byte.valueOf("0"));
            sysRolePOManualMapper.insert(sysRolePO);
        } else {
            sysRolePOMapper.updateByPrimaryKeySelective(sysRolePO);
        }
        if (!CollectionUtils.isEmpty(newPermsPO)) {
            for (SysRolePermissionPO newPO : newPermsPO) {
                newPO.setRoleId(sysRolePO.getId());
                newPO.setIsDeleted(Byte.valueOf("0"));
                newPO.setExtInfo("");
                newPO.setAddTime(new Date());
                newPO.setModTime(new Date());
            }
            sysRolePermissionService.batchInsert(newPermsPO);
        }
        if (!CollectionUtils.isEmpty(oldPermsDto)) {
            List<Integer> oldIds = oldPermsDto.stream().map(SysRolePermissionDTO::getId).collect(Collectors.toList());
            sysRolePermissionService.batchDelete(oldIds);
        }
        return 1;
    }

    @Override
    public List<SysRoleDTO> findAll() {
        SysRolePOExample sysRolePOExample = new SysRolePOExample();
        SysRolePOExample.Criteria criteria = sysRolePOExample.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePO> sysRolePOList = sysRolePOMapper.selectByExample(sysRolePOExample);
        return sysRolePOList.stream().map(SysRoleAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public List<SysRoleDTO> findByName(String name, Paging paging) {
        Integer count = sysRolePOManualMapper.count(name);
        List<SysRolePO> sysRolePOList = sysRolePOManualMapper.query(name, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        if (paging.getLimit() + paging.getOffset() >= count) {
            paging.setHasMore(false);
        }
        return sysRolePOList.stream().map(SysRoleAdapter::PO2DTO).collect(Collectors.toList());
    }
}
