package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
import com.jiazhe.youxiang.server.service.SysRoleService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author TU
 * @date 2018/10/15
 */
@Service("sysRoleBiz")
public class SysRoleBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleBiz.class);

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolePermissionBiz sysRolePermissionBiz;

    /**
     * 根据角色id删除角色和权限
     *
     * @param roleId
     * @return
     */
    public void deleteRoleWithPerms(Integer roleId) {
        sysRoleService.deleteRoleWithPerms(roleId);
    }

    public RoleWithPermDTO findRoleWithPermById(Integer roleId) {
        return sysRoleService.findRoleWithPermById(roleId);
    }

    public boolean roleHasExisted(RoleWithPermDTO roleWithPermDTO) {
        List<SysRoleDTO> sysRoleDTOList = sysRoleService.findByName(roleWithPermDTO.getName());
        return (2 == sysRoleDTOList.size()) || (sysRoleDTOList.size() == 1 && !sysRoleDTOList.get(0).getId().equals(roleWithPermDTO.getId()));
    }

    public void saveRoleWithPerm(RoleWithPermDTO roleWithPermDTO) {
        /*判断是新建还是修改，id=0为新建，其他为修改*/
        boolean isAdd = roleWithPermDTO.getId() == 0;
        /*角色信息DTO*/
        SysRoleDTO sysRoleDTO;
        /*新添加的权限DTO*/
        List<SysRolePermissionDTO> newPermsDto = new ArrayList<SysRolePermissionDTO>();
        /*修改后减少的权限DTO*/
        List<SysRolePermissionDTO> oldPermsDto = new ArrayList<SysRolePermissionDTO>();
        String[] perms = Strings.isBlank(roleWithPermDTO.getPermsStr()) ? null : roleWithPermDTO.getPermsStr().split(",");
        if (isAdd) {
            sysRoleDTO = new SysRoleDTO();
            if (roleWithPermDTO.getIsSuper() == 0) {
                for (String perm : perms) {
                    SysRolePermissionDTO sysRolePermissionDTO = new SysRolePermissionDTO();
                    sysRolePermissionDTO.setPermUrl(perm);
                    newPermsDto.add(sysRolePermissionDTO);
                }
            }
        } else {
            sysRoleDTO = sysRoleService.findById(roleWithPermDTO.getId());
            /*修改前的权限*/
            oldPermsDto = sysRolePermissionBiz.findByRoleId(roleWithPermDTO.getId());
            if (roleWithPermDTO.getIsSuper() == 0) {
                if (null != perms) {
                        /*遍历新的权限String[]*/
                    for (String perm : perms) {
                        boolean has = false;
                            /*判断该权限是否已经存在*/
                        for (SysRolePermissionDTO temp : oldPermsDto) {
                            if (temp.getPermUrl().equals(perm)) {
                                /*已经存在，则移除*/
                                Iterator<SysRolePermissionDTO> iterator = oldPermsDto.iterator();
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
                            newPermsDto.add(sysRolePermissionDTO);
                        }
                    }
                }
            }
        }
        sysRoleDTO.setName(roleWithPermDTO.getName());
        sysRoleDTO.setIsSuper(Byte.valueOf(roleWithPermDTO.getIsSuper()));
        sysRoleDTO.setPriority(Integer.valueOf(roleWithPermDTO.getPriority()));
        sysRoleService.saveRoleWithPerm(isAdd, sysRoleDTO, newPermsDto, oldPermsDto);
    }

    public List<SysRoleDTO> findAll() {
        return sysRoleService.findAll();
    }

    public List<SysRoleDTO> getList(String name, Paging paging) {
        return sysRoleService.getList(name, paging);
    }

    public SysRoleDTO findById(Integer id) {
        return sysRoleService.findById(id);
    }
}