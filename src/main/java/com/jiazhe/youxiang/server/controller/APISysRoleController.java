package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.biz.SysRoleBiz;
import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RoleCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
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
 * 后台所有关于角色的接口
 *
 * @author TU
 * @date 2018/10/14.
 */
@RestController
@RequestMapping("api/sysrole")
public class APISysRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysRoleController.class);

    @Autowired
    private SysRoleBiz sysRoleBiz;

    @ApiOperation(value = "listall", httpMethod = "GET", response = SysRoleResp.class, responseContainer = "List",notes = "查询所有角色信息")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public Object listAll() {
        List<SysRoleDTO> sysRoleDTOList = sysRoleBiz.findAll();
        List<SysRoleResp> sysRoleRespList = sysRoleDTOList.stream().map(SysRoleAdapter::DTO2RespVO).collect(Collectors.toList());
        return ResponseFactory.buildResponse(sysRoleRespList);
    }

    @ApiOperation(value = "listpage", httpMethod = "GET", response = SysRoleResp.class, responseContainer = "List",notes = "分页查询角色信息")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute RolePageReq req) {
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum()-1)*req.getPageSize());
        paging.setLimit(req.getPageSize());
        List<SysRoleDTO> sysRoleDTOList = sysRoleBiz.findByName(req.getName(), paging);
        List<SysRoleResp> sysRoleRespList = sysRoleDTOList.stream().map(SysRoleAdapter::DTO2RespVO).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(sysRoleRespList, paging);
    }

    @ApiOperation(value = "delete", httpMethod = "POST", response = SysRoleResp.class, notes = "根据id删除角色信息（包含权限）")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@ModelAttribute IdReq req) {
        int count = sysRoleBiz.deleteRoleWithPerms(req.getId());
        if (count != 1) {
            throw new CommonException(CommonCodeEnum.INTERNAL_ERROR.getCode(), CommonCodeEnum.INTERNAL_ERROR.getType(), "删除失败");
        }
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "getbyid", httpMethod = "GET", response = SysRoleResp.class, notes = "根据id获取角色信息（包含权限）")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //当前角色信息(包括权限字符串）DTO
        RoleWithPermDTO dto = sysRoleBiz.findRoleWithPermById(req.getId());
        //将DTO转为respVO返回
        RoleWithPermResp result = SysRoleAdapter.roleWithPermDTO2RoleWithPermResp(dto);
        return ResponseFactory.buildResponse(result);
    }

    @ApiOperation(value = "save", httpMethod = "POST", response = SysRoleResp.class, notes = "保存角色信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@ModelAttribute RoleSaveReq req) {
        /*参数检查*/
        if (null == req || Strings.isBlank(req.getName())) {
            throw new CommonException(RoleCodeEnum.ROLE_INCOMPLETE_INFO.getCode(),RoleCodeEnum.ROLE_INCOMPLETE_INFO.getType(), RoleCodeEnum.ROLE_INCOMPLETE_INFO.getMessage());
        }
        /*非管理员，还不带权限字符串*/
        if (req.getIsSuper() == 0 && null == req.getPermsStr()) {
            throw new CommonException(RoleCodeEnum.ROLE_PERMISSION_NOTCHOOSE.getCode(), RoleCodeEnum.ROLE_PERMISSION_NOTCHOOSE.getType(), RoleCodeEnum.ROLE_PERMISSION_NOTCHOOSE.getMessage());
        }
        /*判断是否重名，要将新建和修改区分开*/
        RoleWithPermDTO roleWithPermDTO = SysRoleAdapter.roleSaveReq2RoleWithPremDTO(req);
        boolean roleHasExisted = sysRoleBiz.roleHasExisted(roleWithPermDTO);
        if (roleHasExisted) {
            throw new CommonException(RoleCodeEnum.ROLE_HAS_EXISTED.getCode(), RoleCodeEnum.ROLE_HAS_EXISTED.getType(), RoleCodeEnum.ROLE_HAS_EXISTED.getMessage());
        }
        sysRoleBiz.saveRoleWithPerm(roleWithPermDTO);
        return ResponseFactory.buildSuccess();
    }
}
