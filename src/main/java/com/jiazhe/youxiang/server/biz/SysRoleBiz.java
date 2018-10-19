package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
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
     * 保存角色信息，新建或修改（带权限字符串）
     */
    public int saveRoleWithPerm(RoleWithPermDTO roleWithPermDTO) {
        /*判断是新建还是修改，id=0为新建，其他为修改*/
        boolean isAdd = roleWithPermDTO.getId() == 0;
        /*角色信息DTO*/
        SysRoleDTO sysRoleDTO;
        /*新添加的权限DTO*/
        List<SysRolePermissionDTO> newPerms = new ArrayList<SysRolePermissionDTO>();
        /*修改后减少的权限DTO*/
        List<SysRolePermissionDTO> oldPerms = new ArrayList<SysRolePermissionDTO>();
        String[] perms = roleWithPermDTO.getPermsStr().length() < 1 ? null : roleWithPermDTO.getPermsStr().split(",");
        if (isAdd) {
            sysRoleDTO = new SysRoleDTO();
            if (roleWithPermDTO.getIsSuper() == 0) {
                for (String perm : perms) {
                    SysRolePermissionDTO sysRolePermissionDTO = new SysRolePermissionDTO();
                    sysRolePermissionDTO.setPermUrl(perm);
                    sysRolePermissionDTO.setExtInfo("");
                    sysRolePermissionDTO.setIsDeleted(Byte.valueOf("0"));
                    sysRolePermissionDTO.setAddTime(new Date());
                    sysRolePermissionDTO.setModTime(new Date());
                    newPerms.add(sysRolePermissionDTO);
                }
            }
        } else {
            sysRoleDTO = sysRoleService.findById(roleWithPermDTO.getId());
            /*修改前的权限*/
            oldPerms = sysRolePermissionService.findByRoleId(roleWithPermDTO.getId());
            if(roleWithPermDTO.getIsSuper() == 0) {
                if (null != perms) {
                        /*遍历新的权限String[]*/
                    for (String perm : perms) {
                        boolean has = false;
                            /*判断该权限是否已经存在*/
                        for (SysRolePermissionDTO temp : oldPerms) {
                            if (temp.getPermUrl().equals(perm)) {
                                /*已经存在，则移除*/
                                Iterator<SysRolePermissionDTO> iterator = oldPerms.iterator();
                                while (iterator.hasNext()) {
                                    SysRolePermissionDTO temp1 = iterator.next();
                                    if (temp.getId().equals(temp1.getId())) {
                                        iterator.remove();
                                    }
                                }
                                has = true;
                                break;
                            }
                        }
                        if (!has) {
                            SysRolePermissionDTO sysRolePermissionDTO = new SysRolePermissionDTO();
                            sysRolePermissionDTO.setRoleId(roleWithPermDTO.getId());
                            sysRolePermissionDTO.setPermUrl(perm);
                            sysRolePermissionDTO.setExtInfo("");
                            sysRolePermissionDTO.setIsDeleted(Byte.valueOf("0"));
                            sysRolePermissionDTO.setAddTime(new Date());
                            sysRolePermissionDTO.setModTime(new Date());
                            newPerms.add(sysRolePermissionDTO);
                        }
                    }
                }
            }
        }
        sysRoleDTO.setName(roleWithPermDTO.getName());
        sysRoleDTO.setIsSuper(Byte.valueOf(roleWithPermDTO.getIsSuper()));
        sysRoleDTO.setPriority(Integer.valueOf(roleWithPermDTO.getPriority()));
        sysRoleDTO.setModTime(new Date());
        return sysRoleService.saveRoleWithPerm(isAdd, sysRoleDTO, newPerms, oldPerms);
    }

    /**
     * 根据id软删除角色，同时软删除所有的权限信息
     *
     * @param roleId
     * @return
     */
    public int softDeleteById(Integer roleId) {
        SysRoleDTO sysRoleDTO = sysRoleService.findById(roleId);
        sysRoleDTO.setIsDeleted(Byte.valueOf("1"));
        sysRoleDTO.setModTime(new Date());
        //查询所有权限字符串
        List<SysRolePermissionDTO> perms = sysRolePermissionService.findByRoleId(roleId);
        for (SysRolePermissionDTO temp : perms) {
            temp.setIsDeleted(Byte.valueOf("1"));
            temp.setModTime(new Date());
        }
        return sysRoleService.softDeleteById(sysRoleDTO, perms);
    }

    public boolean roleHasExisted(SysRoleDTO sysRoleDto) {
        List<SysRoleDTO> sysRoleDTOList = sysRoleService.findByName(sysRoleDto.getName());
        return 2 == sysRoleDTOList.size() || (sysRoleDTOList.size() == 1 && sysRoleDTOList.get(0).getId() != sysRoleDto.getId());
    }
}
