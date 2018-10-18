package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PageFormatUtil;
import com.jiazhe.youxiang.base.util.ValidateUtils;
import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.biz.SysRoleBiz;
import com.jiazhe.youxiang.server.common.enums.RoleCodeEnum;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.service.SysRolePermissionService;
import com.jiazhe.youxiang.server.service.SysRoleService;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.SysRoleReq;
import com.jiazhe.youxiang.server.vo.req.sysrole.RoleIdReq;
import com.jiazhe.youxiang.server.vo.req.sysrole.RoleSaveReq;
import com.jiazhe.youxiang.server.vo.resp.sysrole.RoleWithPermResp;
import com.jiazhe.youxiang.server.vo.resp.sysrole.SysRoleResp;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 后台所有关于角色的接口
 * Created by tujia on 2018/10/14.
 */
@RestController
@RequestMapping("api/sysrole")
public class APISysRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysRoleController.class);

    @Autowired
    private SysRoleBiz sysRoleBiz;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @ApiOperation(value = "list", httpMethod = "GET", response = SysRoleResp.class, notes = "查询角色列表，并分页")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@ModelAttribute SysRoleReq req) {
        /*SysRoleResp result = new SysRoleResp();
        int count = sysRoleService.count(req);
        List<Map> maps = sysRoleService.getPageContent(req);
        result.setDataRows(PageFormatUtil.format(maps));
        result.setCurrPage(req.getPageNum());
        result.setTotalCount(count);
        result.setTotalPage((int) Math.ceil(count * 1.0 / req.getPageSize()));
        return result;*/
        return null;
    }

    @ApiOperation(value = "save", httpMethod = "POST", response = SysRoleResp.class, notes = "保存角色信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@ModelAttribute RoleSaveReq req) {
        /*参数检查*/
        if (null == req || Strings.isBlank(req.getName())) {
            return ResponseFactory.buildFailure(RoleCodeEnum.ROLE_INCOMPLETE_INFO.getCode(), RoleCodeEnum.ROLE_INCOMPLETE_INFO.getType(), RoleCodeEnum.ROLE_INCOMPLETE_INFO.getMessage());
        }
        /*判断是否重名*/
        List<SysRoleDTO> sysRoleDTOList = sysRoleBiz.findByName(req.getName());
        boolean roleHasExisted = 2 == sysRoleDTOList.size() || (sysRoleDTOList.size() == 1 && sysRoleDTOList.get(0).getId() != req.getId());
        /*角色是否已经存在*/
        if (roleHasExisted) {
            return ResponseFactory.buildFailure(RoleCodeEnum.ROLE_HAS_EXISTED.getCode(), RoleCodeEnum.ROLE_HAS_EXISTED.getType(), RoleCodeEnum.ROLE_HAS_EXISTED.getMessage());
        }
        if(null == req.getPermsStr()){
            req.setPermsStr("");
        }
        RoleWithPermDTO roleWithPermDTO = SysRoleAdapter.roleSaveVO2RoleWithPermDTO(req);
        sysRoleBiz.saveRoleWithPerm(roleWithPermDTO);
        return ResponseFactory.buildSuccess();
    }

    /**
     * 根据角色id查询角色信息（包括权限信息）
     * @param req
     * @return
     */
    @ApiOperation(value = "getbyid", httpMethod = "GET", response = SysRoleResp.class, notes = "根据id获取角色信息，带权限信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute RoleIdReq req) {
        //当前角色信息(包括权限字符串）DTO
        RoleWithPermDTO dto = sysRoleBiz.findRoleWithPermById(req.getRoleId());
        //将DTO转为respVO返回
        RoleWithPermResp result = SysRoleAdapter.roleWithPermDTO2RoleWithPermResp(dto);
        return ResponseFactory.buildResponse(result);
    }

    @ApiOperation(value = "delete", httpMethod = "GET", response = SysRoleResp.class, notes = "根据id删除角色信息")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(@ModelAttribute RoleIdReq req) {
        int count = sysRoleBiz.softDeleteById(req.getRoleId());
        return ResponseFactory.buildSuccess();
    }
}
