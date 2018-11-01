package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.SysUserPO;
import com.jiazhe.youxiang.server.domain.po.SysUserPOExample;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;
import com.jiazhe.youxiang.server.dto.sysuser.UserWithRoleDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author tu
 * @date 2018/10/15.
 */
public interface SysUserService {

    List<SysUserDTO> findAll();

    List<SysUserDTO> getList(String loginName,String displayName, Paging paging);

    void deleteUserWithRole(Integer userId);

    UserWithRoleDTO findUserWithRoleById(Integer id);

    SysUserDTO findById(Integer id);

    void saveUserWithRole(boolean isAdd, SysUserDTO sysUserDTO, List<SysUserRoleDTO> newRolesDto, List<SysUserRoleDTO> oldRolesDto);

    List<SysUserDTO> findByLoginName(String loginName);

    void updateLaseLoginInfo(Integer userId, String ipAdrress);

    void changePassword(Integer id, String newPassword);
}
