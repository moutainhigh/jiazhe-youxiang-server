package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.SysUserService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<SysUserDTO> findByName(String name, Paging paging) {
        return sysUserService.findByName(name,paging);
    }
}
