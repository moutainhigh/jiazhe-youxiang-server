package com.jiazhe.youxiang.server.controller.partnerorder;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.partnerorder.PartnerOrderInfoAdapter;
import com.jiazhe.youxiang.server.biz.partnerorder.PartnerOrderInfoBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.PartnerOrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.PartnerOrderException;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.partnerorder.PartnerOrderInfoPageReq;
import com.jiazhe.youxiang.server.vo.req.partnerorder.PartnerOrderSaveReq;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.OverviewMoneyResp;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.PartnerOrderInfoResp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：商家订单记录
 * @date 2018/12/9
 */

@RestController
@RequestMapping("api/partnerorderinfo")
public class APIPartnerOrderInfoController extends BaseController {

    @Autowired
    private PartnerOrderInfoBiz partnerOrderInfoBiz;

    @RequiresPermissions(value = {PermissionConstant.PARTNER_ORDER_MANAGEMENT, PermissionConstant.PARTNER_ORDER_SEARCH}, logical = Logical.OR)
    @ApiOperation(value = "【后台】分页查询商家订单信息", httpMethod = "GET", response = PartnerOrderInfoResp.class, responseContainer = "List", notes = "【后台】分页查询商家订单信息")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PARTNER_ORDER, operate = "分页查询商家订单信息", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute PartnerOrderInfoPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        Date serviceTimeStart = req.getServiceTimeStart() == CommonConstant.NULL_TIME ? null : new Date(req.getServiceTimeStart());
        Date serviceTimeEnd = req.getServiceTimeEnd() == CommonConstant.NULL_TIME ? null : new Date(req.getServiceTimeEnd());
        List<PartnerOrderInfoDTO> dtoList = partnerOrderInfoBiz.getList(req.getStatus(), req.getCustomerCityCode(), req.getPartnerId(), req.getServiceItemId(), serviceTimeStart, serviceTimeEnd, req.getCustomerMobile(), paging);
        List<PartnerOrderInfoResp> respList = dtoList.stream().map(PartnerOrderInfoAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(respList, paging);
    }

    @ApiOperation(value = "【后台】回显商家订单信息", httpMethod = "GET", response = PartnerOrderInfoResp.class, notes = "【后台】回显商家订单信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PARTNER_ORDER, operate = "回显商家订单信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        PartnerOrderInfoDTO dto = partnerOrderInfoBiz.getById(req.getId());
        PartnerOrderInfoResp resp = PartnerOrderInfoAdapter.DTO2Resp(dto);
        return ResponseFactory.buildResponse(resp);
    }

    @RequiresPermissions(value = {PermissionConstant.PARTNER_ORDER_ADD, PermissionConstant.PARTNER_ORDER_EDIT}, logical = Logical.OR)
    @ApiOperation(value = "【后台】保存商家订单信息", httpMethod = "POST", notes = "【后台】保存商家订单信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PARTNER_ORDER, operate = "保存商家订单信息", level = LogLevelEnum.LEVEL_2)
    public Object save(@ModelAttribute PartnerOrderSaveReq req) {
        CommonValidator.validateNull(req.getCustomerName(), new PartnerOrderException(PartnerOrderCodeEnum.CUSTOMER_NAME_IS_NULL));
        CommonValidator.validateNull(req.getCustomerCityCode(), new PartnerOrderException(PartnerOrderCodeEnum.CUSTOMER_CITY_IS_NULL));
        CommonValidator.validateNull(req.getCustomerCityName(), new PartnerOrderException(PartnerOrderCodeEnum.CUSTOMER_CITY_IS_NULL));
        CommonValidator.validateNull(req.getServiceItemId(), new PartnerOrderException(PartnerOrderCodeEnum.SERVICE_ITEM_IS_NULL));
        CommonValidator.validateNull(req.getOrderSource(), new PartnerOrderException(PartnerOrderCodeEnum.ORDER_SOURCE_IS_NULL));
        CommonValidator.validateNull(req.getPrePay(), new PartnerOrderException(PartnerOrderCodeEnum.PRE_PAY_IS_NULL));
        CommonValidator.validateNull(req.getAppendPay(), new PartnerOrderException(PartnerOrderCodeEnum.APPEND_PAY_IS_NULL));
        if (!"".equals(req.getCustomerMobile())) {
            CommonValidator.validateMobile(req.getCustomerMobile(), new PartnerOrderException(PartnerOrderCodeEnum.CUSTOMER_MOBILE_IS_VALID));
        }
        if (req.getOrderTime() == CommonConstant.NULL_TIME) {
            throw new PartnerOrderException(PartnerOrderCodeEnum.EXCHANGE_TIME_IS_NULL);
        }
        if (req.getServiceTime() == CommonConstant.NULL_TIME) {
            throw new PartnerOrderException(PartnerOrderCodeEnum.SERVICE_TIME_IS_NULL);
        }
        if (req.getOrderTime() > req.getServiceTime()) {
            throw new PartnerOrderException(PartnerOrderCodeEnum.SERVICE_TIME_ERROR);
        }
        if (req.getStatus().equals(CommonConstant.PARTNER_ORDER_COMPLETE)) {
            CommonValidator.validateNull(req.getCustomerMobile(), new PartnerOrderException(PartnerOrderCodeEnum.CUSTOMER_MOBILE_IS_NULL));
            CommonValidator.validateNull(req.getKeyt(), new PartnerOrderException(PartnerOrderCodeEnum.KEYT_IS_NULL));
            CommonValidator.validateNull(req.getCustomerAddress(), new PartnerOrderException(PartnerOrderCodeEnum.CUSTOMER_ADDRESS_IS_NULL));
            CommonValidator.validateNull(req.getWorkerName(), new PartnerOrderException(PartnerOrderCodeEnum.WORKER_NAME_IS_NULL));
            CommonValidator.validateNull(req.getWorkerMobile(), new PartnerOrderException(PartnerOrderCodeEnum.WORKER_MOBILE_IS_NULL));
            CommonValidator.validateMobile(req.getWorkerMobile(), new PartnerOrderException(PartnerOrderCodeEnum.WORKER_MOBILE_IS_VALID));
            CommonValidator.validateNull(req.getPartnerId(), new PartnerOrderException(PartnerOrderCodeEnum.PARTNER_IS_NULL));
        }
        PartnerOrderInfoDTO dto = PartnerOrderInfoAdapter.saveReq2DTO(req);
        dto.setModTime(new Date());
        if (req.getId() == 0) {
            dto.setAddTime(new Date());
            dto.setExtInfo("");
            dto.setIsDeleted(Byte.valueOf("0"));
        }
        partnerOrderInfoBiz.save(dto);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】查询预付款相关信息", httpMethod = "GET", response = OverviewMoneyResp.class, responseContainer = "List", notes = "【后台】查询预付款相关信息")
    @RequestMapping(value = "/caloverviewmoney", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PARTNER_ORDER, operate = "查询预付款相关信息", level = LogLevelEnum.LEVEL_1)
    public Object calOverviewMoney(@ModelAttribute PartnerOrderInfoPageReq req) {
        Date timeStart = req.getServiceTimeStart() == CommonConstant.NULL_TIME ? null : new Date(req.getServiceTimeStart());
        Date timeEnd = req.getServiceTimeEnd() == CommonConstant.NULL_TIME ? null : new Date(req.getServiceTimeEnd());
        OverviewMoneyResp resp = partnerOrderInfoBiz.calOverviewMoney(timeStart, timeEnd);
        return ResponseFactory.buildResponse(resp);
    }
}
