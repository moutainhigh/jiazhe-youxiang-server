package com.jiazhe.youxiang.server.controller.point;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeRecordAdapter;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.biz.point.PointExchangeRecordBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.point.exchangerecord.PointExchangeRecordPageReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;
import com.jiazhe.youxiang.server.vo.resp.point.exchangerecord.PointExchangeRecordResp;
import io.swagger.annotations.ApiOperation;
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
 * @description：积分兑换记录
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/pointexchangerecord")
public class APIPointExchangeRecordController extends BaseController {

    @Autowired
    private PointExchangeRecordBiz pointExchangeRecordBiz;
    @Autowired
    private OrderInfoBiz orderInfoBiz;

    @ApiOperation(value = "【后台、暂时无用】分页查询积分卡兑换记录", httpMethod = "GET", response = PointExchangeRecordResp.class, responseContainer = "List", notes = "【组合条件】分页查询积分卡兑换记录")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "分页查询积分卡兑换记录", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute PointExchangeRecordPageReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        Date beginDate = req.getBeginDate() == CommonConstant.NULL_TIME ? null : new Date(req.getBeginDate());
        Date endDate = req.getEndDate() == CommonConstant.NULL_TIME ? null : new Date(req.getEndDate());
        List<PointExchangeRecordDTO> pointExchangeCodeBatchDTOList = pointExchangeRecordBiz.getList(beginDate, endDate, req.getCode(), req.getKeyt(), paging);
        List<PointExchangeRecordResp> pointExchangeCodeBatchRespList = pointExchangeCodeBatchDTOList.stream().map(PointExchangeRecordAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(pointExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "【后台、暂时无用】通过积分卡id，查找兑换记录", httpMethod = "GET", response = PointExchangeRecordResp.class, notes = "通过积分卡id，查找兑换记录")
    @RequestMapping(value = "/getbyrcid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "通过积分卡id，查找兑换记录", level = LogLevelEnum.LEVEL_1)
    public Object getByPointId(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        PointExchangeRecordDTO pointExchangeCodeBatchDTO = pointExchangeRecordBiz.getByPointId(req.getId());
        PointExchangeRecordResp pointExchangeCodeBatchResp = PointExchangeRecordAdapter.dto2Resp(pointExchangeCodeBatchDTO);
        return ResponseFactory.buildResponse(pointExchangeCodeBatchResp);
    }

    @ApiOperation(value = "【后台、暂时无用】通过积分卡id，查找该积分卡消费记录", httpMethod = "GET", response = OrderInfoResp.class, notes = "通过积分卡id，查找该积分卡消费记录")
    @RequestMapping(value = "/getorderbyrcid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "通过积分卡id，查找该积分卡消费记录", level = LogLevelEnum.LEVEL_1)
    public Object getOrderByPointId(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        List<OrderInfoDTO> orderInfoDTOList = orderInfoBiz.getOrderByPointId(req.getId());
        List<OrderInfoResp> orderInfoRespList = orderInfoDTOList.stream().map(OrderInfoAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(orderInfoRespList);
    }

}
