package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.EncryptPasswordUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.base.util.ValidateUtils;
import com.jiazhe.youxiang.server.adapter.SysUserAdapter;
import com.jiazhe.youxiang.server.biz.SysUserBiz;
import com.jiazhe.youxiang.server.common.enums.UserCodeEnum;
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
 * @description：
 * @date 2018/10/19
 */
@RestController
@RequestMapping("api/sysuser")
public class APISysUserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APISysUserController.class);

    @Autowired
    private SysUserBiz sysUserBiz;

    @ApiOperation(value = "查询所有员工信息", httpMethod = "GET", response = SysUserResp.class, responseContainer = "List", notes = "查询所有员工信息")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public Object listAll() {
        List<SysUserDTO> sysUserDTOList = sysUserBiz.findAll();
        return ResponseFactory.buildResponse(sysUserDTOList);
    }

    @ApiOperation(value = "分页查询员工信息", httpMethod = "GET", response = SysUserResp.class, responseContainer = "List", notes = "分页查询员工信息")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute UserPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<SysUserDTO> sysUserDTOList = sysUserBiz.getList(req.getLoginName(), req.getDisplayName(), paging);
        List<SysUserResp> sysUserRespList = sysUserDTOList.stream().map(SysUserAdapter::DTO2RespVO).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(sysUserRespList, paging);
    }

    @ApiOperation(value = "根据id删除员工信息（包含对应角色）", httpMethod = "POST", notes = "根据id删除员工信息（包含对应角色）")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        sysUserBiz.deleteUserWithRole(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据id获取员工信息（包含角色）", httpMethod = "GET", response = UserWithRoleResp.class, notes = "根据id获取员工信息（包含角色）")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
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
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getLoginName(), new UserException(UserCodeEnum.USER_LOGIN_NAME_IS_NULL));
        CommonValidator.validateNull(req.getDisplayName(), new UserException(UserCodeEnum.USER_DISPLAY_NAME_IS_NULL));
        CommonValidator.validateNull(req.getMobile(), new UserException(UserCodeEnum.USEER_MOBILE_IS_NULL));
        if(!ValidateUtils.phoneValidate(req.getMobile())){
            new UserException(UserCodeEnum.USEER_MOBILE_IS_ILLEGAL);
        }
        if(req.getId() == 0){
            CommonValidator.validateNull(req.getPassword(), new UserException(UserCodeEnum.USEER_PASSWORD_IS_NULL));
        }
        CommonValidator.validateNull(req.getRoleIds(),new UserException(UserCodeEnum.USER_ROLE_NOT_CHOOSE));
        /*判断是否重名，要将新建和修改区分开*/
        UserWithRoleDTO userWithRoleDTO = SysUserAdapter.userSaveReq2UserWithDTO(req);
        boolean roleHasExisted = sysUserBiz.userHasExisted(userWithRoleDTO);
        if (roleHasExisted) {
            throw new UserException(UserCodeEnum.USER_HAS_EXISTED);
        }
        sysUserBiz.saveRoleWithPerm(userWithRoleDTO);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "获取登录员工信息", httpMethod = "GET", response = SysUserResp.class, notes = "获取登陆员工信息")
    @RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
    public Object getUserInfo() {
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        //将DTO转为respVO返回
        if(null == sysUserDTO){
            throw new UserException(UserCodeEnum.USER_NOT_EXISTED);
        }
        SysUserResp sysUserResp = SysUserAdapter.DTO2RespVO(sysUserDTO);
        return ResponseFactory.buildResponse(sysUserResp);
    }

    @ApiOperation(value = "修改密码", httpMethod = "POST", notes = "修改密码")
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public Object changePassword(@ModelAttribute ChangePasswordReq req) {
        CommonValidator.validateNull(req.getOldPassword(),new UserException(UserCodeEnum.USEER_PASSWORD_IS_NULL));
        CommonValidator.validateNull(req.getPassword1(),new UserException(UserCodeEnum.USEER_PASSWORD_IS_NULL));
        CommonValidator.validateNull(req.getPassword2(),new UserException(UserCodeEnum.USEER_PASSWORD_IS_NULL));
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
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
}
