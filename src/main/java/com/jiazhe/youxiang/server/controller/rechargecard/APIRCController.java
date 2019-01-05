package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.*;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.common.exceptions.CustomerException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.*;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rc.RCResp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @description：充值卡【rc是rechargecard缩写】
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/rc")
public class APIRCController extends BaseController{

    @Autowired
    private RCBiz rcBiz;

    @RequiresPermissions(value = {PermissionConstant.CUSTOMER_RECHARGE_CARD_DETAIL,PermissionConstant.RECHARGE_CARD_SEARCH},logical = Logical.OR)
    @ApiOperation(value = "【后台】充值卡列表（用于信息查询页面）", httpMethod = "GET", response = RCResp.class, responseContainer = "List",notes = "信息查询页查询充值卡")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "充值卡列表", level = LogLevelEnum.LEVEL_1)
    public Object searchListPage(@ModelAttribute RCPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCDTO> rcDTOList = rcBiz.getList(req.getMobile(),req.getExchangeType(),req.getStatus(),req.getExpiry(),paging);
        List<RCResp> rcRespList = rcDTOList.stream().map(RCAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】客户查询所有充值卡（分页）", httpMethod = "GET", response = RCResp.class, responseContainer = "List",notes = "客户查询所有充值卡，分页")
    @RequestMapping(value = "/findbycustomeridpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "客户查询所有充值卡", level = LogLevelEnum.LEVEL_1)
    public Object findByCustomerIdPage(@ModelAttribute RCCustomerPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCDTO> rcDTOList = rcBiz.getListByCustomerId(req.getCustomerId(),req.getStatus(),paging);
        List<RCResp> rcRespList = rcDTOList.stream().map(RCAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcRespList, paging);
    }

    @AppApi
    @ApiOperation(value = "【后台、APP端】根据购买物属性（商品和城市），查询客户可使用的充值卡，分页", httpMethod = "GET", response = RCResp.class, responseContainer = "List",notes = "根据购买物属性（商品和城市），查询客户可使用的充值卡，分页")
    @RequestMapping(value = "/findbygoodsattrpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "根据购买物属性（商品和城市），查询客户可使用的充值卡，分页", level = LogLevelEnum.LEVEL_1)
    public Object findByGoodsAttrPage(@ModelAttribute RCGoodsAttrPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCDTO> rcDTOList = rcBiz.getListByGoodsAttr(req.getCustomerId(),req.getProductId(),req.getCityCode(),paging);
        List<RCResp> rcRespList = rcDTOList.stream().map(RCAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.RECHARGE_CARD_SEARCH_STATUS_CHANGE)
    @ApiOperation(value = "【后台】启用充值卡", httpMethod = "POST",notes = "启用充值卡")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "启用充值卡", level = LogLevelEnum.LEVEL_2)
    public Object startUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        rcBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.RECHARGE_CARD_SEARCH_STATUS_CHANGE)
    @ApiOperation(value = "【后台】停用充值卡", httpMethod = "POST",notes = "停用充值卡")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "停用充值卡", level = LogLevelEnum.LEVEL_2)
    public Object stopUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        rcBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_RECHARGE_CARD_RECHARGE)
    @ApiOperation(value = "【后台】直接给客户充值任意分数", httpMethod = "POST",notes = "直接给客户充值任意分数")
    @RequestMapping(value = "/directcharge", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "直接给客户充值任意分数", level = LogLevelEnum.LEVEL_3)
    public Object directCharge(@ModelAttribute DirectChargeReq req) throws ParseException {
        CommonValidator.validateId(req.getId());
        CommonValidator.validateId(req.getBatchId());
        CommonValidator.validateNull(req.getFaceValue());
        rcBiz.directCharge(req.getId(),req.getBatchId(),req.getFaceValue());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】回显充值卡信息", httpMethod = "GET",response = RCResp.class,notes = "回显充值卡信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "回显充值卡信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        RCDTO rcDTO = rcBiz.getById(req.getId());
        RCResp rcResp = RCAdapter.DTO2Resp(rcDTO);
        return ResponseFactory.buildResponse(rcResp);
    }

    @RequiresPermissions(PermissionConstant.RECHARGE_CARD_SEARCH_EDIT)
    @ApiOperation(value = "【后台】修改充值卡信息", httpMethod = "POST",notes = "修改充值卡信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "修改充值卡信息", level = LogLevelEnum.LEVEL_2)
    public Object editSave(@ModelAttribute RCEditReq req) throws ParseException {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_NAME_IS_NULL));
        if(req.getExpiryTime()==0){
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_EXPIRY_TIME_IS_NULL);
        }
        req.setExpiryTime(DateUtil.getLastSecond(req.getExpiryTime()));
        if(req.getEffectiveTime()==0){
            throw new VoucherException(VoucherCodeEnum.VOUCHER_EFFECTIVE_TIME_IS_NULL);
        }
        req.setEffectiveTime(DateUtil.getFirstSecond(req.getEffectiveTime()));
        RCEditDTO dto = RCAdapter.EditReq2EditDTO(req);
        rcBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }

}
