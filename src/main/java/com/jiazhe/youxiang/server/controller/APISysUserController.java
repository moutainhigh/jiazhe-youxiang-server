package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.SysRoleAdapter;
import com.jiazhe.youxiang.server.adapter.SysUserAdapter;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
import com.jiazhe.youxiang.server.biz.SysUserRoleBiz;
import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RoleCodeEnum;
import com.jiazhe.youxiang.server.common.enums.UserCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.dto.sysrole.SysRoleDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.sysuser.UserWithRoleDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.sysrole.RolePageReq;
import com.jiazhe.youxiang.server.vo.req.sysuser.UserPageReq;
import com.jiazhe.youxiang.server.vo.req.sysuser.UserSaveReq;
import com.jiazhe.youxiang.server.vo.resp.sysrole.SysRoleResp;
import com.jiazhe.youxiang.server.vo.resp.sysuser.SysUserResp;
import com.jiazhe.youxiang.server.vo.resp.sysuser.UserWithRoleResp;
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

    @ApiOperation(value = "查询所有员工信息", httpMethod = "GET", response = SysUserResp.class, responseContainer = "List",notes = "查询所有员工信息")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public Object listAll() {
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findAll();
        return ResponseFactory.buildResponse(sysUserDTOList);
    }

    @ApiOperation(value = "分页查询员工信息", httpMethod = "GET", response = SysRoleResp.class, responseContainer = "List",notes = "分页查询员工信息")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute UserPageReq req) {
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum()-1)*req.getPageSize());
        paging.setLimit(req.getPageSize());
        List<SysUserDTO> sysUserDTOList = sysUserBiz.getList(req.getName(), paging);
        List<SysUserResp> sysUserRespList = sysUserDTOList.stream().map(SysUserAdapter::DTO2RespVO).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(sysUserRespList, paging);
    }

    @ApiOperation(value = "根据id删除员工信息（包含对应角色）", httpMethod = "POST", notes = "根据id删除员工信息（包含对应角色）")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@ModelAttribute IdReq req) {
        int count = sysUserBiz.deleteUserWithRole(req.getId());
        if (count != 1) {
            throw new CommonException(CommonCodeEnum.INTERNAL_ERROR.getCode(), CommonCodeEnum.INTERNAL_ERROR.getType(), "删除失败");
        }
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据id获取员工信息（包含角色）", httpMethod = "GET", response = UserWithRoleResp.class, notes = "根据id获取员工信息（包含角色）")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //当前员工信息(包括角色字符串）DTO
        UserWithRoleDTO dto = sysUserBiz.findUserWithRoleById(req.getId());
        //将DTO转为respVO返回
        UserWithRoleResp result = SysUserAdapter.userWithRoleDTO2UserWithRoleResp(dto);
        return ResponseFactory.buildResponse(result);
    }

    @ApiOperation(value = "【新建、修改】保存员工信息", httpMethod = "POST", notes = "【新建、修改】保存员工信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@ModelAttribute UserSaveReq req) {
        /*参数检查*/
        if (null == req || Strings.isBlank(req.getName())) {
            throw new CommonException(CommonCodeEnum.INTERNAL_ERROR.getCode(), CommonCodeEnum.INTERNAL_ERROR.getType(), "信息填写不完整");
        }
        if(Strings.isBlank((req.getRoleIds()))){
            throw new CommonException(UserCodeEnum.USER_Role_NOTCHOOSE.getCode(), UserCodeEnum.USER_Role_NOTCHOOSE.getType(), UserCodeEnum.USER_Role_NOTCHOOSE.getMessage());
        }
        /*判断是否重名，要将新建和修改区分开*/
        UserWithRoleDTO userWithRoleDTO = SysUserAdapter.userSaveReq2UserWithDTO(req);
        boolean roleHasExisted = sysUserBiz.userHasExisted(userWithRoleDTO);
        if (roleHasExisted) {
            throw new CommonException(UserCodeEnum.USER_HAS_EXISTED.getCode(),UserCodeEnum.USER_HAS_EXISTED.getType(), UserCodeEnum.USER_HAS_EXISTED.getMessage());
        }
        sysUserBiz.saveRoleWithPerm(userWithRoleDTO);
        return ResponseFactory.buildSuccess();
    }
}
