package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.base.util.EncryptPasswordUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;
import com.jiazhe.youxiang.server.dto.sysuser.UserWithRoleDTO;
import com.jiazhe.youxiang.server.service.SysUserService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.perms;

/**
 * @author tu
 * @description：
 * @date 2018/10/20
 */
@Service("sysUserBiz")
public class SysUserBiz {

    @Autowired
    private SysUserRoleBiz sysUserRoleBiz;
    @Autowired
    private SysUserService sysUserService ;

    public List<SysUserDTO> findAll() {
        List<SysUserDTO> sysUserDTOList = sysUserService.findAll();
        return  sysUserDTOList;
    }

    public List<SysUserDTO> getList(String loginName, String displayName,Paging paging) {
        return sysUserService.getList(loginName,displayName,paging);
    }

    public void deleteUserWithRole(Integer userId) {
        sysUserService.deleteUserWithRole(userId);
    }

    public UserWithRoleDTO findUserWithRoleById(Integer id) {
        return sysUserService.findUserWithRoleById(id);
    }

    public boolean userHasExisted(UserWithRoleDTO userWithRoleDTO) {
        List<SysUserDTO> sysUserDTOList = sysUserService.findByLoginName(userWithRoleDTO.getLoginName());
        return (2 == sysUserDTOList.size()) || (sysUserDTOList.size() == 1 && !sysUserDTOList.get(0).getId().equals(userWithRoleDTO.getId()));
    }

    public void saveRoleWithPerm(UserWithRoleDTO userWithRoleDTO) {
        /*判断是新建还是修改，id=0为新建，其他为修改*/
        boolean isAdd = userWithRoleDTO.getId() == 0;
        /*用户信息DTO*/
        SysUserDTO sysUserDTO;
        /*新添加的角色DTO*/
        List<SysUserRoleDTO> newRolesDto = new ArrayList<SysUserRoleDTO>();
        /*修改后减少的权限DTO*/
        List<SysUserRoleDTO> oldRolesDto = new ArrayList<SysUserRoleDTO>();
        String[] roleIds = Strings.isBlank(userWithRoleDTO.getRoleIds()) ? null : userWithRoleDTO.getRoleIds().split(",");
        if (isAdd){
            sysUserDTO = new SysUserDTO();
            String salt = RandomUtil.generateSalt(6);
            sysUserDTO.setSalt(salt);
            sysUserDTO.setPassword(EncryptPasswordUtil.encrypt(salt,userWithRoleDTO.getPassword()));
            for (String roleId : roleIds) {
                SysUserRoleDTO sysUserRoleDTO = new SysUserRoleDTO();
                sysUserRoleDTO.setRoleId(Integer.valueOf(roleId));
                newRolesDto.add(sysUserRoleDTO);
            }
        } else {
            sysUserDTO = sysUserService.findById(userWithRoleDTO.getId());
            /*修改前的角色*/
            oldRolesDto = sysUserRoleBiz.findByUserId(userWithRoleDTO.getId());
            if (null != roleIds) {
                /*遍历新的角色String[]*/
                for (String id : roleIds) {
                    boolean has = false;
                    /*判断该角色是否已经存在*/
                    for (SysUserRoleDTO temp : oldRolesDto) {
                        if (temp.getRoleId().equals(Integer.valueOf(id))) {
                                /*已经存在，则移除*/
                            Iterator<SysUserRoleDTO> iterator = oldRolesDto.iterator();
                            while (iterator.hasNext()) {
                                SysUserRoleDTO temp1 = iterator.next();
                                if (temp.getId().equals(temp1.getId())) {
                                    iterator.remove();
                                }
                            }
                            has = true;
                            break;
                        }
                    }
                    if (!has) {
                        SysUserRoleDTO sysUserRoleDTO = new SysUserRoleDTO();
                        sysUserRoleDTO.setUserId(userWithRoleDTO.getId());
                        sysUserRoleDTO.setRoleId(Integer.valueOf(id));
                        newRolesDto.add(sysUserRoleDTO);
                    }
                }
            }
        }
        sysUserDTO.setMobile(userWithRoleDTO.getMobile());
        sysUserDTO.setDisplayName(userWithRoleDTO.getDisplayName());
        sysUserDTO.setLoginName(userWithRoleDTO.getLoginName());
        sysUserService.saveUserWithRole(isAdd, sysUserDTO, newRolesDto, oldRolesDto);
    }

    public List<SysUserDTO> findByLoginName(String loginName) {
        return sysUserService.findByLoginName(loginName);
    }

    public void updateLastLoginInfo(Integer userId , String ipAdrress) {
        sysUserService.updateLaseLoginInfo(userId,ipAdrress);
    }

    public void changePassword(Integer id, String newPassword) {
        sysUserService.changePassword(id,newPassword);
    }
}
