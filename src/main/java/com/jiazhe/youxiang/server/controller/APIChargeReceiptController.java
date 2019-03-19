package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.ChargeReceiptAdapter;
import com.jiazhe.youxiang.server.biz.ChargeReceiptBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.chargereceipt.ChargeReceiptPageReq;
import com.jiazhe.youxiang.server.vo.resp.chargereceipt.ChargeReceiptResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：充值凭证管理接口列表
 * @date 2019-03-09
 */
@RestController
@RequestMapping("api/chargereceipt")
public class APIChargeReceiptController extends BaseController {

    @Autowired
    private ChargeReceiptBiz chargeReceiptBiz;

//    @RequiresPermissions(PermissionConstant.AUDIT_RECORD_MANAGEMENT)
    @ApiOperation(value = "【后台】消费凭证列表", httpMethod = "GET", response = ChargeReceiptResp.class, responseContainer = "List", notes = "【后台】消费凭证列表")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_RECEIPT, operate = "消费凭证列表表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute ChargeReceiptPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<ChargeReceiptDTO> chargeReceiptDTOList = chargeReceiptBiz.getList(req.getAuditRecordId(), paging);
        List<ChargeReceiptResp> chargeReceiptRespList = chargeReceiptDTOList.stream().map(ChargeReceiptAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(chargeReceiptRespList, paging);
    }

    @ApiOperation(value = "【后台】删除消费凭证", httpMethod = "POST", notes = "【后台】删除消费凭证")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_RECEIPT, operate = "消费凭证列表表", level = LogLevelEnum.LEVEL_3)
    public Object delete(@ModelAttribute IdReq req) {
        chargeReceiptBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }
}
