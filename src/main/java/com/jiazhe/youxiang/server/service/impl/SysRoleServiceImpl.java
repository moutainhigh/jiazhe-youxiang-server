package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
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
import com.jiazhe.youxiang.server.service.SysRoleService;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**@author Tu
 * Created by TU on 2018/10/15.
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
    public List<Map> getPageContent(SysRoleReq req) {
        return sysRolePOManualMapper.getPageContent(req);
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
    public int saveRoleWithPerm(boolean isAdd, SysRolePO sysRolePO, List<SysRolePermissionPO> newPerms,List<SysRolePermissionPO> oldPerms) {
        if (isAdd) {
            sysRolePOManualMapper.insert(sysRolePO);
            for (SysRolePermissionPO temp : newPerms) {
                temp.setRoleId(sysRolePO.getId());
            }
        } else {
            sysRolePOMapper.updateByPrimaryKeySelective(sysRolePO);
        }
        if (newPerms != null && newPerms.size() > 0) {
            sysRolePermissionPOManualMapper.batchInsert(newPerms);
        }
        if (newPerms != null && oldPerms.size() > 0) {
            for (SysRolePermissionPO old : oldPerms) {
                old.setIsDeleted(Byte.valueOf("1"));
                old.setModTime(new Date());
            }
            sysRolePermissionPOManualMapper.batchUpdate(oldPerms);
        }
        return 1;
    }

    @Override
    public int update(SysRolePO sysRolePO) {
        return sysRolePOMapper.updateByPrimaryKeySelective(sysRolePO);
    }

    @Override
    public int insert(SysRolePO sysRolePO) {
        return sysRolePOManualMapper.insert(sysRolePO);
    }

    @Override
    public int softDeleteById(SysRolePO sysRolePO, List<SysRolePermissionPO> perms) {
        if(null!=perms&&perms.size()>0){
            sysRolePermissionPOManualMapper.batchUpdate(perms);
        }
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
        RoleWithPermDTO roleAndPermissionDTO = new RoleWithPermDTO();
        roleAndPermissionDTO.setId(sysRolePO.getId());
        roleAndPermissionDTO.setIsSuper(sysRolePO.getIsSuper());
        roleAndPermissionDTO.setName(sysRolePO.getName());
        roleAndPermissionDTO.setPriority(sysRolePO.getPriority());
        roleAndPermissionDTO.setPermsStr(perms.toString());
        return roleAndPermissionDTO;
    }
}
