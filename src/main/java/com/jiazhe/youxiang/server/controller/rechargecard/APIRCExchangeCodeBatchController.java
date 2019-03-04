package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.ExportExcelUtils;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBatchBiz;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchPageReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditResp;
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
 * @description：充值卡兑换码批次【rc是rechargecard缩写】
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/rcexchangecodebatch")
public class APIRCExchangeCodeBatchController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIRCExchangeCodeBatchController.class);

    @Autowired
    private RCExchangeCodeBatchBiz rcExchangeCodeBatchBiz;
    @Autowired
    private RCExchangeCodeBiz rcExchangeCodeBiz;

    @RequiresPermissions(value = {PermissionConstant.RC_BATCH_MANAGEMENT,PermissionConstant.RC_BATCH_SEARCH},logical = Logical.OR)
    @ApiOperation(value = "【后台】充值卡兑换码批次信息列表（分页）", httpMethod = "GET", response = RCExchangeCodeBatchResp.class, responseContainer = "List", notes = "查询充值卡兑换码批次信息（根据项目id和批次名称查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "充值卡兑换码批次信息列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute RCExchangeCodeBatchPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCExchangeCodeBatchDTO> rcExchangeCodeBatchDTOList = rcExchangeCodeBatchBiz.getList(req.getProjectId(), req.getName(), paging);
        List<RCExchangeCodeBatchResp> rcExchangeCodeBatchRespList = rcExchangeCodeBatchDTOList.stream().map(RCExchangeCodeBatchAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "【后台】根据项目id查询充值卡兑换码虚拟批次", httpMethod = "GET", response = RCExchangeCodeBatchResp.class, responseContainer = "List", notes = "根据项目id查询充值卡兑换码虚拟批次")
    @RequestMapping(value = "/findvirtualbyprojectid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "根据项目id查询充值卡兑换码虚拟批次", level = LogLevelEnum.LEVEL_1)
    public Object findVirtualByProjectId(@ModelAttribute IdReq req) {
        List<RCExchangeCodeBatchDTO> dtoList = rcExchangeCodeBatchBiz.getVirtualByProjectId(req.getId());
        List<RCExchangeCodeBatchResp> respList = dtoList.stream().map(RCExchangeCodeBatchAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(respList);
    }

    @RequiresPermissions(value = {PermissionConstant.RC_BATCH_ADD,PermissionConstant.RC_BATCH_EDIT},logical = Logical.OR)
    @ApiOperation(value = "【后台】保存充值卡兑换码批次信息", httpMethod = "POST", notes = "【新建、修改】保存充值卡兑换码批次信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "保存充值卡兑换码批次信息", level = LogLevelEnum.LEVEL_2)
    public Object save(@ModelAttribute RCExchangeCodeBatchSaveReq req)  {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(),new RechargeCardException(RechargeCardCodeEnum.BATCH_NAME_IS_NULL));
        CommonValidator.validateNull(req.getRechargeCardName(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_NAME_IS_NULL));
        CommonValidator.validateNull(req.getProjectId(), new RechargeCardException(RechargeCardCodeEnum.PROJECT_IS_NULL));
        CommonValidator.validateNull(req.getCityCodes(), new RechargeCardException(RechargeCardCodeEnum.CITY_IS_NULL));
        CommonValidator.validateNull(req.getProductIds(), new RechargeCardException(RechargeCardCodeEnum.PRODUCT_IS_NULL));
        //虚拟批次
        if(req.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)){
            req.setAmount(0);
            req.setFaceValue(BigDecimal.ZERO);
        }else{
           CommonValidator.validateNull(req.getAmount(),new RechargeCardException(RechargeCardCodeEnum.NOT_VIRTUAL_NEED_AMOUNT));
           CommonValidator.validateNull(req.getFaceValue(),new RechargeCardException(RechargeCardCodeEnum.NOT_VIRTUAL_NEED_FACE_VALUE));
        }
        //批次过期时间不为空
        if(req.getExpiryTime() == CommonConstant.NULL_TIME){
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_EXPIRY_TIME_IS_NULL);
        }
        req.setExpiryTime(DateUtil.getLastSecond(req.getExpiryTime()));
        //充值卡过期时间为指定的时间
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            if(req.getRechargeCardEffectiveTime()==CommonConstant.NULL_TIME){
                throw new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EFFECTIVE_TIME_IS_NULL);
            }
            if(req.getRechargeCardEffectiveTime() > req.getExpiryTime()){
                throw new RechargeCardException(RechargeCardCodeEnum.RC_EFFECTIVE_TIME_LATER_BATCH_EXPIRY_TIME);
            }
            if(req.getRechargeCardExpiryTime()==CommonConstant.NULL_TIME){
                throw new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL);
            }
            if(req.getRechargeCardEffectiveTime()> req.getRechargeCardExpiryTime()){
                throw new RechargeCardException(RechargeCardCodeEnum.RC_EFFECTIVE_TIME_LATER_RC_EXPIRY_TIME);
            }
            req.setRechargeCardEffectiveTime(DateUtil.getFirstSecond(req.getRechargeCardEffectiveTime()));
            req.setRechargeCardExpiryTime(DateUtil.getLastSecond(req.getRechargeCardExpiryTime()));
            req.setValidityPeriod(0);
        }
        //积分卡时间为兑换后间隔的天数
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXCHANGE_PERIOD)||req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_ACTIVE_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL));
            if (req.getValidityPeriod() == 0) {
                throw new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL);
            }
            req.setRechargeCardEffectiveTime(DateUtil.getFirstSecond(System.currentTimeMillis()));
            req.setRechargeCardExpiryTime(DateUtil.getLastSecond(System.currentTimeMillis()));
        }
        RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO = RCExchangeCodeBatchAdapter.ReqSave2DTOSave(req);
        if (req.getId() == 0) {
            rcExchangeCodeBatchBiz.addSave(rcExchangeCodeBatchSaveDTO);
        } else {
            rcExchangeCodeBatchBiz.editSave(rcExchangeCodeBatchSaveDTO);
        }
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.RC_CODE_GENERATE)
    @ApiOperation(value = "【后台】生成充值卡兑换码", httpMethod = "POST", notes = "生成批次下的充值卡兑换码")
    @RequestMapping(value = "/generatecode", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "生成充值卡兑换码", level = LogLevelEnum.LEVEL_3)
    public Object generateCode(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        rcExchangeCodeBatchBiz.generateCode(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】充值卡兑换码批次信息回显", httpMethod = "GET", response = RCExchangeCodeBatchEditResp.class, notes = "充值卡兑换码批次信息回显")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "充值卡兑换码批次信息回显", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchBiz.getById(req.getId());
        RCExchangeCodeBatchEditResp rcExchangeCodeBatchEditResp = RCExchangeCodeBatchAdapter.DTOEdit2RespEdit(rcExchangeCodeBatchEditDTO);
        return ResponseFactory.buildResponse(rcExchangeCodeBatchEditResp);
    }

    @RequiresPermissions(PermissionConstant.RC_BATCH_STATUS_CHANGE)
    @ApiOperation(value = "【后台】启用批次", httpMethod = "POST", notes = "启用批次")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "启用批次", level = LogLevelEnum.LEVEL_2)
    public Object startUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        rcExchangeCodeBatchBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.RC_BATCH_STATUS_CHANGE)
    @ApiOperation(value = "【后台】停用批次", httpMethod = "POST", notes = "停用批次")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "停用批次", level = LogLevelEnum.LEVEL_2)
    public Object stopUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        rcExchangeCodeBatchBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.RC_CODE_EXPORT)
    @ApiOperation(value = "【后台】导出批次下兑换码", httpMethod = "GET", notes = "导出批次下兑换码")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "导出批次下兑换码", level = LogLevelEnum.LEVEL_3)
    public void export(@ModelAttribute IdReq req, HttpServletResponse response) throws IOException {
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList= rcExchangeCodeBiz.getByBatchId(req.getId());
        ExportExcelUtils.exportRechargeCardCode(response,rcExchangeCodeDTOList);
    }

    @RequiresPermissions(PermissionConstant.RC_CODE_EXPORT)
    @ApiOperation(value = "【后台】导出前检查", httpMethod = "GET", notes = "导出前检查，看批次下是否有兑换码")
    @RequestMapping(value = "/exportcheck", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "导出前检查", level = LogLevelEnum.LEVEL_1)
    public Object exportCheck(@ModelAttribute IdReq req){
        CommonValidator.validateId(req);
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList= rcExchangeCodeBiz.getByBatchId(req.getId());
        if(rcExchangeCodeDTOList.isEmpty()){
            throw new RechargeCardException(RechargeCardCodeEnum.NO_CODE_TO_EXPORT);
        }
        return ResponseFactory.buildSuccess();
    }

}
