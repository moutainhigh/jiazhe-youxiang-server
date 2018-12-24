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

    /**
     * 查询所有员工信息
     * @return
     */
    List<SysUserDTO> findAll();

    /**
     * 通过员工登录名、显示名、分页参数分页查询员工信息
     * @param loginName
     * @param displayName
     * @param paging
     * @return
     */
    List<SysUserDTO> getList(String loginName,String displayName, Paging paging);

    /**
     * 根据用户id删除用户和绑定的角色信息
     * @param userId
     */
    void deleteUserWithRole(Integer userId);

    UserWithRoleDTO findUserWithRoleById(Integer id);

    SysUserDTO findById(Integer id);

    /**
     * 保存用户信息
     * @param isAdd
     * @param sysUserDTO
     * @param newRolesDto
     * @param oldRolesDto
     */
    void saveUserWithRole(boolean isAdd, SysUserDTO sysUserDTO, List<SysUserRoleDTO> newRolesDto, List<SysUserRoleDTO> oldRolesDto);

    /**
     * 通过登录名查找员工信息
     * @param loginName
     * @return
     */
    List<SysUserDTO> findByLoginName(String loginName);

    void updateLastLoginInfo(Integer userId, String ipAdrress);

    /**
     * 修改密码
     * @param id
     * @param newPassword
     */
    void changePassword(Integer id, String newPassword);

    /**
     * 根据id查找员工
     * @param id
     * @return
     */
    SysUserDTO getById(Integer id);
}
