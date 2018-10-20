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

    List<SysUserDTO> findByName(String name, Paging paging);

    int deleteUserWithRole(Integer userId);

    int delete(Integer id);

    UserWithRoleDTO findUserWithRoleById(Integer id);

    List<SysUserDTO> findByName(String name);

    SysUserDTO findById(Integer id);

    int saveUserWithRole(boolean isAdd, SysUserDTO sysUserDTO, List<SysUserRoleDTO> newRolesDto, List<SysUserRoleDTO> oldRolesDto);
}
