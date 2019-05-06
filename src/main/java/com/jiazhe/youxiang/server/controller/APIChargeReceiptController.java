package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.ChargeReceiptAdapter;
import com.jiazhe.youxiang.server.biz.ChargeReceiptBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.ChargeReceiptCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.exceptions.ChargeReceiptException;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.chargereceipt.ChargeReceiptPageReq;
import com.jiazhe.youxiang.server.vo.req.chargereceipt.ChargeReceiptSaveReq;
import com.jiazhe.youxiang.server.vo.resp.chargereceipt.ChargeReceiptResp;
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
 * @description：充值凭证管理接口列表
 * @date 2019-03-09
 */
@RestController
@RequestMapping("api/chargereceipt")
public class APIChargeReceiptController extends BaseController {

    @Autowired
    private ChargeReceiptBiz chargeReceiptBiz;

    @RequiresPermissions(PermissionConstant.CHARGE_RECEIPT_MANAGEMENT)
    @ApiOperation(value = "【后台】消费凭证列表", httpMethod = "GET", response = ChargeReceiptResp.class, responseContainer = "List", notes = "【后台】消费凭证列表")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_RECEIPT, operate = "消费凭证列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute ChargeReceiptPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<ChargeReceiptDTO> chargeReceiptDTOList = chargeReceiptBiz.getList(req.getAuditRecordId(),null,null,null,null,null, paging);
        List<ChargeReceiptResp> chargeReceiptRespList = chargeReceiptDTOList.stream().map(ChargeReceiptAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(chargeReceiptRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.CHARGE_RECEIPT_DELETE)
    @ApiOperation(value = "【后台】删除消费凭证", httpMethod = "POST", notes = "【后台】删除消费凭证")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_RECEIPT, operate = "删除消费凭证", level = LogLevelEnum.LEVEL_3)
    public Object delete(@ModelAttribute IdReq req) {
        chargeReceiptBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(value={PermissionConstant.CHARGE_RECEIPT_ADD,PermissionConstant.CHARGE_RECEIPT_EDIT},logical= Logical.OR)
    @ApiOperation(value = "【后台】保存消费凭证", httpMethod = "POST", notes = "【后台】保存消费凭证")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_RECEIPT, operate = "保存消费凭证", level = LogLevelEnum.LEVEL_3)
    public Object save(@ModelAttribute ChargeReceiptSaveReq req) {
        CommonValidator.validateNull(req.getExchangePoint(),new ChargeReceiptException(ChargeReceiptCodeEnum.EXCHANGE_POINT_IS_NULL));
        CommonValidator.validateNull(req.getCustomerName(),new ChargeReceiptException(ChargeReceiptCodeEnum.CUSTOMER_NAME_IS_NULL));
        CommonValidator.validateNull(req.getPosCode(),new ChargeReceiptException(ChargeReceiptCodeEnum.POS_CODE_IS_NULL));
        CommonValidator.validateNull(req.getCardNo(),new ChargeReceiptException(ChargeReceiptCodeEnum.CARD_NO_IS_NULL));
        if(req.getTradeTime().equals(CommonConstant.NULL_TIME)){
            throw new ChargeReceiptException(ChargeReceiptCodeEnum.TRADE_TIME_IS_NULL);
        }
        ChargeReceiptSaveDTO dto = ChargeReceiptAdapter.saveReq2SaveDto(req);
        //检查记录是否重复
        if(Byte.valueOf("1").equals(req.getCheck())){
            boolean hasExisted = chargeReceiptBiz.hasExisted(dto);
            if(hasExisted){
                throw new ChargeReceiptException(ChargeReceiptCodeEnum.CHARGE_RECEIPT_REPEAT);
            }
        }
        chargeReceiptBiz.save(dto);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】获取消费凭证详情", httpMethod = "GET", response = ChargeReceiptResp.class, notes = "获取消费凭证详情")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_RECEIPT, operate = "获取消费凭证详情", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req.getId());
        ChargeReceiptDTO chargeReceiptDTO = chargeReceiptBiz.getById(req.getId());
        ChargeReceiptResp chargeReceiptResp = ChargeReceiptAdapter.dto2Resp(chargeReceiptDTO);
        return ResponseFactory.buildResponse(chargeReceiptResp);
    }

    @ApiOperation(value = "【后台】消费凭证列表", httpMethod = "GET", response = ChargeReceiptResp.class, responseContainer = "List", notes = "【后台】消费凭证列表")
    @RequestMapping(value = "/listpageall", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_RECEIPT, operate = "消费凭证列表", level = LogLevelEnum.LEVEL_1)
    public Object listPageAll(@ModelAttribute ChargeReceiptPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        Date tradeStartTime = req.getTradeStartTime() == CommonConstant.NULL_TIME ? null : new Date(DateUtil.getFirstSecond(req.getTradeStartTime()));
        Date tradeEndTime = req.getTradeEndTime() == CommonConstant.NULL_TIME ? null : new Date(DateUtil.getLastSecond(req.getTradeEndTime()));
        List<ChargeReceiptDTO> chargeReceiptDTOList = chargeReceiptBiz.getList(req.getAuditRecordId(),req.getCustomerName(),req.getCardNo(),req.getPosCode(),tradeStartTime,tradeEndTime, paging);
        List<ChargeReceiptResp> chargeReceiptRespList = chargeReceiptDTOList.stream().map(ChargeReceiptAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(chargeReceiptRespList, paging);
    }
}
