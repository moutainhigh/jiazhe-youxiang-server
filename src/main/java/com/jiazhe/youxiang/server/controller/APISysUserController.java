package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.adapter.SysUserAdapter;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
import com.jiazhe.youxiang.server.biz.SysUserRoleBiz;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.sysrole.RolePageReq;
import com.jiazhe.youxiang.server.vo.req.sysuser.UserPageReq;
import com.jiazhe.youxiang.server.vo.resp.sysrole.SysRoleResp;
import com.jiazhe.youxiang.server.vo.resp.sysuser.SysUserResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
@RestController
@RequestMapping("api/sysuser")
public class APISysUserController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysUserController.class);

    @Autowired
    private SysUserBiz sysUserBiz;

    @ApiOperation(value = "listall", httpMethod = "GET", response = SysUserResp.class, responseContainer = "List",notes = "查询所有用户信息")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public Object listAll() {
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findAll();
        return ResponseFactory.buildResponse(sysUserDTOList);
    }

    @ApiOperation(value = "listpage", httpMethod = "GET", response = SysRoleResp.class, responseContainer = "List",notes = "分页查询用户信息")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute UserPageReq req) {
        Paging paging = new Paging();
        paging.setOffset(req.getOffset());
        paging.setLimit(req.getLimit());
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findByName(req.getName(), paging);
        List<SysUserResp> sysUserRespList = sysUserDTOList.stream().map(SysUserAdapter::DTO2RespVO).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(sysUserRespList, paging);
    }
}
