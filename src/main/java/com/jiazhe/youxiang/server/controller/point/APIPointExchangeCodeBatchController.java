package com.jiazhe.youxiang.server.controller.point;

import com.jiazhe.youxiang.base.controller.BaseController;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.ExportExcelUtils;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.biz.point.PointExchangeCodeBatchBiz;
import com.jiazhe.youxiang.server.biz.point.PointExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.point.exchangecodebatch.PointExchangeCodeBatchPageReq;
import com.jiazhe.youxiang.server.vo.req.point.exchangecodebatch.PointExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.point.exchangecodebatch.PointExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.point.exchangecodebatch.PointExchangeCodeBatchResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchResp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：积分兑换码批次
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/pointexchangecodebatch")
public class APIPointExchangeCodeBatchController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIPointExchangeCodeBatchController.class);

    @Autowired
    private PointExchangeCodeBatchBiz pointExchangeCodeBatchBiz;
    @Autowired
    private PointExchangeCodeBiz pointExchangeCodeBiz;

    @RequiresPermissions(value = {PermissionConstant.POINT_BATCH_MANAGEMENT, PermissionConstant.POINT_BATCH_SEARCH}, logical = Logical.OR)
    @ApiOperation(value = "【后台】积分卡兑换码批次信息列表（分页）", httpMethod = "GET", response = PointExchangeCodeBatchResp.class, responseContainer = "List", notes = "积分卡兑换码批次信息列表（根据项目id和批次名称查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "积分卡兑换码批次信息列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute PointExchangeCodeBatchPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<PointExchangeCodeBatchDTO> dtoList = pointExchangeCodeBatchBiz.getList(req.getProjectId(), req.getName(), paging);
        List<PointExchangeCodeBatchResp> respList = dtoList.stream().map(PointExchangeCodeBatchAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(respList, paging);
    }

    @ApiOperation(value = "【后台】根据项目id查询积分卡兑换码可用虚拟批次", httpMethod = "GET", response = RCExchangeCodeBatchResp.class, responseContainer = "List", notes = "根据项目id查询积分卡兑换码可用虚拟批次")
    @RequestMapping(value = "/findvirtualbyprojectid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "根据项目id查询积分卡兑换码可用虚拟批次", level = LogLevelEnum.LEVEL_1)
    public Object findVirtualByProjectId(@ModelAttribute IdReq req) {
        List<PointExchangeCodeBatchDTO> dtoList = pointExchangeCodeBatchBiz.getVirtualByProjectId(req.getId());
        List<PointExchangeCodeBatchResp> respList = dtoList.stream().map(PointExchangeCodeBatchAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(respList);
    }

    @RequiresPermissions(value = {PermissionConstant.POINT_BATCH_ADD, PermissionConstant.POINT_BATCH_EDIT}, logical = Logical.OR)
    @ApiOperation(value = "【后台】保存积分卡兑换码批次信息", httpMethod = "POST", notes = "【新建、修改】保存积分卡兑换码批次信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "保存积分卡兑换码批次信息", level = LogLevelEnum.LEVEL_2)
    public Object save(@ModelAttribute PointExchangeCodeBatchSaveReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(), new PointException(PointCodeEnum.BATCH_NAME_IS_NULL));
        CommonValidator.validateNull(req.getPointName(), new PointException(PointCodeEnum.POINT_NAME_IS_NULL));
        CommonValidator.validateNull(req.getProjectId(), new PointException(PointCodeEnum.PROJECT_IS_NULL));
        CommonValidator.validateNull(req.getCityCodes(), new PointException(PointCodeEnum.CITY_IS_NULL));
        CommonValidator.validateNull(req.getProductIds(), new PointException(PointCodeEnum.PRODUCT_IS_NULL));
        //是否为虚拟批次（是）
        if (req.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            req.setAmount(0);
            req.setFaceValue(BigDecimal.ZERO);
            //判断商户号是否重复
            if (pointExchangeCodeBatchBiz.merchantNoIsRepeat(req.getId(), req.getExtInfo())) {
                throw new PointException(PointCodeEnum.MERCHANT_NO_REPEAT);
            }
        } else {
            //不是虚拟批次，将绑定商户号的入参置为空字符串
            req.setExtInfo("");
            CommonValidator.validateNull(req.getAmount(), new PointException(PointCodeEnum.NOT_VIRTUAL_NEED_AMOUNT));
            CommonValidator.validateNull(req.getFaceValue(), new PointException(PointCodeEnum.NOT_VIRTUAL_NEED_FACE_VALUE));
        }
        //批次过期时间不为空
        if (req.getExpiryTime() == CommonConstant.NULL_TIME) {
            throw new PointException(PointCodeEnum.BATCH_EXPIRY_TIME_IS_NULL);
        }
        req.setExpiryTime(DateUtil.getLastSecond(req.getExpiryTime()));
//        //积分卡生效时间不为空
//        if (req.getPointEffectiveTime() == CommonConstant.NULL_TIME) {
//            throw new PointException(PointCodeEnum.POINT_EFFECTIVE_TIME_IS_NULL);
//        }
        //积分卡过期时间为指定的时间
        if (req.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)) {
            if (req.getPointEffectiveTime() == CommonConstant.NULL_TIME || req.getPointExpiryTime() == CommonConstant.NULL_TIME) {
                throw new PointException(PointCodeEnum.POINT_EXPIRY_TIME_IS_NULL);
            }
            if (req.getPointEffectiveTime() > req.getPointExpiryTime()) {
                throw new PointException(PointCodeEnum.POINT_EFFECTIVE_TIME_LATER_POINT_EXPIRY_TIME);
            }
            if (req.getPointEffectiveTime() > req.getExpiryTime()) {
                throw new PointException(PointCodeEnum.POINT_EFFECTIVE_TIME_LATER_BATCH_EXPIRY_TIME);
            }
            req.setPointEffectiveTime(DateUtil.getFirstSecond(req.getPointEffectiveTime()));
            req.setPointExpiryTime(DateUtil.getLastSecond(req.getPointExpiryTime()));
            req.setValidityPeriod(0);
        }
        //自兑换之日起有效天数 或 自激活之日起有效天数
        if (req.getExpiryType().equals(CommonConstant.POINT_EXCHANGE_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(), new PointException(PointCodeEnum.POINT_EXPIRY_TIME_IS_NULL));
            if (req.getValidityPeriod() == 0) {
                throw new PointException(PointCodeEnum.POINT_EXPIRY_TIME_IS_NULL);
            }
            req.setPointEffectiveTime(DateUtil.getFirstSecond(System.currentTimeMillis()));
            req.setPointExpiryTime(DateUtil.getLastSecond(System.currentTimeMillis()));
        }
        PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO = PointExchangeCodeBatchAdapter.reqSave2dtoSave(req);
        if (req.getId() == 0) {
            pointExchangeCodeBatchBiz.addSave(pointExchangeCodeBatchSaveDTO);
        } else {
            pointExchangeCodeBatchBiz.editSave(pointExchangeCodeBatchSaveDTO);
        }
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.POINT_CODE_GENERATE)
    @ApiOperation(value = "【后台】生成积分卡兑换码", httpMethod = "POST", notes = "生成批次下的积分卡兑换码")
    @RequestMapping(value = "/generatecode", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "生成积分卡兑换码", level = LogLevelEnum.LEVEL_3)
    public Object generateCode(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        pointExchangeCodeBatchBiz.generateCode(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】积分卡兑换码批次信息回显", httpMethod = "GET", response = PointExchangeCodeBatchEditResp.class, notes = "积分卡兑换码批次信息回显")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "积分卡兑换码批次信息回显", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        PointExchangeCodeBatchEditDTO pointExchangeCodeBatchEditDTO = pointExchangeCodeBatchBiz.getById(req.getId());
        PointExchangeCodeBatchEditResp pointExchangeCodeBatchEditResp = PointExchangeCodeBatchAdapter.DtoEdit2RespEdit(pointExchangeCodeBatchEditDTO);
        return ResponseFactory.buildResponse(pointExchangeCodeBatchEditResp);
    }

    @RequiresPermissions(PermissionConstant.POINT_BATCH_STATUS_CHANGE)
    @ApiOperation(value = "【后台】启用批次", httpMethod = "POST", notes = "启用批次")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "启用批次", level = LogLevelEnum.LEVEL_2)
    public Object startUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        pointExchangeCodeBatchBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.POINT_BATCH_STATUS_CHANGE)
    @ApiOperation(value = "【后台】停用批次", httpMethod = "POST", notes = "停用批次")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "停用批次", level = LogLevelEnum.LEVEL_2)
    public Object stopUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        pointExchangeCodeBatchBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.POINT_CODE_EXPORT)
    @ApiOperation(value = "【后台】导出批次下兑换码", httpMethod = "GET", notes = "导出批次下兑换码")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "导出批次下兑换码", level = LogLevelEnum.LEVEL_3)
    public void export(@ModelAttribute IdReq req, HttpServletResponse response) throws IOException {
        List<PointExchangeCodeDTO> pointExchangeCodeDTOList = pointExchangeCodeBiz.getByBatchId(req.getId());
        ExportExcelUtils.exportPointCode(response, pointExchangeCodeDTOList);
    }

    @RequiresPermissions(PermissionConstant.POINT_CODE_EXPORT)
    @ApiOperation(value = "【后台】导出前检查", httpMethod = "GET", notes = "导出前检查，看批次下是否有兑换码")
    @RequestMapping(value = "/exportcheck", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "导出前检查", level = LogLevelEnum.LEVEL_1)
    public Object exportCheck(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        List<PointExchangeCodeDTO> pointExchangeCodeDTOList = pointExchangeCodeBiz.getByBatchId(req.getId());
        if (pointExchangeCodeDTOList.isEmpty()) {
            throw new PointException(PointCodeEnum.NO_CODE_TO_EXPORT);
        }
        return ResponseFactory.buildSuccess();
    }
}
