package com.jiazhe.youxiang.server.controller.order;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.*;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@RestController
@RequestMapping("api/orderinfo")
public class APIOrderInfoController extends BaseController {

    @Autowired
    private OrderInfoBiz orderInfoBiz;

    @ApiOperation(value = "【后台】分页查询订单信息", httpMethod = "GET", response = OrderInfoResp.class, responseContainer = "List", notes = "【后台】分页查询订单信息")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute OrderInfoPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<OrderInfoDTO> orderInfoDTOList = orderInfoBiz.getList(req.getStatus(), req.getOrderCode(), req.getMobile(), req.getCustomerMobile(), req.getOrderStartTime(), req.getOrderEndTime(), req.getWorkerMobile(), paging);
        List<OrderInfoResp> orderInfoRespList = orderInfoDTOList.stream().map(OrderInfoAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(orderInfoRespList, paging);
    }

    @ApiOperation(value = "【APP端】分页查询订单信息", httpMethod = "GET", response = OrderInfoResp.class, responseContainer = "List", notes = "【后台】分页查询订单信息")
    @RequestMapping(value = "/customerlistpage", method = RequestMethod.GET)
    public Object customerListPage(@ModelAttribute CustomerOrderInfoPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<OrderInfoDTO> orderInfoDTOList = orderInfoBiz.custoemrGetList(req.getCustomerId(), req.getStatus(), paging);
        List<OrderInfoResp> orderInfoRespList = orderInfoDTOList.stream().map(OrderInfoAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(orderInfoRespList, paging);
    }

    @ApiOperation(value = "【APP端】客户取消订单", httpMethod = "POST", notes = "【APP端】客户取消订单")
    @RequestMapping(value = "/customercancelorder", method = RequestMethod.POST)
    public Object customerCancelOrder(@ModelAttribute IdReq req) {
        orderInfoBiz.customerCancelOrder(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "计算客户需要在线支付的金额", httpMethod = "GET", response = NeedPayResp.class, notes = "计算客户需要在线支付的金额")
    @RequestMapping(value = "/customerneedpaycash", method = RequestMethod.GET)
    public Object customerNeedPayCash(@ModelAttribute IdReq req) {
        BigDecimal needPayCash = orderInfoBiz.customerNeedPayCash(req.getId());
        NeedPayResp needPayResp = new NeedPayResp();
        needPayResp.setPayCash(needPayCash);
        return ResponseFactory.buildResponse(needPayResp);
    }

    @ApiOperation(value = "【APP端】客户支付", httpMethod = "POST", response = NeedPayResp.class, notes = "客户支付")
    @RequestMapping(value = "/customerpay", method = RequestMethod.POST)
    public Object customerPay(@ModelAttribute CustomerPayReq req) {
        BigDecimal needPayCash = orderInfoBiz.customerPay(req.getOrderId(), req.getPayCash(), req.getSerialNumber());
        NeedPayResp needPayResp = new NeedPayResp();
        needPayResp.setPayCash(needPayCash);
        return ResponseFactory.buildResponse(needPayResp);
    }

    @ApiOperation(value = "审核通过", httpMethod = "POST", notes = "审核通过")
    @RequestMapping(value = "/ordercancelpass", method = RequestMethod.POST)
    public Object orderCancelPass(@ModelAttribute IdReq req) {
        orderInfoBiz.orderCancelPass(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "审核不通过", httpMethod = "POST", notes = "审核不通过")
    @RequestMapping(value = "/ordercancelunpass", method = RequestMethod.POST)
    public Object orderCancelUnpass(@ModelAttribute OrderCancelUnpassReq req) {
        orderInfoBiz.orderCancelUnpass(req.getOrderId(),req.getAuditReason());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "员工取消订单", httpMethod = "POST", notes = "员工取消订单")
    @RequestMapping(value = "/usercancelorder", method = RequestMethod.POST)
    public Object userCancelOrder(@ModelAttribute IdReq req) {
        orderInfoBiz.userCancelOrder(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "员工完成订单", httpMethod = "POST", notes = "员工完成订单")
    @RequestMapping(value = "/usercompleteorder", method = RequestMethod.POST)
    public Object userCompleteOrder(@ModelAttribute IdReq req) {
        orderInfoBiz.userCompleteOrder(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后端、APP端】下单", httpMethod = "POST", notes = "【后端、APP端】下单")
    @RequestMapping(value = "/placeorder", method = RequestMethod.POST)
    public Object userPlaceOrder(@ModelAttribute PlaceOrderReq req) throws ParseException {
        orderInfoBiz.placeOrder(OrderInfoAdapter.ReqPlaceOrder2DTOPlaceOrder(req));
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "员工预约订单", httpMethod = "POST", notes = "员工预约订单")
    @RequestMapping(value = "/userreservationorder", method = RequestMethod.POST)
    public Object userReservationOrder(@ModelAttribute UserReservationOrderReq req) {
        orderInfoBiz.userReservationOrder(req);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "员工收取订单超额费用", httpMethod = "POST", notes = "员工收取订单超额费用")
    @RequestMapping(value = "/userchargeadditional", method = RequestMethod.POST)
    public Object userChargeAdditional(@ModelAttribute ChargeAdditionalReq req) {
        orderInfoBiz.userChargeAdditional(req.getId(), req.getAdditionalPay());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "获取订单信息", httpMethod = "GET", response = OrderInfoResp.class, notes = "客户支付")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        OrderInfoDTO orderInfoDTO = orderInfoBiz.getById(req.getId());
        OrderInfoResp orderInfoResp = OrderInfoAdapter.DTO2Resp(orderInfoDTO);
        return ResponseFactory.buildResponse(orderInfoResp);
    }

    @ApiOperation(value = "待预约订单数量", httpMethod = "GET", notes = "待预约订单数量")
    @RequestMapping(value = "/getunsentordercount", method = RequestMethod.GET)
    public Object getUnsentOrderCount() {
        Integer count = orderInfoBiz.getUnsentOrderCount();
        return ResponseFactory.buildResponse(count);
    }

    @ApiOperation(value = "待审核订单数量", httpMethod = "GET", notes = "待审核订单数量")
    @RequestMapping(value = "/getunauditordercount", method = RequestMethod.GET)
    public Object getUnauditOrderCount() {
        Integer count = orderInfoBiz.getUnauditOrderCount();
        return ResponseFactory.buildResponse(count);
    }

}
