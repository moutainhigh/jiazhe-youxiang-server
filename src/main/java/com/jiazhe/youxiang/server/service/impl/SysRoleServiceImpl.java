package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.adapter.SysRolePermissionAdapter;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePermissionPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysRolePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysRolePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.SysRolePermissionPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.domain.po.SysRolePOExample;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPOExample;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
import com.jiazhe.youxiang.server.service.SysRoleService;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tu
 *         Created by TU on 2018/10/15.
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRolePOMapper sysRolePOMapper;
    @Autowired
    private SysRolePermissionPOMapper sysRolePermissionPOMapper;
    @Autowired
    private SysRolePOManualMapper sysRolePOManualMapper;
    @Autowired
    private SysRolePermissionPOManualMapper sysRolePermissionPOManualMapper;

    @Override
    public int count(SysRoleReq req) {
        return sysRolePOManualMapper.count(req);
    }

    @Override
    public SysRoleDTO findById(int id) {
        SysRolePO sysRolePO = sysRolePOMapper.selectByPrimaryKey(id);
        SysRoleDTO sysRoleDto = SysRoleAdapter.sysRolePO2SysRoleDTO(sysRolePO);
        return sysRoleDto;
    }

    @Override
    public List<SysRoleDTO> findByName(String name) {
        SysRolePOExample sysRolePOExample = new SysRolePOExample();
        SysRolePOExample.Criteria criteria = sysRolePOExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePO> sysRolePOList = sysRolePOMapper.selectByExample(sysRolePOExample);
        return sysRolePOList.stream().map(SysRoleAdapter::sysRolePO2SysRoleDTO).collect(Collectors.toList());
    }

    @Override
    public int saveRoleWithPerm(boolean isAdd, SysRoleDTO sysRoleDTO, List<SysRolePermissionDTO> newPerms, List<SysRolePermissionDTO> oldPerms) {
        SysRolePO sysRolePO = SysRoleAdapter.sysRoleDTO2SysRolePO(sysRoleDTO);
        List<SysRolePermissionPO> newPermsPO = newPerms.stream().map(SysRolePermissionAdapter::DTO2PO).collect(Collectors.toList());
        List<SysRolePermissionPO> oldPermsPO = oldPerms.stream().map(SysRolePermissionAdapter::DTO2PO).collect(Collectors.toList());
        if (isAdd) {
            sysRolePO.setExtInfo("");
            sysRolePO.setAddTime(new Date());
            sysRolePO.setModTime(new Date());
            sysRolePO.setIsDeleted(Byte.valueOf("0"));
            sysRolePOManualMapper.insert(sysRolePO);
            for (SysRolePermissionPO temp : newPermsPO) {
                temp.setRoleId(sysRoleDTO.getId());
            }
        } else {
            sysRolePO.setModTime(new Date());
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
            sysRolePermissionPOManualMapper.batchInsert(newPermsPO);
        }
        if (!CollectionUtils.isEmpty(oldPermsPO)) {
            for (SysRolePermissionPO old : oldPermsPO) {
                old.setIsDeleted(Byte.valueOf("1"));
                old.setModTime(new Date());
            }
            sysRolePermissionPOManualMapper.batchUpdate(oldPermsPO);
        }
        return 1;
    }

    @Override
    public int softDeleteById(SysRoleDTO sysRoleDTO, List<SysRolePermissionDTO> perms) {
        if (!CollectionUtils.isEmpty(perms)) {
            List<SysRolePermissionPO> sysRolePermissionPOList = perms.stream().map(SysRolePermissionAdapter::DTO2PO).collect(Collectors.toList());
            sysRolePermissionPOManualMapper.batchUpdate(sysRolePermissionPOList);
        }
        SysRolePO sysRolePO = SysRoleAdapter.sysRoleDTO2SysRolePO(sysRoleDTO);
        sysRolePO.setIsDeleted(Byte.valueOf("1"));
        sysRolePO.setModTime(new Date());
        return sysRolePOMapper.updateByPrimaryKeySelective(sysRolePO);
    }


    @Override
    public RoleWithPermDTO findRoleWithPermById(Integer roleId) {
        SysRolePO sysRolePO = sysRolePOMapper.selectByPrimaryKey(roleId);
        SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
        SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePermissionPO> sysRolePermissionPOList = sysRolePermissionPOMapper.selectByExample(sysRolePermissionPOExample);
        StringBuilder perms = new StringBuilder();
        for (SysRolePermissionPO sysRolePermissionPO : sysRolePermissionPOList) {
            perms.append(sysRolePermissionPO.getPermUrl() + ",");
        }
        if (sysRolePermissionPOList.size() > 0) {
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
}
