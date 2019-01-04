package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CustomerException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.CodeChargeReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.RCExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.RCExchangeCodePageReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecode.RCExchangeCodeResp;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：充值卡兑换码【rc是rechargecard缩写】
 * @date 2018/10/20
 */
@RestController
@RequestMapping("api/rcexchangecode")
public class APIRCExchangeCodeController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(APIRCExchangeCodeController.class);

    @Autowired
    private RCExchangeCodeBiz rcExchangeCodeBiz;

    @RequiresPermissions(PermissionConstant.RC_CODE_MANAGEMENT)
    @ApiOperation(value = "【后台】充值卡兑换码列表", httpMethod = "GET", response = RCExchangeCodeResp.class, responseContainer = "List",notes = "分页查询充值卡兑换码（根据批次id和兑换码的码和密钥查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "充值卡兑换码列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute RCExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList = rcExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<RCExchangeCodeResp> rcExchangeCodeBatchRespList = rcExchangeCodeDTOList.stream().map(RCExchangeCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.RECHARGE_CARD_CODE_SEARCH)
    @ApiOperation(value = "【后台】充值卡兑换码列表（信息查询功能模块使用）", httpMethod = "GET", response = RCExchangeCodeResp.class, responseContainer = "List",notes = "分页查询充值卡兑换码，不指定批次等信息")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "充值卡兑换码列表", level = LogLevelEnum.LEVEL_1)
    public Object searchListPage(@ModelAttribute RCExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        if(Strings.isBlank(req.getCode())&&Strings.isBlank(req.getKeyt())){
            req.setCode("xxxxxxxxxxxxxxxx");
            req.setKeyt("xxxxxxxxxxxxxxxx");
        }
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList = rcExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<RCExchangeCodeResp> rcExchangeCodeBatchRespList = rcExchangeCodeDTOList.stream().map(RCExchangeCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @RequiresPermissions(value = {PermissionConstant.RC_CODE_STATUS_CHANGE, PermissionConstant.RECHARGE_CARD_CODE_SEARCH_STATUS_CHANGE}, logical = Logical.OR)
    @ApiOperation(value = "【后台】启用充值卡兑换码", httpMethod = "POST",notes = "启用充值卡兑换码，已经兑换的充值卡不能修改")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "启用充值卡兑换码", level = LogLevelEnum.LEVEL_2)
    public Object startUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        rcExchangeCodeBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(value = {PermissionConstant.RC_CODE_STATUS_CHANGE, PermissionConstant.RECHARGE_CARD_CODE_SEARCH_STATUS_CHANGE}, logical = Logical.OR)
    @ApiOperation(value = "【后台】停用充值卡兑换码", httpMethod = "POST",notes = "停用充值卡兑换码，已经兑换的充值卡不能修改")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "停用充值卡兑换码", level = LogLevelEnum.LEVEL_2)
    public Object stopUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        rcExchangeCodeBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】获取兑换码信息", httpMethod = "GET",response = RCExchangeCodeResp.class,notes = "获取兑换码信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "获取兑换码信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        RCExchangeCodeDTO rcExchangeCodeDTO = rcExchangeCodeBiz.getById(req.getId());
        RCExchangeCodeResp rcExchangeCodeResp = RCExchangeCodeAdapter.DTO2Resp(rcExchangeCodeDTO);
        return ResponseFactory.buildResponse(rcExchangeCodeResp);
    }

    @RequiresPermissions(value = {PermissionConstant.RC_CODE_EDIT, PermissionConstant.RECHARGE_CARD_CODE_SEARCH_EDIT}, logical = Logical.OR)
    @ApiOperation(value = "修改兑换码信息", httpMethod = "POST",notes = "修改兑换码信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "修改兑换码信息", level = LogLevelEnum.LEVEL_2)
    public Object editSave(@ModelAttribute RCExchangeCodeEditReq req) throws ParseException {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getRechargeCardName(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_NAME_IS_NULL));
        CommonValidator.validateNull(req.getCityCodes(), new RechargeCardException(RechargeCardCodeEnum.CITY_IS_NULL));
        CommonValidator.validateNull(req.getProductIds(), new RechargeCardException(RechargeCardCodeEnum.PRODUCT_IS_NULL));
        if(req.getExpiryTime()==0){
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_EXPIRY_TIME_IS_NULL);
        }
        req.setExpiryTime(DateUtil.getLastSecond(req.getExpiryTime()));
        if(req.getRechargeCardEffectiveTime()==0){
            throw new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EFFECTIVE_TIME_IS_NULL);
        }
        req.setRechargeCardEffectiveTime(DateUtil.getFirstSecond(req.getRechargeCardEffectiveTime()));
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            if(req.getRechargeCardExpiryTime()==0){
                throw new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL);
            }
            req.setRechargeCardExpiryTime(DateUtil.getLastSecond(req.getRechargeCardExpiryTime()));
        }
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL));
        }
        RCExchangeCodeEditDTO dto = RCExchangeCodeAdapter.EditReq2EditDTO(req);
        rcExchangeCodeBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】客户用兑换码进行兑换", httpMethod = "POST",notes = "客户用充值卡兑换码兑换")
    @RequestMapping(value = "/customerselfcodecharge", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "客户用兑换码进行兑换", level = LogLevelEnum.LEVEL_2)
    public Object customerSelfCodeCharge(@ModelAttribute CodeChargeReq req) {
        CommonValidator.validateId(req.getId());
        CommonValidator.validateNull(req.getKeyt(),new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED));
        rcExchangeCodeBiz.customerSelfCharge(req.getId(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_RECHARGE_CARD_BINDING)
    @ApiOperation(value = "【后台】后台用兑换码进行兑换", httpMethod = "POST",notes = "后台用兑换码进行兑换")
    @RequestMapping(value = "/backstagecodecharge", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "后台用兑换码进行兑换", level = LogLevelEnum.LEVEL_2)
    public Object backstageCodeCharge(@ModelAttribute CodeChargeReq req) {
        CommonValidator.validateId(req.getId());
        CommonValidator.validateNull(req.getKeyt(),new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED));
        rcExchangeCodeBiz.backstageCodeCharge(req.getId(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

}
