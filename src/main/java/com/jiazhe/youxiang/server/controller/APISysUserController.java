package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.EncryptPasswordUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.base.util.ValidateUtils;
import com.jiazhe.youxiang.server.adapter.SysUserAdapter;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.UserCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.UserException;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.sysuser.UserWithRoleDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.sysuser.ChangePasswordReq;
import com.jiazhe.youxiang.server.vo.req.sysuser.UserPageReq;
import com.jiazhe.youxiang.server.vo.req.sysuser.UserSaveReq;
import com.jiazhe.youxiang.server.vo.resp.sysuser.SysUserResp;
import com.jiazhe.youxiang.server.vo.resp.sysuser.UserWithRoleResp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
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
 * @description：用户管理api
 * @date 2018/10/19
 */
@RestController
@RequestMapping("api/sysuser")
public class APISysUserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(APISysUserController.class);

    @Autowired
    private SysUserBiz sysUserBiz;

    @ApiOperation(value = "【后台】列出所有员工", httpMethod = "GET", response = SysUserResp.class, responseContainer = "List", notes = "列出所有员工")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.USER, operate = "列出所有员工", level = LogLevelEnum.LEVEL_1)
    public Object listAll() {
        List<SysUserDTO> dtoList = sysUserBiz.findAll();
        List<SysUserResp> respList = dtoList.stream().map(SysUserAdapter::dto2RespVo).collect(Collectors.toList());
        return ResponseFactory.buildResponse(respList);
    }

    @ApiOperation(value = "【后台】员工列表", httpMethod = "GET", response = SysUserResp.class, responseContainer = "List", notes = "员工列表（带条件、分页）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.USER, operate = "员工列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute UserPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<SysUserDTO> dtoList = sysUserBiz.getList(req.getLoginName(), req.getDisplayName(), paging);
        List<SysUserResp> respList = dtoList.stream().map(SysUserAdapter::dto2RespVo).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(respList, paging);
    }

    @ApiOperation(value = "【后台】删除员工", httpMethod = "POST", notes = "根据id删除员工信息（并删除对应角色）")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.USER, operate = "删除员工", level = LogLevelEnum.LEVEL_3)
    public Object delete(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        sysUserBiz.deleteUserWithRole(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】获取员工信息", httpMethod = "GET", response = UserWithRoleResp.class, notes = "根据id获取员工信息（包含角色id，多个角色id用逗号连接）")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.USER, operate = "获取员工信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        UserWithRoleDTO dto = sysUserBiz.findUserWithRoleById(req.getId());
        UserWithRoleResp resp = SysUserAdapter.userWithRoleDTO2UserWithRoleResp(dto);
        return ResponseFactory.buildResponse(resp);
    }

    @ApiOperation(value = "【后台】保存员工信息", httpMethod = "POST", notes = "保存员工、员工对应角色信息，新建和修改放一起了")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.USER, operate = "保存员工信息", level = LogLevelEnum.LEVEL_1)
    public Object save(@ModelAttribute UserSaveReq req) {
        CommonValidator.validateNull(req.getLoginName(), new UserException(UserCodeEnum.USER_LOGIN_NAME_IS_NULL));
        CommonValidator.validateNull(req.getDisplayName(), new UserException(UserCodeEnum.USER_DISPLAY_NAME_IS_NULL));
        CommonValidator.validateNull(req.getMobile(), new UserException(UserCodeEnum.USEER_MOBILE_IS_NULL));
        if(req.getId()==0){
            CommonValidator.validateNull(req.getPassword(), new UserException(UserCodeEnum.USEER_PASSWORD_IS_NULL));
        }
        CommonValidator.validateNull(req.getRoleIds(), new UserException(UserCodeEnum.USER_ROLE_NOT_CHOOSE));
        if (!ValidateUtils.phoneValidate(req.getMobile())) {
            throw new UserException(UserCodeEnum.USEER_MOBILE_IS_ILLEGAL);
        }
        UserWithRoleDTO userWithRoleDTO = SysUserAdapter.userSaveReq2UserWithDTO(req);
        boolean userHasExisted = sysUserBiz.userHasExisted(userWithRoleDTO);
        if (userHasExisted) {
            throw new UserException(UserCodeEnum.USER_HAS_EXISTED);
        }
        sysUserBiz.saveRoleWithPerm(userWithRoleDTO);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】获取登录员工信息", httpMethod = "GET", response = SysUserResp.class, notes = "获取登陆员工信息，无入参")
    @RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.USER, operate = "获取登录员工信息", level = LogLevelEnum.LEVEL_1)
    public Object getUserInfo() {
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        //将DTO转为respVO返回
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        SysUserResp sysUserResp = SysUserAdapter.dto2RespVo(sysUserDTO);
        return ResponseFactory.buildResponse(sysUserResp);
    }

    @ApiOperation(value = "【后台】修改密码", httpMethod = "POST", notes = "员工自行修改登陆密码")
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.USER, operate = "修改密码", level = LogLevelEnum.LEVEL_2)
    public Object changePassword(@ModelAttribute ChangePasswordReq req) {
        CommonValidator.validateNull(req.getOldPassword(), new UserException(UserCodeEnum.USEER_PASSWORD_IS_NULL));
        CommonValidator.validateNull(req.getPassword1(), new UserException(UserCodeEnum.USEER_PASSWORD_IS_NULL));
        CommonValidator.validateNull(req.getPassword2(), new UserException(UserCodeEnum.USEER_PASSWORD_IS_NULL));
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        if (!req.getPassword1().equals(req.getPassword2())) {
            throw new UserException(UserCodeEnum.USER_PASSWORD_DIFFERENT);
        }
        String saltPassword = EncryptPasswordUtil.encrypt(sysUserDTO.getSalt(), req.getOldPassword());
        if (!saltPassword.equals(sysUserDTO.getPassword())) {
            throw new UserException(UserCodeEnum.USER_OLD_PASSWORD_WRONG);
        }
        sysUserBiz.changePassword(sysUserDTO.getId(), req.getPassword1());
        SecurityUtils.getSubject().logout();
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】重置员工密码", httpMethod = "POST", notes = "重置员工密码，重置为123456")
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.USER, operate = "重置员工密码", level = LogLevelEnum.LEVEL_2)
    public Object resetPassword(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req.getId());
        sysUserBiz.changePassword(req.getId(), "123456");
        return ResponseFactory.buildSuccess();
    }
}
