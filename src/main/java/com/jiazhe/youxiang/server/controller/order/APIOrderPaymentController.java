package com.jiazhe.youxiang.server.controller.order;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.order.OrderPaymentAdapter;
import com.jiazhe.youxiang.server.biz.order.OrderPaymentBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderpayment.OrderPaymentResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@RestController
@RequestMapping("api/orderpayment")
public class APIOrderPaymentController extends BaseController{

    @Autowired
    private OrderPaymentBiz orderPaymentBiz;
    @ApiOperation(value = "根据充值卡id获取支付记录", httpMethod = "GET", response = OrderPaymentResp.class,notes = "根据充值卡id获取支付记录")
    @RequestMapping(value = "/getbyrechargecardid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "根据充值卡id获取支付记录", level = LogLevelEnum.LEVEL_1)
    public Object getByRechargeCardId(@ModelAttribute IdReq req) {
        List<OrderPaymentDTO> orderPaymentDTOList = orderPaymentBiz.getByRechargeCardId(req.getId());
        if(orderPaymentDTOList.isEmpty()){
            throw new RechargeCardException(RechargeCardCodeEnum.CARD_HAS_NO_PAYMENT);
        }
        List<OrderPaymentResp> orderPaymentRespList =orderPaymentDTOList.stream().map(OrderPaymentAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(orderPaymentRespList);
    }

    @ApiOperation(value = "根据充值卡兑换码id获取支付记录", httpMethod = "GET", response = OrderPaymentResp.class,notes = "根据充值卡兑换码id获取支付记录")
    @RequestMapping(value = "/getbyrechargecardcodeid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "根据充值卡兑换码id获取支付记录", level = LogLevelEnum.LEVEL_1)
    public Object getByRechargeCardCodeId(@ModelAttribute IdReq req) {
        List<OrderPaymentDTO> orderPaymentDTOList = orderPaymentBiz.getByRechargeCardCodeId(req.getId());
        if(orderPaymentDTOList.isEmpty()){
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_HAS_NO_PAYMENT);
        }
        List<OrderPaymentResp> orderPaymentRespList =orderPaymentDTOList.stream().map(OrderPaymentAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(orderPaymentRespList);
    }

    @ApiOperation(value = "根据代金券id获取支付记录", httpMethod = "GET", response = OrderPaymentResp.class,notes = "根据代金券id获取支付记录")
    @RequestMapping(value = "/getbyvoucherid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "根据代金券id获取支付记录", level = LogLevelEnum.LEVEL_1)
    public Object getByVoucherId(@ModelAttribute IdReq req) {
        List<OrderPaymentDTO> orderPaymentDTOList = orderPaymentBiz.getByVoucherId(req.getId());
        if(orderPaymentDTOList.isEmpty()){
            throw new VoucherException(VoucherCodeEnum.VOUCHER_HAS_NO_PAYMENT);
        }
        List<OrderPaymentResp> orderPaymentRespList =orderPaymentDTOList.stream().map(OrderPaymentAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(orderPaymentRespList);
    }

    @ApiOperation(value = "根据代金券兑换码id获取支付记录", httpMethod = "GET", response = OrderPaymentResp.class,notes = "根据代金券兑换码id获取支付记录")
    @RequestMapping(value = "/getbyvouchercodeid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "根据代金券兑换码id获取支付记录", level = LogLevelEnum.LEVEL_1)
    public Object getByVoucherCodeId(@ModelAttribute IdReq req) {
        List<OrderPaymentDTO> orderPaymentDTOList = orderPaymentBiz.getByVoucherCodeId(req.getId());
        if(orderPaymentDTOList.isEmpty()){
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_HAS_NO_PAYMENT);
        }
        List<OrderPaymentResp> orderPaymentRespList =orderPaymentDTOList.stream().map(OrderPaymentAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(orderPaymentRespList);
    }
}
