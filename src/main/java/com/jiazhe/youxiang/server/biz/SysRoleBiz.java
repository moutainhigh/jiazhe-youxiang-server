package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.domain.po.SysRolePO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPO;
import com.jiazhe.youxiang.server.domain.po.SysRolePermissionPOExample;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import com.jiazhe.youxiang.server.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TU on 2018/10/15.
 */
@Service("sysRoleBiz")
public class SysRoleBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleBiz.class);

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 根据角色id，查询角色信息，包括权限字符串
     */
    public RoleWithPermDTO findRoleWithPermById(Integer roleId) {
        return sysRoleService.findRoleWithPermById(roleId);
    }

    /**
     * 根据角色id，查询角色信息
     */
    public SysRoleDTO findById(Integer roleId) {
        return sysRoleService.findById(roleId);
    }

    /**
     * 根据角色名称，查询角色列表信息
     */
    public List<SysRoleDTO> findByName(String name) {
        return sysRoleService.findByName(name);
    }

    /**
     * 保存角色信息，新建或修改（带权限字符串）
     */
    public int saveRoleWithPerm(RoleWithPermDTO roleWithPermDTO) {
        /*判断是新建还是修改，id=0为新建，其他为修改*/
        boolean isAdd = roleWithPermDTO.getId() == 0;
        /*角色信息*/
        SysRolePO sysRolePO;
        /*新添加的权限*/
        List<SysRolePermissionPO> newPerms = new ArrayList<SysRolePermissionPO>();
        /*修改后减少的权限*/
        List<SysRolePermissionPO> oldPerms = new ArrayList<SysRolePermissionPO>();
        String[] perms = roleWithPermDTO.getPermsStr().length() < 1 ? null : roleWithPermDTO.getPermsStr().split(",");
        if (isAdd) {
            sysRolePO = new SysRolePO();
            sysRolePO.setExtInfo("");
            sysRolePO.setIsDeleted(Byte.valueOf("0"));
            sysRolePO.setAddTime(new Date());
            if (roleWithPermDTO.getIsSuper() == 0) {
                for (String perm : perms) {
                    SysRolePermissionPO sysRolePermissionPO = new SysRolePermissionPO();
                    sysRolePermissionPO.setPermUrl(perm);
                    sysRolePermissionPO.setExtInfo("");
                    sysRolePermissionPO.setIsDeleted(Byte.valueOf("0"));
                    sysRolePermissionPO.setAddTime(new Date());
                    sysRolePermissionPO.setModTime(new Date());
                    newPerms.add(sysRolePermissionPO);
                }
            }
        } else {
            SysRoleDTO sysRoleDTO = sysRoleService.findById(roleWithPermDTO.getId());
            sysRolePO = SysRoleAdapter.sysRoleDTO2SysRolePO(sysRoleDTO);
            SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
            SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
            criteria.andRoleIdEqualTo(roleWithPermDTO.getId());
            criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
                /*修改前的权限*/
            oldPerms = sysRolePermissionService.selectByExample(sysRolePermissionPOExample);
            if (null != perms) {
                    /*遍历新的权限String[]*/
                for (String perm : perms) {
                    boolean has = false;
                        /*判断该权限是否已经存在*/
                    for (SysRolePermissionPO temp : oldPerms) {
                        if (temp.getPermUrl().equals(perm)) {
                                /*已经存在，则移除*/
                            Iterator<SysRolePermissionPO> iterator = oldPerms.iterator();
                            while (iterator.hasNext()) {
                                SysRolePermissionPO temp1 = iterator.next();
                                if (temp.getId().equals(temp1.getId())) {
                                    iterator.remove();
                                }
                            }
                            has = true;
                            break;
                        }
                    }
                    if (!has) {
                        SysRolePermissionPO sysRolePermissionPO = new SysRolePermissionPO();
                        sysRolePermissionPO.setRoleId(roleWithPermDTO.getId());
                        sysRolePermissionPO.setPermUrl(perm);
                        sysRolePermissionPO.setExtInfo("");
                        sysRolePermissionPO.setIsDeleted(Byte.valueOf("0"));
                        sysRolePermissionPO.setAddTime(new Date());
                        sysRolePermissionPO.setModTime(new Date());
                        newPerms.add(sysRolePermissionPO);
                    }
                }
            }
        }
        sysRolePO.setName(roleWithPermDTO.getName());
        sysRolePO.setIsSuper(Byte.valueOf(roleWithPermDTO.getIsSuper()));
        sysRolePO.setPriority(Integer.valueOf(roleWithPermDTO.getPriority()));
        sysRolePO.setModTime(new Date());
        return sysRoleService.saveRoleWithPerm(isAdd, sysRolePO, newPerms, oldPerms);
    }

    /**
     * 根据id软删除角色，同时软删除所有的权限信息
     * @param roleId
     * @return
     */
    public int softDeleteById(Integer roleId) {
        SysRoleDTO sysRoleDTO = sysRoleService.findById(roleId);
        SysRolePO sysRolePO = SysRoleAdapter.sysRoleDTO2SysRolePO(sysRoleDTO);
        sysRolePO.setIsDeleted(Byte.valueOf("1"));
        sysRolePO.setModTime(new Date());
        //查询所有权限字符串
        SysRolePermissionPOExample sysRolePermissionPOExample = new SysRolePermissionPOExample();
        SysRolePermissionPOExample.Criteria criteria = sysRolePermissionPOExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        List<SysRolePermissionPO> perms = sysRolePermissionService.selectByExample(sysRolePermissionPOExample);
        for (SysRolePermissionPO temp : perms) {
            temp.setIsDeleted(Byte.valueOf("1"));
            temp.setModTime(new Date());
        }
        return sysRoleService.softDeleteById(sysRolePO,perms);
    }
}
