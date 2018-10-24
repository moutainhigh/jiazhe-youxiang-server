package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeRecordAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeRecordBiz;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordListDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangerecord.RCExchangeRecordListReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord.RCExchangeRecordListResp;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiOperation(value = "【组合条件】分页查询充值卡兑换记录", httpMethod = "GET", response = RCExchangeRecordListResp.class, responseContainer = "List",notes = "【组合条件】分页查询充值卡兑换记录")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute RCExchangeRecordListReq req) {
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum()-1)*req.getPageSize());
        paging.setLimit(req.getPageSize());
        List<RCExchangeRecordListDTO> rcExchangeCodeBatchListDTOS = rcExchangeRecordBiz.getList(req.getBeginDate(),req.getEndDate(),req.getCode(),req.getKeyt(),paging);
        List<RCExchangeRecordListResp> rcExchangeCodeBatchListResps = rcExchangeCodeBatchListDTOS.stream().map(RCExchangeRecordAdapter::DTOList2RespList).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchListResps, paging);
    }


}
