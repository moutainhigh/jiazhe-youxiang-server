package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.biz.SysRoleBiz;
import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RoleCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.common.exceptions.RoleException;
import com.jiazhe.youxiang.server.dto.sysrole.RoleWithPermDTO;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.sysrole.RolePageReq;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * 所有关于角色的接口
 * @author TU
 * @date 2018/10/14.
 */
@RestController
@RequestMapping("api/sysrole")
public class APISysRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysRoleController.class);

    @Autowired
    private SysRoleBiz sysRoleBiz;

    @ApiOperation(value = "查询所有角色信息", httpMethod = "GET", response = SysRoleResp.class, responseContainer = "List",notes = "查询所有角色信息")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public Object listAll() {
        List<SysRoleDTO> sysRoleDTOList = sysRoleBiz.findAll();
        List<SysRoleResp> sysRoleRespList = sysRoleDTOList.stream().map(SysRoleAdapter::DTO2RespVO).collect(Collectors.toList());
        return ResponseFactory.buildResponse(sysRoleRespList);
    }

    @ApiOperation(value = "分页查询角色信息", httpMethod = "GET", response = SysRoleResp.class, responseContainer = "List",notes = "分页查询角色信息")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute RolePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<SysRoleDTO> sysRoleDTOList = sysRoleBiz.getList(req.getName(), paging);
        List<SysRoleResp> sysRoleRespList = sysRoleDTOList.stream().map(SysRoleAdapter::DTO2RespVO).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(sysRoleRespList, paging);
    }

    @ApiOperation(value = "根据id删除角色信息（包含权限）", httpMethod = "POST",notes = "根据id删除角色信息（包含权限）")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        sysRoleBiz.deleteRoleWithPerms(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据id获取角色信息（包含权限）", httpMethod = "GET", response = RoleWithPermResp.class, notes = "根据id获取角色信息（包含权限）")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //当前角色信息(包括权限字符串）DTO
        RoleWithPermDTO dto = sysRoleBiz.findRoleWithPermById(req.getId());
        //将DTO转为respVO返回
        RoleWithPermResp result = SysRoleAdapter.roleWithPermDTO2RoleWithPermResp(dto);
        return ResponseFactory.buildResponse(result);
    }

    @ApiOperation(value = "【新建、修改】保存角色信息", httpMethod = "POST", notes = "【新建、修改】保存角色信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@ModelAttribute RoleSaveReq req) {
        /*参数检查*/
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getId());
        CommonValidator.validateNull(req.getName(),new RoleException(RoleCodeEnum.ROLE_NAME_IS_NULL));
        /*非管理员，还不带权限字符串*/
        if (req.getIsSuper() == 0 && Strings.isBlank(req.getPermsStr())) {
            throw new RoleException(RoleCodeEnum.ROLE_PERMISSION_NOTCHOOSE);
        }
        /*判断是否重名，要将新建和修改区分开*/
        RoleWithPermDTO roleWithPermDTO = SysRoleAdapter.roleSaveReq2RoleWithPremDTO(req);
        boolean roleHasExisted = sysRoleBiz.roleHasExisted(roleWithPermDTO);
        if (roleHasExisted) {
            throw new RoleException(RoleCodeEnum.ROLE_NAME_HAS_EXISTED);
        }
        sysRoleBiz.saveRoleWithPerm(roleWithPermDTO);
        return ResponseFactory.buildSuccess();
    }
}
