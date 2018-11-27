package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeRecordAdapter;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeRecordBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangerecord.RCExchangeRecordPageReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord.RCExchangeRecordResp;
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
 * @description：充值卡兑换记录【rc是rechargecard缩写】
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/rcexchangerecord")
public class APIRCExchangeRecordController extends BaseController {

    @Autowired
    private RCExchangeRecordBiz rcExchangeRecordBiz;
    @Autowired
    private OrderInfoBiz orderInfoBiz;

    @ApiOperation(value = "【后台，暂时无用】分页查询充值卡兑换记录", httpMethod = "GET", response = RCExchangeRecordResp.class, responseContainer = "List", notes = "【组合条件】分页查询充值卡兑换记录")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "分页查询充值卡兑换记录", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute RCExchangeRecordPageReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCExchangeRecordDTO> rcExchangeCodeBatchDTOList = rcExchangeRecordBiz.getList(req.getBeginDate(), req.getEndDate(), req.getCode(), req.getKeyt(), paging);
        List<RCExchangeRecordResp> rcExchangeCodeBatchRespList = rcExchangeCodeBatchDTOList.stream().map(RCExchangeRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "【后台，暂时无用】通过充值卡id，查找兑换记录", httpMethod = "GET", response = RCExchangeRecordResp.class, notes = "通过充值卡id，查找兑换记录")
    @RequestMapping(value = "/getbyrcid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "通过充值卡id，查找兑换记录", level = LogLevelEnum.LEVEL_1)
    public Object getByRCId(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        RCExchangeRecordDTO rcExchangeCodeBatchDTO = rcExchangeRecordBiz.getByRCId(req.getId());
        RCExchangeRecordResp rcExchangeCodeBatchResp = RCExchangeRecordAdapter.DTO2Resp(rcExchangeCodeBatchDTO);
        return ResponseFactory.buildResponse(rcExchangeCodeBatchResp);
    }

    @ApiOperation(value = "【后台，暂时无用】通过充值卡id，查找该充值卡消费记录", httpMethod = "GET", response = OrderInfoResp.class, notes = "通过充值卡id，查找该充值卡消费记录")
    @RequestMapping(value = "/getorderbyrcid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.RECHARGE, operate = "通过充值卡id，查找该充值卡消费记录", level = LogLevelEnum.LEVEL_1)
    public Object getOrderByRCId(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        List<OrderInfoDTO> orderInfoDTOList = orderInfoBiz.getOrderByRCId(req.getId());
        List<OrderInfoResp> orderInfoRespList = orderInfoDTOList.stream().map(OrderInfoAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(orderInfoRespList);
    }
}
