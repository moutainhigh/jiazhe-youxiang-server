package com.jiazhe.youxiang.server.controller.advancepay;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.advancepay.AdvancePayAdapter;
import com.jiazhe.youxiang.server.biz.advancepay.AdvancePayBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.advancepay.AdvancePayDTO;
import com.jiazhe.youxiang.server.service.advancepay.AdvancePayService;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.AdvancePaySaveReq;
import com.jiazhe.youxiang.server.vo.req.partnerorder.PartnerOrderInfoPageReq;
import com.jiazhe.youxiang.server.vo.resp.advancepay.AdvancePayResp;
import com.jiazhe.youxiang.server.vo.resp.partnerorder.OverviewMoneyResp;
import io.swagger.annotations.ApiOperation;
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
 * @author TU
 * @description
 * @date 2018/12/10.
 */
@RestController
@RequestMapping("api/advancepay")
public class APIAdvancePayController extends BaseController{

    @Autowired
    private AdvancePayBiz advancePayBiz;

    @RequiresPermissions(PermissionConstant.ADVANCE_PAY_MANAGEMENT)
    @ApiOperation(value = "【后台】查询预付款充值信息", httpMethod = "GET", response = AdvancePayResp.class, responseContainer = "List", notes = "【后台】查询预付款充值信息")
    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.PARTNER_ORDER, operate = "查询预付款充值信息", level = LogLevelEnum.LEVEL_1)
    public Object listAll(@ModelAttribute PartnerOrderInfoPageReq req) {
        Date timeStart = req.getServiceTimeStart() == 0 ? null : new Date(req.getServiceTimeStart());
        Date timeEnd = req.getServiceTimeEnd() == 0 ? null : new Date(req.getServiceTimeEnd());
        List<AdvancePayDTO> advancePayDTOList = advancePayBiz.getList(timeStart,timeEnd);
        List<AdvancePayResp> advancePayRespList = advancePayDTOList.stream().map(AdvancePayAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(advancePayRespList);
    }

    @RequiresPermissions(PermissionConstant.ADVANCE_PAY_ADD)
    @ApiOperation(value = "【后台】保存预支信息", httpMethod = "POST", notes = "保存预支信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.PARTNER_ORDER,operate = "保存预支信息",level = LogLevelEnum.LEVEL_2)
    public Object save(@ModelAttribute AdvancePaySaveReq req) {
        advancePayBiz.save(req.getAdvancePay(),req.getAdvanceTime(),req.getRemark());
        return ResponseFactory.buildSuccess();
    }
}
