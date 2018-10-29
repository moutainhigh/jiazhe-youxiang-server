package com.jiazhe.youxiang.base.realm;

import com.jiazhe.youxiang.server.biz.SysRoleBiz;
import com.jiazhe.youxiang.server.biz.SysRolePermissionBiz;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
import com.jiazhe.youxiang.server.biz.SysUserRoleBiz;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRolePermissionDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserRoleDTO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tu
 * @date 2018/10/20
 */
public class AuthRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    private SysUserBiz sysUserBiz;
    @Autowired
    private SysUserRoleBiz sysUserRoleBiz;
    @Autowired
    private SysRoleBiz sysRoleBiz;
    @Autowired
    private SysRolePermissionBiz sysRolePermissionBiz;

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("=========进入用户登录验证============");
        try {
            /*获取用户输入的token*/
            UsernamePasswordToken utoken = (UsernamePasswordToken) token;
            String loginname = utoken.getUsername();
            List<SysUserDTO> sysUserDTOList = sysUserBiz.findByLoginName(loginname);
            if (sysUserDTOList.size() == 1) {
                SysUserDTO sysUserDTO = sysUserDTOList.get(0);
                //若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
                ByteSource salt = ByteSource.Util.bytes(sysUserDTO.getSalt());
                //放入shiro.调用CredentialsMatcher检验密码
                return new SimpleAuthenticationInfo(sysUserDTO, sysUserDTO.getPassword(),
                        salt, this.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 授权
     *
     * @param principal
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        logger.info("=========进入用户授权============");
        //用户一个
        SysUserDTO sysUserDTO = (SysUserDTO) principal.getPrimaryPrincipal();
        List<String> permissionList = new ArrayList<String>();
        //一个用户对应一个User_Role_list
        List<SysUserRoleDTO> sysUserRoleDTOList = sysUserRoleBiz.findByUserId(sysUserDTO.getId());
        //遍历User_Role_list
        for (SysUserRoleDTO sysUserRoleDTO : sysUserRoleDTOList) {
            //每个User_Role对应一个Role
            SysRoleDTO sysRoleDTO = sysRoleBiz.findById(sysUserRoleDTO.getRoleId());
            //判断此角色是否删除
            if (null != sysRoleDTO) {
                //是超级用户
                if (sysRoleDTO.getIsSuper() == 1) {
                    permissionList.removeAll(permissionList);
                    permissionList.add("*");
                    break;
                } else {
                    //每个Role对应Role_Permission_list
                    List<SysRolePermissionDTO> sysRolePermissionDTOList = sysRolePermissionBiz.findByRoleId(sysRoleDTO.getId());
                    for (SysRolePermissionDTO tempSysRolePermissionDTO : sysRolePermissionDTOList) {
                        permissionList.add(tempSysRolePermissionDTO.getPermUrl());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return info;
    }
}
