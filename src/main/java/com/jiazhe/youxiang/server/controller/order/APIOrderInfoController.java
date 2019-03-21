package com.jiazhe.youxiang.server.controller.order;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.PlaceOrderDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.AppendOrderReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.CustomerOrderInfoPageReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.CustomerPayReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.CustomerPlaceOrderReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.OrderCancelUnpassReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.OrderInfoPageReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.UserPlaceOrderReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.UserReservationOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.NeedPayResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.WaitingDealCountResp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
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

    @RequiresPermissions(value = {PermissionConstant.ORDER_MANAGEMENT, PermissionConstant.ORDER_SEARCH, PermissionConstant.CUSTOMER_ORDER_DETAIL}, logical = Logical.OR)
    @ApiOperation(value = "【后台】分页查询订单信息", httpMethod = "GET", response = OrderInfoResp.class, responseContainer = "List", notes = "【后台】分页查询订单信息")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "分页查询订单信息", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute OrderInfoPageReq req) {
        //如果包含0，说明查全部订单，否则按需查询
        if (Arrays.binarySearch(req.getStatus().split(","), "0") > -1) {
            req.setStatus(null);
        }
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        Date orderStartTime = req.getOrderStartTime() == CommonConstant.NULL_TIME ? null : new Date(req.getOrderStartTime());
        Date orderEndTime = req.getOrderEndTime() == CommonConstant.NULL_TIME ? null : new Date(req.getOrderEndTime());
        List<OrderInfoDTO> orderInfoDTOList = orderInfoBiz.getList(req.getStatus(), req.getOrderCode(), req.getMobile(), req.getCustomerMobile(), orderStartTime, orderEndTime, req.getWorkerMobile(), paging);
        List<OrderInfoResp> orderInfoRespList = orderInfoDTOList.stream().map(OrderInfoAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(orderInfoRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】分页查询订单信息", httpMethod = "GET", response = OrderInfoResp.class, responseContainer = "List", notes = "【后台】分页查询订单信息")
    @RequestMapping(value = "/customerlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "分页查询订单信息", level = LogLevelEnum.LEVEL_1)
    public Object customerListPage(@ModelAttribute CustomerOrderInfoPageReq req) {
        //如果包含0，说明查全部订单，否则按需查询
        if (Arrays.binarySearch(req.getStatus().split(","), String.valueOf(CommonConstant.ORDER_ALL)) > -1) {
            req.setStatus(null);
        }
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<OrderInfoDTO> orderInfoDTOList = orderInfoBiz.customerGetList(req.getCustomerId(), req.getStatus(), paging);
        List<OrderInfoResp> orderInfoRespList = orderInfoDTOList.stream().map(OrderInfoAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(orderInfoRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】客户取消订单", httpMethod = "POST", notes = "【APP端】客户取消订单")
    @RequestMapping(value = "/customercancelorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "客户取消订单", level = LogLevelEnum.LEVEL_2)
    public Object customerCancelOrder(@ModelAttribute IdReq req) {
        orderInfoBiz.customerCancelOrder(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】计算客户需要在线支付的金额", httpMethod = "GET", response = NeedPayResp.class, notes = "计算客户需要在线支付的金额")
    @RequestMapping(value = "/customerneedpaycash", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "计算客户需要在线支付的金额", level = LogLevelEnum.LEVEL_1)
    public Object customerNeedPayCash(@ModelAttribute IdReq req) {
        BigDecimal needPayCash = orderInfoBiz.customerNeedPayCash(req.getId());
        NeedPayResp needPayResp = new NeedPayResp();
        needPayResp.setPayCash(needPayCash);
        return ResponseFactory.buildResponse(needPayResp);
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】支付前检查", httpMethod = "GET", notes = "支付前检查")
    @RequestMapping(value = "/prepaymentcheck", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "支付前检查", level = LogLevelEnum.LEVEL_1)
    public Object prePaymentCheck(@ModelAttribute IdReq req) {
        orderInfoBiz.prePaymentCheck(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】客户支付", httpMethod = "POST", response = NeedPayResp.class, notes = "客户支付")
    @RequestMapping(value = "/customerpay", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "客户支付", level = LogLevelEnum.LEVEL_2)
    public Object customerPay(@ModelAttribute CustomerPayReq req) {
        BigDecimal needPayCash = orderInfoBiz.customerPay(req.getOrderId(), req.getPayCash(), req.getSerialNumber());
        NeedPayResp needPayResp = new NeedPayResp();
        needPayResp.setPayCash(needPayCash);
        return ResponseFactory.buildResponse(needPayResp);
    }

    @RequiresPermissions(PermissionConstant.ORDER_CHECK)
    @ApiOperation(value = "审核通过", httpMethod = "POST", notes = "审核通过")
    @RequestMapping(value = "/ordercancelpass", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "审核通过", level = LogLevelEnum.LEVEL_2)
    public Object orderCancelPass(@ModelAttribute IdReq req) {
        orderInfoBiz.orderCancelPass(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.ORDER_CHECK)
    @ApiOperation(value = "审核不通过", httpMethod = "POST", notes = "审核不通过")
    @RequestMapping(value = "/ordercancelunpass", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "审核不通过", level = LogLevelEnum.LEVEL_2)
    public Object orderCancelUnpass(@ModelAttribute OrderCancelUnpassReq req) {
        orderInfoBiz.orderCancelUnpass(req.getOrderId(), req.getAuditReason());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.ORDER_CANCEL)
    @ApiOperation(value = "员工取消订单", httpMethod = "POST", notes = "员工取消订单")
    @RequestMapping(value = "/usercancelorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "员工取消订单", level = LogLevelEnum.LEVEL_2)
    public Object userCancelOrder(@ModelAttribute IdReq req) {
        orderInfoBiz.userCancelOrder(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.ORDER_COMPLETE)
    @ApiOperation(value = "员工完成订单", httpMethod = "POST", notes = "员工完成订单")
    @RequestMapping(value = "/usercompleteorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "员工完成订单", level = LogLevelEnum.LEVEL_2)
    public Object userCompleteOrder(@ModelAttribute IdReq req) {
        orderInfoBiz.userCompleteOrder(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_USER_PLACE_ORDER)
    @ApiOperation(value = "【后端】下单", httpMethod = "POST", response = NeedPayResp.class, notes = "【后端】下单")
    @RequestMapping(value = "/userplaceorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "下单", level = LogLevelEnum.LEVEL_2)
    public Object userPlaceOrder(@ModelAttribute UserPlaceOrderReq req)  {
        PlaceOrderDTO placeOrderDTO = OrderInfoAdapter.ReqUserPlaceOrder2DTOPlaceOrder(req);
        placeOrderDTO.setType(CommonConstant.USER_PLACE_ORDER);
        placeOrderDTO.setServiceTime(new Date(req.getRealServiceTime()));
        NeedPayResp needPayResp = orderInfoBiz.userPlaceOrder(placeOrderDTO);
        return ResponseFactory.buildResponse(needPayResp);
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】下单", httpMethod = "POST", response = NeedPayResp.class, notes = "【APP端】下单")
    @RequestMapping(value = "/customerplaceorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "下单", level = LogLevelEnum.LEVEL_2)
    public Object customerPlaceOrder(@ModelAttribute CustomerPlaceOrderReq req)  {
        if((!req.getPointIds().isEmpty())&&(!req.getRechargeCardIds().isEmpty())){
            throw new OrderException(OrderCodeEnum.POINT_RECHARGE_CARD_CONCURRENT_PAY);
        }
        PlaceOrderDTO placeOrderDTO = OrderInfoAdapter.ReqCustomerPlaceOrder2DTOPlaceOrder(req);
        placeOrderDTO.setWorkerMobile("");
        placeOrderDTO.setWorkerName("");
        placeOrderDTO.setCost(BigDecimal.ZERO);
        placeOrderDTO.setType(CommonConstant.CUSTOMER_PLACE_ORDER);
        placeOrderDTO.setRealServiceTime(new Date(req.getServiceTime()));
        placeOrderDTO.setComments("");
        NeedPayResp needPayResp = orderInfoBiz.customerPlaceOrder(placeOrderDTO);
        return ResponseFactory.buildResponse(needPayResp);
    }

    @RequiresPermissions(PermissionConstant.ORDER_RESERVATION)
    @ApiOperation(value = "员工预约服务、派单", httpMethod = "POST", notes = "员工预约服务、派单")
    @RequestMapping(value = "/userreservationorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "员工预约服务、派单", level = LogLevelEnum.LEVEL_2)
    public Object userReservationOrder(@ModelAttribute UserReservationOrderReq req) {
        if (req.getRealServiceTime() == CommonConstant.NULL_TIME) {
            throw new OrderException(OrderCodeEnum.REAL_SERVICE_TIME_IS_NULL);
        }
        CommonValidator.validateNull(req.getWorkerName(), new OrderException(OrderCodeEnum.WORKER_NAME_IS_NAME));
        CommonValidator.validateNull(req.getWorkerMobile(), new OrderException(OrderCodeEnum.WORKER_MOBILE_IS_NAME));
        CommonValidator.validateNull(req.getCost(), new OrderException(OrderCodeEnum.ORDER_COST_IS_NULL));
        orderInfoBiz.userReservationOrder(OrderInfoAdapter.ReqUserReservationOrder2DTOUserReservationOrder(req));
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.ORDER_EDIT)
    @ApiOperation(value = "员工修改预约信息", httpMethod = "POST", notes = "员工修改预约信息")
    @RequestMapping(value = "/userchangereservationinfo", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "员工修改预约信息", level = LogLevelEnum.LEVEL_2)
    public Object userChangeReservationInfo(@ModelAttribute UserReservationOrderReq req) {
        if (req.getRealServiceTime() == CommonConstant.NULL_TIME) {
            throw new OrderException(OrderCodeEnum.REAL_SERVICE_TIME_IS_NULL);
        }
        CommonValidator.validateNull(req.getWorkerName(), new OrderException(OrderCodeEnum.WORKER_NAME_IS_NAME));
        CommonValidator.validateNull(req.getWorkerMobile(), new OrderException(OrderCodeEnum.WORKER_MOBILE_IS_NAME));
        CommonValidator.validateNull(req.getCost(), new OrderException(OrderCodeEnum.ORDER_COST_IS_NULL));
        orderInfoBiz.userChangeReservationInfo(OrderInfoAdapter.ReqUserReservationOrder2DTOUserReservationOrder(req));
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.ORDER_APPEND)
    @ApiOperation(value = "员工追加订单", httpMethod = "POST", notes = "员工追加订单")
    @RequestMapping(value = "/appendorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "员工追加订单", level = LogLevelEnum.LEVEL_2)
    public Object appendOrder(@ModelAttribute AppendOrderReq req) {
        orderInfoBiz.appendOrder(OrderInfoAdapter.ReqAppendOrder2DTOAppendOrder(req));
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "获取订单信息", httpMethod = "GET", response = OrderInfoResp.class, notes = "客户支付")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "获取订单信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        OrderInfoDTO orderInfoDTO = orderInfoBiz.getById(req.getId());
        OrderInfoResp orderInfoResp = OrderInfoAdapter.DTO2Resp(orderInfoDTO);
        return ResponseFactory.buildResponse(orderInfoResp);
    }

    @RequiresPermissions(PermissionConstant.ORDER_RESERVATION)
    @ApiOperation(value = "待预约订单数量", httpMethod = "GET", response = WaitingDealCountResp.class, notes = "待预约订单数量")
    @RequestMapping(value = "/getunsentordercount", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "待预约订单数量", level = LogLevelEnum.LEVEL_1)
    public Object getUnsentOrderCount() {
        Integer count = orderInfoBiz.getUnsentOrderCount();
        WaitingDealCountResp waitingDealCountResp = new WaitingDealCountResp();
        waitingDealCountResp.setCount(count);
        return ResponseFactory.buildResponse(waitingDealCountResp);
    }

    @RequiresPermissions(PermissionConstant.ORDER_CHECK)
    @ApiOperation(value = "取消待审核订单数量", httpMethod = "GET", response = WaitingDealCountResp.class, notes = "取消待审核订单数量")
    @RequestMapping(value = "/getunauditordercount", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "取消待审核订单数量", level = LogLevelEnum.LEVEL_1)
    public Object getUnauditOrderCount() {
        Integer count = orderInfoBiz.getUnauditOrderCount();
        WaitingDealCountResp waitingDealCountResp = new WaitingDealCountResp();
        waitingDealCountResp.setCount(count);
        return ResponseFactory.buildResponse(waitingDealCountResp);
    }

}
