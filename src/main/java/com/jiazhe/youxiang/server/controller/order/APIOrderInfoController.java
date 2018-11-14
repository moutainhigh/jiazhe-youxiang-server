package com.jiazhe.youxiang.server.controller.order;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.PlaceOrderDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.*;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.WaitingDealCountResp;
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

    @ApiOperation(value = "【后端】下单", httpMethod = "POST", notes = "【后端】下单")
    @RequestMapping(value = "/userplaceorder", method = RequestMethod.POST)
    public Object userPlaceOrder(@ModelAttribute UserPlaceOrderReq req) throws ParseException {
        PlaceOrderDTO placeOrderDTO = OrderInfoAdapter.ReqUserPlaceOrder2DTOPlaceOrder(req);
        placeOrderDTO.setType(Byte.valueOf("0"));
        placeOrderDTO.setServiceTime(req.getRealServiceTime());
        orderInfoBiz.placeOrder(placeOrderDTO);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【APP端】下单", httpMethod = "POST", notes = "【APP端】下单")
    @RequestMapping(value = "/customerplaceorder", method = RequestMethod.POST)
    public Object customerPlaceOrder(@ModelAttribute CustomerPlaceOrderReq req) throws ParseException {
        PlaceOrderDTO placeOrderDTO = OrderInfoAdapter.ReqCustomerPlaceOrder2DTOPlaceOrder(req);
        placeOrderDTO.setWorkerMobile("");
        placeOrderDTO.setWorkerName("");
        placeOrderDTO.setCost(new BigDecimal(0));
        placeOrderDTO.setType(Byte.valueOf("1"));
        placeOrderDTO.setRealServiceTime(req.getServiceTime());
        orderInfoBiz.placeOrder(placeOrderDTO);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "员工预约服务、派单", httpMethod = "POST", notes = "员工预约服务、派单")
    @RequestMapping(value = "/userreservationorder", method = RequestMethod.POST)
    public Object userReservationOrder(@ModelAttribute UserReservationOrderReq req) {
        CommonValidator.validateNull(req.getRealServiceTime(),new OrderException(OrderCodeEnum.REAL_SERVICE_TIME_IS_NULL));
        CommonValidator.validateNull(req.getWorkerName(),new OrderException(OrderCodeEnum.WORKER_NAME_IS_NAME));
        CommonValidator.validateNull(req.getWorkerMobile(),new OrderException(OrderCodeEnum.WORKER_MOBILE_IS_NAME));
        CommonValidator.validateNull(req.getCost(),new OrderException(OrderCodeEnum.ORDER_COST_IS_NULL));
        orderInfoBiz.userReservationOrder(OrderInfoAdapter.ReqUserReservationOrder2DTOUserReservationOrder(req));
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "员工追加订单", httpMethod = "POST", notes = "员工追加订单")
    @RequestMapping(value = "/appendorder", method = RequestMethod.POST)
    public Object appendOrder(@ModelAttribute AppendOrderReq req) {
        orderInfoBiz.appendOrder(OrderInfoAdapter.ReqAppendOrder2DTOAppendOrder(req));
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "获取订单信息", httpMethod = "GET", response = OrderInfoResp.class, notes = "客户支付")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        OrderInfoDTO orderInfoDTO = orderInfoBiz.getById(req.getId());
        OrderInfoResp orderInfoResp = OrderInfoAdapter.DTO2Resp(orderInfoDTO);
        return ResponseFactory.buildResponse(orderInfoResp);
    }

    @ApiOperation(value = "待预约订单数量", httpMethod = "GET",  response = WaitingDealCountResp.class,  notes = "待预约订单数量")
    @RequestMapping(value = "/getunsentordercount", method = RequestMethod.GET)
    public Object getUnsentOrderCount() {
        Integer count = orderInfoBiz.getUnsentOrderCount();
        WaitingDealCountResp waitingDealCountResp = new WaitingDealCountResp();
        waitingDealCountResp.setCount(count);
        return ResponseFactory.buildResponse(waitingDealCountResp);
    }

    @ApiOperation(value = "取消待审核订单数量", httpMethod = "GET", response = WaitingDealCountResp.class,  notes = "取消待审核订单数量")
    @RequestMapping(value = "/getunauditordercount", method = RequestMethod.GET)
    public Object getUnauditOrderCount() {
        Integer count = orderInfoBiz.getUnauditOrderCount();
        WaitingDealCountResp waitingDealCountResp = new WaitingDealCountResp();
        waitingDealCountResp.setCount(count);
        return ResponseFactory.buildResponse(waitingDealCountResp);
    }

}
