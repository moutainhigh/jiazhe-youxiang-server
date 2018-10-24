package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeRecordAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeRecordBiz;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangerecord.RCExchangeRecordPageReq;
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
public class APIRCExchangeRecordController extends BaseController{

    @Autowired
    private RCExchangeRecordBiz rcExchangeRecordBiz;

    @ApiOperation(value = "【组合条件】分页查询充值卡兑换记录", httpMethod = "GET", response = RCExchangeRecordResp.class, responseContainer = "List",notes = "【组合条件】分页查询充值卡兑换记录")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute RCExchangeRecordPageReq req) {
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum()-1)*req.getPageSize());
        paging.setLimit(req.getPageSize());
        List<RCExchangeRecordDTO> rcExchangeCodeBatchDTOList = rcExchangeRecordBiz.getList(req.getBeginDate(),req.getEndDate(),req.getCode(),req.getKeyt(),paging);
        List<RCExchangeRecordResp> rcExchangeCodeBatchRespList = rcExchangeCodeBatchDTOList.stream().map(RCExchangeRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "通过充值卡id，查找兑换记录", httpMethod = "GET", response = RCExchangeRecordResp.class,notes = "通过充值卡id，查找兑换记录")
    @RequestMapping(value = "/getbyrcid", method = RequestMethod.GET)
    public Object getByRCId(@ModelAttribute IdReq req) {
        RCExchangeRecordDTO rcExchangeCodeBatchDTO  = rcExchangeRecordBiz.getByRCId(req.getId());
        RCExchangeRecordResp rcExchangeCodeBatchResp =RCExchangeRecordAdapter.DTO2Resp(rcExchangeCodeBatchDTO);
        return ResponseFactory.buildResponse(rcExchangeCodeBatchResp);
    }


}
