package com.jiazhe.youxiang.server.controller.point;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.base.util.boccc.BOCCCConstant;
import com.jiazhe.youxiang.base.util.boccc.BOCCCUtils;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.point.PointExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.EnvironmentConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.point.exchangecode.CodeChargeReq;
import com.jiazhe.youxiang.server.vo.req.point.exchangecode.PointExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.req.point.exchangecode.PointExchangeCodePageReq;
import com.jiazhe.youxiang.server.vo.resp.point.exchangecode.PointExchangeCodeResp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：积分兑换码
 * @date 2018/10/20
 */
@RestController
@RequestMapping("api/pointexchangecode")
public class APIPointExchangeCodeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIPointExchangeCodeController.class);

    @Autowired
    private PointExchangeCodeBiz pointExchangeCodeBiz;

    @RequiresPermissions(PermissionConstant.POINT_CODE_MANAGEMENT)
    @ApiOperation(value = "【后台】积分卡兑换码列表", httpMethod = "GET", response = PointExchangeCodeResp.class, responseContainer = "List", notes = "分页查询积分卡兑换码（根据批次id和兑换码的码和密钥查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "积分卡兑换码列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute PointExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<PointExchangeCodeDTO> pointExchangeCodeDTOList = pointExchangeCodeBiz.getList(req.getBatchId(), req.getCode(), req.getKeyt(), req.getStatus(), req.getUsed(), paging);
        List<PointExchangeCodeResp> pointExchangeCodeBatchRespList = pointExchangeCodeDTOList.stream().map(PointExchangeCodeAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(pointExchangeCodeBatchRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.POINT_CODE_SEARCH)
    @ApiOperation(value = "【后台】积分卡兑换码列表（信息查询功能模块使用）", httpMethod = "GET", response = PointExchangeCodeResp.class, responseContainer = "List", notes = "分页查询积分卡兑换码，不指定批次等信息")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "积分卡兑换码列表", level = LogLevelEnum.LEVEL_1)
    public Object searchListPage(@ModelAttribute PointExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        if (Strings.isBlank(req.getCode()) && Strings.isBlank(req.getKeyt())) {
            req.setCode("xxxxxxxxxxxxxxxx");
            req.setKeyt("xxxxxxxxxxxxxxxx");
        }
        List<PointExchangeCodeDTO> pointExchangeCodeDTOList = pointExchangeCodeBiz.getList(req.getBatchId(), req.getCode(), req.getKeyt(), req.getStatus(), req.getUsed(), paging);
        List<PointExchangeCodeResp> pointExchangeCodeBatchRespList = pointExchangeCodeDTOList.stream().map(PointExchangeCodeAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(pointExchangeCodeBatchRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.ALL_POINT_CODE_STATUS_CHANGE)
    @ApiOperation(value = "【后台】启用所有积分卡兑换码", httpMethod = "POST", notes = "启用所有积分卡兑换码和已经兑换成的积分卡")
    @RequestMapping(value = "/allstartusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "启用所有积分卡兑换码", level = LogLevelEnum.LEVEL_2)
    public Object allStartUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        pointExchangeCodeBiz.allStartUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.ALL_POINT_CODE_STATUS_CHANGE)
    @ApiOperation(value = "【后台】停用所有积分卡兑换码", httpMethod = "POST", notes = "停用所有积分卡兑换码和已经兑换成的积分卡")
    @RequestMapping(value = "/allstopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "停用所有积分卡兑换码", level = LogLevelEnum.LEVEL_2)
    public Object allStopUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        pointExchangeCodeBiz.allStopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(value = {PermissionConstant.POINT_CODE_STATUS_CHANGE, PermissionConstant.POINT_CODE_SEARCH_STATUS_CHANGE}, logical = Logical.OR)
    @ApiOperation(value = "【后台】启用积分卡兑换码", httpMethod = "POST", notes = "启用积分卡兑换码，已经兑换的积分卡不修改")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "启用积分卡兑换码", level = LogLevelEnum.LEVEL_2)
    public Object startUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        pointExchangeCodeBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(value = {PermissionConstant.POINT_CODE_STATUS_CHANGE, PermissionConstant.POINT_CODE_SEARCH_STATUS_CHANGE}, logical = Logical.OR)
    @ApiOperation(value = "【后台】停用积分卡兑换码", httpMethod = "POST", notes = "停用积分卡兑换码，已经兑换的积分卡不能修改")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "停用积分卡兑换码", level = LogLevelEnum.LEVEL_2)
    public Object stopUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        pointExchangeCodeBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】获取兑换码信息", httpMethod = "GET", response = PointExchangeCodeResp.class, notes = "获取兑换码信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "获取兑换码信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        PointExchangeCodeDTO pointExchangeCodeDTO = pointExchangeCodeBiz.getById(req.getId());
        PointExchangeCodeResp pointExchangeCodeResp = PointExchangeCodeAdapter.dto2Resp(pointExchangeCodeDTO);
        return ResponseFactory.buildResponse(pointExchangeCodeResp);
    }

    @RequiresPermissions(value = {PermissionConstant.POINT_CODE_EDIT, PermissionConstant.POINT_CODE_SEARCH_EDIT}, logical = Logical.OR)
    @ApiOperation(value = "修改兑换码信息", httpMethod = "POST", notes = "修改兑换码信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "修改兑换码信息", level = LogLevelEnum.LEVEL_2)
    public Object editSave(@ModelAttribute PointExchangeCodeEditReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getId());
        CommonValidator.validateNull(req.getPointName(), new PointException(PointCodeEnum.POINT_NAME_IS_NULL));
        CommonValidator.validateNull(req.getCityCodes(), new PointException(PointCodeEnum.CITY_IS_NULL));
        CommonValidator.validateNull(req.getProductIds(), new PointException(PointCodeEnum.PRODUCT_IS_NULL));
        if (req.getExpiryTime() == CommonConstant.NULL_TIME) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_EXPIRY_TIME_IS_NULL);
        }
        req.setExpiryTime(DateUtil.getLastSecond(req.getExpiryTime()));
        //积分卡过期时间为指定的时间
        if (req.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)) {
            if (req.getPointEffectiveTime() == CommonConstant.NULL_TIME) {
                throw new PointException(PointCodeEnum.POINT_EFFECTIVE_TIME_IS_NULL);
            }
            if (req.getPointExpiryTime() == CommonConstant.NULL_TIME) {
                throw new PointException(PointCodeEnum.POINT_EXPIRY_TIME_IS_NULL);
            }
            if (req.getPointEffectiveTime() > req.getPointExpiryTime()) {
                throw new PointException(PointCodeEnum.POINT_EFFECTIVE_TIME_LATER_POINT_EXPIRY_TIME);
            }
            if (req.getPointEffectiveTime() > req.getExpiryTime()) {
                throw new PointException(PointCodeEnum.POINT_EFFECTIVE_TIME_LATER_CODE_EXPIRY_TIME);
            }
            req.setPointEffectiveTime(DateUtil.getFirstSecond(req.getPointEffectiveTime()));
            req.setPointExpiryTime(DateUtil.getLastSecond(req.getPointExpiryTime()));
            req.setValidityPeriod(0);
        }
        //自兑换之日起有效天数 或 自激活之日起有效天数
        if (req.getExpiryType().equals(CommonConstant.POINT_EXCHANGE_PERIOD) || req.getExpiryType().equals(CommonConstant.POINT_ACTIVE_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(), new PointException(PointCodeEnum.POINT_EXPIRY_TIME_IS_NULL));
            if (req.getValidityPeriod() == 0) {
                throw new PointException(PointCodeEnum.POINT_EXPIRY_TIME_IS_NULL);
            }
            req.setPointEffectiveTime(DateUtil.getFirstSecond(System.currentTimeMillis()));
            req.setPointExpiryTime(DateUtil.getLastSecond(System.currentTimeMillis()));
        }
        PointExchangeCodeEditDTO dto = PointExchangeCodeAdapter.editReq2EditDto(req);
        pointExchangeCodeBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】客户用兑换码进行兑换", httpMethod = "POST", notes = "客户用积分卡兑换码兑换")
    @RequestMapping(value = "/customerselfcodecharge", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "客户用兑换码进行兑换", level = LogLevelEnum.LEVEL_2)
    public Object customerSelfCodeCharge(@ModelAttribute CodeChargeReq req) {
        CommonValidator.validateId(req.getId());
        CommonValidator.validateNull(req.getKeyt(), new PointException(PointCodeEnum.EXCHANGE_CODE_NOT_EXISTED));
        pointExchangeCodeBiz.customerSelfCharge(req.getId(), req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_POINT_BINDING)
    @ApiOperation(value = "【后台】后台用兑换码进行兑换", httpMethod = "POST", notes = "后台用兑换码进行兑换")
    @RequestMapping(value = "/backstagecodecharge", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "后台用兑换码进行兑换", level = LogLevelEnum.LEVEL_2)
    public Object backstageCodeCharge(@ModelAttribute CodeChargeReq req) {
        CommonValidator.validateId(req.getId());
        CommonValidator.validateNull(req.getKeyt(), new PointException(PointCodeEnum.EXCHANGE_CODE_NOT_EXISTED));
        pointExchangeCodeBiz.backstageCodeCharge(req.getId(), req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【小程序】通过code检查", httpMethod = "GET", notes = "【小程序】通过code检查")
    @RequestMapping(value = "/checkbycode", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "【小程序】通过code检查", level = LogLevelEnum.LEVEL_1)
    public Object checkByCode(@RequestParam String code) {
        pointExchangeCodeBiz.checkByCode(code);
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.POINT_CODE_REFUND)
    @ApiOperation(value = "请求退货", httpMethod = "POST", notes = "请求退货")
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "请求退货", level = LogLevelEnum.LEVEL_3)
    public Object refund(@RequestParam Integer id, @RequestParam Integer force) {
        if (!Arrays.asList(BOCCCConstant.BOCCC_ENVIRONMENT).contains(EnvironmentConstant.ENVIRONMENT)) {
            throw new PointException(PointCodeEnum.FEFUND_EVN_BOCCC);
        }
        pointExchangeCodeBiz.refund(id, force);
        return ResponseFactory.buildSuccess();
    }

}
